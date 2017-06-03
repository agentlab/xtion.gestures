package Tests;

import com.company.CommandsRecognizers.CommandsRecognizer;
import com.company.microphone.MicrophoneAnalyzer;
import com.company.testInterfaceCommandsListener;
import com.darkprograms.speech.microphone.Microphone;
import com.darkprograms.speech.recognizer.GoogleResponse;
import com.darkprograms.speech.recognizer.Recognizer;
import javaFlacEncoder.FLACFileWriter;

import java.io.File;
import java.io.IOException;

/**
 * Created by user on 06.02.17.
 */
public class TestFromDirectory {

    public static String pathToDirectoryWithAudio = "all_records/Converted";

    public static CommandsRecognizer commandsRecognizer = new CommandsRecognizer();

    public static TestCommandCounterAndStatistic testCommandCounterAndStatistic = new TestCommandCounterAndStatistic();

    public static void main (String[]args) throws IOException {

        commandsRecognizer.addListener(testCommandCounterAndStatistic);
        commandsRecognizer.addListener(new testInterfaceCommandsListener());

        File f = new File(pathToDirectoryWithAudio);
        File[] directory = f.listFiles();

        for (File file : directory) {
            testFromFile(file);
            System.out.println(file.getName());
        }

        testCommandCounterAndStatistic.printStats();

    }

    public static void testFromFile(File audioFile) throws IOException {

        Microphone mic = new Microphone(FLACFileWriter.FLAC);


        Recognizer recognizer = new Recognizer (Recognizer.Languages.RUSSIAN, "AIzaSyDMRFZsdncfP2udmTbozAQ2owJuL5RRm34");
        //Although auto-detect is available, it is recommended you select your region for added accuracy.
        try {
            int maxNumOfResponses = 1;
            System.out.println("Sample rate is: " + (int) mic.getAudioFormat().getSampleRate());
            GoogleResponse response = recognizer.getRecognizedDataForFlac (audioFile, maxNumOfResponses, (int) mic.getAudioFormat().getSampleRate ());
            System.out.println ("Google Response: " + response.getResponse ());
            System.out.println ("Google is " + Double.parseDouble (response.getConfidence ()) * 100 + "% confident in" + " the reply");
            System.out.println ("Other Possible responses are: ");
            for (String s:response.getOtherPossibleResponses ()) {
                System.out.println ("\t" + s);
            }

            commandsRecognizer.onResponce(response);
        }
        catch (Exception ex) {
            // TODO Handle how to respond if Google cannot be contacted
            System.out.println ("ERROR: Google cannot be contacted");
            ex.printStackTrace ();
        }



    }

}
