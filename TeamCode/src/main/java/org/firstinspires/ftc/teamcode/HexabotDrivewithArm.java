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

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Hexabot arm", group="K9bot")
public class HexabotDrivewithArm extends LinearOpMode {

    /* Declare OpMode members. */
    public DcMotor leftMotor   = null;
    public DcMotor  rightMotor  = null;
    public DcMotor armMotor = null;
    public DcMotor armMotor2 = null;
    public Servo claw = null;
    public Servo claw2 = null;
    public Servo sensorArmB = null;
    public Servo sensorArmR = null;

    //public ColorSensor colorSensor = null;

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
        double clawpo = .9;
        double clawpo2 = .1;
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();
        sensorArmB.setPosition(.9);
        sensorArmR.setPosition(.05);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        //leftMotor.setPower(left);
        //rightMotor.setPower(right);


        while (opModeIsActive()) {

            // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
            left = -gamepad1.left_stick_y;
            right = -gamepad1.right_stick_y;
            up = -gamepad2.right_stick_y;
            leftMotor.setPower(left*.25);
            rightMotor.setPower(right*.25);
            if (gamepad2.left_bumper == true) {
                sensorArmR.setPosition(.05 );
                sensorArmB.setPosition(0);

            }
            if (gamepad1.a == true && opModeIsActive()) {
                leftMotor.setPower(left*.5);
                rightMotor.setPower(right*.5);
            }


                armMotor.setPower(up * .2);
                armMotor2.setPower( up *.2);

           /* if (gamepad2.y == true && opModeIsActive()) {
                moveUpDistance(.25);

            }
            if (gamepad2.x == true) {
               moveUpDistance(-.25);
            }*/

            if (gamepad2.a == true && opModeIsActive()) { //both arms open
                clawpo+=.01;
                clawpo=Math.min(1,clawpo);
                clawpo2-=.01;
                clawpo2=Math.max(.3,clawpo2);
                claw.setPosition(clawpo);
                claw2.setPosition(clawpo2);
                telemetry.addData("servo1:", clawpo);    //
                telemetry.addData("servo2:", clawpo2);    //
                telemetry.update();
            }
            if (gamepad2.b == true && opModeIsActive()) { //both arms
                clawpo = Math.max(.3,clawpo);//close
                clawpo -= .01;
                clawpo2+=.01;
                clawpo2=Math.min(1,clawpo2);
                claw.setPosition(clawpo);
                claw2.setPosition(clawpo2);
                telemetry.addData("servo1:", clawpo);
                telemetry.addData("servo2:", clawpo2);    //
                telemetry.update();
            }
            if (gamepad2.x==true && opModeIsActive()) {
                claw.setPosition(.9);
                claw2.setPosition(.3);
                clawpo = .9;
                clawpo2 = .3;

            }
            if (gamepad2.y==true && opModeIsActive()) {
                claw.setPosition(.4);
                claw2.setPosition(.6);
                clawpo = .45;
                clawpo2 = .65;

            }

        }


    }public void moveDownDistance (double speed) {
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        armMotor.setTargetPosition(-2000);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        armMotor.setPower(-speed);


        while ((armMotor.isBusy()) && opModeIsActive()){


            //do anything
            //we could spit out some telemetry about the encoder value
        }
        telemetry.addData("5th", "Hello Driver");    //
        telemetry.update();

        armMotor.setPower(0);
        armMotor.setPower(0);
        telemetry.addData("6th", "Hello Driver");    //
        telemetry.update();

        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void moveUpDistance (double speed) {
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        armMotor.setTargetPosition(2000);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        armMotor.setPower(speed);


        while ((armMotor.isBusy()) && opModeIsActive()){


            //do anything
            //we could spit out some telemetry about the encoder value
        }
        telemetry.addData("5th", "Hello Driver");    //
        telemetry.update();

        armMotor.setPower(0);
        armMotor.setPower(0);
        telemetry.addData("6th", "Hello Driver");    //
        telemetry.update();

        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
