package frc.team6434.robot;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain implements Subsystem {

    double lastError;

    public ADXRS450_Gyro gyro;
    VictorSP left, right;
    Encoder leftEncoder, rightEncoder;

    public void init()
    {
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

    //teleop driving
    public void arcadeDrive(double x, double y) {
        double left = y + x;
        double right = y - x;
        if (left > 1) {
            left = 1;
        }
        if (right > 1) {
            right = 1;
        }
        drive(-left/2, -right/2);

    }

    //Resets gyro
    public void resetGyro()
    {
       //to be done
    }

    //reads gyro (between 0-360)
    public double readGyro()
    {
        return (gyro.getAngle() % 360 + 360) % 360;
    }

    //resets both encoders
    public void resetEncoders() {
        leftEncoder.reset();
        rightEncoder.reset();
    }

    //returns average of both encoders (mil)
    public double getEncoderAvg()
    {
        return - constants.encoderRatio * (((rightEncoder.get()) + (leftEncoder.get())) / 2);
    }

    public void distanceSensitivity(double leftSpeed, double rightSpeed, double currentDistance, double targetDistance)
    {
        final double firstSensitivity = 0.9;
        final double secondSensitivity = 0.7;

        if(currentDistance < 500){
            drive(firstSensitivity*leftSpeed, firstSensitivity*rightSpeed);
        }
        else if(currentDistance < 1500){
            drive(secondSensitivity*leftSpeed, secondSensitivity*rightSpeed);
        }
        else if((targetDistance - currentDistance) < 500)
        {
            drive(firstSensitivity*leftSpeed, firstSensitivity*rightSpeed);
        }
        else if((targetDistance - currentDistance) < 1500)
        {
            drive(secondSensitivity*leftSpeed, secondSensitivity*rightSpeed);
        }
        else
        {
            drive(leftSpeed, rightSpeed);
        }
    }

    //drives straight using gyro
    public void driveStraight(double speed, double targetAngle, double currentDistance, double targetDistance)
    {
        //sensitivit settings so you can change all 4 instances of it at once
        final double firstSensitivity = 0.85;
        final double secondSensitivity = 0.5;


        SmartDashboard.putNumber("Gyro Angle", readGyro());
        SmartDashboard.putNumber("Encoder Average", getEncoderAvg());
        double error = calculateError(targetAngle);
        if (error < -10) {
            distanceSensitivity(speed, speed * secondSensitivity, currentDistance, targetDistance);
        }
        else if (error < 0)
        {
            distanceSensitivity(speed, speed * firstSensitivity, currentDistance, targetDistance);
        }
        else if (error < 10)
        {
            distanceSensitivity(speed * firstSensitivity, speed, currentDistance, targetDistance);
        }
        else
        {
            distanceSensitivity(speed * secondSensitivity, speed, currentDistance, targetDistance);
        }
    }

    //calculates the error bewteen the target angle and the current angle
    public double calculateError(double targetAngle)
    {
        double error = readGyro() - targetAngle;

        while (error > 180) {
            error = error - 360;
        }

        while (error < -180) {
            error = error + 360;
        }
        return error;
    }

    //turns to specified angle
    public void turnToAngle(double targetAngle, double speed)
    {
        double error = calculateError(targetAngle);
        if(error > 0)
        {
            drive(-speed, speed);
        }
        else
        {
            drive(speed, -speed);
        }
        this.lastError = error;
    }

    //put dashboard stuff here
    public void showDashboard()
    {
        SmartDashboard.putNumber("Gyro Angle", readGyro());
        SmartDashboard.putNumber("Left Power", left.get());
        SmartDashboard.putNumber("Right Power", right.get());
        SmartDashboard.putNumber("Left Encoder", -leftEncoder.get() * constants.encoderRatio);
        SmartDashboard.putNumber("Right Encoder", -rightEncoder.get() * constants.encoderRatio);
        SmartDashboard.putNumber("Error", lastError);



    }

}
