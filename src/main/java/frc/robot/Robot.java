// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {
	private PS4Controller controller;
	private TankDrivetrain drivetrain;

  /** Called once at the beginning of the robot program. */
  public Robot() {
			controller = new PS4Controller(Config.controllerPortIndex);
			drivetrain = new TankDrivetrain(
					Config.CanID.DriveMotor.topLeft,
					Config.CanID.DriveMotor.topRight,
					Config.CanID.DriveMotor.bottomLeft,
					Config.CanID.DriveMotor.bottomRight
			);
  }

  @Override
  public void teleopPeriodic() {
			double leftSpeed = controller.getLeftY();
			double rightSpeed = controller.getRightY();

			drivetrain.setLeftSpeed(leftSpeed);
			drivetrain.setRightSpeed(rightSpeed);
  }
}
