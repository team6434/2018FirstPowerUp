package frc.team6434.robot;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CameraServer;
import javafx.scene.Camera;

public class Robot extends IterativeRobot {

    boolean flag = true;
    Assistive_Climb assistive_climb;
    Climb climber;
    Joystick joystick;
    Drivetrain drivetrain;
    CameraServer cameraServer;
    @Override
    public void robotInit() {
        assistive_climb = new Assistive_Climb();
        climber = new Climb();
        drivetrain = new Drivetrain();
        joystick = new Joystick(0);
        drivetrain.init();
        /*
        Untested camera code
        cameraServer = CameraServer.getInstance();
        cameraServer.startAutomaticCapture();*/

    }

    @Override
    public void disabledInit() { }

    @Override
    public void autonomousInit()
    {
        drivetrain.resetEncoders();
    }

    @Override
    public void teleopInit() {

    }

    @Override
    public void testInit() { }


    @Override
    public void disabledPeriodic() { }

    @Override
    public void autonomousPeriodic()
    {
        for(int i = 0; i < 4; i++) {
            while (drivetrain.getEncoderLeft() < 1000 && flag == true) {
                drivetrain.driveDistanceMilli(1100);
            }
            while (drivetrain.getEncoderLeft() > 1000 || flag == false) {
                drivetrain.turnToAngle((i*90) + 90, 0.3);
                flag = false;
                if (drivetrain.read_gyro() > ((i*90) + 89) && drivetrain.read_gyro() < ((i*90) + 91)) {
                    flag = true;
                }
            }
            drivetrain.resetEncoders();
        }



//        if(drivetrain.getEncoderLeft() < 2000) {
//            drivetrain.driveDistanceMilli(2500);
//            drivetrain.turnToAngle(300,0.3);
//            drivetrain.showDashboard();
//        }
//        else{
//            drivetrain.turnToAngle(90, 0.3);
//        }

    }

    @Override
    public void teleopPeriodic() {
        /*if (joystick.getTrigger()) {
            climber.climb();
        }
        */
        if (joystick.getRawButton(12)) {
            drivetrain.leftEncoder.reset();
            drivetrain.rightEncoder.reset();
        }
        if (joystick.getPOV() != -1)
        {
            //drivetrain.turnToAngle(joystick.getPOV(), 0.5);
            drivetrain.driveAngle(joystick.getPOV(), 0.6);
        }
        else
        {
            drivetrain.arcadeDrive(joystick.getX(), joystick.getY());

        }
        drivetrain.showDashboard();
        SmartDashboard.putNumber("POV", joystick.getPOV());
    }

    @Override
    public void testPeriodic() {
    }
}