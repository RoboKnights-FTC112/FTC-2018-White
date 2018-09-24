package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@TeleOp(name="BasicDriver", group="White Team")
@Disabled
public class GamepadTelemetryTest extends LinearOpMode {

    @Override
    public void runOpMode() {

        telemetry.addData("Status", "We Ready");
        telemetry.update();

        waitForStart();

        //wheel motor variables
        private DcMotor rearLeft = hardwareMap.get(DcMotor.class, "rearLeft");
        private DcMotor rearRight = hardwareMap.get(DcMotor.class, "rearRight");
        private DcMotor frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        private DcMotor frontRight = hardwareMap.get(DcMotor.class, "frontRight");

        //gamepad variables
        private float rightTrigger = gamepad1.right_trigger;
        private float leftTrigger = gamepad1.left_trigger;

        private float leftStickX = gamepad1.left_stick_x;

        while (opModeIsActive()){
            //check if robot is moving forward & turning left
            if(rightTrigger>0&&leftStickX<0){
                //set power for non-turning side wheels
                rearRight.setPower(rightTrigger);
                frontRight.setPower(rightTrigger);
                //set power for turning side wheels
                rearLeft.setPower(rightTrigger-Math.abs(leftStickX));
                frontLeft.setPower(rightTrigger-Math.abs(leftStickX));
            }


        }


    }


}