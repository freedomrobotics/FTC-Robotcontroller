package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotorSimple

@TeleOp
class MecanumTeleOp : LinearOpMode() {
    @Throws(InterruptedException::class)
    override fun runOpMode() {
        // Declare our motors
        // Make sure your ID's match your configuration
        val motorFrontLeft = hardwareMap.dcMotor["motorFrontLeft"]
        val motorBackLeft = hardwareMap.dcMotor["motorBackLeft"]
        val motorFrontRight = hardwareMap.dcMotor["motorFrontRight"]
        val motorBackRight = hardwareMap.dcMotor["motorBackRight"]

        // Reverse the right side motors
        // Reverse left motors if you are using NeveRests
        motorFrontRight.direction = DcMotorSimple.Direction.REVERSE
        motorBackRight.direction = DcMotorSimple.Direction.REVERSE
        waitForStart()
        if (isStopRequested) return
        while (opModeIsActive()) {
            val y = -gamepad1.left_stick_y.toDouble() // Remember, this is reversed!
            val x = gamepad1.left_stick_x * 1.1 // Counteract imperfect strafing
            val rx = gamepad1.right_stick_x.toDouble()

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]
            val denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1.0)
            val frontLeftPower = (y + x + rx) / denominator
            val backLeftPower = (y - x + rx) / denominator
            val frontRightPower = (y - x - rx) / denominator
            val backRightPower = (y + x - rx) / denominator
            motorFrontLeft.power = frontLeftPower
            motorBackLeft.power = backLeftPower
            motorFrontRight.power = frontRightPower
            motorBackRight.power = backRightPower
        }
    }
}