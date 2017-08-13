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
package club.towr5291.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import club.towr5291.robotconfig.HardwarePushbot;
/**
 * This file provides basic Telop driving for a Pushbot robot.
 * The code is structured as an Iterative OpMode
 *
 * This OpMode uses the common Pushbot hardware class to define the devices on the robot.
 * All device access is managed through the HardwarePushbot class.
 *
 * This particular OpMode executes a basic Tank Drive Teleop for a PushBot
 * It raises and lowers the claw using the Gampad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Pushbot: Teleop Tank", group="5291Test")
@Disabled
public class PushbotTeleopTank_Iterative extends OpMode{

    public DcMotor  leftMotor   = null;
    public DcMotor  rightMotor  = null;
    /* Declare OpMode members. */
    HardwarePushbot robot       = new HardwarePushbot(); // use the class created to define a Pushbot's hardware
                                                         // could also use HardwarePushbotMatrix class.
//    double          clawOffset  = 0.0 ;                  // Servo mid position
//    final double    CLAW_SPEED  = 0.02 ;                 // sets rate to move servo


    /*
     * Code to run ONCE when the driver hits INIT
     */
    
    public void init(HardwareMap ahwMap) {


        leftMotor   = hardwareMap.dcMotor.get("left_drive");
        rightMotor  = hardwareMap.dcMotor.get("right_drive");
//        armMotor    = hwMap.dcMotor.get("left_arm");

        // Set all motors to zero power
        leftMotor.setPower(0);
        rightMotor.setPower(0);
//        armMotor.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void init() {

    }

    /*
         * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
         */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        double left;
        double right;
        boolean reverse = false;


        left = -gamepad1.left_stick_y;
        right = -gamepad1.right_stick_y;
        // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
        if (!reverse) {
            leftMotor.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
            rightMotor.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors

            robot.leftMotor.setPower(left);
            robot.rightMotor.setPower(right);


            updateTelemetry(telemetry);
        } else {
            leftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
            rightMotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

            robot.leftMotor.setPower(left);
            robot.rightMotor.setPower(right);
        }

        telemetry.addData("left", "%.2f", left);
        telemetry.addData("right", "%.2f", right);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}