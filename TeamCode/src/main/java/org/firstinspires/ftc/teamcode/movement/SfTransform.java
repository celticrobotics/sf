package org.firstinspires.ftc.teamcode.movement;

public class SfTransform {
    public double x_position;
    public double y_position;
    public double heading;

    public SfTransform(double x_position, double y_position, double heading) {
        this.x_position = x_position;
        this.y_position = y_position;
        this.heading = heading;
    }

    public static boolean equals(SfTransform t1, SfTransform t2, double tolerance) {
        return Math.abs(t1.x_position - t2.x_position) <= tolerance &&
               Math.abs(t1.y_position - t2.y_position) <= tolerance &&
                Math.abs(t1.heading - t2.heading) <= tolerance;
    }
}