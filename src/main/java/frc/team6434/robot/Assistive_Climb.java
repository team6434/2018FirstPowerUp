package frc.team6434.robot;

import edu.wpi.first.wpilibj.VictorSP;

public class Assistive_Climb {

    private VictorSP assistiveClimbMotorLeft, getAssistiveClimbMotorLeft;
    private VictorSP assistiveClimbMotorRight, getAssistiveClimbMotorRight;

    final double AMS = Constants.AMS;

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
