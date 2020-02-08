/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * This is a demo program showing the use of the DifferentialDrive class.
 * Runs the motors with arcade steering.
 */
public class Robot extends TimedRobot {
  private final int BUTTON_A = 1;
  private final int BUTTON_B = 2;
  private final int BUTTON_X = 3;
  private final int BUTTON_Y = 4;
  private final int BUTTON_LT = 5;
  private final int BUTTON_RT = 6;
  private final int BUTTON_BACK = 7;
  private final int BUTTON_START = 8;
  private final int BUTTON_L_ANALOG = 9;
  private final int BUTTON_R_ANALOG = 10;

  private final Spark m_leftMotor = new Spark(0);
  private final Spark m_rightMotor = new Spark(1);
  private final Spark m_intake = new Spark(2);
  private final Spark m_shooter_left = new Spark(3);
  private final Spark m_shooter_right = new Spark(4);
  private final Spark m_colorWheelMotor = new Spark(5);
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);
  private final DifferentialDrive m_shooter = new DifferentialDrive(m_shooter_right, m_shooter_left);
  private final Joystick m_cStick = new Joystick(0);

  @Override
  public void robotInit() {
    System.out.println("Robot Init");
    super.robotInit();
  }

  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    m_robotDrive.arcadeDrive(m_cStick.getY(), m_cStick.getX());
    m_shooter.arcadeDrive(0, 0);
  }

  @Override
      public void autonomousPeriodic() {
  }
}
