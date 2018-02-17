package frc.team6434.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Lift implements Subsystem{

    VictorSP liftMotor;
    DigitalInput limitSwitch;

    public void init()
    {
        liftMotor = new VictorSP(2);
//        limitSwitch = new DigitalInput(4);
    }

    //Move Lift (negative is up)
    public void moveLift(double speed)
    {
        liftMotor.set(-speed);
    }

    //Stop lift motor
    public void liftStop ()
    {
        liftMotor.set(0);
    }

    public void showDashboard()
    {
        SmartDashboard.putNumber("Lift Motor Speed", liftMotor.get());
    }
}
