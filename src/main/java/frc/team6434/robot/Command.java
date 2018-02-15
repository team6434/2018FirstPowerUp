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

class Turn extends Command{

    final double targetAngle;
    final double speed;
    final double tolerance = 10;

    Turn(double targetAngle, double speed) {
        this.targetAngle = targetAngle;
        this.speed = speed;
    }
    void begin(Drivetrain drivetrain) {

    }
    boolean progress(Drivetrain drivetrain) {
        double error = drivetrain.calculateError(targetAngle);
        if ((error  >  - tolerance) && (error  <  tolerance)) {
            return true;
        }

        drivetrain.turnToAngle(targetAngle, speed);
        return false;
    }
}