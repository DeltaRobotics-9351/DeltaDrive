package com.github.deltarobotics9351.deltadrive.extendable.opmodes.linear.mecanum;

import com.github.deltarobotics9351.deltadrive.drive.mecanum.EncoderDriveMecanum;
import com.github.deltarobotics9351.deltadrive.drive.mecanum.hardware.DeltaHardwareMecanum;
import com.github.deltarobotics9351.deltadrive.parameters.EncoderDriveParameters;
import com.github.deltarobotics9351.deltadrive.utils.Invert;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Remember to override defineHardware() and define the 4 DcMotor variables in there!
 */
public class EncoderMecanumLinearOpMode extends LinearOpMode {

    private EncoderDriveMecanum encoderDrive;

    private DeltaHardwareMecanum deltaHardware;

    public EncoderDriveParameters encoderParameters = new EncoderDriveParameters();

    public DcMotor frontLeft = null;
    public DcMotor frontRight = null;
    public DcMotor backLeft = null;
    public DcMotor backRight = null;

    /**
     * Enum that defines which side of the chassis will be inverted (motors)
     */
    public Invert WHEELS_INVERT = Invert.RIGHT_SIDE;

    /**
     * boolean that defines if motors brake when their power is 0
     */
    public boolean WHEELS_BRAKE = true;


    @Override
    public final void runOpMode() {
        defineHardware();

        if(frontLeft == null || frontRight == null || backLeft == null || backRight == null){
            telemetry.addData("[/!\\]", "OpMode will not start in order to avoid Robot Controller crash.");
            telemetry.addData("frontLeft", (frontLeft == null) ? "is null" : "OK");
            telemetry.addData("frontRight", (frontRight == null) ? "is null" : "OK");
            telemetry.addData("backLeft", (backLeft == null) ? "is null" : "OK");
            telemetry.addData("backRight", (backRight == null) ? "is null" : "OK");
            telemetry.addData("POSSIBLE SOLUTION 1", "Override defineHardware() method in your OpMode class and\ndefine the null motor variables specified above.");
            telemetry.addData("POSSIBLE SOLUTION 2", "Check that all your motors are correctly named and\nthat they are get from the hardwareMap");
            telemetry.update();
            while(opModeIsActive());
            return;
        }

        deltaHardware = new DeltaHardwareMecanum(hardwareMap, WHEELS_INVERT);

        deltaHardware.initHardware(frontLeft, frontRight, backLeft, backRight, WHEELS_BRAKE);

        encoderDrive = new EncoderDriveMecanum(deltaHardware, telemetry, encoderParameters);

        Thread t = new Thread(new ParametersCheck());

        t.start();

        _runOpMode();
    }


    /**
     * Overridable void to be executed after all required variables are initialized
     * (Remember to override defineHardware() and define the 4 DcMotor variables in there!)
     */
    public void _runOpMode(){

    }

    /**
     * Overridable void to define all wheel motors.
     * Define frontLeft, frontRight, backLeft and backRight DcMotor variables here!
     */
    public void defineHardware(){

    }

    public final void forward(double inches, double speed, double timeOutSecs){
        encoderDrive.forward(inches, speed, timeOutSecs);
    }

    public final void backwards(double inches, double speed, double timeOutSecs){
        encoderDrive.backwards(inches, speed, timeOutSecs);
    }

    public final void strafeLeft(double inches, double speed, double timeOutSecs){
        encoderDrive.strafeLeft(inches, speed, timeOutSecs);
    }

    public final void strafeRight(double inches, double speed, double timeOutSecs){
        encoderDrive.strafeRight(inches, speed, timeOutSecs);
    }

    public final void turnLeft(double inches, double speed, double timeOutSecs){
        encoderDrive.turnLeft(inches, speed, timeOutSecs);
    }

    public final void turnRight(double inches, double speed, double timeOutSecs){
        encoderDrive.turnRight(inches, speed, timeOutSecs);
    }

    class ParametersCheck implements Runnable{

        @Override
        public void run(){
            waitForStart();
            if(!encoderParameters.haveBeenDefined()) {
                telemetry.addData("[/!\\]", "Remember to define encoder constants, encoder functions will not work because parameters are 0 by default. ");
            }
            telemetry.update();
        }
    }

}
