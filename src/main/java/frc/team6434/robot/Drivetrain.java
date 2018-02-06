package frc.team6434.robot;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain implements Subsystem {

    ADXRS450_Gyro gyro;
    VictorSP leftA, leftB, rightA, rightB;
    Encoder leftEncoder, rightEncoder;

    public void init()
    {
        leftA = new VictorSP(0);
        leftB = new VictorSP(1);
        rightA = new VictorSP(2);
        rightB = new VictorSP(3);
        leftEncoder = new Encoder(0,1);
        leftEncoder.setDistancePerPulse(1);
        rightEncoder = new Encoder(2,3);
        rightEncoder.setDistancePerPulse(1);
        gyro = new ADXRS450_Gyro();
        gyro.calibrate();
    }

    public void drive(double left, double right)
    {
        leftA.set(left);
        leftB.set(left);
        rightA.set(-right);
        rightB.set(-right);
    }
    public void arcadeDrive(double x, double y)
    {
        double left = y+x;
        double right = y-x;
        if (left > 1)
        {
            left = 1;
        }
        if (right > 1)
        {
            right = 1;
        }
        drive(left,right);

    }

    public double read_gyro()
    {
        return gyro.getAngle()%360;
    }

    public void turnToAngle(double angle)
    {

    }
    public void resetEncoders()
    {
        leftEncoder.reset();
        rightEncoder.reset();
    }

    public void driveDistanceMilli(double milli)
    {
        driveDistance(milli/2.4);
    }

    // Drives a set distance, assuming the encoders have been reset.
    // Reset the encoders once the robot has reached a target to drive a set distance again
    // Right now only uses left encoder, and doesn't use the gyro to drive straight
    public void driveDistance(double distance)
    {
        if (leftEncoder.get() > distance)
        {
            drive(-0.05,-0.05);
        }
        else
        {
            if (leftEncoder.get() < 0.7*distance)
            {
                drive(0.8,0.8);
            }
            else {
                drive(0.3, 0.3);
            }
        }
    }
    // Turn on the spot to a set angle
    public void turnToAngle(double angle, double speed)
    {
        // Testing very basic proportional speed
        //speed = Math.abs((read_gyro() - angle)/360);

        if ((read_gyro() - angle) > 0.5)
        {
            // Turn left
            drive(-speed,speed);
        }
        else if((read_gyro() - angle < -0.5))
        {
            // Turn right
            drive(speed,-speed);
        }
        else{
            drive(0,0);
        }
        SmartDashboard.putNumber("Speed", speed);
    }
    // Turns to a set angle then drives forward
    public void driveAngle(double angle, double speed)
    {
        if (Math.abs(read_gyro() - angle) > 5) {
            turnToAngle(angle, speed);
        }
        else{
            drive(-speed,-speed);
        }
    }

    public void showDashboard()
    {
        SmartDashboard.putNumber("Gyro Angle", read_gyro());
        SmartDashboard.putNumber("Left Power", leftA.get());
        SmartDashboard.putNumber("Right Power", rightB.get());
        SmartDashboard.putNumber("Left Encoder", leftEncoder.get());
        SmartDashboard.putNumber("Right Encoder", rightEncoder.get());

    }

}
