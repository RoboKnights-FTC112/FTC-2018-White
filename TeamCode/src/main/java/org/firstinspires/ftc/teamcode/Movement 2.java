
package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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
public class Movement extends LinearOpMode {
  
  public DcMotor frontLeftMotor = null; // motor controller 1
  public DcMotor frontRightMotor = null;
  public DcMotor backLeftMotor = null; // motor controller 1
  public DcMotor backRightMotor = null;
  public DcMotor intakeMotor;
  public DcMotor medianMotor;
  public DcMotor launcherMotor;
  public BNO055IMU imu;

  public Orientation lastAngles = new Orientation();
  double globalAngle, power = .30, correction;
  
  
    @Override
    public void runOpMode() {
  
      frontLeftMotor = hardwareMap.dcMotor.get("fl");//controler 1
      frontRightMotor = hardwareMap.dcMotor.get("fr");
      backLeftMotor = hardwareMap.dcMotor.get("bl");
      backRightMotor = hardwareMap.dcMotor.get("br");
      
      intakeMotor = hardwareMap.dcMotor.get("intake");
      launcherMotor = hardwareMap.dcMotor.get("launcher");
      medianMotor = hardwareMap.dcMotor.get("median");
      
      //frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
      backRightMotor.setDirection(DcMotor.Direction.REVERSE);
      //backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
    
      frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      
      intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
      launcherMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
      medianMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
      
      //frontLeftMotor 
      //frontRightMotor 
      //backLeftMotor 
      //backRightMotor 
        
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();
        
        while (opModeIsActive()) {
              telemetry.addData("Status", "Running");
          
              double backLeftMotorposition = backLeftMotor.getCurrentPosition();
              double backRightMotorposition = backRightMotor.getCurrentPosition();
              double frontLeftMotorposition = frontLeftMotor.getCurrentPosition();
              double frontRightMotorposition = frontRightMotor.getCurrentPosition();
              
              
              
              // movement up down left and right
            if(this.gamepad1.right_stick_y!=0||gamepad1.right_stick_x!=0||this.gamepad1.left_stick_x==0){
              frontLeftMotor.setPower(Power("left"));
              frontRightMotor.setPower(Power("right"));
              backLeftMotor.setPower(Power("left"));
              backRightMotor.setPower(Power("left"));}
              
        
              //rotation
              Rotation(this.gamepad1.left_stick_x);
              
              Intake(this.gamepad1.b);
              Median(this.gamepad1.y);
              Launcher(this.gamepad1.a,this.gamepad1.x);
              //if(this.gamepad1.dpad_up)
              //telemetry.addData("Encoder leftMotorposition", leftMotorposition);
              // telemetry.addData("Encoder rightMotorposition", rightMotorposition);
              telemetry.addData("stick",this.gamepad1.right_stick_x );
              //telemetry.addData("dir",checkDirection());
              telemetry.update();

          }}
      private void resetAngle()
    {
        lastAngles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        globalAngle = 0;
    }
      private double checkDirection()
    {
        // The gain value determines how sensitive the correction is to direction changes.
        // You will have to experiment with your robot to get small smooth direction changes
        // to stay on a straight line.
        double correction, angle, gain = .10;

        angle = getAngle();

        if (angle == 0)
            correction = 0;             // no adjustment.
        else
            correction = -angle;        // reverse sign of angle for correction.

        correction = correction * gain;

        return correction;
    }
    private double getAngle()
    {
        // We experimentally determined the Z axis is the axis we want to use for heading angle.
        // We have to process the angle because the imu works in euler angles so the Z axis is
        // returned as 0 to +180 or 0 to -180 rolling back to -179 or +179 when rotation passes
        // 180 degrees. We detect this transition and track the total cumulative angle of rotation.

        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        double deltaAngle = angles.firstAngle - lastAngles.firstAngle;

        if (deltaAngle < -180)
            deltaAngle += 360;
        else if (deltaAngle > 180)
            deltaAngle -= 360;

        globalAngle += deltaAngle;

        lastAngles = angles;

        return globalAngle;
    }

      public double Power(String motorOrentation){
        double powerOutput = 0;
        if(motorOrentation == "left"){
          powerOutput -= this.gamepad1.right_stick_y;
          powerOutput += this.gamepad1.right_stick_x;
          powerOutput = powerOutput/2;
        }
        if(motorOrentation == "right"){
          powerOutput += this.gamepad1.right_stick_y;
          powerOutput += this.gamepad1.right_stick_x;
          powerOutput = powerOutput/2;
        }

        return powerOutput;
      }

      public void Rotation(double joystick){
        if(joystick>0){//left rotation
              frontLeftMotor.setPower(-.6);
              frontRightMotor.setPower(.6);
              backLeftMotor.setPower(.6);
              backRightMotor.setPower(-.6);
            }
        else if(joystick<0){//right rotation
              frontLeftMotor.setPower(.6);
              frontRightMotor.setPower(-.6);
              backLeftMotor.setPower(-.6);
              backRightMotor.setPower(.6);
            }
      }
      public void Intake(boolean button){
        int timesClicked = 0;
        if(button){
          timesClicked++;
        }
        int motorPower = timesClicked%2;
        intakeMotor.setPower(-motorPower);
      }
      public void Median(boolean button){
        if (button){
          medianMotor.setPower(-1);
        }
        else{
          medianMotor.setPower(0);
        }
      }
      public void Launcher(boolean button, boolean button2){
        if (button){
          launcherMotor.setPower(-.75);
        }
        else if (button2){
          launcherMotor.setPower(-.69);
        }
        else{
          launcherMotor.setPower(0);
        }
}}


