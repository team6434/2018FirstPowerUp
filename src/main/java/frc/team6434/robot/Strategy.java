package frc.team6434.robot;

public class Strategy implements Subsystem {

    private double straightSpeed = 0.5;
    private double turnSpeed = 0.3;
    public Strategy()
    {
    }

    public void init(){

    }


    public Command[] strategyLeftRed()
    {
        return new Command[]{
                new Straight(3030, straightSpeed),
                new Turn(90, turnSpeed),
                new Straight(1000, straightSpeed)
        };

        //drivetrain.driveDistanceMilli(50, straightSpeed, 0);
//        drivetrain.driveDistanceMilli(3030, straightSpeed, 0);
//        drivetrain.turnToAngle(90,0.5)
    }

    public void showDashboard()
    {

    }
}