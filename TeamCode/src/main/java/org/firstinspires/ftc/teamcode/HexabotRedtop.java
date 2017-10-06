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
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.SwitchableLight;

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

@Autonomous(name="Hexabotredtop", group="Senior")

public class HexabotRedtop extends LinearOpMode {

    /* Declare OpMode members. */
    public DcMotor leftMotor   = null;
    public DcMotor  rightMotor  = null;
    public Servo claw = null;
    //public ColorSensor colorSensor = null;

    @Override
    public void runOpMode() {
        double left = 0;
        double right = 0;
        double clawpo = .5;
        leftMotor   = hardwareMap.dcMotor.get("left_drive");
        rightMotor  = hardwareMap.dcMotor.get("right_drive");

        claw = hardwareMap.servo.get("servo");
        claw.setPosition(clawpo);
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        if (opModeIsActive()) {

            //driveUntilGrey(.25);
            //NormalizedRGBA colors = colorSensor.getNormalizedColors();
            //@ColorInt int color = colors.toColor();
            //telemetry.addLine("color: ")
             //       .addData("a", "%02x", Color.alpha(color))
               //     .addData("r", "%02x", Color.red(color))
                 //   .addData("g", "%02x", Color.green(color))
             //       .addData("b", "%02x", Color.blue(color));
            //telemetry.update();
            //sleep(5000);
            driveDistance(.25,1000);
            turnleft90(.25);
            driveDistance(.25,3850);




        }
    }


        public void turnleft90 (double speed) {
           rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


            rightMotor.setTargetPosition(2520);
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
