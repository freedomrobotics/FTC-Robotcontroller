package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import kotlin.math.abs

/**
 * This is an example minimal implementation of the mecanum drivetrain
 * for demonstration purposes.  Not tested and not guaranteed to be bug free.
 *
 * @author Brandon Gong
 */
@TeleOp(name = "Mecanum Drive Example", group = "Iterative Opmode")
class MecanumDrive : OpMode() {
    /*
     * The mecanum drivetrain involves four separate motors that spin in
     * different directions and different speeds to produce the desired
     * movement at the desired speed.
     */
    // declare and initialize four DcMotors.
    private var front_left: DcMotor? = null
    private var front_right: DcMotor? = null
    private var back_left: DcMotor? = null
    private var back_right: DcMotor? = null
    var front_left_mod = 1.0
    var front_right_mod = 1.0
    var back_left_mod = 1.0
    var back_right_mod = 1.0
    var active = 0


    override fun init() {
        // Name strings must match up with the config on the Robot Controller
        // app.
        front_left = hardwareMap.get(DcMotor::class.java, "motorFrontLeft")
        front_right = hardwareMap.get(DcMotor::class.java, "motorFrontRight")
        back_left = hardwareMap.get(DcMotor::class.java, "motorBackLeft")
        back_right = hardwareMap.get(DcMotor::class.java, "motorBackRight")
    }

    override fun loop() {
        // Mecanum drive is controlled with three axes: drive (front-and-back),
        // strafe (left-and-right), and twist (rotating the whole chassis).
        val drive = gamepad1.left_stick_y.toDouble()
        val strafe = gamepad1.left_stick_x.toDouble()
        val twist = gamepad1.right_stick_x.toDouble()
        val change_left = gamepad1.dpad_left
        val change_right = gamepad1.dpad_right
        val change_pos = gamepad1.dpad_up
        val change_neg = gamepad1.dpad_down

        if (change_left) {
            if (active == 0) {
                active = 3
            } else {
                active -= 1
            }
        }

        if (change_right) {
            if (active == 3) {
                active = 0
            } else {
                active += 1
            }
        }

        telemetry.addData("Active", if (active == 0) "Front Left" else if (active == 1) "Front Right" else if (active == 2) "Back Left" else "Back Right")
        if (change_pos) {
            when (active) {
                0 -> {
                    front_left_mod += 0.01
                }
                1 -> {
                    front_right_mod += 0.01
                }
                2 -> {
                    back_right_mod += 0.01
                }
                3 -> {
                    back_left_mod += 0.01
                }
            }
        }
        if (change_neg) {
            when (active) {
                0 -> {
                    front_left_mod -= 0.01

                }
                1 -> {
                    front_right_mod -= 0.01

                }
                2 -> {
                    back_right_mod -= 0.01

                }
                3 -> {
                    back_left_mod -= 0.01

                }
            }
        }

        // Telemetry for the values to modify motor power
        run {
            telemetry.addData("front_left_mod", "Run Time: $front_left_mod")
            telemetry.addData("front_right_mod", "Run Time: $front_right_mod")
            telemetry.addData("back_right_mod", "Run Time: $back_right_mod")
            telemetry.addData("back_left_mod", "Run Time: $back_left_mod")
        }

        // You may need to multiply some of these by -1 to invert direction of
        // the motor.  This is not an issue with the calculations themselves.
        val speeds = doubleArrayOf(
            (drive + strafe + twist) * front_left_mod,
            drive - strafe - twist * front_right_mod,
            (drive - strafe + twist * -1) * back_left_mod,
            (drive + strafe - twist * -1) * back_right_mod
        )

        // Because we are adding vectors and motors only take values between
        // [-1,1] we may need to normalize them.

        // Loop through all values in the speeds[] array and find the greatest
        // *magnitude*.  Not the greatest velocity.
        var max = abs(speeds[0])
        for (i in speeds.indices) {
            if (max < abs(speeds[i])) max = abs(speeds[i])
        }

        // If and only if the maximum is outside of the range we want it to be,
        // normalize all the other speeds based on the given speed value.
        if (max > 1) {
            for (i in speeds.indices) speeds[i] /= max
        }

        // apply the calculated values to the motors.
        front_left!!.power = speeds[0]
        front_right!!.power = speeds[1]
        back_left!!.power = speeds[2]
        back_right!!.power = speeds[3]
    }
}