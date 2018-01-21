package frc.team6434.robot;

import edu.wpi.first.wpilibj.VictorSP;

/**
 * Created by jwill on 1/17/2018.
 */
public class Assistive_Climb {

    VictorSP assistiveClimbMotorLeft, getAssistiveClimbMotorRight;
    
    private double AMS = 0.5 //Assitive Motor Speed

    public void init()
    {
        assistiveClimbMotorLeft = new VictorSP(9);
        assistiveClimbMotorRight = new VictorSP(10);
    }

    public void leftRelease()
    {
        assistiveClimbMotorLeft.set(AMS);
    }

    public void rightRelease()
    {
        assistiveClimbMotorRight.set(AMS);
    }

    public void bothRelease()
    {
        assistiveClimbMotorLeft.set(AMS);
        assistiveClimbMotorRight.set(AMS);
    }
    
    public void pullUpLeft()
    {
        assistiveClimbMotorLeft.set(-AMS);
    }
    
    public void pullUpRight()
    {
        assistiveClimbMotorRight.set(-AMS);
    }
    
}
