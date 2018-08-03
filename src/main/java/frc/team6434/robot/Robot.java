package frc.team6434.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj.Servo;

public class Robot extends IterativeRobot {

    Step[] currentStrategy;

    int currentStep;
    boolean holdCube = false;
    boolean fixCube = false;
    final double triggerThreshold = Constants.triggerThreshold;

    Assistive_Climb assistive_climb;
    XboxController controller;
    Drivetrain drivetrain;
    Intake intake;
    Lift lift;
    Strategy strategy;
//    Timer astTimer;
    Servo pin = new Servo(3);

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
//        astTimer = new Timer();
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
        drivetrain.resetEncoders();
        drivetrain.resetGyro();

        currentStrategy = strategy.pickStrategy();
        currentStep = 0;
        currentStrategy[currentStep].begin(drivetrain, lift, intake);
    }

    @Override
    public void autonomousPeriodic()
    {
        strategy.showDashboard();
        drivetrain.showDashboard();
        intake.showDashboard();
        lift.showDashboard();

        if (currentStep < currentStrategy.length) {
            if (currentStrategy[currentStep].progress(drivetrain, lift, intake)) {
                currentStep = currentStep + 1;
                if (currentStep < currentStrategy.length)
                {
                    currentStrategy[currentStep].begin(drivetrain, lift, intake);
                }
            }
        }
    }


    @Override
    public void teleopInit()
    {
//        astTimer.start();
    }

    @Override
    public void testInit() { }

    @Override
    public void disabledPeriodic()
    {
        strategy.showDashboard();
    }

    @Override
    public void teleopPeriodic()
    {

        Hand LEFT = Hand.kLeft;
        Hand RIGHT = Hand.kRight;

        lift.showDashboard();
        intake.showDashboard();
        drivetrain.showDashboard();


        //Drivetrain
        drivetrain.arcadeDrive(controller.getX(LEFT), controller.getY(LEFT));

        //Lift
        if (controller.getBButton())
        {
            lift.moveUp();
        }
        else if (controller.getAButton())
        {
            lift.moveDown();
        }
        else
        {
            lift.liftStop();
        }

        //Intake
        if (controller.getBumper(LEFT))
        {
            fixCube = true;
            intake.fixCube();
        }
        else
        {
            fixCube = false;
            intake.stopFixCube();
            if (controller.getTriggerAxis(LEFT) > triggerThreshold)
            {
                intake.getCube();
                holdCube = true;
            }
            else if (controller.getYButton())
            {
                holdCube = false;
            }
            else if (controller.getTriggerAxis(RIGHT) > triggerThreshold)
            {
                intake.ejectCubeSlow();
                holdCube = false;
            }
            else if (controller.getBumper(RIGHT))
            {
                intake.ejectCubeFast();
                holdCube = false;
            }
            else if (holdCube)
            {
                intake.keepCube();
            }
            else
            {
                intake.intakeStop();
            }
        }


        //Assitive Climb
//        if (controller.getXButton())
//        {
//            assistive_climb.climbUp();
//        }
//        else {
//            assistive_climb.climbStop();
//        }

    }

    @Override
    public void testPeriodic()
    {
        if (controller.getXButton())
        {
            pin.set(0.8);
//            assistive_climb.extend();
        }
        if (controller.getYButton())
        {
            pin.set(0.18);
        }
    }
}