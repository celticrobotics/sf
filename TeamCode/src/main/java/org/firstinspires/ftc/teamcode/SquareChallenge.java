package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.movement.SfMove;
import org.firstinspires.ftc.teamcode.movement.SfTransform;

@TeleOp(name="take this l")
public class SquareChallenge extends LinearOpMode {
    @Override  public void runOpMode() throws InterruptedException {
        SfMove mover = new SfMove(hardwareMap);

        waitForStart();

        mover.move(new SfTransform(2, 2, Math.toRadians(90)));
    }
}