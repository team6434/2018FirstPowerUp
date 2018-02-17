package frc.team6434.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CameraServer;


public class Robot extends IterativeRobot {

    Step[] currentStrategy;
    int currentStep;

    boolean holdCube = false;

    Assistive_Climb assistive_climb;
    Joystick joystick;
    Drivetrain drivetrain;
    Intake intake;
    Lift lift;
    Strategy strategy;

    @Override
    public void robotInit()
    {
        assistive_climb = new Assistive_Climb();
        drivetrain = new Drivetrain();
        joystick = new Joystick(0);
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
        currentStrategy[currentStep].begin(drivetrain);
    }

    @Override
    public void autonomousPeriodic()
    {
        drivetrain.showDashboard();
        if (currentStep < currentStrategy.length) {
            if (currentStrategy[currentStep].progress(drivetrain)) {
                currentStep = currentStep + 1;
                if (currentStep < currentStrategy.length)
                {
                    currentStrategy[currentStep].begin(drivetrain);
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
        lift.showDashboard();
        intake.showDashboard();
        drivetrain.showDashboard();
        SmartDashboard.putNumber("Throttle", joystick.getThrottle());

        //Drivetrain
        drivetrain.arcadeDrive(joystick.getX(), joystick.getY());

        double speed = (joystick.getThrottle()+1)/2; //Throttle between 0 and 1
        //Intake
        if (joystick.getRawButton(2))
            {
                intake.getCube(speed);
                holdCube = true;
            }
        else if (joystick.getRawButton(1))
        {
            intake.ejectCube(speed);
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
            if (joystick.getRawButton(11)) //Lift up
            {
                lift.moveLift(0.6);//speed);
            }
            else if (joystick.getRawButton(12)) //Lift down
            {
                lift.moveLift(0.1);//speed);
        }
        else
        {
            lift.liftStop();
        }

        SmartDashboard.putNumber("POV", joystick.getPOV());
    }

    @Override
    public void testPeriodic() {
    }
}