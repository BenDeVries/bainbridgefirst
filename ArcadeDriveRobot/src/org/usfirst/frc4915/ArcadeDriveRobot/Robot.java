// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc4915.ArcadeDriveRobot;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc4915.ArcadeDriveRobot.commands.*;
import org.usfirst.frc4915.ArcadeDriveRobot.subsystems.*;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    Command autonomousCommand;
    public static OI oi;

    public double kThrottleValueRAW; 

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static DriveTrain driveTrain;
    public static Harvester harvester;
    public static AirCompressor airCompressor;
    public static Launcher launcher;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    public static final String VERSION = "v1.05.03";
    // Added Launcher (single solenoid) gearbox pneumatics functionality
    // Includes a Gyro, and limit switch
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
	RobotMap.init();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrain = new DriveTrain();
        harvester = new Harvester();
        airCompressor = new AirCompressor();
        launcher = new Launcher();
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();
	
        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        autonomousCommand = new AutonomousCommand();
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        System.out.println(VERSION);
        SendUserMessages messages = new SendUserMessages();
        messages.display(1, "Hi 456789012345678901234567890"); //tests the SendUserMessages, 30 characters
        messages.display(2, "Hi2456789012345678901234567890"); //tests the SendUserMessages, 30 characters
        messages.display(3, "Hi3456789012345678901234567890"); //tests the SendUserMessages, 30 characters
        
        Robot.airCompressor.start();
    }
    
    public void autonomousInit() {
        // schedule the autonomous command (example)
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
    }
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putBoolean("Pressure Switch", RobotMap.airCompressorCompressor.getPressureSwitchValue());
        SmartDashboard.putNumber("Throttle", oi.getJoystickDrive().getAxis(Joystick.AxisType.kThrottle));  
        SmartDashboard.putNumber("NumAxis", oi.getJoystickDrive().getAxis(Joystick.AxisType.kNumAxis));
        SmartDashboard.putNumber("X", oi.getJoystickDrive().getAxis(Joystick.AxisType.kX));
        SmartDashboard.putNumber("Z", oi.getJoystickDrive().getAxis(Joystick.AxisType.kZ));
        SmartDashboard.putNumber("Y", oi.getJoystickDrive().getAxis(Joystick.AxisType.kY));
        SmartDashboard.putNumber("Twist", oi.getJoystickDrive().getAxis(Joystick.AxisType.kTwist));
        kThrottleValueRAW = oi.joystickDrive.getAxis(Joystick.AxisType.kThrottle);
        SmartDashboard.putNumber("kThrottle ", .5 * ( -1 * kThrottleValueRAW + 1)); // Advanced Joystick Throttle
        // SmartDashboard.putNumber("Z Axis ", oi.joystickDrive.getAxis(Joystick.AxisType.kZ)); // Attack Joystick Throttle
        
        SmartDashboard.putBoolean("Gearbox Pneumatics", launcher.getStatePneumatics());
        
        SmartDashboard.putBoolean("Harvester Limit Switch1: ", harvester.getLimitSwitch1());
    }
    /**
     * This function called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
