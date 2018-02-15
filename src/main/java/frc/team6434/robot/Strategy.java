package frc.team6434.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Strategy implements Subsystem {

    private double straightSpeed = 0.5;
    private double turnSpeed = 0.3;
    public Strategy()
    {
    }

    public void init(){
        SmartDashboard.putNumber("robotPosition", 0);
    }

    public Step[] pickStrategy()
    {
        int robotPosition = (int)SmartDashboard.getNumber("robotPosition", 0);
        if (robotPosition == 4)
        {
            // not a real position. Used for experimenting

            return strategyTestSquare();
        }
        String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        if(gameData.length() == 0)
        {
            gameData = "L";
        }

        if(Character.toUpperCase(gameData.charAt(0)) == 'L')
        {
            return strategyLeftRed();
        } else {
            return strategyLeftBlue();
        }

    }

    public Step[] strategyLeftRed()
    {
        SmartDashboard.putString("Strategy", "Left Red");
        return new Step[]{
                new Straight(3030, straightSpeed),
                new Turn(-90, turnSpeed),
                new Straight(1000, straightSpeed)
        };
    }

    public Step[] strategyLeftBlue()
    {
        SmartDashboard.putString("Strategy", "Left Blue");
        return new Step[]{
                new Straight(3030, straightSpeed),
                new Turn(90, turnSpeed),
                new Straight(1000, straightSpeed)
        };
    }

    public Step[] strategyTestSquare() {
        SmartDashboard.putString("Strategy", "Test Square");
        return new Step[]{
                new Straight(1000, straightSpeed),
                new Turn(-90, turnSpeed),
                new Straight(1000, straightSpeed),
                new Turn(-180, turnSpeed),
                new Straight(1000, straightSpeed),
                new Turn(-270, turnSpeed),
                new Straight(1000, straightSpeed),
                new Turn(-360, turnSpeed)
        };
    }
    public void showDashboard()
    {

    }
}