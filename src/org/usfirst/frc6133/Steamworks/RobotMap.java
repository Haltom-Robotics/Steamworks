package org.usfirst.frc6133.Steamworks;

import org.opencv.core.Mat;

import com.ctre.CANTalon;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CameraServer;
//import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
//import edu.wpi.first.wpilibj.livewindow.LiveWindow;
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
    public static Victor shooterLeftMotor;
    public static Victor shooterRightMotor;
    public static RobotDrive shooterLauncher;
    public static Victor shooterBelt;
    public static Victor shooterAim;
    public static ADXRS450_Gyro gyro;
    
    public static UsbCamera USBcam;
    public static CameraServer cam;

    //public static SendableChooser<String> allianceChooser;
    public static SendableChooser<String> liftChooser;
    //public static SendableChooser<String> shootChooser;
    //public static SendableChooser<String> beltSpeed;
    //public static SendableChooser<String> launcherSpeed;
    //public static SendableChooser<String> joySelect;

    public static void init() {
        drivetrainLeftMotor1 = new CANTalon(3);
        drivetrainLeftMotor2 = new CANTalon(2);
        drivetrainLeftMotor3 = new CANTalon(6);
        
        drivetrainRightMotor1 = new CANTalon(5);
        drivetrainRightMotor2 = new CANTalon(1);
        drivetrainRightMotor3 = new CANTalon(4);
        
        winchMotor = new CANTalon(7);

        drivetrainRobotDrive = new RobotDrive(drivetrainLeftMotor1, drivetrainRightMotor1);
        
        drivetrainRobotDrive.setSafetyEnabled(true);
        drivetrainRobotDrive.setExpiration(0.1);
        drivetrainRobotDrive.setSensitivity(0.5);
        drivetrainRobotDrive.setMaxOutput(1.0);

        drivetrainRobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        drivetrainRobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        /*
        shooterLeftMotor = new Victor(0);
        shooterRightMotor = new Victor(1);
        shooterLauncher = new RobotDrive(shooterLeftMotor, shooterRightMotor);
        
        shooterBelt = new Victor(2);
        */
        gyro = new ADXRS450_Gyro();
        /*
        try {
        	//USBcam = new UsbCamera("cam0",0);
        	//cam = CameraServer.getInstance();
        	//USBcam.setBrightness(50);
        	//USBcam.setResolution(320, 240);
        	//cam.startAutomaticCapture(USBcam);	
        	CameraServer.getInstance().startAutomaticCapture();
        } catch (RuntimeException e) {
        	System.out.println("Error instatiating USB Camera: " + e.getMessage());
        }
        */
        
        /*
        allianceChooser = new SendableChooser<String>();
        allianceChooser.addDefault("Blue Alliance", "blue");
        allianceChooser.addObject("Red Alliance", "red");
        SmartDashboard.putData("Alliance Color", allianceChooser);
        */
        liftChooser = new SendableChooser<String>();
        liftChooser.addObject("Lift 1 - Left", "left");
        liftChooser.addDefault("Lift 2 - Middle", "mid");
        liftChooser.addObject("Lift 3 - Right", "right");
        liftChooser.addObject("None - Drive Straight", "none");
        SmartDashboard.putData("Lift Chooser", liftChooser);
        /*
        shootChooser = new SendableChooser<String>();
        shootChooser.addDefault("Do not shoot", "ceaseFire");
        shootChooser.addObject("FIRE ZE MISSILES!", "fire");
        shootChooser.addObject("Aim, no fire", "aim");
        SmartDashboard.putData("Launcher Settings", shootChooser);
        */
        /*
        joySelect = new SendableChooser<String>();
        joySelect.addObject("Chris Merkle", "0");
        joySelect.addDefault("Colton Lyons", "1");
        joySelect.addObject("Stephen Lewis", "2");
        SmartDashboard.putData("Joystick Selection", joySelect);
        */
        /*
        beltSpeed = new SendableChooser<String>();
        beltSpeed.addDefault("100%", "100");
        beltSpeed.addObject("85%", "85");
        beltSpeed.addObject("70%", "70");
        beltSpeed.addObject("50%", "50");
        beltSpeed.addObject("25%", "25");
        SmartDashboard.putData("Belt Speed", beltSpeed);
        
        launcherSpeed = new SendableChooser<String>();
        launcherSpeed.addDefault("100%", "100");
        launcherSpeed.addObject("85%", "85");
        launcherSpeed.addObject("70%", "70");
        launcherSpeed.addObject("50%", "50");
        launcherSpeed.addObject("25%", "25");
        SmartDashboard.putData("Launcher Speed", launcherSpeed);
        */
    }
}
