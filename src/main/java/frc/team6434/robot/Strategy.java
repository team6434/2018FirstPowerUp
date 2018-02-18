package frc.team6434.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Strategy implements Subsystem {

    private double straightSpeed = 0.9;
    private double turnSpeed = 0.3;

    public Strategy() { }

    public void init()
    {
        SmartDashboard.putNumber("robotPosition", 1);
    }

    public Step[] pickStrategy()
    {
        Step[] driveSteps = pickDrivingStrategy();
        Step[] endSteps = endOfStrat();
        Step[] combined = new Step[driveSteps.length + endSteps.length];
        System.arraycopy(driveSteps, 0, combined, 0, driveSteps.length);
        System.arraycopy(endSteps, 0, combined, driveSteps.length, endSteps.length);
        return combined;
    }

    //logic for picking a strategy
    public Step[] pickDrivingStrategy()
    {
        String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        int robotPosition = (int)SmartDashboard.getNumber("robotPosition", 0);

        if (robotPosition == 1)
        {
            if(Character.toUpperCase(gameData.charAt(0)) == 'L')
            {
                return oneLeft();
            } else {
                return oneRight();
            }
        }
        else if (robotPosition == 2)
        {
            if(Character.toUpperCase(gameData.charAt(0)) == 'L')
            {
                return twoLeft();
            } else {
                return twoRight();
            }
        }
        else if (robotPosition == 3)
        {
            if(Character.toUpperCase(gameData.charAt(0)) == 'L')
            {
                return threeLeft();
            } else {
                return threeRight();
            }
        }
        else if (robotPosition == 4)
        {
            return testEject();
        }
        else
        {
            return baseLine();
        }

//        if(gameData.length() == 0)
//        {
//            gameData = "L";
//        }
    }

    public Step[] endOfStrat()
    {
        return new Step[]{
//                new Raise()
                new Eject(1)
        };
    }

    public Step[] testEject()
    {
        SmartDashboard.putString("Strategy", "Test Eject");
        return new Step[]{
                new Eject(1)
        };
    }


    public Step[] oneLeft() //DONE
    {
        SmartDashboard.putString("Strategy", "1 Left");
        return new Step[]{
                new Straight(4700, straightSpeed),
                new Turn(90, turnSpeed),
                new Straight(400, straightSpeed)
        };
    }

    public Step[] oneRight()
    {
        SmartDashboard.putString("Strategy", "1 Right");
        return new Step[]{
                new Straight(7000, straightSpeed),
                new Turn(90, turnSpeed),
                new Straight(8000, straightSpeed),
                new Turn(180, turnSpeed),
                new Straight(3500, straightSpeed),
                new Turn(270, turnSpeed),
                new Straight(500, straightSpeed)
        };
    }

    public Step[] twoLeft()
    {
        SmartDashboard.putString("Strategy", "2 Left");
        return new Step[]{
                new Straight(1000, straightSpeed),
                new Turn(-90, turnSpeed),
                new Straight(1000, straightSpeed),
                new Turn(0, turnSpeed),
                new Straight(2000, straightSpeed),
                new Turn(90, turnSpeed),
                new Straight(2000, straightSpeed),
                new Turn(180, turnSpeed)
        };
    }

    public Step[] twoRight()
    {
        SmartDashboard.putString("Strategy", "2 Right");
        return new Step[]{
                new Straight(2000, straightSpeed),
        };
    }

    public Step[] threeLeft()
    {
        SmartDashboard.putString("Strategy", "3 Left");
        return new Step[]{
                new Straight(4000, straightSpeed),
                new Turn(90, turnSpeed),
                new Straight(2000, straightSpeed),
                new Turn(180, turnSpeed),
                new Straight(1000, straightSpeed)
        };
    }

    public Step[] threeRight()
    {
        SmartDashboard.putString("Strategy", "3 Right");
        return new Step[]{
                new Straight(4700, straightSpeed),
                new Turn(-90, turnSpeed),
                new Straight(400, straightSpeed)
        };
    }

    public Step[] baseLine()
    {
        SmartDashboard.putString("Strategy", "Base line");
        return new Step[]{
                new Straight(3030, straightSpeed)
        };
    }

    public void showDashboard() { }
}