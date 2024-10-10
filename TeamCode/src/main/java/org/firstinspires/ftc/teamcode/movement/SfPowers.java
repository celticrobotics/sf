package org.firstinspires.ftc.teamcode.movement;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class SfPowers {
    public SfPowers(double x_velocity, double y_velocity, double angular_velocity, HardwareMap map) {
        final double combined_velocity_factor = (SfConstants.DISTANCE_HALF_LATERAL_MECANUM + SfConstants.DISTANCE_HALF_VERTICAL_MECANUM) * angular_velocity;
        final double radius_scalar =  1 / SfConstants.MECANUM_WHEEL_RADIUS;

        FL_POWER = (((radius_scalar * (x_velocity - y_velocity - combined_velocity_factor)) * 60) / (2 * Math.PI)) / SfConstants.RPM_MECANUM_MOTOR;
        FR_POWER = (((radius_scalar * (x_velocity + y_velocity + combined_velocity_factor)) * 60) / (2 * Math.PI)) / SfConstants.RPM_MECANUM_MOTOR;
        BL_POWER = (((radius_scalar * (x_velocity + y_velocity - combined_velocity_factor)) * 60) / (2 * Math.PI)) / SfConstants.RPM_MECANUM_MOTOR;
        BR_POWER = (((radius_scalar * (x_velocity - y_velocity + combined_velocity_factor)) * 60) / (2 * Math.PI)) / SfConstants.RPM_MECANUM_MOTOR;

        FL = map.get(FL.getClass(), SfConstants.FRONT_LEFT_MOTOR_NAME);
        FR = map.get(FL.getClass(), SfConstants.FRONT_RIGHT_MOTOR_NAME);
        BL = map.get(FL.getClass(), SfConstants.BACK_LEFT_MOTOR_NAME);
        BR = map.get(FL.getClass(), SfConstants.BACK_RIGHT_MOTOR_NAME);

        FL.setDirection(DcMotorSimple.Direction.REVERSE);
        BL.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void power() throws InterruptedException {
        FL.setPower(FL_POWER);
        FR.setPower(FR_POWER);
        BL.setPower(BL_POWER);
        BR.setPower(BR_POWER);

        Thread.sleep(10);
    }

    DcMotorEx FL, FR, BL, BR;
    final double FL_POWER, FR_POWER, BL_POWER, BR_POWER;
}