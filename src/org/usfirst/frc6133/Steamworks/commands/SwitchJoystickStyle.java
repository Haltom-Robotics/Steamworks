package org.usfirst.frc6133.Steamworks.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc6133.Steamworks.Robot;

public class SwitchJoystickStyle extends Command {

    private Command switchStyleCommand;
	public SwitchJoystickStyle() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.drivetrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.joyMode += 1;
    	if (Robot.joyMode > 2)
    		Robot.joyMode = 0;
    	switch (Robot.joyMode) {
    	case 0:
    		switchStyleCommand = new JoyDrive();
    		break;
    	case 1:
    		switchStyleCommand = new JoyDrive2();
    		break;
    	case 2:
    		switchStyleCommand = new JoyDrive3();
    		break;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}