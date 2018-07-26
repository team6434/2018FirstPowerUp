package frc.team6434.robot;

import edu.wpi.first.wpilibj.DriverStation;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public abstract class Strategy implements Subsystem {

    private double straightSpeed = 0.75;
    private double turnSpeed = 0.4;

    public Strategy() { }

    public void init() { }


    //logic for picking a strategy
    public Step[] pickStrategy()
    {
        String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();

        if(Character.toUpperCase(gameData.charAt(0)) == 'L') {
            return Left(); }
        else if(Character.toUpperCase(gameData.charAt(0)) == 'R'){
            return Right(); }
//        else {
            return baseLine();
//        }

    }



    public Step[] test()
    {
//        SmartDashboard.putString("Strategy", "Test");
        return new Step[]{
                new Straight(400, straightSpeed),
                new Eject()
        };
    }



    //TESTED
    public Step[] Left()
    {
        return new Step[]{
                new Straight(1, straightSpeed),
                new Turn(-90, turnSpeed),
                new Straight(1, straightSpeed),
                new Turn(0, turnSpeed),
                new Straight(1, straightSpeed),
                new Eject()
        };
    }

    //TESTED
    public Step[] Right()
    {
        return new Step[]{
                new Straight(1, 0.7),
                new Eject()
        };
    }



    public Step[] baseLine()
    {
        return new Step[]{
                new Straight(1, straightSpeed)
        };
    }

}