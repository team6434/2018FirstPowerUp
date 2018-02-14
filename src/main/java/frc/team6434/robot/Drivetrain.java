package frc.team6434.robot;

//import java.util.map;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain implements Subsystem {

    //    double diff1 = leftEncoder.get - rightEncoder.get;
//    double diffmap = map(diff1,-1000.0,1000.0,-1.0,1.0);
    boolean flagon = true;
    double optangle = 0.0;

//    double minus = leftEncoder.get() - rightEncoder.get();

    public ADXRS450_Gyro gyro;
    VictorSP left, right;
    Encoder leftEncoder, rightEncoder;
    double lastError;

    public void init() {
        left = new VictorSP(1);
        right = new VictorSP(0);
        leftEncoder = new Encoder(0, 1);
        leftEncoder.setDistancePerPulse(1);
        rightEncoder = new Encoder(2, 3);
        rightEncoder.setDistancePerPulse(1);
        gyro = new ADXRS450_Gyro();
        gyro.calibrate();
    }


    //sets the speeds of all driving motors
    public void drive(double leftSpeed, double rightSpeed) {
        left.set(leftSpeed);
        right.set(-rightSpeed);
    }


    //teleop driving stuff
    public void arcadeDrive(double x, double y) {
        double left = y - x;
        double right = y + x;
        if (left > 1) {
            left = 1;
        }
        if (right > 1) {
            right = 1;
        }
        drive(left/2, right/2);

    }


    //
    public double readGyro() {
        return (gyro.getAngle() % 360 + 360) % 360;
    }


//    public void restGyro(){
//    }

//    public void turnToAngle(double angle)
//    {
//
//    }

    public void resetGyro() {
        // gyro.calibrate();
    }
    //resets both encoders
    public void resetEncoders() {
        leftEncoder.reset();
        rightEncoder.reset();
    }


    //returns average of both encoders (mil)
    public double getEncoderAvg() {
        return (((rightEncoder.get() * 2.4) + (leftEncoder.get() * 2.4)) / 2);
    }


    //Drives a set distance in millimetres (roughly)
    public void driveDistanceMilli(int milli, double speed) {
//        driveDistance(milli/2.4);
        gyroStraight(milli / 2.4, speed);
    }


    // Drives a set distance, assuming the encoders have been reset.
    // Reset the encoders once the robot has reached a target to drive a set distance again
    // Right now only uses left encoder, and doesn't use the gyro to drive straight
    public void driveDistance(double distance) {
        if (leftEncoder.get() > distance) {
            drive(-0.05, -0.05);
        } else {
            if (leftEncoder.get() < 0.7 * distance) {
                drive(0.8, 0.8);
            } else {
                drive(0.3, 0.3);
            }
        }
    }

    //drives straight using gyro
    public void gyroStraight(double distance, double speed)
    {
        //sensitivit settings so you can change all 4 instances of it at once
        final double firstSensitivity = 0.85;
        final double secondSensitivity = 0.5;

        if (flagon) {
            flagon = false;
            optangle = readGyro();
        }
        double avg_dis = (getEncoderAvg());
        while (avg_dis < distance) {
//             if(optangle < readGyro())
//             {
//                 drive(speed*0.85, speed);
//             }
//             if(optangle > readGyro())
//             {
//                 drive(speed, speed*0.85);
//             }
//             else
//             {
//                 drive(speed,speed);
//             }            
            if (optangle - readGyro() < 10 && optangle - readGyro() > 0)
                drive(speed * firstSensitivity, speed);
            if (optangle - readGyro() >= 10)
            drive(speed * secondSensitivity, speed);
            if (optangle - readGyro() > 10 && optangle - readGyro() < 0)
                drive(speed, speed * firstSensitivity);
            if (optangle - readGyro() <= 10)
            drive(speed, speed * secondSensitivity);
            if (optangle - readGyro() == 0)
                drive(speed, speed);
            avg_dis = (getEncoderAvg());
        }
        //stop at end of routine
        drive(0, 0);
    }


    public void turnToAngle(double targetAngle, double speed)
    {

        double error = readGyro() - targetAngle;

        while(error > 180)
        {
            error = error - 360;
        }

        while(error < -180)
        {
            error = error + 360;
        }

        if(error > 0)
        {
            drive(-speed*0.85, speed);
        }
        else
        {
            drive(speed, -speed*0.85);
        }
        this.lastError = error;
    }

    //put dashboard stuff here
    public void showDashboard()
    {
        SmartDashboard.putNumber("Gyro Angle", readGyro());
        SmartDashboard.putNumber("Left Power", left.get());
        SmartDashboard.putNumber("Right Power", right.get());
        SmartDashboard.putNumber("Left Encoder", leftEncoder.get());
        SmartDashboard.putNumber("Right Encoder", rightEncoder.get());
        SmartDashboard.putNumber("Error", lastError);



    }

}
