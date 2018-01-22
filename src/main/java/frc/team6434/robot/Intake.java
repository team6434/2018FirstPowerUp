package frc.team6434.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * Created by jwill on 1/17/2018.
 */
public class Intake {

    VictorSP intakeMotor;
    DigitalInput intakeLimitSwitch;

    public void init(){
        intakeMotor = new VictorSP(7);
        intakeLimitSwitch = new DigitalInput(8);
    }

    public void getCube()
    {
        if (intakeLimitSwitch.get() == false)
        {
            intakeMotor.set(1.0);
        }
        else
        {
            intakeMotor.set(0);
        }
    }

    public void ejectCubeFast()
    {
        if (intakeLimitSwitch.get() == true)
        {
            ejectCube(1.0);
        }
    }

    public void ejectCubeSlow()
    {
        if (intakeLimitSwitch.get() == true)
        {
            ejectCube(0.5);
        }
    }

    private void ejectCube(double intakeSpeed)
    {
        intakeMotor.set(-intakeSpeed);
    }


}
