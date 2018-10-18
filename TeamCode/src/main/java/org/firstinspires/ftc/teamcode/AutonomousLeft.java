package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous

public class AutonomousLeft extends LinearOpMode{
    public DcMotor arm;
    public DcMotor rearLeft;
    public DcMotor rearRight;
    public DcMotor frontLeft;
    public DcMotor frontRight;
    
    public Servo plowOne;
    public Servo plowTwo;
    public DcMotor plowMotor;
    
    public void runOpMode() {

        telemetry.addData("Status", "AutoBot Ready");
        telemetry.update();

        waitForStart();

        //wheel motor variables
        rearLeft = hardwareMap.get(DcMotor.class, "rearLeft");
        rearRight = hardwareMap.get(DcMotor.class, "rearRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        
        //plow servos
        plowOne = hardwareMap.servo.get("plowOne");
        plowTwo = hardwareMap.servo.get("plowTwo");
        
        plowMotor=hardwareMap.dcMotor.get("plowMotor");
        
        //arm motor
        DcMotor arm = hardwareMap.get(DcMotor.class, "arm");

        if(opModeIsActive()){
            plowOne.setPosition(.5);
            driveForward(0.4,1500);
            moveArm(true);
            sleep(900);
            plowOne.setPosition(1);
            moveArm(false);
            turnRight(0.4,700);
            driveForward(1,3000);
        }
    }
    
    public void driveForward(double speed, double time){
        rearLeft.setPower(-speed);
        rearRight.setPower(speed);
        frontLeft.setPower(-speed);
        frontRight.setPower(speed);
        sleep((long)time);
        rearLeft.setPower(0);
        rearRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        
    }
    public void moveArm(boolean pos){
        if(pos){
            plowMotor.setPower(-0.6);
            sleep(400);
        } else {
            plowMotor.setPower(0.6);
            sleep(650);
        }
        plowMotor.setPower(0);
    }
    public void turnRight(double speed, int time){
        rearLeft.setPower(-speed);
        rearRight.setPower(-speed);
        frontLeft.setPower(-speed);
        frontRight.setPower(-speed);
        sleep(time);
        rearLeft.setPower(0);
        rearRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
    }
}