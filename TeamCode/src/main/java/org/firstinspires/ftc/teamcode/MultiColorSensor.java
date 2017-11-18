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
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.I2cAddr;


@Autonomous(name="colorsensor", group="Senior")

public class MultiColorSensor extends LinearOpMode {
    ColorSensor Color0;
    ColorSensor Color1;
    /* Declare OpMode members. */

    //public NormalizedColorSensor = null;

    @Override
    public void runOpMode() {
        Color0 = hardwareMap.colorSensor.get("LeftColor");
        Color0.setI2cAddress(I2cAddr.create7bit(0x1e)); //3c
        Color1 = hardwareMap.colorSensor.get("RightColor");
        Color1.setI2cAddress(I2cAddr.create7bit(0x26)); //4c

        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            //driveUntilGrey(.25);
            //int color = colors.toColor();
            telemetry.addLine("color: ")
                    .addData("0,r", "%.3f", Color0.red())
                    .addData("0,b", "%.3f", Color0.blue())
                    .addData("1,r", "%.3f", Color1.red())
                    .addData("1,b", "%.3f", Color1.blue());
            telemetry.update();
            sleep(10000);

            ////NormalizedRGBA colors = colorSensor.getNormalizedColors();

            /**
             *  float[] hsvValues = new float[3];
             Color.colorToHSV(colors.toColor(), hsvValues);
             telemetry.addLine()
             .addData("H", "%.3f", hsvValues[0])
             .addData("S", "%.3f", hsvValues[1])
             .addData("V", "%.3f", hsvValues[2]);
             telemetry.addLine()
             .addData("a", "%.3f", colors.alpha)
             .addData("r", "%.3f", colors.red)
             .addData("g", "%.3f", colors.green)
             .addData("b", "%.3f", colors.blue); Use telemetry to display feedback on the driver station. We show the conversion
             * of the colors to hue, saturation and value, and display the the normalized values
             * as returned from the sensor.
             * @see <a href="http://infohost.nmt.edu/tcc/help/pubs/colortheory/web/hsv.html">HSV</a>*/



        }
    }


}
