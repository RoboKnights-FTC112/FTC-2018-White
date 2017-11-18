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
import com.qualcomm.robotcore.hardware.Servo;

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

@Disabled
@TeleOp(name="Senior TriBot Drive", group="Senior")

public class SeniorstriangleRobot extends LinearOpMode {

    /* Declare OpMode members. */
    public DcMotor leftMotor   = null;
    public DcMotor  rightMotor  = null;
    public DcMotor thirdMotor = null;
    public DcMotor arm1 = null;
    public DcMotor arm2 = null;
    public double pow = 0.50;

    public Servo serv1 = null;
    public Servo serv2 = null;
    public Servo armserv = null;
    public double serv1Incr = .03;
    public double armInc = .01001;
    public double armpow;

    @Override
    public void runOpMode() {



        leftMotor   = hardwareMap.dcMotor.get("leftdrive");
        rightMotor  = hardwareMap.dcMotor.get("rightdrive");
        thirdMotor = hardwareMap.dcMotor.get("thirddrive");
        arm1 = hardwareMap.dcMotor.get("arm1");
        arm2 = hardwareMap.dcMotor.get("arm2");
        serv1 = hardwareMap.servo.get("serv1");
        serv1.setPosition(.9900000000000007);
        serv2 = hardwareMap.servo.get("serv2");
        serv2.setPosition(.00999999999999937);
        armserv = hardwareMap.servo.get("servclamp");
        armserv.setPosition(.55000000000000003);

        arm2.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

       // armMotor1 = hardwareMap.dcMotor.get("arm1");
        //armMotor2 = hardwareMap.dcMotor.get("arm2");
        //armMotor2.setDirection(DcMotor.Direction.REVERSE);
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
           // DriveTriBot();
            newDrive();
            rotateBot();
            //armCode();
           /* if(gamepad1.dpad_up) pow = .35;
            if(gamepad1.dpad_down) pow = .4;
            if(gamepad1.dpad_left) pow = .5;
            if(gamepad1.dpad_right) pow = .6;
            if(Math.abs(gamepad1.left_trigger) > 0) pow = .45;
            if(Math.abs(gamepad1.right_trigger) > 0) pow = .3;
            telemetry.addData("Pow", pow);
            updateTelemetry(telemetry);
            telemetry.update();*/
            double pos1 = serv1.getPosition();
            double pos2 = serv2.getPosition();
            if(gamepad2.b){
                arm1.setPower(-.275786);
                arm2.setPower(-.275786);
            }
            else if(gamepad2.a) {
                arm1.setPower(pow);
                arm2.setPower(pow);
            }else if(gamepad2.y){
                arm1.setPower(-pow);
                arm2.setPower(-pow);
            } else {
                arm1.setPower(0);
                arm2.setPower(0);
            }
            if(gamepad2.left_trigger != 0 &&( armpow + armInc <= 1)){
                armpow += armInc;
            } else if(gamepad2.right_trigger != 0 && (armpow - armInc >=0)){
                armpow -= armInc;
            }
            armserv.setPosition(armpow);
            if (gamepad2.dpad_down){
               if((pos1 + serv1Incr >1 || pos2 - serv1Incr <0)){
                   pos1 = pos1;
                   pos2 = pos2;
               }else{
                   pos1 += serv1Incr;
                   pos2 -= serv1Incr;
               }

            } else if (gamepad2.dpad_up){
                if(pos1 - serv1Incr<0 || pos2 + serv1Incr>1) {

                }else {
                    pos1 -= serv1Incr;
                    pos2 += serv1Incr;

                }
            }

            serv1.setPosition(pos1);
            serv2.setPosition(pos2);

            telemetry.addData("Serv1 Pos: ", serv1.getPosition());
            telemetry.addData("Serv2 Pos: ", serv2.getPosition());
            telemetry.addData("ArmServ Pos: ", armserv.getPosition());
            telemetry.update();


        }
    }

    private void DriveTriBot(){
        double speed = .25 ;
        double motor1pow = 0;
        double motor2pow = 0;
        double motor3pow = 0;
        double leftStickx = gamepad1.left_stick_x * speed;
        double leftSticky = gamepad1.left_stick_y * speed;
        double m1x = 0;
        double m1y = 0;
        double m2x = 0;
        double m2y = 0;
        double m3x = 0;
        double m3y = 0;
        if(leftStickx > 0 && leftSticky ==0 ){
            m1x = speed - leftStickx;
            m1y = leftSticky/2;
            m3x = speed;
            m3y = m1y;

        }
        else if(leftStickx <0 && leftSticky ==0){
            m2x = speed + leftStickx;
            m2y = leftSticky/2;
            m1y = m2y;
            m1x = speed;
        }
        else if (leftSticky != 0 && leftStickx ==0) {
            motor3pow = 0;
            if (leftSticky > 0) {
                m1x = 0;
                m2x = 0;
                m2y = -speed;
                m1y = -speed;
            } else{
                m1x = 0;
                m2x = 0;
                m2y = speed;
                m1y = speed;
            }
        }
        else if(leftSticky>0 && leftStickx>0){
            ////////
            m1x = -leftStickx;
            m1y = leftSticky;
            m3x = -leftStickx;
            m3y = -leftSticky;
        }
        else if(leftSticky>0 && leftStickx<0){
            m2x= leftStickx;
            m2y= -speed;
            m3x= leftStickx;//motor3 is misbehaving
            m3y= leftSticky;
        }
        else if(leftSticky < 0 && leftStickx < 0){
            m1x = -leftStickx;
            m1y = -leftSticky;
            m3x = -leftStickx;
            m3y = leftSticky;
        }  else if(leftSticky < 0 && leftStickx > 0){

            m2x = leftStickx;
            m2y = -speed;
            m3x = leftStickx;
            m3y = leftSticky;
        }

        motor1pow = Math.pow((Math.pow(m1x,2) + Math.pow(m1y,2)), .5);
        motor2pow =  Math.pow((Math.pow(m2x,2) + Math.pow(m2y,2)), .5);
        motor3pow = Math.pow((Math.pow(m3x,2) + Math.pow(m3y,2)), .5);
            if(leftStickx < 0 && leftSticky < 0){
                // all positive
            } else if (leftStickx > 0 && leftSticky < 0){
                motor3pow = -motor3pow;
            } else if (leftStickx < 0 && leftSticky > 0){
                motor2pow = -motor2pow;
            } else if (leftStickx > 0 && leftSticky > 0){
                motor3pow = -motor3pow;
                motor1pow = -motor1pow;
            }

            if (leftSticky < 0 && Math.abs(leftStickx) < 0.15){
                motor1pow = -motor1pow;
                motor2pow = -motor2pow;
                //all positive
            } else if (leftSticky > 0 && Math.abs(leftStickx) < 0.15) {
            }
        leftMotor.setPower(motor1pow);
        rightMotor.setPower(motor2pow);
        thirdMotor.setPower(motor3pow);

    }
    public void rotateBot(){

        double speed = .15;
        if(gamepad1.left_bumper) {
            while (gamepad1.left_bumper) {
                leftMotor.setPower(-speed);
                rightMotor.setPower(speed);
                thirdMotor.setPower(speed);
            }
        } else if(gamepad1.right_bumper){
            while(gamepad1.right_bumper) {
                leftMotor.setPower(speed);//counter
                rightMotor.setPower(-speed);//counter
                thirdMotor.setPower(-speed);//clock
            }
        }
    }

    public void newDrive(){
        double powah = .27;
        double powahhhhhh = .5;
        if(gamepad1.dpad_up){
            leftMotor.setPower(-powahhhhhh);
            rightMotor.setPower(-powahhhhhh);
        } else if(gamepad1.dpad_down){
            leftMotor.setPower(powahhhhhh);
            rightMotor.setPower(powahhhhhh);
        }else if(gamepad1.dpad_left){
            leftMotor.setPower(powah);
            thirdMotor.setPower(powah);
        }else if(gamepad1.dpad_right){
            thirdMotor.setPower(-powah);
            rightMotor.setPower(powah);

        }else if(Math.abs(gamepad1.right_trigger) > 0){
            thirdMotor.setPower(-powah);
            leftMotor.setPower(-powah);
        } else if(Math.abs(gamepad1.left_trigger) >0 ){
            thirdMotor.setPower(powah);
            rightMotor.setPower(-powah);
        }
        else {
            thirdMotor.setPower(0);
            leftMotor.setPower(0);
            rightMotor.setPower(0);
        }


    }
    public void armCode(){


        boolean armmove = gamepad1.dpad_left;
        boolean armmoveOtherway = gamepad1.dpad_right;




        if(gamepad2.a){
            arm1.setPower(pow);
            arm2.setPower(pow);
        }
        if(gamepad2.y){
            arm1.setPower(-pow);
            arm2.setPower(-pow);
        }

    }
}
