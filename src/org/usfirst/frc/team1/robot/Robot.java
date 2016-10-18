package org.usfirst.frc.team1.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	boolean countUp, countDown;
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	DigitalInput upSensor, downSensor;
	double value1 = 0;
	double value2 = 0;
	int runtimes = 0;
	int bart = 0;
	public SpeedController motor;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		upSensor = new DigitalInput(8);
		downSensor = new DigitalInput(3);
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
		countUp = upSensor.get();
		countDown = downSensor.get();
		try {
			//while (countDown && !countUp) {
			goUp();
			//}
			
		//	while (countUp && !countDown) {
			goDown();
		//	}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
		Timer.delay(50);
	}

	public void goUp() throws RuntimeException {
		System.out.println("in goUp: countDown " + countDown + " countUp " + countUp);
		
		countUp = upSensor.get();
		while (countUp && countDown) {
			
			countUp = upSensor.get();
			countDown = downSensor.get();
			System.out.println("in goUp: countDown " + countDown + " countUp " + countUp);
			motor.set(0.4);
		}
		motor.set(0);
		if (countUp) {
			throw new RuntimeException("wrong input triggered for goUp");
		}
		countUp = true;
	}

	public void goDown() throws RuntimeException {
		System.out.println("in goDown: countDown " + countDown + " countUp " + countUp);
		
		countDown = downSensor.get();
		while (countUp && countDown ) {
			countUp = upSensor.get();
			countDown = downSensor.get();
			System.out.println("in goDown: countDown " + countDown + " countUp " + countUp);
			motor.set(-0.4);
		}
		motor.set(0);
		if (countDown) {
			throw new RuntimeException("wrong input triggered for countDown");
		}
		countDown = true;
	}
}