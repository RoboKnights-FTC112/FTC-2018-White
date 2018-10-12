package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@TeleOp(name="BasicDriver", group="White Team")

public class ArmControl extends LinearOpMode {
    
    public DcMotor arm = null;
    
    @Override
    public void runOpMode() {

        telemetry.addData("Status", "We Ready");
        telemetry.update();

        waitForStart();

        //wheel motor variables
        /*
        private DcMotor rearLeft = hardwareMap.get(DcMotor.class, "rearLeft");
        private DcMotor rearRight = hardwareMap.get(DcMotor.class, "rearRight");
        private DcMotor frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        private DcMotor frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        */

        //arm motor
        DcMotor arm = hardwareMap.dcMotor.get("arm");

        //gamepad variables
        float rightTrigger = gamepad1.right_trigger;
        float leftTrigger = gamepad1.left_trigger;

        float leftStickX = gamepad1.left_stick_x;

        float rightStickY = gamepad1.right_stick_y;



        while (opModeIsActive()){
            if(rightTrigger!=0){
                telemetry.addData("Status", "Right Trigger Pressed");
                telemetry.update();
            }
            //check if robot is moving forward & turning left or turning right
            /*
            if(rightTrigger>0&&leftStickX<0&&opModeIsActive()){
                //set power for non-turning side wheels
                rearRight.setPower(rightTrigger);
                frontRight.setPower(rightTrigger);
                //set power for turning side wheels
                rearLeft.setPower(rightTrigger-Math.abs(leftStickX));
                frontLeft.setPower(rightTrigger-Math.abs(leftStickX));
            } else if(rightTrigger>0&&leftStickX>0&&opModeIsActive()){
                //set power for non-turning side wheels
                rearLeft.setPower(rightTrigger);
                frontLeft.setPower(rightTrigger);
                //set power for turning side wheels
                rearRight.setPower(rightTrigger-Math.abs(leftStickX));
                frontRight.setPower(rightTrigger-Math.abs(leftStickX));
            }

            if(rightTrigger<0&&leftStickX<0&&opModeIsActive()){
                //set power for non-turning side wheels
                rearRight.setPower(-rightTrigger);
                frontRight.setPower(-rightTrigger);
                //set power for turning side wheels
                rearLeft.setPower(-rightTrigger+Math.abs(leftStickX));
                frontLeft.setPower(-rightTrigger+Math.abs(leftStickX));
            } else if(rightTrigger<0&&leftStickX>0&&opModeIsActive()){
                //set power for non-turning side wheels
                rearLeft.setPower(-rightTrigger);
                frontLeft.setPower(-rightTrigger);
                //set power for turning side wheels
                rearRight.setPower(-rightTrigger+Math.abs(leftStickX));
                frontRight.setPower(-rightTrigger+Math.abs(leftStickX));
            }
            */
            telemetry.addData("Status", "NOT IN LOOp");
            telemetry.update();
            arm.setPower(rightTrigger);
            if(rightStickY>0){
                arm.setPower(-rightStickY);
                telemetry.addData("Status", "move");
                telemetry.update();

            } else if(rightStickY<0){
                arm.setPower(rightStickY);
                telemetry.addData("Status", "otherMove");
                telemetry.update();
            }

        }


    }


}