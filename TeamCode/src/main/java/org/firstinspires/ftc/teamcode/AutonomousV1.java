package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Autonomous V1", group="White")

public class AutonomousV1 extends LinearOpMode{
    public DcMotor arm;
    public DcMotor rearLeft=null;
    public DcMotor rearRight=null;
    public DcMotor frontLeft=null;
    public DcMotor frontRight=null;
    
    public void turnleft90 (double speed) {
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontRight.setTargetPosition(2500);
        rearRight.setTargetPosition(2500);
        
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        rearRight.setPower(speed);
        frontRight.setPower(speed);
        
        while ((rearRight.isBusy()) && opModeIsActive()){
        }
        //leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    
    public void runOpMode(){
        telemetry.addData("Status", "Change");
        telemetry.update();
        
        //wheel motor variables
        rearLeft = hardwareMap.dcMotor.get("rearLeft");
        rearRight = hardwareMap.dcMotor.get("rearRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        //arm variable
        DcMotor arm = hardwareMap.get(DcMotor.class, "arm");

        waitForStart();
        
        while(opModeIsActive()){
            telemetry.addData("Status", "Running");
            telemetry.update();
            /*rearLeft.setPower(-.3);
            rearRight.setPower(.3);
            frontLeft.setPower(-.3);
            frontRight.setPower(.3);
            sleep(1000);*/
            turnleft90(.5);
            break;
        }
    }
    
    
}