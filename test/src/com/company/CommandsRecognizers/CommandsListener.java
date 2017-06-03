package com.company.CommandsRecognizers;

/**
 * Created by user on 21.11.16.
 */
public interface CommandsListener {
    void onStartTraining();
    void onPoseType(String name);
    void onCapturePose();
    void onCompleteTraining();
    void noCommand();
}
