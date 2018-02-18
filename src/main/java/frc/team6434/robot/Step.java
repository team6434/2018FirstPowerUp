package frc.team6434.robot;

import edu.wpi.first.wpilibj.Timer;

public abstract class Step
{
    abstract void begin(Drivetrain drivetrain, Lift lift, Intake intake);
    abstract boolean progress(Drivetrain drivetrain, Lift lift, Intake intake);
}

//step for driving straight
class Straight extends Step
{
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
        drivetrain.resetEncoders();
        initialAngle = drivetrain.readGyro();
    }

    boolean progress(Drivetrain drivetrain, Lift lift, Intake intake)
    {
        if (drivetrain.getEncoderAvg()  > distance)
        {
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
    final double tolerance = 10;

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
            return true;
        }
        drivetrain.turnToAngle(targetAngle, speed);
        return false;
    }
}


//step for raising the lift
class Raise extends Step
{
//    boolean limitSwitch = lift.limitSwitch;

    Raise(){}

    void begin(Drivetrain drivetrain, Lift lift, Intake intake) { }

    boolean progress(Drivetrain drivetrain, Lift lift, Intake intake)
    {
//        if(limitSwitch.get = 1)
//        {
//            return true;
//        }
        lift.moveUp();
        return false;
    }
}


//step for ejecting the cube (2 secs)
class Eject extends Step
{
    final double speed;
    Timer ejectTimer = new Timer();

    Eject(double speed)
    {
        this.speed = speed;
    }

    void begin(Drivetrain drivetrain, Lift lift, Intake intake)
    {
        ejectTimer.start();
    }

    boolean progress(Drivetrain drivetrain, Lift lift, Intake intake)
    {
        if(ejectTimer.get() > 2.0)
        {
            return true;
        }
        intake.ejectCube(speed);
        return false;
    }
}