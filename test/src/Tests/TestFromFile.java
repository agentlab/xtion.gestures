package Tests;


import com.darkprograms.speech.microphone.Microphone;
import com.darkprograms.speech.recognizer.GoogleResponse;
import com.darkprograms.speech.recognizer.Recognizer;
import javaFlacEncoder.FLACFileWriter;

import java.io.File;
import java.io.IOException;

/**
 * Created by user on 12.01.17.
 */
public class TestFromFile {

    public static void main1(String[] args) {

        try {
            Tests.RunAllTests();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main (String[]args) {



        Microphone mic = new Microphone(FLACFileWriter.FLAC);
        File file = new File ("/Users/user/Desktop/1/1.flac");	//Name your file whatever you want


        Recognizer recognizer = new Recognizer (Recognizer.Languages.RUSSIAN, "AIzaSyDMRFZsdncfP2udmTbozAQ2owJuL5RRm34");
        //Although auto-detect is available, it is recommended you select your region for added accuracy.
        try {
            int maxNumOfResponses = 1;
            System.out.println("Sample rate is: " + (int) mic.getAudioFormat().getSampleRate());
            GoogleResponse response = recognizer.getRecognizedDataForFlac (file, maxNumOfResponses, (int) mic.getAudioFormat().getSampleRate ());
            System.out.println ("Google Response: " + response.getResponse ());
            System.out.println ("Google is " + Double.parseDouble (response.getConfidence ()) * 100 + "% confident in" + " the reply");
            System.out.println ("Other Possible responses are: ");
            for (String s:response.getOtherPossibleResponses ()) {
                System.out.println ("\t" + s);
            }
        }
        catch (Exception ex) {
            // TODO Handle how to respond if Google cannot be contacted
            System.out.println ("ERROR: Google cannot be contacted");
            ex.printStackTrace ();
        }

    }

}
