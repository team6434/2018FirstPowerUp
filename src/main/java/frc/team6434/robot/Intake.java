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

    //set speed of both intake motors
    private void intakeSpeed (double speed)
    {
        intakeMotorLeft.set(speed);
        intakeMotorRight.set(speed);
    }

    //Get the cube
    public void getCube(double speed)
    {
        intakeSpeed(speed);
    }

    //For keep in the cube while driving
    public void keepCube(double speed)
    {
        intakeSpeed(speed/2);
    }

    //Ejects the cube
    public void ejectCube(double speed)
    {
        intakeSpeed(-speed);
    }

    public void showDashboard()
    {
        SmartDashboard.putNumber("Left Intake Power", intakeMotorLeft.get());
        SmartDashboard.putNumber("Right Intake Power", intakeMotorRight.get());
    }


}
