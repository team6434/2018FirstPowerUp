package frc.team6434.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake {

    VictorSP intakeMotorLeft, intakeMotorRight;
    DigitalInput intakeLimitSwitch;

    public void init(){
        intakeMotorLeft = new VictorSP(7);
        intakeMotorRight = new VictorSP(8);
    }

    public void getCube(double speed)
    {
        intakeMotorLeft.set(speed);
        intakeMotorRight.set(speed);
    }

    public void ejectCube(double speed)
    {
        intakeMotorLeft.set(-speed);
        intakeMotorRight.set(-speed);
    }

    public void showDashboard()
    {
        SmartDashboard.putNumber("Left Intake Power", intakeMotorLeft.get());
        SmartDashboard.putNumber("Right Intake Power", intakeMotorRight.get());
    }


}
