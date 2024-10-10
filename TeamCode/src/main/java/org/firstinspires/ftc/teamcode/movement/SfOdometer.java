package org.firstinspires.ftc.teamcode.movement;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class SfOdometer {
    public SfOdometer(HardwareMap map, String name, boolean reverse) {
        encoder = map.get(DcMotorEx.class, name);

        if (reverse) { encoder.setDirection(DcMotorSimple.Direction.REVERSE); }
    }

    public double get_change() {
        final double current_position = encoder.getCurrentPosition();
        final double result = current_position - previous_position;
        previous_position = current_position;

        return result;
    }

    public static final double TICKS_TO_METRES_MULTIPLIER = 2 * Math.PI * SfConstants.ENCODER_WHEEL_RADIUS / SfConstants.ENCODER_TICKS_PER_REVOLUTION;
    public static final double TICKS_TO_RADIANS_MULTIPLIER = 2 * Math.PI * TICKS_TO_METRES_MULTIPLIER / SfConstants.DISTANCE_BETWEEN_MIDDLE_BACK_ENCODER;

    private final DcMotorEx encoder;
    private double previous_position = 0;
}