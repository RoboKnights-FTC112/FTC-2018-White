package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.hardware.bosch.BNO055IMU;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Rotation;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import com.qualcomm.robotcore.hardware.LED;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class Comp2Test extends LinearOpMode {
  
  public DcMotor frontLeftMotor; 
  public DcMotor frontRightMotor;
  public DcMotor backLeftMotor; 
  public DcMotor backRightMotor;
  public DcMotor intakeMotor;
  public DcMotor medianMotor;
  public DcMotor launcherMotor;
  public Servo clampServo;
  public DcMotor armMotor;
  public BNO055IMU imu;
  public CRServo contServo;
  //public Servo clampServo;

    @Override
    public void runOpMode() {
  
    frontLeftMotor = hardwareMap.dcMotor.get("fr");
    frontRightMotor = hardwareMap.dcMotor.get("fl");
    backLeftMotor = hardwareMap.dcMotor.get("br");
    backRightMotor = hardwareMap.dcMotor.get("bl");
      
    intakeMotor = hardwareMap.dcMotor.get("intake");
    launcherMotor = hardwareMap.dcMotor.get("launcher");
    medianMotor = hardwareMap.dcMotor.get("median");
    contServo = hardwareMap.crservo.get("clampServo");
    armMotor = hardwareMap.dcMotor.get("arm");
      
    frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
    backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
    
      
        
    telemetry.addData("Status", "Initialized");
    telemetry.update();
    waitForStart();
        
    while (opModeIsActive()) {
        telemetry.addData("Status", "Running");
        //backLeftMotor.setPower(1);
        //backRightMotor.setPower(1);
        telemetry.addData("left X", this.gamepad1.left_stick_x);
        telemetry.addData("left Y", this.gamepad1.left_stick_y);
        telemetry.addData("right X", this.gamepad1.right_stick_x);
        telemetry.addData("right Y", this.gamepad1.right_stick_y);
        telemetry.addData("dPadUP", this.gamepad1.dpad_up);
        telemetry.addData("dPadDown", this.gamepad1.dpad_down);
        telemetry.addData("a", this.gamepad1.a);
        telemetry.addData("rightTrigger", this.gamepad1.right_trigger);
        telemetry.addData("leftTrigger", this.gamepad1.left_trigger);
        telemetry.addData("clamp",this.gamepad1.x);
        telemetry.addData("clampDecline",this.gamepad1.b);
        telemetry.update();
        
        /* //Strafing diagonal right
        if(this.gamepad1.left_stick_y > 0.25 && this.gamepad1.left_stick_x > 0.25){
          frontLeftMotor.setPower(this.gamepad1.left_stick_y);
          backRightMotor.setPower(this.gamepad1.left_stick_y);
        } */
        if(this.gamepad1.left_stick_y < -.25 || this.gamepad1.left_stick_y > .25) {
          setAllPower(-this.gamepad1.left_stick_y);
        } else if(this.gamepad1.left_stick_x < -.25 || this.gamepad1.left_stick_x > .25) {
          strafe(-gamepad1.left_stick_y, gamepad1.left_stick_x*1.25,gamepad1.right_stick_x);
          //strafe(this.gamepad1.left_stick_x);
        } else {
          setAllPower(0);
        }
        if(this.gamepad1.right_stick_x > .1 || this.gamepad1.right_stick_x < -.1){
          turn(this.gamepad1.right_stick_x);
        }
        //Conveyor Belt
        if(this.gamepad2.dpad_up == true){
          medianMotor.setPower(0.5);
        } else if(this.gamepad2.dpad_down == true){
          medianMotor.setPower(-0.5);
        } else{
          medianMotor.setPower(0);
        }
        //intake forward
        if(this.gamepad2.a == true){
          intakeMotor.setPower(-0.9);
        } else{
          intakeMotor.setPower(0);
        }
        //intake backwards
        if(this.gamepad2.left_bumper == true){
          intakeMotor.setPower(0.9);
        } else{
          intakeMotor.setPower(0);
        }
        //clamp arm code
        //if(this.gamepad2.y == true){
        //  armMotor.setPower(.1);
        //}else{
        //  armMotor.setPower(0);
        //}
        if(this.gamepad2.y == true){
          armMotor.setPower(1);
        }else{
          armMotor.setPower(0);
        }
        if(this.gamepad2.x == true){
          armMotor.setPower(-1);
        }else{
          armMotor.setPower(0);
        }
        //clamp
        /*
        if(gamepad1.x == true){
          contServo.setPower(.8);
        } 
        if(gamepad1.x == false){
          contServo.setPower(0);
        }
        if(gamepad1.y == true){
          contServo.setPower(-.7);
        } 
        if(gamepad1.y == false){
          contServo.setPower(0);
        }
        */
        if(gamepad1.left_trigger > 0.1){
          contServo.setPower(0.8);
        }
        
        
        if(gamepad1.right_trigger > 0.1){
          contServo.setPower(-0.8);
        }
        if(gamepad1.x == true){
          contServo.setPower(0);
        }
        
        
       
          
        
        
       
        
        
        //launcher
        if(this.gamepad2.right_trigger > 0.1){
          fastSpin(0.72);
        }else if (this.gamepad2.left_trigger > 0.1){
          slowSpin(0.70);
        } else{
          launcherMotor.setPower(0);
        }
        
    }
  }
  // Strafing Forward/Backward Method
  public void setAllPower(float speed){
    frontLeftMotor.setPower(speed);
    frontRightMotor.setPower(speed);
    backLeftMotor.setPower(speed);
    backRightMotor.setPower(speed);
  }
  
  // left right
  /*public void strafe(float speed){
      frontLeftMotor.setPower(speed);
      frontRightMotor.setPower(-speed);
      backLeftMotor.setPower(-speed);
      backRightMotor.setPower(speed);
  }
  */
  public void strafe(double y,double x, double rx){
    frontLeftMotor.setPower(y + x + rx);
    backLeftMotor.setPower(y - x + rx);
    frontRightMotor.setPower(y - x - rx);
    backRightMotor.setPower(y + x - rx);
  }
  //spinning
  public void turn(float speed){
    frontLeftMotor.setPower(speed);
    frontRightMotor.setPower(-speed);
    backLeftMotor.setPower(speed);
    backRightMotor.setPower(-speed);
  }
  public void fastSpin(double speed){
    launcherMotor.setPower(speed);
  }
   public void slowSpin(double speed){
    launcherMotor.setPower(speed);
  }
  
      
}