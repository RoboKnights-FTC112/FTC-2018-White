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
    public Servo claw = null;
    //public ColorSensor colorSensor = null;

    @Override
    public void runOpMode() {
        double left = 0;
        double right = 0;
        double up = 0;
        double clawpo = .5;
        leftMotor   = hardwareMap.dcMotor.get("left_drive");
        rightMotor  = hardwareMap.dcMotor.get("right_drive");
        armMotor = hardwareMap.dcMotor.get("armmotor");
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        claw = hardwareMap.servo.get("claw");
        claw.setPosition(clawpo);
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        //leftMotor.setPower(left);
        //rightMotor.setPower(right);


        while (opModeIsActive()) {

            // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
            left = -gamepad1.left_stick_y;
            right = -gamepad1.right_stick_y;
            up = -gamepad2.right_stick_y;
            armMotor.setPower(up *.25);
            leftMotor.setPower(left*.25);
            rightMotor.setPower(right*.25);
            while (gamepad2.a == true && opModeIsActive()) {
                clawpo+=.05;
                clawpo=Math.min(1,clawpo);
                claw.setPosition(clawpo);
            }
            while (gamepad2.b == true && opModeIsActive()) {
                clawpo -= .05;
                clawpo = Math.max(-1,clawpo);
                claw.setPosition(clawpo);
            }


        }
    }
}
