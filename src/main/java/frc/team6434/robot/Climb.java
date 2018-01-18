package frc.team6434.robot;
import edu.wpi.first.wpilibj.VictorSP;

public class Climb {
    VictorSP climberMotor;
    public void init() {
        climberMotor = new VictorSP(9);
    }
    public void climb() {
        climberMotor.set(0.5);
    }

}
