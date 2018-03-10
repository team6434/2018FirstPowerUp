package frc.team6434.robot;

import edu.wpi.first.wpilibj.Servo;

public class Assistive_Climb {

    Servo linearActuator;

//    final double AMI = Constants.AMI;
//    final double AMO = Constants.AMO;

    public void init()
    {
        linearActuator = new Servo(5);
    }

    public void extend()
    {
//        linearActuator.set(AMO);
        linearActuator.set(0.5);
    }

    public void retract()
    {
        linearActuator.set(0);
    }
}
