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
	double motorSpeed;
	DigitalInput upSensor, downSensor;
	double value1 = 0;
	double value2 = 0;
	int runtimes = 0;
	int bart = 0;
	public SpeedController motor;
	public Potentiometer pot;
	double [] intervals = {0.04168986652866695, 0.1246882843, 0.2076867022, 0.2906851201, 0.37368353790338404};
	double deltaI = 0.025;
	
//plz do gud 
	/**be
	 * used for any initialization code.
	 */
	public void robotInit() {
		pot = new AnalogPotentiometer(0,1,0);
		
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
				System.out.println("potValue at top: " + pot.get());
				
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
				System.out.println("potValue at bottom: " + pot.get());
			}
			
		});
		downSensor.enableInterrupts();
		motor = new Talon(4); // initialize the motor as a Talon on channel 0
		if (intervals[0] - deltaI <= pot.get() <= intervals[0] + deltaI){
			
		}

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

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		System.out.println("In testPerioding");
		if (pot.get() < .2 && pot.get() > .17){
			//.2076867022
			motorSpeed *= 0;
		}
		// goUp = upSensor.get();
		// goDown = downSensor.get();
		try {
			motor.set(motorSpeed);
			System.out.println("potValue" + pot.get());
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
		Timer.delay(0.1);
	}
}