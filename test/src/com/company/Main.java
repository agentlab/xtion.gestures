package com.company;

import Tests.TestCommandCounterAndStatistic;
import com.company.CommandsRecognizers.CommandsRecognizer;
import com.company.CommandsRecognizers.DisplayAllReponce;
import com.company.RecordingThread.RecordingThread;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Main {

    public static TestCommandCounterAndStatistic testCommandCounterAndStatistic = new TestCommandCounterAndStatistic();

    public static void main (String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        //RecordingThread recordingThread = new RecordingThread();
        //File AudioStream = new File("/Users/user/IdeaProjects/test_google_speach_api/all_records/Converted/4.wav");
        RecordingThread recordingThread = new RecordingThread();
        CommandsRecognizer commandsRecognizer = new CommandsRecognizer();

        recordingThread.addResponceListener(new DisplayAllReponce());
        recordingThread.addResponceListener(commandsRecognizer);

        commandsRecognizer.addListener(new testInterfaceCommandsListener());
        commandsRecognizer.addListener(testCommandCounterAndStatistic);

        recordingThread.start();
    }
}