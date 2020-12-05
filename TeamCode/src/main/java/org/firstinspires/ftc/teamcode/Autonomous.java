/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="Autonomous", group="Iterative Opmode")
//@Disabled
public class Autonomous extends OpMode
{

    Hardware hardware;

    //set initial robot location here in inches(center of field is origin)
    float[] robotLocation = {-40, -50};

    float[] frontLeftLocation;
    float[] frontRightLocation;
    float[] rearLeftLocation;
    float[] rearRightLocation;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        hardware = new Hardware(hardwareMap);

    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {

    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {



    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

    private void moveTo(float[] targetLocation){

        float xDirMovement = targetLocation[0] - robotLocation[0];
        float yDirMovement = targetLocation[1] - robotLocation[1];

        // Turn On RUN_TO_POSITION
        hardware.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardware.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardware.rearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardware.rearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // move forward/backward
        hardware.frontLeft.setTargetPosition((int) xDirMovement);
        hardware.frontRight.setTargetPosition((int) xDirMovement);
        hardware.rearLeft.setTargetPosition((int) xDirMovement);
        hardware.rearRight.setTargetPosition((int) xDirMovement);

        hardware.frontLeft.setPower(1);
        hardware.frontRight.setPower(1);
        hardware.rearLeft.setPower(1);
        hardware.rearRight.setPower(1);

        while (
                (hardware.frontLeft.isBusy()
                        && hardware.frontRight.isBusy()
                        && hardware.rearLeft.isBusy()
                        && hardware.rearRight.isBusy())) {

            // Display it for the driver.
            telemetry.addData("Path2",  "Running at %7d :%7d",
                    hardware.frontLeft.getCurrentPosition(),
                    hardware.frontRight.getCurrentPosition());
            telemetry.update();
        }

        hardware.frontLeft.setPower(0);
        hardware.frontRight.setPower(0);
        hardware.rearLeft.setPower(0);
        hardware.rearRight.setPower(0);

        //rotate
        hardware.frontLeft.setTargetPosition(12);
        hardware.rearLeft.setTargetPosition(-12);

        hardware.frontLeft.setPower(1);
        hardware.frontRight.setPower(1);
        hardware.rearLeft.setPower(1);
        hardware.rearRight.setPower(1);

        while (
                (hardware.frontLeft.isBusy()
                        && hardware.frontRight.isBusy()
                        && hardware.rearLeft.isBusy()
                        && hardware.rearRight.isBusy())) {

            // Display it for the driver.
            telemetry.addData("Path2",  "Running at %7d :%7d",
                    hardware.frontLeft.getCurrentPosition(),
                    hardware.frontRight.getCurrentPosition());
            telemetry.update();
        }

        hardware.frontLeft.setPower(0);
        hardware.frontRight.setPower(0);
        hardware.rearLeft.setPower(0);
        hardware.rearRight.setPower(0);

        // move left/right
        // move forward/backward
        hardware.frontLeft.setTargetPosition((int) yDirMovement);
        hardware.frontRight.setTargetPosition((int) yDirMovement);
        hardware.rearLeft.setTargetPosition((int) yDirMovement);
        hardware.rearRight.setTargetPosition((int) yDirMovement);

        hardware.frontLeft.setPower(1);
        hardware.frontRight.setPower(1);
        hardware.rearLeft.setPower(1);
        hardware.rearRight.setPower(1);

        while (
                (hardware.frontLeft.isBusy()
                        && hardware.frontRight.isBusy()
                        && hardware.rearLeft.isBusy()
                        && hardware.rearRight.isBusy())) {

            // Display it for the driver.
            telemetry.addData("Path2",  "Running at %7d :%7d",
                    hardware.frontLeft.getCurrentPosition(),
                    hardware.frontRight.getCurrentPosition());
            telemetry.update();
        }

        hardware.frontLeft.setPower(0);
        hardware.frontRight.setPower(0);
        hardware.rearLeft.setPower(0);
        hardware.rearRight.setPower(0);

        robotLocation = targetLocation;

    }

}
