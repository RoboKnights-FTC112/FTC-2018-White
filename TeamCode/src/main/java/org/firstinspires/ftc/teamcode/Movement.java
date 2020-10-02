/*
Code for Robot 112
(The best team of all time)
Coded by Cummings the great of 'murica
                                o
                                /\
                               /::\
                              /::::\
                ,a_a         /\::::/\
               {/ ''\_      /\ \::/\ \
               {\ ,_oo)    /\ \ \/\ \ \
               {/  (_^____/  \ \ \ \ \ \
     .=.      {/ \___)))*)    \ \ \ \ \/
    (.=.`\   {/   /=;  ~/      \ \ \ \/
        \ `\{/(   \/\  /        \ \ \/
         \  `. `\  ) )           \ \/
          \    // /_/_            \/
           '==''---))))
*/

package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.LED;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
@TeleOp
public class Code extends LinearOpMode {
  
  public DcMotor frontLeftMotor = null; // motor controller 1
  public DcMotor frontRightMotor = null;
  public DcMotor backLeftMotor = null; // motor controller 1
  public DcMotor backRightMotor = null;
  
  
    @Override
    public void runOpMode() {
  
      frontLeftMotor = hardwareMap.dcMotor.get("fl");//controler 1
      frontRightMotor = hardwareMap.dcMotor.get("fr");
      backLeftMotor = hardwareMap.dcMotor.get("bl");
      backRightMotor = hardwareMap.dcMotor.get("br");
      
      frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
      backRightMotor.setDirection(DcMotor.Direction.REVERSE);
      
      //frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      //frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      //backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      //backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      
      //frontLeftMotor 
      //frontRightMotor 
      //backLeftMotor 
      //backRightMotor 
        
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();
        
        while (opModeIsActive()) {
              telemetry.addData("Status", "Running");
          
              /*double backLeftMotorposition = backLeftMotor.getCurrentPosition();
              double backRightMotorposition = backRightMotor.getCurrentPosition();
              double frontLeftMotorposition = frontLeftMotor.getCurrentPosition();
              double frontRightMotorposition = frontRightMotor.getCurrentPosition();*/
              
              
              // movement up down left and right
              frontLeftMotor.setPower((this.gamepad1.right_stick_y-this.gamepad1.right_stick_x)/2);
              frontRightMotor.setPower(((this.gamepad1.right_stick_y+this.gamepad1.right_stick_x)/2));
              backLeftMotor.setPower((this.gamepad1.right_stick_y-this.gamepad1.right_stick_x)/2);
              backRightMotor.setPower((this.gamepad1.right_stick_y+this.gamepad1.right_stick_x)/2);
          
        
              //rotation
              if(this.gamepad1.left_stick_x>0){//left rotation
                frontLeftMotor.setPower(1);
                frontRightMotor.setPower(-1);
                backLeftMotor.setPower(-1);
                backRightMotor.setPower(1);
                }
              else if(this.gamepad1.left_stick_x<0){//right rotation
                frontLeftMotor.setPower(-1);
                frontRightMotor.setPower(1);
                backLeftMotor.setPower(1);
                backRightMotor.setPower(-1);
                
              }
              //if(this.gamepad1.dpad_up)
                
  
              // telemetry.addData("Encoder leftMotorposition", leftMotorposition);
              // telemetry.addData("Encoder rightMotorposition", rightMotorposition);
              telemetry.addData("stick",this.gamepad1.right_stick_x );
              telemetry.update();
          }
      }
}


