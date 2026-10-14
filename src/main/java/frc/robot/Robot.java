// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.Percent;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.swerve.SwerveDrivetrainController;

public class Robot extends TimedRobot {
	private PS4Controller controller;
	private SwerveDrivetrainController drivetrain;

  /** Called once at the beginning of the robot program. */
  public Robot() {
			controller = new PS4Controller(Config.controllerPortIndex); 
			drivetrain = new SwerveDrivetrainController();
			 
  }

  @Override
  public void teleopPeriodic() {
			double speedX = -controller.getLeftY(); // Reads joystick inputs fo speedX
			double speedY = controller.getLeftX(); // Reads joystick input for speedY
			double rotation = controller.getRightX(); // Reads joystick input for rotation
			boolean resetWheelPositions = controller.getR1Button(); // Bool to check if R1 is pressed to reset wheels

			if(resetWheelPositions){
				drivetrain.resetWheels(); // If R1 is press reset the wheels 
				return;
			}

			double speedLimit = 1;
			speedX = MathUtil.clamp(controller.getLeftY(), -speedLimit, speedLimit); // Sets the speed limit for the X and Y movement
			speedY = MathUtil.clamp(controller.getLeftX(), -speedLimit, speedLimit);

			drivetrain.drive(speedX, speedY, rotation); // sends the inputs to the drivetrain to move the robot
  }
}
