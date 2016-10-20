
package org.usfirst.frc.team3729.robot.commands;

import edu.wpi.first.wpilibj.Talon;

public class mechenumDrive {

	Talon RightMotor1, LeftMotor1, RightMotor2, LeftMotor2;
	XboxControler _xbox;

	public mechenumDrive(XboxControler xbox) {
		RightMotor1 = new Talon(2);
		RightMotor2 = new Talon(3);
		LeftMotor1 = new Talon(1);
		LeftMotor2 = new Talon(0);
		this._xbox = xbox;
	}

	public void mechenumDrive() {
		boolean leftInput = _xbox.GetLeftBumper();
		boolean rightInput = _xbox.GetRightBumper();

		if (leftInput == true) {
RightMotor1.set(0.5);
RightMotor2.set(0.5);
LeftMotor1.set(-0.5);
LeftMotor2.set(-0.5);
		} else if (rightInput == true) {

		} else {

		}

	}

}
