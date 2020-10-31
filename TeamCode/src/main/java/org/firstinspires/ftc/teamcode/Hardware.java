package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Hardware {

    // Declare drivetrain motors
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor rearLeft;
    public DcMotor rearRight;

    // Declare other motors
    public DcMotor ejectionMotor;
    public DcMotor intakeMotor;
    public DcMotor liftMotor;
    public DcMotor beltMotor;

    public Hardware(HardwareMap hardwareMap){

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        rearLeft = hardwareMap.get(DcMotor.class, "rearLeft");
        rearRight = hardwareMap.get(DcMotor.class, "rearRight");

    }

}
