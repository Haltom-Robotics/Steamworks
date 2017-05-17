package org.usfirst.frc6133.Steamworks;

import com.ctre.CANTalon;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
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
    public static Servo shooterServo;
    public static ADXRS450_Gyro gyro;
    public static AnalogInput sonar;
    
    public static UsbCamera USBcam;
    public static CameraServer cam;

    public static SendableChooser<String> allianceChooser;
    public static SendableChooser<String> liftChooser;

    public static void init() {
        drivetrainLeftMotor1 = new CANTalon(4);
        drivetrainLeftMotor2 = new CANTalon(2);
        drivetrainLeftMotor3 = new CANTalon(8);
        
        drivetrainRightMotor1 = new CANTalon(6);
        drivetrainRightMotor2 = new CANTalon(5);
        drivetrainRightMotor3 = new CANTalon(7);
        
        winchMotor = new CANTalon(7);

        drivetrainRobotDrive = new RobotDrive(drivetrainLeftMotor1, drivetrainRightMotor1);
        
        drivetrainRobotDrive.setSafetyEnabled(true);
        drivetrainRobotDrive.setExpiration(0.1);
        drivetrainRobotDrive.setSensitivity(0.5);
        drivetrainRobotDrive.setMaxOutput(1.0);

        drivetrainRobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        drivetrainRobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        
        //shooterServo = new Servo(0);
        
        gyro = new ADXRS450_Gyro();
        sonar = new AnalogInput(0);
        
        
        allianceChooser = new SendableChooser<String>();
        allianceChooser.addDefault("Blue Alliance", "blue");
        allianceChooser.addObject("Red Alliance", "red");
        SmartDashboard.putData("Alliance Color", allianceChooser);
        
        liftChooser = new SendableChooser<String>();
        liftChooser.addObject("Lift 1 - Left", "left");
        liftChooser.addDefault("Lift 2 - Middle", "mid");
        liftChooser.addObject("Lift 3 - Right", "right");
        liftChooser.addObject("None - Drive Straight", "none");
        liftChooser.addObject("None - drop fuel", "fuel");
        SmartDashboard.putData("Lift Chooser", liftChooser);
                
        
        /*
         * OLD SHOOTER CODE
    public static Victor shooterLeftMotor;
    public static Victor shooterRightMotor;
    public static RobotDrive shooterLauncher;
    public static Victor shooterBelt;
    public static Victor shooterAim;
        shooterLeftMotor = new Victor(0);
        shooterRightMotor = new Victor(1);
        shooterLauncher = new RobotDrive(shooterLeftMotor, shooterRightMotor);
        
        shooterBelt = new Victor(2);
        */
    }
}
