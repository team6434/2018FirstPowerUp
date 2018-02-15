package frc.team6434.robot;

import edu.wpi.first.wpilibj.DriverStation;

public class Strategy implements Subsystem {

    private double straightSpeed = 0.5;
    private double turnSpeed = 0.3;
    public Strategy()
    {
    }

    public void init(){

    }

    public Command[] pickStrategy()
    {
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

    public Command[] strategyLeftRed()
    {
        return new Command[]{
                new Straight(3030, straightSpeed),
                new Turn(-90, turnSpeed),
                new Straight(1000, straightSpeed)
        };
    }

    public Command[] strategyLeftBlue()
    {
        return new Command[]{
                new Straight(3030, straightSpeed),
                new Turn(90, turnSpeed),
                new Straight(1000, straightSpeed)
        };
    }

    public void showDashboard()
    {

    }
}