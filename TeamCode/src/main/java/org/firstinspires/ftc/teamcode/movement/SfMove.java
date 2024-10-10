package org.firstinspires.ftc.teamcode.movement;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class SfMove {
    public SfMove(HardwareMap map) {
        odometry = new SfOdometry(map);

        controller = new SfPID(SfConstants.PX, SfConstants.IX, SfConstants.DX,
                               SfConstants.PY, SfConstants.IY, SfConstants.DY,
                               SfConstants.PH, SfConstants.IH, SfConstants.DH,
                               map, () -> odometry.get_position());
    }

    public void move(SfTransform destination) throws InterruptedException {
        controller.move(destination);
    }

    public void update() {
        odometry.get_position();
    }

    private SfOdometry odometry;
    private SfPID controller;
}
