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
        return gyro.getAngle();
    }

    public void turnToAngle(double angle)
    {

    }
    public void resetEncoders()
    {
        leftEncoder.reset();
        rightEncoder.reset();
    }
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
    public void driveAngle(double angle, double speed)
    {
        double currentAngle = read_gyro()%360;
        if ((currentAngle - angle) > 5)
        {
            // Turn left
            drive(-speed,speed);
        }
        else if((currentAngle - angle < -5))
        {
            // Turn right
            drive(speed,-speed);
        }
        else{
            drive(0,0);
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
