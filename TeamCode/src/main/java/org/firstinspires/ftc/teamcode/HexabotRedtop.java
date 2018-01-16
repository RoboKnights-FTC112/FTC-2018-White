/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.SwitchableLight;
import com.qualcomm.robotcore.util.ElapsedTime;
@Disabled
@Autonomous(name="Hexabotredtop", group="Senior")

public class HexabotRedtop extends LinearOpMode {

    public DcMotor leftMotor   = null;
    public DcMotor  rightMotor  = null;
    public DcMotor armMotor = null;
    public Servo claw = null;
    public Servo claw2 = null;
    public DcMotor armMotor2 = null;
    public Servo sensorArmB = null;
    public Servo sensorArmR = null;

    @Override
    public void runOpMode() {
        double left = 0;
        double right = 0;

        double up = .5;

        leftMotor   = hardwareMap.dcMotor.get("left_drive");
        rightMotor  = hardwareMap.dcMotor.get("right_drive");
        armMotor = hardwareMap.dcMotor.get("armmotor");
        armMotor2 = hardwareMap.dcMotor.get("armmotor2");
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        sensorArmB=hardwareMap.servo.get("servoB");
        sensorArmR=hardwareMap.servo.get("servoR");
        claw = hardwareMap.servo.get("claw");
        claw2 = hardwareMap.servo.get("claw2");
        double clawpo = claw.getPosition();
        double clawpo2 = claw2.getPosition();


        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();



        // run until the end of the match (driver presses STOP)
        if (opModeIsActive()) {


            //NormalizedRGBA colors = colorSensor.getNormalizedColors();
            //@ColorInt int color = colors.toColor();
            //telemetry.addLine("color: ")
            //       .addData("a", "%02x", Color.alpha(color))
            //     .addData("r", "%02x", Color.red(color))
            //   .addData("g", "%02x", Color.green(color))
            //       .addData("b", "%02x", Color.blue(color));
            //telemetry.update();
            //sleep(5000);

            claw.setPosition(.4);
            claw2.setPosition(.6);
            sleep(200);
            armMotor.setPower(.2);
            armMotor2.setPower(-.2);
            sleep(900);
            armMotor.setPower(0);
            armMotor2.setPower(0);
            driveDistance(.25,2750);
            turnleft90(.25);

            turnright90(.25);
            driveDistance(.25,200);
            claw2.setPosition(.1);
            claw.setPosition(.9);
            driveDistance(-.2,-600);





        }
    }


    public void turnleft90 (double speed) {
           rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


            rightMotor.setTargetPosition(2500);
            rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

             rightMotor.setPower(speed);


            while ((rightMotor.isBusy()) && opModeIsActive()){


                //do anything
                //we could spit out some telemetry about the encoder value
            }
            telemetry.addData("5th", "Hello Driver");    //
            telemetry.update();

            leftMotor.setPower(0);
            rightMotor.setPower(0);
            telemetry.addData("6th", "Hello Driver");    //
            telemetry.update();

            leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    public void turnright90 (double speed) {
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        leftMotor.setTargetPosition(2500);
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftMotor.setPower(speed);


        while ((leftMotor.isBusy()) && opModeIsActive()){


            //do anything
            //we could spit out some telemetry about the encoder value
        }
        telemetry.addData("5th", "Hello Driver");    //
        telemetry.update();

        leftMotor.setPower(0);
        rightMotor.setPower(0);
        telemetry.addData("6th", "Hello Driver");    //
        telemetry.update();

        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void drive(double speed){
            if (opModeIsActive()){
                leftMotor.setPower(speed);
                rightMotor.setPower(speed);
            }
        }
    public void driveDistance(double speed, int targetEncoderValue){
            leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            leftMotor.setTargetPosition(targetEncoderValue);
            rightMotor.setTargetPosition(targetEncoderValue);

            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            leftMotor.setPower(speed);
            rightMotor.setPower(speed);

        ElapsedTime time = new ElapsedTime();
        time.reset();
        while ((leftMotor.isBusy() || rightMotor.isBusy()) && opModeIsActive() && time.seconds() < 4){
            telemetry.addData("Left Loop Current", leftMotor.getCurrentPosition());
                telemetry.addData("Left Loop Target", leftMotor.getTargetPosition());
                telemetry.addData("Right Loop Current", rightMotor.getCurrentPosition());
                telemetry.addData("Right Loop Target", rightMotor.getTargetPosition());  //
                telemetry.update();
                telemetry.addData("4th", "Hello Driver");    //
                telemetry.update();

                //do anything
                //we could spit out some telemetry about the encoder value
            }
            telemetry.addData("5th", "Hello Driver");    //
            telemetry.update();

            leftMotor.setPower(0);
            rightMotor.setPower(0);
            telemetry.addData("6th", "Hello Driver");    //
            telemetry.update();

            leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

    public void turn(double speed, int Value){
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftMotor.setTargetPosition(Value);
        rightMotor.setTargetPosition(-Value);

        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftMotor.setPower(speed);
        rightMotor.setPower(-speed);


        while ((leftMotor.isBusy() || rightMotor.isBusy()) && opModeIsActive()){
            telemetry.addData("Left Loop Current", leftMotor.getCurrentPosition());
            telemetry.addData("Left Loop Target", leftMotor.getTargetPosition());
            telemetry.addData("Right Loop Current", rightMotor.getCurrentPosition());
            telemetry.addData("Right Loop Target", rightMotor.getTargetPosition());  //
            telemetry.update();
            telemetry.addData("4th", "Hello Driver");    //
            telemetry.update();

            //do anything
            //we could spit out some telemetry about the encoder value
        }
        telemetry.addData("5th", "Hello Driver");    //
        telemetry.update();

        leftMotor.setPower(0);
        rightMotor.setPower(0);
        telemetry.addData("6th", "Hello Driver");    //
        telemetry.update();

        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
