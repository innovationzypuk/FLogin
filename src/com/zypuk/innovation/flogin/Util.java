/*
-------------------------------------------------------------------------------
GrFinger Sample
(c) 2006 Griaule Tecnologia Ltda.
http://www.griaule.com
-------------------------------------------------------------------------------

This sample is provided with "GrFinger Fingerprint Recognition Library" and
can't run without it. It's provided just as an example of using GrFinger
Fingerprint Recognition Library and should not be used as basis for any
commercial product.

Griaule Tecnologia makes no representations concerning either the merchantability
of this software or the suitability of this sample for any particular purpose.

THIS SAMPLE IS PROVIDED BY THE AUTHOR "AS IS" AND ANY EXPRESS OR
IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
IN NO EVENT SHALL GRIAULE BE LIABLE FOR ANY DIRECT, INDIRECT,
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

You can download the free version of GrFinger directly from Griaule website.

These notices must be retained in any copies of any part of this
documentation and/or sample.

-------------------------------------------------------------------------------
 */
// -----------------------------------------------------------------------------------
// Support and fingerprint management routines
// -----------------------------------------------------------------------------------
package com.zypuk.innovation.flogin;

import java.awt.Color;
import java.util.Vector;

import com.griaule.grFinger.Context;
import com.griaule.grFinger.FingerCallBack;
import com.griaule.grFinger.FingerprintImage;
import com.griaule.grFinger.FingerprintTemplate;
import com.griaule.grFinger.GrErrorException;
import com.griaule.grFinger.GrFinger;
import com.griaule.grFinger.GrFingerAppletInstaller;
import com.griaule.grFinger.ImageCallBack;
import com.griaule.grFinger.MatchParameters;
import com.griaule.grFinger.MatchingResult;
import com.griaule.grFinger.StatusCallBack;

public class Util implements StatusCallBack, ImageCallBack, FingerCallBack {

    private GrFinger grFinger = null;
    private FormMain ui;
    private boolean autoIdentify = false;
    private boolean autoExtract = true;
    private FingerprintImage fingerprint; // The last acquired image.
    private FingerprintTemplate template; // The template extracted from the acquired image.
    private Vector templateVector;
    DatabaseOP dbop = null;
    private GrFingerAppletInstaller installer = null;

    public Util(FormMain extformMain) throws Exception {
        ui = extformMain;
        ui.setVisible(true);

        // Initializes GrFinger DLL and all necessary utilities.
        try {
            dbop = new DatabaseOP();
            // Setting default parameters
            String licenseFile = "GrFingerLicenseAgreementLightTrial.txt";
            String DllZipName = "GrFingerDLLs.zip";
            String urlInstallSourceDir = "";

            //Getting all Input Web Parameters
            String inputUrlDir = ui.getParameter("urlInstallDir");
            if (inputUrlDir != null) {
                urlInstallSourceDir = inputUrlDir;
            }
            String inputZipName = ui.getParameter("DllZipName");
            if (inputZipName != null) {
                DllZipName = inputZipName;
            }
            String inputLicenseFile = ui.getParameter("licenseFile");
            if (inputLicenseFile != null) {
                licenseFile = inputLicenseFile;
            }

            // Creating a installer with the Acquired Data
            installer =
                    new GrFingerAppletInstaller(urlInstallSourceDir, DllZipName);

            // Copy licenses
            installer.copyLicense(licenseFile);

            //Copy Zip File And Extract it
            installer.copyAndExtractZip();

            // Writing on Log about
            ui.writeLog("Libraries loaded.");

            grFinger = installer.getGrFinger();

            ui.writeLog("GrFinger installed.");
            templateVector = new Vector();
            start();
        } catch (Exception e) {
//			 write error to log
            throw e;
        }
    }

    public void start() {
        try {
//			 Initializing GrCapture.
            grFinger.initializeCapture(this);
            ui.writeLog("**GrFinger Initialized Successfull**");
        } catch (GrErrorException e) {
//			 write error to log
            ui.writeLog(e.getMessage());
        }

    }

    public FingerprintImage createFingerprintImage(JpegPixelGrabber px, String resolition) {
        return new FingerprintImage(grFinger, px.getPixels(), px.getWidth(), px.getHeight(), Integer.parseInt(resolition));
    }

    /* (non-Javadoc)
     * @see griaule.grFinger.StatusCallBack#onPlug(java.lang.String)
     */
//	 This function is called every time a fingerprint reader is plugged.
    public void onPlug(String idSensor) {
        ui.writeLog("Sensor: " + idSensor + ". Event: Plugged.");
        try {
//			 Start capturing from plugged sensor.
            grFinger.startCapture(idSensor, this, this);
        } catch (GrErrorException e) {
//			 write error to log
            ui.writeLog(e.getMessage());
        }

    }

    /* (non-Javadoc)
     * @see griaule.grFinger.StatusCallBack#onUnplug(java.lang.String)
     */
//	 This function is called every time a fingerprint reader is unplugged.
    public void onUnplug(String idSensor) {
        ui.writeLog("Sensor: " + idSensor + ". Event: Unplugged.");
        try {
//			 Stop capturing from unplugged sensor.
            grFinger.stopCapture(idSensor);
        } catch (GrErrorException e) {
//			 write error to log
            ui.writeLog(e.getMessage());
        }

    }

    /* (non-Javadoc)
     * @see griaule.grFinger.ImageCallBack#onImage(java.lang.String, griaule.grFinger.FingerprintImage)
     */
//	 This function is called every time a finger image is captured
    public void onImage(String idSensor, FingerprintImage fingerprint) {
        // Signaling that an Image Event occurred.
        ui.writeLog("Sensor: " + idSensor + ". Event: Image Captured.");
        try {
//			 Display fingerprint image
            ui.showImage(fingerprint.newImageProducer());
        } catch (GrErrorException e) {
//			 write error to log
            ui.writeLog(e.getMessage());
        }
        this.fingerprint = fingerprint;
//		 now we have a fingerprint, so we can extract the template
        ui.enableImage();
        if (autoExtract) {
//			extracting template from image.
            extract();
//			identify fingerprint
            if (autoIdentify) {
                identify();
            }
        }

    }

    /* (non-Javadoc)
     * @see griaule.grFinger.FingerCallBack#onFingerDown(java.lang.String)
     */
//	 This Function is called every time a finger is placed on sensor.
    public void onFingerDown(String idSensor) {
        // Just signals that a finger event ocurred.
        ui.writeLog("Sensor: " + idSensor + ". Event: Finger Placed.");

    }

    /* (non-Javadoc)
     * @see griaule.grFinger.FingerCallBack#onFingerUp(java.lang.String)
     */
//	 This Function is called every time a finger is removed from sensor.
    public void onFingerUp(String idSensor) {
        // Just signals that a finger event ocurred.
        ui.writeLog("Sensor: " + idSensor + ". Event: Finger Removed.");

    }

//	 Add a fingerprint template to database
    public void enroll(String uname, String pwd) {
        // Adds template to database and gets the ID.
        //int id = templateVector.size();
        //templateVector.add(id,template);
        try {
            dbop.insert(uname, template.getData(), pwd);
            ui.writeLog("Fingerprint enrolled");
        } catch (Exception ex) {
            ui.writeLog("Failed to add user");
        }

    }

//	 Check current fingerprint against another one in our database
    public int verify() throws GrErrorException {
        int result = dbop.authenticate(ui, grFinger, template);
        if (result > 20) {
            ui.writeLog("Matched with score = " + result + ".");
//					 if they match, display matching minutiae/segments/directions
            ui.showImage(grFinger.getBiometricDisplay(template, fingerprint, Context.DEFAULT_CONTEXT));
            return result;
        } else {
            ui.writeLog("Did not match with score ");
            return -1;
        }


    }

//	 Identify current fingerprint on our database
    public void identify() {
        try {
            // Starting identification process and supplying query template.
            grFinger.identifyPrepare(template, Context.DEFAULT_CONTEXT);
//			 Getting enrolled templates from database.
            // Iterate over all templates in database
            for (int i = 0; i < templateVector.size(); i++) {
                // Create a new Template
                FingerprintTemplate referenceTamplate = (FingerprintTemplate) templateVector.get(i);
//				 Comparing current template.
                MatchingResult result = grFinger.identify(referenceTamplate, Context.DEFAULT_CONTEXT);
//				 Checking if query template and reference template match.
                if (result.doesMatched()) {
                    ui.writeLog("Fingerprint identified. ID = " + i + ". Score = " + result.getScore() + ".");
                    // if they match, display matching minutiae/segments/directions
                    ui.showImage(grFinger.getBiometricDisplay(template, fingerprint, Context.DEFAULT_CONTEXT));
                    return;
                }
            }
            ui.writeLog("Fingerprint not found.");


        } catch (GrErrorException e) {
//			 write error to log
            ui.writeLog(e.getMessage());
        }
    }

//	 Extract a fingerprint template from current image
    public void extract() {

        try {
            template = grFinger.extract(fingerprint, Context.DEFAULT_CONTEXT);
            String msg = "Template extracted successfully. ";
//			 write template quality to log
            switch (template.getImageQuality()) {
                case FingerprintTemplate.GR_HIGH_QUALITY:
                    msg += "High quality.";
                    break;
                case FingerprintTemplate.GR_MEDIUM_QUALITY:
                    msg += "Medium quality.";
                    break;
                case FingerprintTemplate.GR_BAD_QUALITY:
                    msg += "Bad quality.";
                    break;
            }
            ui.writeLog(msg);
            ui.enableTemplate();
//			 display minutiae/segments/directions into image
            ui.showImage(grFinger.getBiometricDisplay(template, fingerprint, Context.GR_NO_CONTEXT));
        } catch (GrErrorException e) {
//			 write error to log
            ui.writeLog(e.getMessage());
        }

    }

    public void clearDB() {
        if (templateVector != null) {
            templateVector.clear();
            ui.writeLog("Memory is clear...");
        }
    }

    public void setAutoIdentify(boolean state) {
        autoIdentify = state;
    }

    public void setAutoExtract(boolean state) {
        autoExtract = state;
    }

    public void stop() {
        finalize();
    }

    protected void finalize() {
//		 Finalize library and close DB.
        try {
            if (grFinger != null) {
                grFinger.finalize();
            }
        } catch (Throwable t) {
        }
        if (installer != null) {
            installer.finalize();
        }
        grFinger = null;
        installer = null;
    }

    public void saveToFile(String fileName) {
        try {
            // Save image.
            fingerprint.saveToFile(fileName, FingerprintImage.GRCAP_IMAGE_FORMAT_BMP);
        } catch (GrErrorException e) {
//			 write error to log
            ui.writeLog(e.getMessage());
        }

    }

    public void loadFile(String fileName, int resolution) {
        try {
//			 Load a fingerprint image from a file
            grFinger.loadImageFromFile(fileName, resolution);
        } catch (GrErrorException e) {
//			 write error to log
            ui.writeLog(e.getMessage());
        }

    }

    public void setParemeters(int identifyThreshold, int identifyRotationTolerance, int verifyThreshold, int verifyRotationTolorance) {
        try {
            if (grFinger.setIdentifyParameters(new MatchParameters(identifyThreshold, identifyRotationTolerance), Context.DEFAULT_CONTEXT) == GrFinger.GR_DEFAULT_USED) {
                ui.writeLog("Invalid identify parameters. Default values used.");
            }
            if (grFinger.setVerifyParameters(new MatchParameters(verifyThreshold, verifyRotationTolorance), Context.DEFAULT_CONTEXT) == GrFinger.GR_DEFAULT_USED) {
                ui.writeLog("Invalid verify parameters. Default values used.");
            }
        } catch (GrErrorException e) {
//			 write error to log
            ui.writeLog(e.getMessage());
        }


    }

//	 get current verification parameters
    public MatchParameters getVerifyParameters() {
        try {
            return grFinger.getVerifyParameters(Context.DEFAULT_CONTEXT);
        } catch (GrErrorException e) {
//			 write error to log
            ui.writeLog(e.getMessage());
        }
        return new MatchParameters(0, 0);
    }

//	 get current identification parameters
    public MatchParameters getIdentifyParameters() {
        try {
            return grFinger.getIdentifyParameters(Context.DEFAULT_CONTEXT);
        } catch (GrErrorException e) {
//			 write error to log
            ui.writeLog(e.getMessage());
        }
        return new MatchParameters(0, 0);
    }

    public void setBiometricDisplayColors(Color minutiaeColor, Color minutiaeMatchColor,
            Color segmentColor, Color segmentMatchColor,
            Color directionColor, Color directionMatchColor) {

        try {
            // set new colors for BiometricDisplay
            grFinger.setBiometricDisplayColors(minutiaeColor, minutiaeMatchColor, segmentColor,
                    segmentMatchColor, directionColor, directionMatchColor);
        } catch (GrErrorException e) {
//			 write error to log
            ui.writeLog(e.getMessage());
        }

    }

    public String getGrFingerVersion() {
//		 Getting GrFinger version and type
        try {
            return grFinger.getVersion().toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
