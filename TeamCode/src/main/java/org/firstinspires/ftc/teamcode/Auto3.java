package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorController;
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

public class Auto3 extends LinearOpMode {
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
        

        // circumfrence is 12.566

        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            strafeEncoder(.5,914);
                        stopPower();
                        sleep(100);
                        forwardBackEncoder(.5,4209);
                        stopPower();
                        sleep(100);
                        forwardBackEncoder(.5,-2000);
                        stopPower();
                        sleep(100);
                        strafeEncoder(.5,-2179);
                        stopPower();
                        sleep(100);
                        launcherMotor.setPower(-0.72);
                        sleep(700);
                        medianMotor.setPower(-0.5);
                        sleep(200);
                        forwardBackEncoder(.5,484);
                        launcherMotor.setPower(0);
                        medianMotor.setPower(0);
                        stopPower();
                        break;
            /*telemetry.update();
            forwardBackEncoder(.5,3000);
            //sleep(2400);
            stopPower();
            forwardBackEncoder(.5, -217);
            //sleep(500);
            stopPower();
            strafeEncoder(.4,-2044);
            //sleep(2250);
            stopPower();
            launcherMotor.setPower(-0.72);
            sleep(500);
            medianMotor.setPower(-0.5);
            sleep(2500);
            forwardBackEncoder(0.5,870);
            //sleep(500);
            break;
            */

        }
        
        
 
        
    }
    
    // Strafing Forward/Backward Method
    public void setAllPower(double speed){
        frontLeftMotor.setPower(speed);
        frontRightMotor.setPower(speed);
        backLeftMotor.setPower(speed);
        backRightMotor.setPower(speed);
    }
    public void stopPower(){
        setAllPower(0);
    }


    public void forwardBackEncoder(double speed, int distance){
        //resets
        frontLeftMotor.setMode(DcMotor.RunMode.RESET_ENCODERS);
        frontRightMotor.setMode(DcMotor.RunMode.RESET_ENCODERS);
        backLeftMotor.setMode(DcMotor.RunMode.RESET_ENCODERS);
        backRightMotor.setMode(DcMotor.RunMode.RESET_ENCODERS);
        
        // target
        frontLeftMotor.setTargetPosition(distance);
        frontRightMotor.setTargetPosition(distance);
        backLeftMotor.setTargetPosition(distance);
        backRightMotor.setTargetPosition(distance);

        //Go to that position

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //SET THE SPEED
        setAllPower(speed);

        //Until we reach

        while(frontLeftMotor.isBusy() && frontRightMotor.isBusy() && backLeftMotor.isBusy() && backRightMotor.isBusy()){

        }

        //
        stopPower();

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
    }

    //strafe encoders
    public void strafeEncoder(double speed, int distance){
        //resets
        frontLeftMotor.setMode(DcMotor.RunMode.RESET_ENCODERS);
        frontRightMotor.setMode(DcMotor.RunMode.RESET_ENCODERS);
        backLeftMotor.setMode(DcMotor.RunMode.RESET_ENCODERS);
        backRightMotor.setMode(DcMotor.RunMode.RESET_ENCODERS);
        
        // target
        frontLeftMotor.setTargetPosition(distance);
        frontRightMotor.setTargetPosition(-distance);
        backLeftMotor.setTargetPosition(-distance);
        backRightMotor.setTargetPosition(distance);

        //Go to that position

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //SET THE SPEED
        strafe(speed);

        //Until we reach

        while(frontLeftMotor.isBusy() && frontRightMotor.isBusy() && backLeftMotor.isBusy() && backRightMotor.isBusy()){

        }

        //
        stopPower();

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
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
