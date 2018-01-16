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
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

@Autonomous(name="vuforiaREDcorner", group="Senior")

public class vuforiaredcorner extends LinearOpMode {

    /* Declare OpMode members. */
    NormalizedColorSensor colorSensorR;

    public DcMotor leftMotor   = null;
    public DcMotor  rightMotor  = null;
    public DcMotor armMotor = null;
    public DcMotor armMotor2 = null;
    public Servo claw = null;
    public Servo claw2 = null;

    public Servo sensorArmB = null;
    public Servo sensorArmR = null;
    VuforiaLocalizer vuforia;
    @Override
    public void runOpMode() {
        double left = 0;
        double right = 0;
        double up = .5;
        colorSensorR = hardwareMap.get(NormalizedColorSensor.class, "colorSensorR");

        leftMotor   = hardwareMap.dcMotor.get("left_drive");
        rightMotor  = hardwareMap.dcMotor.get("right_drive");
        armMotor = hardwareMap.dcMotor.get("armmotor");
        armMotor2 = hardwareMap.dcMotor.get("armmotor2");
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        sensorArmB=hardwareMap.servo.get("servoB");
        sensorArmR=hardwareMap.servo.get("servoR");
        claw = hardwareMap.servo.get("claw");
        claw2 = hardwareMap.servo.get("claw2");
       // double clawpo = claw.getPosition();
        //double clawpo2 = claw2.getPosition();
      //  double num = sensorArmB.getPosition();
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();


        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "AVy6Ghb/////AAAAGegEa3vzQ0X/rA9myJqBvacSbrAh5gHRPhMizQfh6URkJ+DbZREbbuUEgvwdX46xNXX8oCALhTdkNbGfvbduWuXze3AydZYHc0vXKODraVrE4KTByQYm61PMGMWl13T+LycKky4cT5xcWvQi/1ClNl0KEIKXZDcWMFXCjUZt/H8e04jweEUIiLkPgadYJJPhffGepTe6BHFJISIogaKdXvZtqf4xMtkxoMzjLgZNl2vh+P3HC7Lj70f0d6dM6QNglHtPXBMoktQG+MJRRIGvbf4AbN8rFkzxXVD7hF51XpAVexmMxMCUjhBH4XhC8ujZtGIWabhyriVYHvUyu1UDQNHjbMUEH5kSELrlQ1tPyjOP";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT; // Use FRONT Camera (Change to BACK if you want to use that one)
        parameters.cameraMonitorFeedback = VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES; // Display Axes

        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        if (opModeIsActive()) {


            sensorArmR.setPosition(.7);//lower red side arm
            closeClaw();
            sleep(200);

            sleep(1500);
            NormalizedRGBA colors = colorSensorR.getNormalizedColors();
                //color sensor data
            telemetry.addData("Blue Value", colors.blue);
            telemetry.addData("Red Value", colors.red);
            sensorArmB.setPosition(.9); //fix blue side arm
            telemetry.update();
            sleep(1500);
            colors = colorSensorR.getNormalizedColors();
            ElapsedTime time = new ElapsedTime();
            time.reset();

            if (colors.red > colors.blue && opModeIsActive()) { //if it sees red
                telemetry.addData("In the", "IF");
                telemetry.update();
                driveDistance(.1,400,4); // knock off blue
                sleep(500);
                sensorArmR.setPosition(.05);
                driveDistance(-.1,-900,4); //go back to the vuforia picture

            }
            else if (colors.blue > colors.red && opModeIsActive()){ //if it sees blue
                telemetry.addData("In the ", "else if");
                telemetry.update();
                driveDistance(-.1,-500,4); //knock off blue and it gets to vuforia picture
                sleep(1000);
                 sensorArmR.setPosition(.05);
                sleep(1000);
            }
            else { //if it doesn't see anything
                sensorArmR.setPosition(.05);
                sleep(1000);
                driveDistance(-.1,-500,4); //go back to vuforia
            }
            relicTrackables.activate(); // Activate Vuforia
            sleep(1000);
            armMotor. setPower(.18);//start lifting arm so the robot doesnt trip
            armMotor2.setPower(.18);
            drive(0);
            sleep(1200);
            int driveForward = 0;
            int turn2Amount = 1250;
            if (vuforiaM(relicTemplate)==0 && opModeIsActive()) { //LEFT
                driveForward=3590;//2990
            }
            else if (vuforiaM(relicTemplate)==1 && opModeIsActive()) {  //CENTER
                driveForward=4510;//3910
                turn2Amount = 1200;
            }
            else if (vuforiaM(relicTemplate)==2 && opModeIsActive()) {  //RIGHT
                driveForward=5800;
            }
            else { //MISS
                driveForward=4510;//3910
                turn2Amount = 1200;
            }
            armMotor. setPower(.07);//start lifting arm so the robot doesnt trip
            armMotor2.setPower(.07);

            driveDistance(.2, driveForward, 7); //drive forward to the spot
            turnright(.2,1250);
            driveDistance(-.2,-700,2);
            turnright(.2,turn2Amount);
            driveDistance(.2, 850, 5); //push into box
            openClaw();
            driveDistance(-.2, -700, 5);//move back
            sensorArmB.setPosition(.9);//reset sensor position so they are out of the way
            sensorArmR.setPosition(.05);

        }
    }
    public void openClaw () {
        claw.setPosition(.4); //open claw
        claw2.setPosition(.6 );

    }
    public void closeClaw() {
        claw.setPosition(.9);//close claw
        claw2.setPosition(.1);

    }
    public int vuforiaM (VuforiaTrackable relicTemplate) {
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        //while (vuMark == RelicRecoveryVuMark.UNKNOWN) { // Test to see if image is visable
        //TODO: add timeout with timer
        //  vuMark = RelicRecoveryVuMark.from(relicTemplate);
        //}
        if (vuMark == RelicRecoveryVuMark.LEFT && opModeIsActive()) {// Test to see if Image is the "LEFT" image and display value.
            return 0;

        } else if (vuMark == RelicRecoveryVuMark.CENTER && opModeIsActive()) { // Test to see if Image is the "RIGHT" image and display values.
            return 1;
        } else if (vuMark == RelicRecoveryVuMark.RIGHT && opModeIsActive()) { // Test to see if Image is the "CENTER" image and display values.
            return 2;

        } else return -1;
    } //ends metnod

    public void turnleft (double speed, int distance) {
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setTargetPosition(distance);

        rightMotor.setPower(speed);

        ElapsedTime time = new ElapsedTime();
        time.reset();

        while ((rightMotor.isBusy()) && opModeIsActive() && time.seconds() < 4){

            idle();
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

    public void turnleft90 (double speed) {
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setTargetPosition(2500);

        rightMotor.setPower(speed);

        ElapsedTime time = new ElapsedTime();
        time.reset();

        while ((rightMotor.isBusy()) && opModeIsActive() && time.seconds() < 4){


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

    public void turnright (double speed, int distance) {
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftMotor.setTargetPosition(distance);

        leftMotor.setPower(speed);

        ElapsedTime time = new ElapsedTime();
        time.reset();
        while ((leftMotor.isBusy()) && opModeIsActive() && time.seconds() < 4){


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

        ElapsedTime time = new ElapsedTime();
        time.reset();
        while ((leftMotor.isBusy()) && opModeIsActive() && time.seconds() < 4){


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
    public void driveDistance(double speed, int targetEncoderValue, int times){
            leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftMotor.setTargetPosition(targetEncoderValue);
        rightMotor.setTargetPosition(targetEncoderValue);

            leftMotor.setPower(speed);
            rightMotor.setPower(speed);

        ElapsedTime time = new ElapsedTime();
        time.reset();
        while (leftMotor.isBusy()  && opModeIsActive() && time.seconds() < times){
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
