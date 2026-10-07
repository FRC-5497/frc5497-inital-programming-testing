package frc.robot.swerve;

import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.robot.Config;

public class SwerveWheelModule {
	public final SparkMax driveMotor;
	public final SparkMax rotationMotor;

	public SwerveWheelModule(int driveMotorCanID, int rotationMotorCanID){
		this.driveMotor = new SparkMax(driveMotorCanID, SparkLowLevel.MotorType.kBrushless);
		this.rotationMotor = new SparkMax(rotationMotorCanID, SparkLowLevel.MotorType.kBrushless);
	}

	public void applySwerveModuleState(SwerveModuleState state){
		Rotation2d currentRotationMotorAngle = getRotationMotorAngle();

		// keeps the robot from going from 0 -> -270 and instead does 0 -> 90
		state = SwerveModuleState.optimize(state, currentRotationMotorAngle);

		applyDriveMotorVelocity(state.speedMetersPerSecond);
		applyRotationMotorVelocity(state.angle);
	}

	private void applyDriveMotorVelocity(double metersPerSecondSpeed){
		driveMotor.getClosedLoopController().setSetpoint(
			convertMetersPerSecondToRPM(metersPerSecondSpeed),
			SparkBase.ControlType.kVelocity
		);
	}
	public void applyRotationMotorVelocity(Rotation2d desiredRotation){
		rotationMotor.getClosedLoopController().setSetpoint(
				desiredRotation.getRotations() * Config.driveGearRatio,
				SparkBase.ControlType.kPosition
		);
	}

	private double convertMetersPerSecondToRPM(double metersPerSecond){
		double metersPerMinute = metersPerSecond * 60;

		return (metersPerMinute / Math.PI * inchesToMeters(Config.wheelDiameter));
	}

	private double inchesToMeters(double inches){
		return inches * 0.0254;
	}

	private Rotation2d getRotationMotorAngle() {
		double motorRotations = rotationMotor.getEncoder().getPosition();
		double wheelRotations = motorRotations / Config.driveGearRatio;

		return Rotation2d.fromRotations(wheelRotations);
	}
}