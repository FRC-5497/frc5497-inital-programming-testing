package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;

public class Config {
		public static final int controllerPortIndex = 0;
		public static final double maximumMotorSpeedCapability = 4.5; // m/s
		public static final int wheelDiameter = 3; // inches
		public static final double driveGearRatio = 1;
		public static final int rotationMoterEncoderTicks = 42;

		public static class WheelPositions{
				// each translation is relative to the robots center
				// measurements are in meters here
				public static final Translation2d topLeft = new Translation2d(-inchesToMeters(13), inchesToMeters(13));
				public static final Translation2d topRight = new Translation2d(inchesToMeters(13), inchesToMeters(13));
				public static final Translation2d bottomLeft = new Translation2d(-inchesToMeters(13), -inchesToMeters(13));
				public static final Translation2d bottomRight = new Translation2d(-inchesToMeters(13), inchesToMeters(13));
		}

		public static class CanID{
				public static class DriveMotor{
						public static final int topLeft = 15;
						public static final int topRight = 7;
						public static final int bottomLeft = 13;
						public static final int bottomRight = 17;
				}
				public static class RotationMotor{
						public static final int topLeft = 16;
						public static final int topRight = 8;
						public static final int bottomLeft = 14;
						public static final int bottomRight = 18;
				}
		}
		private static double inchesToMeters(double inches){
			return inches * 0.0254;
		}
}