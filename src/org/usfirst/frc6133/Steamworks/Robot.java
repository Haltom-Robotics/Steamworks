package org.usfirst.frc6133.Steamworks;

//import edu.wpi.first.wpilibj.DriverStation;
//import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.IterativeRobot;
//import edu.wpi.first.wpilibj.SerialPort;
//import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc6133.Steamworks.commands.*;
import org.usfirst.frc6133.Steamworks.subsystems.*;
//import com.kauailabs.navx.frc.AHRS;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;
    //public static AHRS ahrs;
    public static Grip grip;

    public static OI oi;
    public static Drivetrain drivetrain;
    public static Shooter shooter;
    public static Winch winch;

    public static int joyMode = 0;
    public static int autonMode = 0;
    private static boolean isCalibrated = false;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	RobotMap.init();
    	grip = new Grip();
    	grip.initCamera();
    	//Timer.delay(2);
    	//try {
        //	ahrs = new AHRS(SerialPort.Port.kUSB);
        	
        	//System.out.println(ahrs.isConnected());
        //} catch (RuntimeException e) {
        //	System.out.println("Error instatiating navx mico: " + e.getMessage());
        //}
        drivetrain = new Drivetrain();
        //shooter = new Shooter();
        winch = new Winch();
               
        oi = new OI();
        
        isCalibrated = false;

        autonomousCommand = new AutonomousCommand();
        
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void robotPeriodic() {
    	if (!isCalibrated)
    	{
    		System.out.println("Calibrating gyro...");
    		RobotMap.gyro.calibrate();
    		System.out.println("Calibrated.");
    		isCalibrated = true;
    	}
    	//System.out.println(RobotMap.gyro.getAngle());
    }
    public void disabledInit(){
    	
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
    	RobotMap.gyro.reset();
    	if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        Scheduler.getInstance().removeAll();
        //RobotMap.gyro.reset();
        //if (RobotMap.joySelect.getSelected() == "0") joyMode = 0;
        //if (RobotMap.joySelect.getSelected() == "1") joyMode = 1;
        //if (RobotMap.joySelect.getSelected() == "2") joyMode = 2;
        autonomousCommand = new JoyDrive2();;
        autonomousCommand.start();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
