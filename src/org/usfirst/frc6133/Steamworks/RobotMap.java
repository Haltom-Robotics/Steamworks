package org.usfirst.frc6133.Steamworks;

import com.ctre.CANTalon;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    public static CANTalon drivetrainLeftMotor1;
    public static CANTalon drivetrainLeftMotor2;
    public static CANTalon drivetrainLeftMotor3;
    public static CANTalon drivetrainRightMotor1;
    public static CANTalon drivetrainRightMotor2;
    public static CANTalon drivetrainRightMotor3;
    public static CANTalon winchMotor;
    public static RobotDrive drivetrainRobotDrive;
    
    public static UsbCamera USBcam;
    public static CameraServer cam;

    public static SendableChooser allianceChooser;
    public static SendableChooser liftChooser;
    public static SendableChooser shootChooser;

    public static void init() {
        drivetrainLeftMotor1 = new CANTalon(1);
        drivetrainLeftMotor2 = new CANTalon(3);
        drivetrainLeftMotor3 = new CANTalon(5);
        
        drivetrainRightMotor1 = new CANTalon(2);
        drivetrainRightMotor2 = new CANTalon(4);
        drivetrainRightMotor3 = new CANTalon(6);
        
        winchMotor = new CANTalon(7);

        drivetrainRobotDrive = new RobotDrive(drivetrainLeftMotor1, drivetrainRightMotor1);
        
        drivetrainRobotDrive.setSafetyEnabled(true);
        drivetrainRobotDrive.setExpiration(0.1);
        drivetrainRobotDrive.setSensitivity(0.5);
        drivetrainRobotDrive.setMaxOutput(1.0);

        //drivetrainRobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        //drivetrainRobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        
        try {
        	USBcam = new UsbCamera("cam0",0);
        	cam = CameraServer.getInstance();
        	USBcam.setBrightness(50);
        	USBcam.setResolution(320, 240);
        	cam.startAutomaticCapture(USBcam);	
        } catch (RuntimeException e) {
        	DriverStation.reportError("Error instatiating USB Camera: " + e.getMessage(), true);
        }
        
        
        allianceChooser = new SendableChooser();
        allianceChooser.addDefault("Blue Alliance", "blue");
        allianceChooser.addObject("Red Alliance", "red");
        SmartDashboard.putData("Alliance Color", allianceChooser);
        
        liftChooser = new SendableChooser();
        liftChooser.addObject("Lift 1 - Left", "left");
        liftChooser.addDefault("Lift 2 - Middle", "mid");
        liftChooser.addObject("Lift 3 - Right", "right");
        SmartDashboard.putData("Lift Chooser", liftChooser);
        
        shootChooser = new SendableChooser();
        shootChooser.addDefault("Do not shoot", "ceaseFire");
        shootChooser.addObject("FIRE ZE MISSILES!", "fire");
        shootChooser.addObject("Aim, no fire", "aim");
        SmartDashboard.putData("Launcher Settings", shootChooser);
    }
}
