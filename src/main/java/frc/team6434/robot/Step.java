package frc.team6434.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public abstract class Step
{
    abstract void begin(Drivetrain drivetrain, Lift lift, Intake intake);
    abstract boolean progress(Drivetrain drivetrain, Lift lift, Intake intake);
}


//step for driving straight
class Straight extends Step
{
    Timer straightTimer = new Timer();

    double initialAngle;
    final double distance;
    final double speed;

    Straight(double distance, double speed)
    {
        this.distance = distance;
        this.speed = speed;
    }

    void begin(Drivetrain drivetrain, Lift lift, Intake intake)
    {
        straightTimer.start();
        initialAngle = drivetrain.readGyro();
    }

    boolean progress(Drivetrain drivetrain, Lift lift, Intake intake)
    {
        if (straightTimer.get()  > distance)
        {
            drivetrain.drive(0,0);
            return true;
        }
        drivetrain.driveStraight(speed, initialAngle, drivetrain.getEncoderAvg(), distance);
        return false;
    }
}


//step for turning
class Turn extends Step
{
    final double targetAngle;
    final double speed;
    final double tolerance = 3;

    Turn(double targetAngle, double speed)
    {
        this.targetAngle = targetAngle;
        this.speed = speed;
    }

    void begin(Drivetrain drivetrain, Lift lift, Intake intake) { }

    boolean progress(Drivetrain drivetrain, Lift lift, Intake intake)
    {
        double error = drivetrain.calculateError(targetAngle);

        if ((error  >  - tolerance) && (error  <  tolerance))
        {
            intake.intakeStop();
            drivetrain.drive(0,0);
            return true;
        }
        intake.getCube();
        drivetrain.turnToAngle(targetAngle, speed);
        return false;
    }
}



//step for ejecting the cube (2 secs)
class Eject extends Step
{
    boolean flag = false;

    Eject() {}

    void begin(Drivetrain drivetrain, Lift lift, Intake intake)
    { }

    boolean progress(Drivetrain drivetrain, Lift lift, Intake intake)
    {
        if(flag == true)
        {
            intake.intakeStop();
            return true;
        }
        intake.ejectCubeFast();
        flag = true;
        return false;
    }

}