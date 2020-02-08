/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
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
  private boolean m_isShooting;
  private Timer m_timer = new Timer();

  @Override
  public void robotInit() {
    System.out.println("Robot Init");
    super.robotInit();
    m_isShooting = false;
  }

  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    m_robotDrive.arcadeDrive(m_cStick.getY(), m_cStick.getX());

    shoot();
    
    if(m_cStick.getRawButton(BUTTON_B)) {
      double speed = 0.5;
      m_intake.set(speed);
    } else {
      m_intake.set(0);
    }

  }

  @Override
      public void autonomousPeriodic() {
        // TODO: Add code here for autonomous mode
  }

  public void shoot()
  {
    if( m_cStick.getRawButton(BUTTON_A) && !m_isShooting ) {
      m_isShooting = true;
      m_timer.start();
    }

    if( m_isShooting ) {
      double intake_speed = 0;
      if( m_timer.get() < 0.25 ) {
        // Initialize period, move intake back a smidge
        intake_speed = -0.25;
      } else {
        intake_speed = 0;
      }
      m_intake.set(intake_speed);

      double speed = 0.75;
      // NOTE: May need to make speed negative if shooter spins in opposite direction
      m_shooter.arcadeDrive(speed, 0);
    } else {
      m_shooter.arcadeDrive(0, 0);
    }

    if( m_timer.get() > 3.0 ) {
      m_isShooting = false;
      m_timer.stop();
      m_timer.reset();
    }
  }
}
