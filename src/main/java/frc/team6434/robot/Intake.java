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
        intakeLimitSwitch = new DigitalInput(0);
    }

//    public void getCube()
//    {
//        if (intakeLimitSwitch.get() == false)
//        {
//            intakeMotor.set(1.0);
//        }
//        else
//        {
//            intakeMotor.set(0);
//        }
//    }

//    public void ejectCubeFast()
//    {
//        if (intakeLimitSwitch.get() == true)
//        {
//            ejectCube(1.0);
//        }
//    }
//
//    public void ejectCubeSlow()
//    {
//        if (intakeLimitSwitch.get() == true)
//        {
//            ejectCube(0.5);
//        }
//    }


    public void getCubeTest(double speed)
    {
        intakeMotorLeft.set(-speed);
        intakeMotorRight.set(speed);
    }

    public void ejectCubeTest(double speed)
    {
        intakeMotorLeft.set(speed);
        intakeMotorRight.set(-speed);
    }

//    private void ejectCube(double speed)
//    {
//        intakeMotorLeft.set(speed);
//        intakeMotorRight.set(-speed);
//    }
    
    public void showDashboard()
    {
        SmartDashboard.putBoolean("Intake Limit Switch", intakeLimitSwitch.get());
    }


}
