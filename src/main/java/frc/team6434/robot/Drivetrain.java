package frc.team6434.robot;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class Drivetrain {

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
        rightEncoder = new Encoder(2,3);
        //leftEncoder.setDistancePerPulse(1);
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

    public double read_gyro()
    {
        return gyro.getAngle();
    }



}
