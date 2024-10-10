package org.firstinspires.ftc.teamcode.movement;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class SfPID {
    public SfPID(double Px, double Ix, double Dx,
                 double Py, double Iy, double Dy,
                 double Ph, double Ih, double Dh,
                 HardwareMap map, Supplier<SfTransform> get_function
    ) {
        this.Px = Px;   this.Py = Py;   this.Ph = Ph;
        this.Ix = Ix;   this.Iy = Iy;   this.Ih = Ih;
        this.Dx = Dx;   this.Dy = Dy;   this.Dh = Dh;

        this.map = map;

        this.get_function = get_function;
    }

    public void move(SfTransform target_position) throws InterruptedException {
        ElapsedTime stopwatch = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        SfTransform current_position = get_function.get();
        SfTransform previous_error   = new SfTransform(0, 0, 0);
        SfTransform running_error    = new SfTransform(0, 0, 0);

        while (!SfTransform.equals(current_position, target_position, SfConstants.PID_TOLERANCE)) {
            current_position = get_function.get();

            final double current_error_x = target_position.x_position - current_position.x_position;
            final double current_error_y = target_position.y_position - current_position.y_position;
            final double current_error_heading = target_position.heading - current_position.heading;

            final double dt = stopwatch.time(TimeUnit.SECONDS);  stopwatch.reset();

            running_error.x_position += current_error_x * dt;
            running_error.y_position += current_error_y * dt;
            running_error.heading += current_error_heading * dt;

            final double x_output = Px * current_error_x + Ix * running_error.x_position + Dx * ((current_error_x - previous_error.x_position) / (dt));
            final double y_output = Py * current_error_y + Iy * running_error.y_position + Dy * ((current_error_y - previous_error.y_position) / (dt));
            final double heading_output = Ph * current_error_heading + Ih * running_error.heading + Dh * ((current_error_heading - previous_error.heading) / (dt));

            final double new_x_output = x_output * Math.cos(-current_position.heading) - y_output * Math.sin(-current_position.heading);
            final double new_y_output = x_output * Math.sin(-current_position.heading) + y_output * Math.cos(-current_position.heading);

            SfPowers velocities = new SfPowers(new_x_output, new_y_output, heading_output, map);
            velocities.power();

            previous_error = new SfTransform(current_error_x, current_error_y, current_error_heading);
        }
    }

    double Px, Ix, Dx;
    double Py, Iy, Dy;
    double Ph, Ih, Dh;
    Supplier<SfTransform> get_function;
    HardwareMap map;
}