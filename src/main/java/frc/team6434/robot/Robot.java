package frc.team6434.robot;
import edu.wpi.first.wpilibj.IterativeRobot;
import frc.team6434.robot.Assistive_Climb;
import frc.team6434.robot.Climb;
import frc.team6434.robot.Drivetrain;
import frc.team6434.robot.Intake;
import edu.wpi.first.wpilibj.Joystick;
import frc.team6434.robot.Lift;

public class Robot extends IterativeRobot {

    Assistive_Climb assistive_climb;
    Climb climber;
    Joystick joystick;
    @Override
    public void robotInit() {
        assistive_climb = new Assistive_Climb();
        climber = new Climb();
        joystick = new Joystick(0);
    }

    @Override
    public void disabledInit() { }

    @Override
    public void autonomousInit() { }

    @Override
    public void teleopInit() {
    }

    @Override
    public void testInit() { }


    @Override
    public void disabledPeriodic() { }
    
    @Override
    public void autonomousPeriodic() {}

    @Override
    public void teleopPeriodic() {
        if (joystick.getTrigger()) {
            climber.climb();
        }
    }

    @Override
    public void testPeriodic() {
    }
}