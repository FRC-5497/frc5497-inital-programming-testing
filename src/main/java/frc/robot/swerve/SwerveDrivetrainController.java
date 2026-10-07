package frc.robot.swerve;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.Config;
import com.studica.frc.AHRS;

public class SwerveDrivetrainController {
		private AHRS gyroscope;
		// wheel modules
		private SwerveWheelModule topLeft;
		private SwerveWheelModule topRight;
		private SwerveWheelModule bottomLeft;
		private SwerveWheelModule bottomRight;

		// used to create swerve functions without me having to do it manually (thank god)
		SwerveDriveKinematics kinematics;

		public SwerveDrivetrainController() {
				// initialize the navX-MXP on the MXP SPI port
				gyroscope = new AHRS(AHRS.NavXComType.kMXP_SPI);

				// create all of the wheel modules
				topLeft = new SwerveWheelModule(Config.CanID.DriveMotor.topLeft, Config.CanID.RotationMotor.topLeft);
				topRight = new SwerveWheelModule(Config.CanID.DriveMotor.topRight, Config.CanID.RotationMotor.topRight);
				bottomLeft = new SwerveWheelModule(Config.CanID.DriveMotor.bottomLeft, Config.CanID.RotationMotor.bottomLeft);
				bottomRight = new SwerveWheelModule(Config.CanID.DriveMotor.bottomRight, Config.CanID.RotationMotor.bottomRight);

				// set wheel positions in kinematic so it can do math magic
				kinematics = new SwerveDriveKinematics(
						Config.WheelPositions.topLeft,
						Config.WheelPositions.topRight,
						Config.WheelPositions.bottomLeft,
						Config.WheelPositions.bottomRight
				);
		}
		

		public void drive(double speedX, double speedY, double rotation){
				// get the required speeds for every wheel module
				ChassisSpeeds speeds = new ChassisSpeeds(speedX, speedY, rotation);

				// create their state
				SwerveModuleState[] states = kinematics.toSwerveModuleStates(speeds);

				/*
				cap all speeds to the maximum motor speed capacity so it doesnt try to go faster than it can.
				this prevents the motors from attempting to go faster than possible as it could change the direction.
				ex. max = 1.5
				tl: 1.3
				tr: 1.1
				bl: 2.3 - this will result in undesired movement as the motor can not attain this velocity
				br: 1.4
				*/
				SwerveDriveKinematics.desaturateWheelSpeeds(
						states,
						Config.maximumMotorSpeedCapability
				);

				this.topLeft.applySwerveModuleState(states[0]);
				this.topRight.applySwerveModuleState(states[1]);
				this.bottomLeft.applySwerveModuleState(states[2]);
				this.bottomRight.applySwerveModuleState(states[3]);
		}

		public void resetWheels(){
			this.topLeft.applyRotationMotorVelocity(new Rotation2d(0));;
			this.topRight.applyRotationMotorVelocity(new Rotation2d(0));;
			this.bottomLeft.applyRotationMotorVelocity(new Rotation2d(0));;
			this.bottomRight.applyRotationMotorVelocity(new Rotation2d(0));;
		}
}