package org.usfirst.frc.team3729.robot;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.InterruptHandlerFunction;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	boolean goUp, goDown;
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	double motorSpeed = -0.4;
	DigitalInput upSensor, downSensor;
	double value1 = 0;
	double value2 = 0;
	int runtimes = 0;
	int bart = 0;
	public SpeedController motor;
	public Potentiometer pot;
	
//plz do gud 
	/**be
	 * used for any initialization code.
	 */
	public void robotInit() {
		pot = new AnalogPotentiometer(4,1,0);
		
		upSensor = new DigitalInput(8);
		upSensor.requestInterrupts(new InterruptHandlerFunction() {

			@Override
			public void interruptFired(int interruptAssertedMask, Object param) {
				System.out.println("upSensorInterput " + interruptAssertedMask + " prarams " + param);
				goUp = upSensor.get();
				if (motorSpeed > 0)
					motorSpeed *= -1;
				motor.set(motorSpeed);
				System.out.println("upSensorInterput  countUp " + goUp + " motorSpeed " + motorSpeed);
				
			}
			
		});
		upSensor.enableInterrupts();
		downSensor = new DigitalInput(3);
		downSensor.requestInterrupts(new InterruptHandlerFunction() {

			@Override
			public void interruptFired(int interruptAssertedMask, Object param) {
				System.out.println("downSensorInterput " + interruptAssertedMask + " prarams " + param);
				goDown = downSensor.get();
				if (motorSpeed < 0)
					motorSpeed *= -1;
				motor.set(motorSpeed);
				System.out.println("downSensorInterput  countDown " + goDown + " motorSpeed " + motorSpeed);
				
			}
			
		});
		downSensor.enableInterrupts();
		motor = new Talon(4); // initialize the motor as a Talon on channel 0

	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			break;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {

	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		System.out.println("In testPerioding");
		// goUp = upSensor.get();
		// goDown = downSensor.get();
		try {
			motor.set(motorSpeed);
			System.out.println("Pot value =" + pot.get());
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
		Timer.delay(50);
	}


}