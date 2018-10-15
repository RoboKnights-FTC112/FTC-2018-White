package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@TeleOp(name="BasicDriver", group="White")

public class BasicDriver extends LinearOpMode {
    
    public DcMotor arm;
    public DcMotor rearLeft;
    public DcMotor rearRight;
    public DcMotor frontLeft;
    public DcMotor frontRight;
    
    public Servo plowOne;
    public Servo plowTwo;
    public DcMotor plowMotor;
    
    @Override
    public void runOpMode() {

        telemetry.addData("Status", "We Ready");
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

        //gamepad variables





        while (opModeIsActive()){
            
            double rightTrigger = gamepad1.right_trigger;
            double leftTrigger = gamepad1.left_trigger;
            double leftStickX = gamepad1.left_stick_x;
            double rightStickY = gamepad1.right_stick_y;
            
            boolean y = gamepad1.y;
            boolean a = gamepad1.a;
            
            if(gamepad1.y==true){
                arm.setPower(0.75);
            } else if(gamepad1.a==true){
                arm.setPower(-0.75);
            } else {
                arm.setPower(0);
            }
            
            if(gamepad1.x==true){
                //plowOne.setPosition(0);
                plowTwo.setPosition(1);
            } else if(gamepad1.b==true){
                //plowOne.setPosition(1);
                plowTwo.setPosition(0);
            }
            
            while(gamepad1.dpad_up){
                plowMotor.setPower(.5);
            }
            while(gamepad1.dpad_down){
                plowMotor.setPower(-.5);
            }
            plowMotor.setPower(0);
            if(leftTrigger>0&&rightTrigger==0){
                rightTrigger=-leftTrigger;
            }
            
            double leftPower=-rightTrigger;
            double rightPower=rightTrigger;

            if(leftStickX>0){
                rightPower=rightPower*(1-3*(leftStickX+0.001));
                telemetry.addData("rightPower", rightPower);
                telemetry.update();
            } else if(leftStickX<0){
                leftPower=leftPower*(1-3*(-leftStickX+0.001));
                telemetry.addData("leftPower", leftPower);
                telemetry.update();
            }

            rearLeft.setPower(leftPower);
            frontLeft.setPower(leftPower);
            rearRight.setPower(rightPower);
            frontRight.setPower(rightPower);
        }
    }
    

}