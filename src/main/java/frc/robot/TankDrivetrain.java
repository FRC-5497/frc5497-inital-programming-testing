package frc.robot;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

public class TankDrivetrain{
		private SparkMax topLeft;
		private SparkMax topRight;
		private SparkMax bottomLeft;
		private SparkMax bottomRight;

		public TankDrivetrain(int topLeftCanID, int topRightCanID, int bottomLeftCanID, int bottomRightCanID){
				topLeft = new SparkMax(topLeftCanID, MotorType.kBrushless);
				topRight = new SparkMax(topRightCanID, MotorType.kBrushless);
				bottomLeft = new SparkMax(bottomLeftCanID, MotorType.kBrushless);
				bottomRight = new SparkMax(bottomRightCanID, MotorType.kBrushless);
		}

		public void setLeftSpeed(double speed){
				topLeft.set(speed);
				bottomLeft.set(speed);
		}
		public void setRightSpeed(double speed){
				topRight.set(speed);
				bottomRight.set(speed);
		}
}