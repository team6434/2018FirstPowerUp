package frc.team6434.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Created by jwill on 1/17/2018.
 */
public class Lift implements Subsystem{

    VictorSP liftMotor;
    DigitalInput liftLimitSwitchUpper, liftLimitSwitchLower;

    public void init()
    {
        liftMotor = new VictorSP(4);
        liftLimitSwitchUpper = new DigitalInput(5);
        liftLimitSwitchLower = new DigitalInput(6);
    }

    public void moveUp(double speed)
    {
        liftMotor.set(-speed);
    }

    public void moveDown(double speed)
    {
        liftMotor.set(speed);
    }


    public void stop ()
    {
        liftMotor.set(0);
    }

    public void showDashboard()
    {
        SmartDashboard.putNumber("Lift Motor Speed", liftMotor.get());
        SmartDashboard.putBoolean("Lift Motor Upper Limit", liftLimitSwitchUpper.get());
        SmartDashboard.putBoolean("Lift Motor Lower Limit", liftLimitSwitchLower.get());
    }
}
