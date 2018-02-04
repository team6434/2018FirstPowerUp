package frc.team6434.robot;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CameraServer;
import javafx.scene.Camera;

public class Robot extends IterativeRobot {

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
        drivetrain.turnToAngle(180, 0.3);
        drivetrain.showDashboard();
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