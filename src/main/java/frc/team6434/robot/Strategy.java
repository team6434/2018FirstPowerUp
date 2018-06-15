package frc.team6434.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Strategy implements Subsystem {

    private double straightSpeed = 0.75;
    private double turnSpeed = 0.4;

    public Strategy() { }

    public void init()
    {
        SmartDashboard.putNumber("robotPosition", 0);
//        THIS IS WHAT WAS CHANGED ^^^^^^
    }

//    public Step[] pickStrategy()
//    {
//        Step[] driveSteps = pickDrivingStrategy();
//        Step[] endSteps = endOfStrat();
//        Step[] combined = new Step[driveSteps.length + endSteps.length];
//        System.arraycopy(driveSteps, 0, combined, 0, driveSteps.length);
//        System.arraycopy(endSteps, 0, combined, driveSteps.length, endSteps.length);
//        return combined;
//    }

    //logic for picking a strategy
    public Step[] pickStrategy()
    {
        String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        int robotPosition = (int)SmartDashboard.getNumber("robotPosition", 0);

//        if(Character.toUpperCase(gameData.charAt(0)) == 'L')
//            {
//                return baseLine();
////                return threeLeft();
//            } else {
////                return baseLine();
//                return threeRight();
//            }




        //LEFT
        if (robotPosition == 1)
        {
            if(Character.toUpperCase(gameData.charAt(0)) == 'L')
            {
//                return baseLine();
                return oneLeft();
            } else {
                return baseLine();
//                return oneRight();
            }
        }
        //MIDDLE
        else if (robotPosition == 2)
        {
            if(Character.toUpperCase(gameData.charAt(0)) == 'L')
            {
//                return baseLine();
                return twoLeft();
            } else {
//                return baseLine();
                return twoRight();
            }
        }
        //RIGHT
        else if (robotPosition == 3)
        {
            if(Character.toUpperCase(gameData.charAt(0)) == 'L')
            {
                return baseLine();
//                return threeLeft();
            } else {
//                return baseLine();
                return threeRight();
            }
        }
        else
        {
            return baseLine();
        }

    }



//    public Step[] endOfStrat()
//    {
//        return new Step[]{
//                new Raise(),
//                new Eject()
//        };
//    }

    public Step[] test()
    {
        SmartDashboard.putString("Strategy", "Test");
        return new Step[]{
                new Straight(400, straightSpeed),
                new StraightLift(500,0.5),
                new Eject(),
                new Eject()
        };
    }

    //TESTED
    public Step[] oneLeft()
    {
        SmartDashboard.putString("Strategy", "1 Left");
        return new Step[]{
                new Straight(3200, straightSpeed),
                new Turn(90, turnSpeed),
                new Straight(400, straightSpeed),
                new StraightLift(100, 0.6),
                new Eject(),
                new Eject()
        };
    }

    //TESTED
    public Step[] oneRight()
    {
        SmartDashboard.putString("Strategy", "1 Right");
        return new Step[]{
                new Straight(5250, straightSpeed),
                new Turn(90, turnSpeed),
                new Straight(5500, straightSpeed),
                new Turn(180, turnSpeed),
                new Straight(2500, straightSpeed),
                new Turn(270, turnSpeed),
                new StraightLift(1000, straightSpeed),
                new Eject(),
                new Eject()
        };
    }

    //TESTED
    public Step[] twoLeft()
    {
        SmartDashboard.putString("Strategy", "2 Left");
        return new Step[]{
                new Straight(750, straightSpeed),
                new Turn(-90, turnSpeed),
                new Straight(2500, straightSpeed),
                new Turn(0, turnSpeed),
                new StraightLift(750, straightSpeed),
                new Eject(),
                new Eject()
        };
    }

    //TESTED
    public Step[] twoRight()
    {
        SmartDashboard.putString("Strategy", "2 Right");
        return new Step[]{
                new Straight(1800, 0.7),
                new Raise(),
                new Eject(),
                new Eject()
        };
    }


    //TESTED
    public Step[] threeLeft()
    {
        SmartDashboard.putString("Strategy", "3 Left");
        return new Step[]{
                new Straight(5250, straightSpeed),
                new Turn(-90, turnSpeed),
                new Straight(5500, straightSpeed),
                new Turn(-180, turnSpeed),
                new Straight(2250, straightSpeed),
                new Turn(-270, turnSpeed),
                new StraightLift(500, straightSpeed),
                new Eject(),
                new Eject()
        };
    }

    //TESTED
    public Step[] threeRight()
    {
        SmartDashboard.putString("Strategy", "3 Right");
        return new Step[]{
                new Straight(3200, straightSpeed),
                new Turn(-90, turnSpeed),
                new Straight(400, straightSpeed),
                new StraightLift(100, 0.6),
                new Eject(),
                new Eject()
        };
    }

    public Step[] baseLine()
    {
        SmartDashboard.putString("Strategy", "Base line");
        return new Step[]{
                new Straight(3030, straightSpeed)
        };
    }


    public void showDashboard()
    {
        int readbotpos = (int)SmartDashboard.getNumber("robotPosition", 0);
        SmartDashboard.putNumber("Check Position", readbotpos);
    }
}