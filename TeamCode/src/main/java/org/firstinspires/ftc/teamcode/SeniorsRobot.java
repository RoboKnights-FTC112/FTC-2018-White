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

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * This OpMode uses the common HardwareK9bot class to define the devices on the robot.
 * All device access is managed through the HardwareK9bot class. (See this class for device names)
 * The code is structured as a LinearOpMode
 *
 * This particular OpMode executes a basic Tank Drive Teleop for the K9 bot
 * It raises and lowers the arm using the Gampad Y and A buttons respectively.
 * It also opens and closes the claw slowly using the X and B buttons.
 *
 * Note: the configuration of the servos is such that
 * as the arm servo approaches 0, the arm position moves up (away from the floor).
 * Also, as the claw servo approaches 0, the claw opens up (drops the game element).
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

 @TeleOp(name="Toddler Driver", group="Senior")


public class SeniorsRobot extends LinearOpMode {

    /* Declare OpMode members. */
    public DcMotor leftMotor   = null;
    public DcMotor  rightMotor  = null;

    @Override
    public void runOpMode() {

        leftMotor   = hardwareMap.dcMotor.get("left");
        rightMotor  = hardwareMap.dcMotor.get("right");
        leftMotor.setDirection(DcMotor.Direction.REVERSE);


        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            double powRight = Math.abs(.75 * gamepad1.right_stick_y);
            double powLeft = Math.abs(.75 * gamepad1.left_stick_y);
            // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
            if (gamepad1.right_stick_y < 0 && gamepad1.left_stick_y < 0) { // move leftmotor forwards
                leftMotor.setPower(powLeft);
                rightMotor.setPower(powRight);
            } else if (gamepad1.right_stick_y > 0 && gamepad1.left_stick_y > 0) {
                leftMotor.setPower(-powLeft);
                rightMotor.setPower(-powRight);
            } else if (gamepad1.left_stick_y > 0 && gamepad1.right_stick_y < 0) {
                leftMotor.setPower(-powLeft);
                rightMotor.setPower(powRight);
            } else if (gamepad1.left_stick_y < 0 && gamepad1.right_stick_y > 0) {
                leftMotor.setPower(powLeft);
                rightMotor.setPower(-powRight);
            } else if (gamepad1.left_stick_y == 0 && gamepad1.right_stick_y > 0) {
                rightMotor.setPower(-powRight);
                leftMotor.setPower(powLeft);
            } else if (gamepad1.left_stick_y == 0 && gamepad1.right_stick_y < 0) {
                rightMotor.setPower(powRight);
                leftMotor.setPower(powLeft);
            } else if (gamepad1.right_stick_y == 0 && gamepad1.left_stick_y > 0) {
                rightMotor.setPower(powRight);
                leftMotor.setPower(-powLeft);
            } else if (gamepad1.right_stick_y == 0 && gamepad1.left_stick_y < 0) {
                rightMotor.setPower(powRight);
                leftMotor.setPower(powLeft);
            } else {
                leftMotor.setPower(0);
                rightMotor.setPower(0);
            }

            while (gamepad1.left_bumper && gamepad1.right_bumper){
                leftMotor.setPower(1);
                rightMotor.setPower(1);
            } while(gamepad1.left_bumper) {
                leftMotor.setPower(.5);
            } while (gamepad1.right_bumper){
                rightMotor.setPower(.5);
            }

           // boolean dpadup = gamepad2.dpad_up;




            //double spinSpeed = .25;

        }
    }
}
