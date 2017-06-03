package com.company.CommandsRecognizers;

import com.company.RecordingThread.ResponseListener;
import com.darkprograms.speech.recognizer.GoogleResponse;
import info.debatty.java.stringsimilarity.JaroWinkler;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

/**
 * Created by user on 21.11.16.
 */
public class CommandsRecognizer implements ResponseListener {

    private enum  Commands {
        startTraining,
        poseType,
        capturePose,
        completeTraining
    }

    private List<CommandsListener> listeners;

    private EnumMap<Commands, String> commands;
    private EnumMap<Commands, Double> commandsSimilarity;

    private JaroWinkler jaroWinkler;

    private static final String startTraining = "начать обучение";
    private static final String startTraining2 = "обучение";
    private static final String poseType = "новая поза ";
    private static final String capturePose = "фиксация";
    private static final String completeTraining = "завершить";



    public CommandsRecognizer() {
        jaroWinkler = new JaroWinkler();
        commands = new EnumMap<Commands, String>(Commands.class);
        commandsSimilarity = new EnumMap<Commands, Double>(Commands.class);

        commands.put(Commands.startTraining, startTraining);
        commands.put(Commands.capturePose, capturePose);
        commands.put(Commands.poseType, poseType);
        commands.put(Commands.completeTraining, completeTraining);

        listeners = new ArrayList<CommandsListener>();
    }

    public void addListener(CommandsListener listener) {
        listeners.add(listener);
    }


    public void onResponce(GoogleResponse response) {
        List<String> responces =  response.getOtherPossibleResponses();

        double startTrainingSimilarity = 0;
        double poseTypeSimilarity = 0;
        double capturePoseSimilarity = 0;
        double completeTrainingSimilarity = 0;


        for(String responce : responces ) {
            if(responce != null) {

                if(startTrainingSimilarity < jaroWinkler.similarity(responce, startTraining)) {
                    startTrainingSimilarity = jaroWinkler.similarity(responce, startTraining);
                }

                if(startTrainingSimilarity < jaroWinkler.similarity(responce, startTraining2)) {
                    startTrainingSimilarity = jaroWinkler.similarity(responce, startTraining2);
                }

                if(poseTypeSimilarity < jaroWinkler.similarity(responce, poseType)) {
                    poseTypeSimilarity = jaroWinkler.similarity(responce, poseType);
                }

                if(capturePoseSimilarity < jaroWinkler.similarity(responce, capturePose)) {
                    capturePoseSimilarity = jaroWinkler.similarity(responce, capturePose);
                }

                if(completeTrainingSimilarity < jaroWinkler.similarity(responce, completeTraining)) {
                    completeTrainingSimilarity = jaroWinkler.similarity(responce, completeTraining);
                }
            }
        }

        commandsSimilarity.put(Commands.startTraining, startTrainingSimilarity);
        commandsSimilarity.put(Commands.poseType, poseTypeSimilarity);
        commandsSimilarity.put(Commands.capturePose, capturePoseSimilarity);
        commandsSimilarity.put(Commands.completeTraining, completeTrainingSimilarity);

        RecognizedCommand recognizedCommand = new RecognizedCommand();

        for (Commands command : Commands.values()) {
            if(recognizedCommand.Similarity < commandsSimilarity.get(command)) {
                recognizedCommand.command = command;
                recognizedCommand.Similarity = commandsSimilarity.get(command);

            }
            System.out.println(command + " " + commandsSimilarity.get(command));
        }

        double tresHold = 0.4;

        if(recognizedCommand.Similarity > tresHold) {
            for (CommandsListener listener : listeners) {
                sendCommand(listener, recognizedCommand.command);
            }
        } else {
            for (CommandsListener listener : listeners) {
                sendNoCommand(listener);
            }
        }
    }

    private void sendNoCommand(CommandsListener listener) {
        listener.noCommand();
    }

    private void sendCommand(CommandsListener listener, Commands command) {
        switch (command) {
            case startTraining:
                listener.onStartTraining();
                break;
            case poseType:
                listener.onPoseType("New name");
                break;
            case capturePose:
                listener.onCapturePose();
                break;
            case completeTraining:
                listener.onCompleteTraining();
                break;
        }
    }


    class RecognizedCommand {
        public Commands command = null;
        public double Similarity = 0;
    }
}
