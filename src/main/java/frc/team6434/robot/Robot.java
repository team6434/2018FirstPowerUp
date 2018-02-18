package frc.team6434.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CameraServer;
import javafx.scene.Camera;


public class Robot extends IterativeRobot {

    Step[] currentStrategy;

    int currentStep;
    boolean holdCube = false;

    Assistive_Climb assistive_climb;
    XboxController controller;
    Drivetrain drivetrain;
    Intake intake;
    Lift lift;
    Strategy strategy;

    @Override
    public void robotInit()
    {
        assistive_climb = new Assistive_Climb();
        drivetrain = new Drivetrain();
        controller = new XboxController(0);
        drivetrain.init();
        intake = new Intake();
        lift = new Lift();
        strategy = new Strategy();
        lift.init();
        intake.init();
        strategy.init();
        CameraServer.getInstance().startAutomaticCapture();
    }

    @Override
    public void disabledInit() { }

    @Override
    public void autonomousInit()
    {
        drivetrain.resetGyro();
        drivetrain.resetEncoders();

        currentStrategy = strategy.pickStrategy();
        currentStep = 0;
        currentStrategy[currentStep].begin(drivetrain, intake);
    }

    @Override
    public void autonomousPeriodic()
    {
        drivetrain.showDashboard();
        intake.showDashboard();
        lift.showDashboard();

        if (currentStep < currentStrategy.length) {
            if (currentStrategy[currentStep].progress(drivetrain, intake)) {
                currentStep = currentStep + 1;
                if (currentStep < currentStrategy.length)
                {
                    currentStrategy[currentStep].begin(drivetrain, intake);
                }
                else
                {
                    drivetrain.drive(0, 0); // Stop
                }
            }
        }
    }


    @Override
    public void teleopInit() { }

    @Override
    public void testInit() { }

    @Override
    public void disabledPeriodic() { }

    @Override
    public void teleopPeriodic()
    {
        Hand kLeft = Hand.kLeft;
        Hand kRight = Hand.kRight;

        lift.showDashboard();
        intake.showDashboard();
        drivetrain.showDashboard();


        //Drivetrain
        drivetrain.arcadeDrive(controller.getX(kLeft), controller.getY(kLeft));


        //Intake
        if (controller.getBumper(kLeft))
        {
            intake.getCube(0.5);
            holdCube = true;
        }
        else if (controller.getBumper(kRight))
        {
            intake.ejectCube(1);
            holdCube = false;
        }
        else if (holdCube)
        {
            intake.keepCube(0.2);
        }
        else
        {
            intake.keepCube(0);
        }

        //Lift
        if (controller.getAButton()) //Lift up
        {
            lift.moveLift(1);//speed);
        }
        else if (controller.getBButton()) //Lift down
        {
            lift.moveLift(-1);//speed);
        }
        else
        {
            lift.liftStop();
        }


    }

    @Override
    public void testPeriodic() {
    }
}