package frc.robot;

public class Config {
		public static int controllerPortIndex = 0;

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
}
