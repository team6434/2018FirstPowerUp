package frc.team6434.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * Created by jwill on 1/17/2018.
 */
public class Lift {

    VictorSP liftMotor;
    DigitalInput liftLimitSwitchUpper, liftLimitSwitchLower;

    public void init(){
        liftMotor = new VictorSP(4);
        liftLimitSwitchUpper = new DigitalInput(5);
        liftLimitSwitchLower = new DigitalInput(6);
    }

    public void moveUp() {
        if (liftLimitSwitchUpper.get() == true){
            liftMotor.set(0);
        }
        else{
            liftMotor.set(1.0);
        }
    }

    public void moveDown() {
        if (liftLimitSwitchLower.get() == true){
            liftMotor.set(0);
        }
        else{
            liftMotor.set(-1.0);
        }
    }

    public void goToPosition(double position) {
    }

}
