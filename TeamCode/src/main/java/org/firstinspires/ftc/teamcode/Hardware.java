package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

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

    // Declare servos
    public Servo largeClawArmServo;
    public Servo flagServo;
    public Servo smallClawServo;
    public Servo smallClawArmServo;




    public Hardware(HardwareMap hardwareMap){

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        rearLeft = hardwareMap.get(DcMotor.class, "rearLeft");
        rearRight = hardwareMap.get(DcMotor.class, "rearRight");

        ejectionMotor = hardwareMap.get(DcMotor.class, "ejectionMotor");
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        liftMotor = hardwareMap.get(DcMotor.class, "liftMotor");
        beltMotor = hardwareMap.get(DcMotor.class, "beltMotor");

        largeClawArmServo = hardwareMap.get(Servo.class,"largeClawArmServo");
        flagServo = hardwareMap.get(Servo.class,"flagServo");
        smallClawArmServo = hardwareMap.get(Servo.class,"smallClawArmServo");
        smallClawServo = hardwareMap.get(Servo.class, "smallClawServo");
    }

}
