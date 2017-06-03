package Tests;

import com.company.microphone.MicrophoneAnalyzer;
import com.darkprograms.speech.recognizer.GoogleResponse;
import com.darkprograms.speech.recognizer.Recognizer;
import javaFlacEncoder.FLACFileWriter;

import java.io.File;
import java.io.IOException;

/**
 * Created by user on 12.01.17.
 */
public class Tests  {

    static void RunAllTests() throws IOException {

        MicrophoneAnalyzer mic = new MicrophoneAnalyzer(FLACFileWriter.FLAC);
        File audioFile = new File("/Users/user/Desktop/22/TestAudio.flac");
        //mic.setAudioFile(new File("AudioTestNow.flac"));
        mic.setAudioFile(audioFile);

        Recognizer rec = new Recognizer(Recognizer.Languages.RUSSIAN, "AIzaSyDMRFZsdncfP2udmTbozAQ2owJuL5RRm34");
        GoogleResponse response = rec.getRecognizedDataForFlac(mic.getAudioFile(), -1);
        displayResponse(response);//Displays output in Console
        System.out.println(response.getOtherPossibleResponses().size());
    }

    private static void displayResponse(GoogleResponse gr) {
        if (gr.getResponse() == null) {
            System.out.println((String) null);
            return;
        }
        System.out.println("Google Response: " + gr.getResponse());
        System.out.println("Google is " + Double.parseDouble(gr.getConfidence()) * 100 + "% confident in"
                + " the reply");
        System.out.println("Other Possible responses are: ");
        for (String s : gr.getOtherPossibleResponses()) {
            System.out.println("\t" + s);
        }
    }

}
