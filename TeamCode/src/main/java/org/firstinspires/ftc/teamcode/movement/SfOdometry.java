package org.firstinspires.ftc.teamcode.movement;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class SfOdometry {
    public SfOdometry(HardwareMap map) {
        LO = new SfOdometer(map, SfConstants.LEFT_ODOMETER_NAME, SfConstants.REVERSE_LEFT_ENCODER);
        RO = new SfOdometer(map, SfConstants.RIGHT_ODOMETER_NAME, SfConstants.REVERSE_RIGHT_ENCODER);
        MO = new SfOdometer(map, SfConstants.MIDDLE_ODOMETER_NAME, SfConstants.REVERSE_MIDDLE_ENCODER);
    }

    public SfTransform get_position() {
        final double LO_CHANGE = LO.get_change();
        final double RO_CHANGE = RO.get_change();
        final double MO_CHANGE = MO.get_change();

        final double C_THETA = (RO_CHANGE - LO_CHANGE) / (2 * SfConstants.DISTANCE_BETWEEN_OUTSIDE_ENCODER_MIDDLE);
        final double T_R = (SfConstants.DISTANCE_BETWEEN_OUTSIDE_ENCODER_MIDDLE * (LO_CHANGE + RO_CHANGE)) / (RO_CHANGE - LO_CHANGE);
        final double T_F = (MO_CHANGE) / (C_THETA) - SfConstants.DISTANCE_BETWEEN_MIDDLE_BACK_ENCODER;

        if (C_THETA != 0) {
            position.x_position += SfOdometer.TICKS_TO_METRES_MULTIPLIER * (T_R * (Math.cos(C_THETA) - 1) + T_F *      Math.sin(C_THETA));
            position.y_position += SfOdometer.TICKS_TO_METRES_MULTIPLIER * (T_R *  Math.sin(C_THETA)      + T_F * (1 - Math.sin(C_THETA)));
        } else {
            position.x_position += SfOdometer.TICKS_TO_METRES_MULTIPLIER * MO_CHANGE;
            position.y_position += SfOdometer.TICKS_TO_METRES_MULTIPLIER * ((LO_CHANGE + RO_CHANGE) / 2);
        }

        position.heading += C_THETA;

        return position;
    }

    private final SfTransform position = SfConstants.START_POSITION;
    private final SfOdometer LO, RO, MO;
}