package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous

public class AutonomousLeft extends LinearOpMode{
    public DcMotor rearLeft;
    public DcMotor rearRight;
    public DcMotor frontLeft;
    public DcMotor frontRight;
    
    public Servo armOne;
    public Servo plowTwo;
    public DcMotor armMotor;
    
    public void runOpMode() {

        telemetry.addData("Status", "AutoBot Ready");
        telemetry.update();

        waitForStart();

        //wheel motor variables
        rearLeft = hardwareMap.get(DcMotor.class, "rearLeft");
        rearRight = hardwareMap.get(DcMotor.class, "rearRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        
        //arm servos
        armOne = hardwareMap.servo.get("plowOne");
        
        //arm motor
        armMotor=hardwareMap.dcMotor.get("plowMotor");


        if(opModeIsActive()){
            armOne.setPosition(.5);
            driveForward(0.4,1500);
            moveArm(true);
            sleep(900);
            armOne.setPosition(1);
            moveArm(false);
            turnRight(0.4,700);
            driveForward(1,3000);
        }
    }


    public void driveForward(double speed, int time){
        rearLeft.setPower(-speed);
        rearRight.setPower(speed);
        frontLeft.setPower(-speed);
        frontRight.setPower(speed);
        sleep(time);
        rearLeft.setPower(0);
        rearRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
    }
    public void driveBackwards(double speed, int time){
        rearLeft.setPower(speed);
        rearRight.setPower(-speed);
        frontLeft.setPower(speed);
        frontRight.setPower(-speed);
        sleep(time);
        rearLeft.setPower(0);
        rearRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
    }
    public void moveArm(boolean pos){
        if(pos){
            armMotor.setPower(-0.6);
            sleep(400);
        } else {
            armMotor.setPower(0.6);
            sleep(650);
        }
        armMotor.setPower(0);
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

    public void turnLeft(double speed, int time){
        rearLeft.setPower(speed);
        rearRight.setPower(speed);
        frontLeft.setPower(speed);
        frontRight.setPower(speed);
        sleep(time);
        rearLeft.setPower(0);
        rearRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
    }
}