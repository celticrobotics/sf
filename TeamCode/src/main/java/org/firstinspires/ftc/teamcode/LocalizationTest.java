package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.movement.SfMove;
import org.firstinspires.ftc.teamcode.movement.SfTransform;

import java.nio.ByteBuffer;

@TeleOp(name="Localization Text")
public class LocalizationTest extends LinearOpMode {
    enum Type {
        X_TEST,
        Y_TEST,
        HEADING_TEST,
    }

    Type test_type = Type.HEADING_TEST;







    @Override public void runOpMode() throws InterruptedException {
        SfMove mover = new SfMove(hardwareMap);

        waitForStart();

        telemetry.addLine("This is a Localization test, you have currently selected the " + test_type.toString() + ". To " +
                          "continue with the test, press any key on any controller.");
        updateTelemetry(telemetry);

        while (ByteBuffer.wrap(gamepad1.toByteArray()).getInt() == 0 && ByteBuffer.wrap(gamepad1.toByteArray()).getInt() == 0) {
            idle();
        }

        switch (test_type) {
            case X_TEST:            mover.move(new SfTransform(1, 0, 0));
            case Y_TEST:            mover.move(new SfTransform(0, 1, 0));
            case HEADING_TEST:      mover.move(new SfTransform(0, 0, Math.toRadians(180)));
        }
    }
}
