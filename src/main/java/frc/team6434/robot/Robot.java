package frc.team6434.robot;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CameraServer;
import javafx.scene.Camera;

public class Robot extends IterativeRobot {

    Command[] currentStrategy;
    int currentStep;

    boolean flag = true;
    Assistive_Climb assistive_climb;
    Climb climber;
    Joystick joystick;
    Drivetrain drivetrain;
    CameraServer cameraServer;
    Intake intake;
    Lift lift;
    Strategy strategy;
    @Override
    public void robotInit() {
        assistive_climb = new Assistive_Climb();
        climber = new Climb();
        drivetrain = new Drivetrain();
        joystick = new Joystick(0);
        drivetrain.init();
        intake = new Intake();
        lift = new Lift();
        strategy = new Strategy();
        lift.init();
        intake.init();
    }

    @Override
    public void disabledInit() { }

    @Override
    public void autonomousInit()
    {
        drivetrain.resetGyro();
        drivetrain.resetEncoders();

        // We should receive information from the game: switch color
        // We should receive information from the dashboard? (starting position)

        // choose a strategy
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


       //  strategy.runStrategyLeftRed();




//        lift.showDashboard();
//        intake.showDashboard();
//        drivetrain.showDashboard();
//
//        drivetrain.turnToAngle(-500,0.5);
//
////        drivetrain.gyroStraight(10,0.3);
//
////        drivetrain.driveDistanceMilli(2000);
////        drivetrain.driveDistanceMilli(2000);
//
//
//
////        drivetrain.driveStraight(0.3,10000);
//
//
////        for(int i = 0; i < 4; i++) {
////            while (drivetrain.getEncoderLeft() < 1000 && flag == true) {
////                drivetrain.driveDistanceMilli(1100);
////            }
////            while (drivetrain.getEncoderLeft() > 1000 || flag == false) {
////                drivetrain.turnToAngle((i*90) + 90, 0.3);
////                flag = false;
////                if (drivetrain.read_gyro() > ((i*90) + 89) && drivetrain.read_gyro() < ((i*90) + 91)) {
////                    flag = true;
////                }
////            }
////            drivetrain.resetEncoders();
////        }
//
//
//
////        if(drivetrain.getEncoderAvg() < 2000) {
////            drivetrain.driveDistanceMilli(10000, 0.3);
////            drivetrain.showDashboard();
////        }
////        else{
////            drivetrain.turnToAngle(90, 0.3);
////        }
//
    }


    @Override
    public void teleopInit()
    {

    }

    @Override
    public void testInit() { }


    @Override
    public void disabledPeriodic() { }

    @Override
    public void teleopPeriodic() {

        lift.showDashboard();
        intake.showDashboard();
        drivetrain.showDashboard();
        SmartDashboard.putNumber("Throttle", joystick.getThrottle());


        drivetrain.arcadeDrive(joystick.getX(), joystick.getY());



        double speed = (joystick.getThrottle()+1)/2; //Throttle between 0 and 1


        if (joystick.getRawButton(9))
        {
            intake.getCube(speed/2);
        }
        else if (joystick.getRawButton(10))
        {
            intake.ejectCube(speed);
        }
        else
        {
            intake.keepCube(0.2);
        }


        if (joystick.getRawButton(11)) //Lift up
        {
            lift.moveLift(speed);
        }
        else if (joystick.getRawButton(12)) //Lift down
        {
            lift.moveLift(-speed);
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