/*
Copyright 2020 FIRST Tech Challenge Team FTC

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Remove a @Disabled the on the next line or two (if present) to add this opmode to the Driver Station OpMode list,
 * or add a @Disabled annotation to prevent this OpMode from being added to the Driver Station
 */
@Autonomous

public class Auto2 extends LinearOpMode {
    public DcMotor frontLeftMotor; 
  public DcMotor frontRightMotor;
  public DcMotor backLeftMotor; 
  public DcMotor backRightMotor;
  public DcMotor intakeMotor;
  public DcMotor medianMotor;
  public DcMotor launcherMotor;
  //public DcMotor armMotor;
  public BNO055IMU imu;
 // public Servo clampServo;

    @Override
    public void runOpMode() {
      
        frontLeftMotor = hardwareMap.dcMotor.get("fr");
        frontRightMotor = hardwareMap.dcMotor.get("fl");
        backLeftMotor = hardwareMap.dcMotor.get("br");
        backRightMotor = hardwareMap.dcMotor.get("bl");
      
        intakeMotor = hardwareMap.dcMotor.get("intake");
        launcherMotor = hardwareMap.dcMotor.get("launcher");
        medianMotor = hardwareMap.dcMotor.get("median");
        //clampServo = hardwareMap.get(Servo.class, "clampServo");
        //armMotor = hardwareMap.dcMotor.get("arm");
      
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();
            setAllPower(0.5);
            sleep(2400);
            setAllPower(0);
            setAllPower(-0.5);
            sleep(500);
            setAllPower(0);
            strafe(-.4);
            sleep(2250);
            setAllPower(0);
            launcherMotor.setPower(-0.72);
            sleep(500);
            medianMotor.setPower(-0.5);
            sleep(2500);
            setAllPower(0.5);
            sleep(500);
            break;

        }
        
        
 
        
    }
    
    // Strafing Forward/Backward Method
    public void setAllPower(double speed){
        frontLeftMotor.setPower(speed);
        frontRightMotor.setPower(speed);
        backLeftMotor.setPower(speed);
        backRightMotor.setPower(speed);
    }
    
    
    public void turn(double speed){
        frontLeftMotor.setPower(speed);
        frontRightMotor.setPower(-speed);
        backLeftMotor.setPower(speed);
        backRightMotor.setPower(-speed);
  }    
    
    public void strafe(double speed){
      frontLeftMotor.setPower(speed);
      frontRightMotor.setPower(-speed);
      backLeftMotor.setPower(-speed);
      backRightMotor.setPower(speed);}
}
