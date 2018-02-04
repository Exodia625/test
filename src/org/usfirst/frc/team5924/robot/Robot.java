package org.usfirst.frc.team5924.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	// SPARK MOTOR DRIVE
	
	/**
	 * Spark m_frontLeft = new Spark(0);
	Spark m_rearLeft = new Spark(1);
	SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);

	Spark m_frontRight = new Spark(2);
	Spark m_rearRight = new Spark(3);
	SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_rearRight);

	DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);
	*/
	
	
	// JOYSTICK
	Joystick leftJoy = new Joystick(1);
	Joystick buttonPanel = new Joystick(2);
	
	// DART ACTUATOR
	WPI_TalonSRX talon3 = new WPI_TalonSRX(3);
	
	//PIDController pid = new PIDController(1.5, 2.5, 2.1);
	
	
	Timer timer = new Timer();
	
	// TALON MOTOR DRIVE
	WPI_TalonSRX t_frontRight = new WPI_TalonSRX(1);
	WPI_TalonSRX t_rearRight = new WPI_TalonSRX(2);
	WPI_TalonSRX t_rearLeft = new WPI_TalonSRX(4);
	WPI_TalonSRX t_frontLeft = new WPI_TalonSRX(5);
	
	SpeedControllerGroup t_right = new SpeedControllerGroup(t_frontRight, t_rearRight);
	SpeedControllerGroup t_left = new SpeedControllerGroup(t_frontLeft, t_rearLeft);
	
	DifferentialDrive t_drive = new DifferentialDrive(t_right,t_left);
	
	
	// OBJECTS NOT IN USE
	/**
	Compressor c = new Compressor(0);
	
	DoubleSolenoid mySol1 = new DoubleSolenoid(1, 2);
	DoubleSolenoid mySol2 = new DoubleSolenoid(0, 3);
	
	
	 */

	// IN PROGRESS
	/**
	Potentiometer pot = new AnalogPotentiometer(0, 3600, 30);
	
	10 revolutions of 360 degrees to extend to maximum length: 6 inches
	 * so 10 * 360 = 3600 degrees maximum rotation
	 double potDegrees = pot.get();
	 */
	
	
	
	
	//This function is run when the robot is first started up and should be
	//used for any initialization code.
	 
	@Override
	public void robotInit() {
		
		talon3.config_kF(0, 0.0, 10);
		talon3.config_kP(0, 7.0065, 10); //7.007
		talon3.config_kI(0, 0.006, 10); //.006
		talon3.config_kD(0, 0.0001, 10);
	}

	
	 //This function is run once each time the robot enters autonomous mode
	 
	@Override
	public void autonomousInit() {
		timer.reset();
		timer.start();
	}

	
	 //This function is called periodically during autonomous
	 
	@Override
	public void autonomousPeriodic() {
		double maxSpeed = (4455 * 6 * Math.PI) / (60 * 10.75 * 12);
		
		while (timer.get() < 1.65) {
			t_drive.arcadeDrive(0.8, 0.0);
			
		}
		//Turn 90 degrees to the right
		/**while (timer.get() > 1.15 && timer.get() < 2.15)
		
		{
			t_drive.arcadeDrive(0.0, 0.5);
		}
		*/
		
		while (timer.get() > 1.65 && timer.get() < 2.65)
			
		{
			t_drive.arcadeDrive(0.6, 0);
		}
		
	}

	
	 //This function is called once each time the robot enters tele-operated
	 //mode
	
	
	@Override
	public void teleopInit() {
		
		talon3.setSelectedSensorPosition(0, 0, 0);
		
	}

	 //This function is called periodically during operator control
	 
	@Override
	public void teleopPeriodic() {
		
		// VARIABLES IN USE
		
		double buttonAxis = buttonPanel.getRawAxis(1);
		//double yAxisValue = leftJoy.getRawAxis(1);
		//double xAxisValue = leftJoy.getRawAxis(4);
		//talon3.set(yAxisValue);
		//t_rearLeft.set(-yAxisValue);
		//t_rearLeft.set(ControlMode.Follower, 3);
		
		
		
		
		//double motorOutput = talon3.getMotorOutputPercent();
		boolean yButton = buttonPanel.getRawButton(6);
		boolean xButton = buttonPanel.getRawButton(4);
		boolean bButton = buttonPanel.getRawButton(5);
		boolean aButton = buttonPanel.getRawButton(3);
		double groundPosition = 20.0;
		double exchangePosition = 40.0;
		double switchPosition = 310.0;
		double startPosition = 390.0;
		
		talon3.setInverted(false);
		talon3.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 0);
		talon3.setSensorPhase(false);
		//talon3.configForwardSoftLimitThreshold(400, 0);
		//talon3.configForwardSoftLimitEnable(true, 0);
		//talon3.configReverseSoftLimitThreshold(-1, 0);
		//talon3.configReverseSoftLimitEnable(true, 0);
		
		System.out.println(talon3.getSelectedSensorPosition(0));
		
		if (buttonAxis > 0.25){
			
			talon3.set(ControlMode.PercentOutput, 0.25);
			
		}
		
		if (buttonAxis < -0.25){
			
			talon3.set(ControlMode.PercentOutput, -0.25);
			
		}
		
		if (-0.25 < buttonAxis && buttonAxis < 0.25){
			
			talon3.set(ControlMode.PercentOutput, 0.0);
			
		}
		
		if (aButton) {
				timer.reset();
				timer.start();
				while (timer.get() < 3.5) {
					if(bButton || xButton || yButton){
						break;
					}
					talon3.set(ControlMode.Position, groundPosition);
				}
				
			}
			
			//talon3.set(ControlMode.Position, groundPosition);
			
		
		if (bButton) {
			timer.reset();
			timer.start();
			while (timer.get() < 3.5) {
				if(aButton || xButton || yButton){
					break;
				}
				talon3.set(ControlMode.Position, exchangePosition);
			}
			
			//talon3.set(ControlMode.Position, exchangePosition);
		}
		
		if (xButton) {
			timer.reset();
			timer.start();
			while (timer.get() < 3.5) {
				if(bButton || aButton || yButton){
					break;
				}
				talon3.set(ControlMode.Position, switchPosition);
			}
			//talon3.set(ControlMode.Position, switchPosition);
		}
		
		if (yButton) {
			timer.reset();
			timer.start();
			while (timer.get() < 3.5) {
				if(bButton || xButton || aButton){
					break;
				}
				talon3.set(ControlMode.Position, startPosition);
			}

		}
		

			
		// DRIVING SPARK MOTORS
		
		/**
		 * m_drive.arcadeDrive(value, value1);
		m_frontLeft.set(leftJoy.getRawAxis(1));
		*/
		
		//PNEUMATIC CYLINDERS
		
		/**
		if (leftJoy.getRawButtonPressed(4)) {
		
			mySol1.set(DoubleSolenoid.Value.kForward);
			mySol2.set(DoubleSolenoid.Value.kForward);
			
		}
		if (leftJoy.getRawButtonPressed(3)) {
			
			mySol1.set(DoubleSolenoid.Value.kReverse);
			mySol2.set(DoubleSolenoid.Value.kReverse);
		}
		
		if (leftJoy.getRawButtonPressed(2)){
			
			System.out.println(potDegrees);
			
		}
		*/
		//DRIVING TALONSRX
		//t_drive.arcadeDrive(yAxisValue, xAxisValue);

	}
	
	 // This function is called periodically during test mode
	  
	  
	
	@Override
	public void testPeriodic() {
		
		double testValue = leftJoy.getRawAxis(1);
		
		talon3.setInverted(true);
		talon3.set(ControlMode.PercentOutput, testValue);
		talon3.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 0);
		talon3.setSensorPhase(true);
		//talon3.configForwardSoftLimitThreshold(480, 0);
		//talon3.configReverseSoftLimitThreshold(90, 0);
		//talon3.configForwardSoftLimitEnable(true, 0);
		//talon3.configReverseSoftLimitEnable(true, 0);
		SmartDashboard.putNumber("Pot Degrees", talon3.getSelectedSensorPosition(0));
	}
	
	
}
