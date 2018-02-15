package frc.team6434.robot;


public abstract class Command
{
    abstract void begin(Drivetrain drivetrain);
    abstract boolean progress(Drivetrain drivetrain);
}

class Straight extends Command{

    double initialAngle;
    final double distance;
    final double speed;

    Straight(double distance, double speed) {
        this.distance = distance;
        this.speed = speed;
    }
    void begin(Drivetrain drivetrain) {
        drivetrain.resetEncoders();
        initialAngle = drivetrain.readGyro();

    }
    boolean progress(Drivetrain drivetrain) {
        if (drivetrain.getEncoderAvg()  > distance) {
            return true;
        }

        drivetrain.driveStraight(speed, initialAngle);
        return false;
    }
}