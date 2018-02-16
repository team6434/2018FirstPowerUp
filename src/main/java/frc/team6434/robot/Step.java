package frc.team6434.robot;


public abstract class Step
{
    abstract void begin(Drivetrain drivetrain);
    abstract boolean progress(Drivetrain drivetrain);
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

    void begin(Drivetrain drivetrain)
    {
        drivetrain.resetEncoders();
        initialAngle = drivetrain.readGyro();
    }

    boolean progress(Drivetrain drivetrain)
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

    void begin(Drivetrain drivetrain) { }

    boolean progress(Drivetrain drivetrain)
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