/* Copyright (c) 2019 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * This 2020-2021 OpMode illustrates the basics of using the TensorFlow Object Detection API to
 * determine the position of the Ultimate Goal game elements.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
 *
 * IMPORTANT: In order to use this OpMode, you need to obtain your own Vuforia license key as
 * is explained below.
 */
@Autonomous(name = "Autonomous Comp", group = "Concept")

public class Auto5 extends LinearOpMode {
    private static final String TFOD_MODEL_ASSET = "UltimateGoal.tflite";
    private static final String LABEL_FIRST_ELEMENT = "Quad";
    private static final String LABEL_SECOND_ELEMENT = "Single";
    private static final String LABEL_NO_ElEMENT = "No element";
    
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

    /*
     * IMPORTANT: You need to obtain your own license key to use Vuforia. The string below with which
     * 'parameters.vuforiaLicenseKey' is initialized is for illustration only, and will not function.
     * A Vuforia 'Development' license key, can be obtained free of charge from the Vuforia developer
     * web site at https://developer.vuforia.com/license-manager.
     *
     * Vuforia license keys are always 380 characters long, and look as if they contain mostly
     * random data. As an example, here is a example of a fragment of a valid key:
     *      ... yIgIzTqZ4mWjk9wd3cZO9T1axEqzuhxoGlfOOI2dRzKS4T0hQ8kT ...
     * Once you've obtained a license key, copy the string from the Vuforia web site
     * and paste it in to your code on the next line, between the double quotes.
     */
    private static final String VUFORIA_KEY =
            "AVYetcb/////AAABmeVLBlNESEKMr88sub/JcBSJrGxuaAQR/JQidhV8rHt1Y2PZkkKKjfTbUMV7iczPWHYxsym3U+1sDprOxTlkHfd5/jLkyhW9EuBI+S7EeEux3/K8HM5MeRCGk24FGGD3qSXudm+DlT1GMeTM7woA3arwO6NYdniKcArBsenbOYj8D6k19y76+tw1W9IYq5KCf5iXGln9aVfsgYNnfHIC+EC+uj9KDsZM98sMqqVbbnSidQpTVtkCMzd5jh+IpssJ4RKA7yO4NophMFSt+rJOD/dqgrmONSuQiUbt6d9vyJIrqzkYVoXn6o/JPrBqkasrMKafukvXdZQscfpwHeqePfzFPdko5U59cUaT5uP3P/S3";


    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    private VuforiaLocalizer vuforia;

    /**
     * {@link #tfod} is the variable we will use to store our instance of the TensorFlow Object
     * Detection engine.
     */
    private TFObjectDetector tfod;

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
        initVuforia();
        initTfod();

        /**
         * Activate TensorFlow Object Detection before we wait for the start command.
         * Do it here so that the Camera Stream window will have the TensorFlow annotations visible.
         **/
        if (tfod != null) {
            tfod.activate();

            // The TensorFlow software will scale the input images from the camera to a lower resolution.
            // This can result in lower detection accuracy at longer distances (> 55cm or 22").
            // If your target is at distance greater than 50 cm (20") you can adjust the magnification value
            // to artificially zoom in to the center of image.  For best results, the "aspectRatio" argument
            // should be set to the value of the images used to create the TensorFlow Object Detection model
            // (typically 1.78 or 16/9).

            // Uncomment the following line if you want to adjust the magnification and/or the aspect ratio of the input images.
            tfod.setZoom(2.5, 1.78);
        }

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();
        waitForStart();
        
        String objectCode = LABEL_NO_ElEMENT;
        int numRings = 0;
        
        if (opModeIsActive()) {
            while (opModeIsActive()) {
                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        numRings = updatedRecognitions.size();
                      //telemetry.addData("# Object Detected", numRings);
                      // step through the list of recognitions and display boundary info.
                    
                      //int i = 0;
                     //telemetry.update();
                     
                      for (Recognition recognition : updatedRecognitions) {
                        //telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                        //telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                                //recognition.getLeft(), recognition.getTop());
                        //telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                               // recognition.getRight(), recognition.getBottom());
                        objectCode = recognition.getLabel();
                      }
                      if(objectCode.equals("Quad")){
                          numRings = 4;
                      }
                      telemetry.addData("# Object Detected", numRings);
                      telemetry.update();
                    }
                }
                if(numRings == 1){
                   forwardBackEncoder(.4, 3526);
                    stopPower();
                    forwardBackEncoder(.4,-900);
                    stopPower();
                    turn(-.2);
                    sleep(200);
                    stopPower();
                    launcherMotor.setPower(0.72);
                    sleep(1000);
                    intakeMotor.setPower(.8);
                    sleep(200);
                    medianMotor.setPower(0.5);
                    sleep(4700);
                    stopPower();
                    launcherMotor.setPower(0);
                    medianMotor.setPower(0);
                    forwardBackEncoder(.5,400);
                    stopPower();
                    break;
                    /*strafeEncoder(.3,-503);
                    stopPower();
                    forwardBackEncoder(.5,3300);
                    frontLeftMotor.setPower(.2);
                    sleep(400);
                    stopPower();
                    forwardBackEncoder(.5,400);
                    stopPower();
                    forwardBackEncoder(.5,-800);
                    stopPower();
                    break;*/
                } else if(numRings == 0){
                    /*forwardBackEncoder(.5,2216);
                    stopPower();
                    sleep(100);
                    turn(-.2);
                    sleep(250);
                    stopPower();
                    intakeMotor.setPower(.5);
                    launcherMotor.setPower(.76);
                    sleep(800);
                    medianMotor.setPower(.7);
                    sleep(3200);
                    stopPower();
                    turn(.2);
                    sleep(250);
                    stopPower();
                    forwardBackEncoder(.4,302);
                    stopPower();
                    forwardBackEncoder(.7,-400);
                    stopPower();
                    strafeEncoder(.4,503);
                    stopPower();
                    forwardBackEncoder(.4,450);
                    stopPower();
                    strafeEncoder(.4,-1511);
                    stopPower();
                    strafeEncoder(.7,1511);
                    stopPower();
                    break;
                    */
                    forwardBackEncoder(.5,2416);
                    stopPower();
                    sleep(100);
                    turn(-.2);
                    sleep(200);
                    stopPower();
                    intakeMotor.setPower(.5);
                    launcherMotor.setPower(.72);
                    sleep(800);
                    medianMotor.setPower(.7);
                    sleep(3200);
                    stopPower();
                    turn(.2);
                    sleep(200);
                    stopPower();
                    forwardBackEncoder(.5,384);
                    sleep(100);
                    stopPower();
                    forwardBackEncoder(.5,-400);
                    stopPower();
                    sleep(100);
                    strafeEncoder(.5,-1026);
                    stopPower();
                    sleep(300);
                    forwardBackEncoder(.5,900);
                    stopPower();
                    sleep(200);
                    strafeEncoder(.4,1523);
                    stopPower();
                    sleep(200);
                    strafeEncoder(.4,-400);
                    forwardBackEncoder(.5,-300);
                    stopPower();
                    sleep(100);
                    break;
                    /*strafeEncoder(.5,-1500);
                    stopPower();
                    sleep(100);
                    launcherMotor.setPower(0.72);
                    sleep(700);
                    medianMotor.setPower(0.5);
                    sleep(3000);
                    forwardBackEncoder(.5,900);
                    launcherMotor.setPower(0);
                    medianMotor.setPower(0);
                    stopPower();
                    break;
                    */
                    

                    
                } else if(numRings == 4){
                    /*forwardBackEncoder(.5,2216);
                    stopPower();
                    sleep(100);
                    turn(-.2);
                    sleep(200);
                    stopPower();
                    intakeMotor.setPower(.5);
                    launcherMotor.setPower(.76);
                    sleep(800);
                    medianMotor.setPower(.7);
                    sleep(3200);
                    stopPower();
                    turn(.2);
                    stopPower();
                    forwardBackEncoder(.5,2719);
                    stopPower();
                    forwardBackEncoder(.8,-503);
                    stopPower();
                    strafeEncoder(.5,503);
                    stopPower();
                    forwardBackEncoder(.5,900);
                    stopPower();
                    strafeEncoder(.3,1510);
                    stopPower();
                    //posibility for going back and getting more rings to then shoot
                    strafeEncoder(.8,-1800);
                    forwardBackEncoder(.8,2216);
                    stopPower();
                    break;
                    */
                    forwardBackEncoder(.5,2216);
                    stopPower();
                    sleep(100);
                    turn(-.2);
                    sleep(250);
                    stopPower();
                    intakeMotor.setPower(.5);
                    launcherMotor.setPower(.76);
                    sleep(800);
                    medianMotor.setPower(.7);
                    sleep(3200);
                    stopPower();
                    turn(.2);
                    sleep(200);
                    stopPower();
                    forwardBackEncoder(.5,2584);
                    sleep(100);
                    stopPower();
                    forwardBackEncoder(.5,-500);
                    stopPower();
                    sleep(100);
                    strafeEncoder(.5,-1026);
                    stopPower();
                    sleep(100);
                    forwardBackEncoder(.5,800);
                    stopPower();
                    sleep(100);
                    strafeEncoder(.4,1572);
                    stopPower();
                    sleep(100);
                    strafeEncoder(.4,-300);
                    stopPower();
                    sleep(100);
                    forwardBackEncoder(.5,-2000);
                    stopPower();
                    sleep(100);
                    break;
                    /*strafeEncoder(.5,-1900);
                    stopPower();
                    sleep(100);
                    launcherMotor.setPower(0.70);
                    sleep(700);
                    medianMotor.setPower(0.5);
                    sleep(2500);
                    forwardBackEncoder(.5,600);
                    launcherMotor.setPower(0);
                    medianMotor.setPower(0);
                    stopPower();
                    break;
                    */
                    
                }
                
            }
                
                    
                
            }
    
   
                               
        

        if (tfod != null) {
            tfod.shutdown();
        }
    
    
    
   
    

    /**
     * Initialize the Vuforia localization engine.
     */
   } private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }

    /**
     * Initialize the TensorFlow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
            "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
       tfodParameters.minResultConfidence = 0.8f;
       tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
       tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
    }
            // Strafing Forward/Backward Method
 
    public void stopPower(){
        setAllPower(0);
    }


     // Strafing Forward/Backward Method
    public void setAllPower(double speed){
        frontLeftMotor.setPower(speed);
        frontRightMotor.setPower(speed);
        backLeftMotor.setPower(speed);
        backRightMotor.setPower(speed);
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

        
        
        