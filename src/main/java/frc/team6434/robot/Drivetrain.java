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


    //sets the speeds of all driving motors
    public void drive(double left, double right)
    {
        leftA.set(left);
        leftB.set(left);
        rightA.set(-right);
        rightB.set(-right);
    }


    //teleop driving stuff
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


    //
    public double readGyro()
    {
        return (gyro.getAngle()%360+360)%360;
    }



//    public void restGyro(){
//    }

//    public void turnToAngle(double angle)
//    {
//
//    }


    //resets both encoders
    public void resetEncoders()
    {
        leftEncoder.reset();
        rightEncoder.reset();
    }


    //returns average of both encoders (mil)
    public double getEncoderAvg()
    {
        return (((rightEncoder.get()*2.4) + (leftEncoder.get()*2.4))/2);
    }


    //Drives a set distance in millimetres (roughly)
    public void driveDistanceMilli(int milli, double speed)
    {
//        driveDistance(milli/2.4);
        gyroStraight(milli/2.4,speed);
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


//    public void driveStraight(double speed, int distance)
//    {
//
//        drive(speed - diffmap, speed);
//        driveDistanceMilli(distance);
//    }

//    public void driveStriaght (int distance)
//    {
//        if (leftEncoder.get() < rightEncoder.get())
//        {
//            drive(0.5,0.3)
//        }
//        else if (rightEncoder.get() < leftEncoder.get())
//        {
//
//        }
//        else
//        {
//
//        }
//    }


    //drives straight using gyro
    public void gyroStraight(double distance, double speed)
    {
        if (flagon){
            flagon = false;
            optangle = readGyro();
        }
        double avg_dis = (getEncoderAvg());
        while(avg_dis < distance)
        {
            if(optangle < readGyro())
            {
                drive(speed*0.85, speed);
            }
            if(optangle > readGyro())
            {
                drive(speed, speed*0.85);
            }
            else
            {
                drive(speed,speed);
            }
            avg_dis = (getEncoderAvg());
        }
        drive(0,0);
    }


//    // Turn on the spot to a set angle
//    public void turnToAngle(double angle, double speed)
//    {
//        // Testing very basic proportional speed
//        //speed = Math.abs((readGyro() - angle)/360);
//
////        if(angle < 180)
////        {
////            drive(-speed,speed);
////        }
////        else if (angle >= 181)
////        {
////            drive(speed,-speed);
////        }
////        else
////        {
////            drive(0,0);
////        }
//       SmartDashboard.putNumber("Error", speed);
//        if ((readGyro() - angle) > 180 || (readGyro() - angle <= -180))
//        {
//            // Turn left
//            drive(-speed,speed);
//        }
//        /*else if((readGyro() - angle >= -180))
//        {
//            // Turn right
//            drive(speed,-speed);
//        }*/
//        else{
//            //drive(0,0);
//            drive(speed,-speed);
//        }
//        SmartDashboard.putNumber("Speed", speed);
//    }

    public void turnToAngle (double angle, double speed, boolean bool)
    {
        int target = angle;
        int current = readGryo();
        
        boolean direction = true
        
        int leftSteps = 0;
        int rightSteps = 0;
        
        for(int i = current; (i%360 + 360)%360) != target; i--, leftSteps++){}
        for(int i = current; (i%360 + 360)%360) != target; i++, rightSteps++){}
        
        if(leftSteps > rightSteps)
            direction = false;
        else
            direction = true;
        
        if(direction){
            //do turning left routine here
            while(readGyro() > angle + 5 && readGryo() < angle - 5){
                
            }
        }
        else{
            //do turning right routine here
            while(readGyro() > angle + 5 && readGryo() < angle - 5){
                
            }
        }
    }
    
    
    // Turn on the spot to a set angle
    public void turnToAngle (double angle, double speed)
    {
        showDashboard();

        double target = angle;
        double current = readGyro();

        double left = current - target;
        double right = target - current;

        if(left < right) //go left
        {
            while(current != target)
            {
                if(readGyro() < target)
                {
                    drive(-speed*0.85, speed);
                }
                else if(readGyro() > target)
                {
                    drive(speed, -speed*0.85);
                }
            }
            if(current == target)
            {
                drive(0,0);
            }
        }
        else if (right < left) // go right
        {
            while(readGyro() != target)
            {
                if(readGyro() > target)
                {
                    drive(speed, -speed*0.85);
                }
                else if(readGyro() < target)
                {
                    drive(-speed*0.85, speed);
                }
            }
            if(current == target)
            {
                drive(0,0);
            }
        }
        else
        {
            drive(0,0);
        }
    }


    // Turns to a set angle then drives forward
    public void driveAngle(double angle, double speed)
    {
        if (Math.abs(readGyro() - angle) > 10) {
            turnToAngle(angle, speed);
        }
        else{
            drive(-speed,-speed);
        }
    }


    //put dashboard stuff here
    public void showDashboard()
    {
        SmartDashboard.putNumber("Gyro Angle", readGyro());
        SmartDashboard.putNumber("Left Power", leftA.get());
        SmartDashboard.putNumber("Right Power", rightB.get());
        SmartDashboard.putNumber("Left Encoder", leftEncoder.get());
        SmartDashboard.putNumber("Right Encoder", rightEncoder.get());

    }

}
