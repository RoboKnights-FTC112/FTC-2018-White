package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@TeleOp(name="BasicDriver", group="White Team")

public class ArmControl extends LinearOpMode {
    
    public DcMotor arm;
    public DcMotor rearLeft;
    public DcMotor rearRight;
    public DcMotor frontLeft;
    public DcMotor frontRight;
    
    @Override
    public void runOpMode() {

        telemetry.addData("Status", "We Ready");
        telemetry.update();

        waitForStart();

        //wheel motor variables
        
        DcMotor rearLeft = hardwareMap.get(DcMotor.class, "rearLeft");
        DcMotor rearRight = hardwareMap.get(DcMotor.class, "rearRight");
        DcMotor frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        DcMotor frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        

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
                telemetry.addData("Status", "Arm Up");
                telemetry.update();
            } else if(gamepad1.a==true){
                arm.setPower(-0.75);
                telemetry.addData("Status", "Arm Down");
                telemetry.update();
            } else {
                arm.setPower(0);
            }
            
            if(leftTrigger>0&&rightTrigger==0){
                rightTrigger=-leftTrigger;
            }
            
            double leftPower=-rightTrigger;
            double rightPower=rightTrigger;

            if(leftStickX>0){
                rightPower=rightPower*(1-2*(leftStickX+0.001));
            } else if(leftStickX<0){
                leftPower=leftPower*(1-2*(-leftStickX+0.001));
            }

            rearLeft.setPower(leftPower);
            frontLeft.setPower(leftPower);
            rearRight.setPower(rightPower);
            frontRight.setPower(rightPower);
        }

    }


}