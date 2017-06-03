package com.company;

import com.company.CommandsRecognizers.CommandsListener;

/**
 * Created by user on 21.11.16.
 */
public class testInterfaceCommandsListener implements CommandsListener {
    public void onStartTraining() {
        System.out.println("**************");
        System.out.println("Start Training");
        System.out.println("**************");
    }

    public void onPoseType(String name) {
        System.out.println("**************");
        System.out.println(name);
        System.out.println("**************");
    }

    public void onCapturePose() {
        System.out.println("**************");
        System.out.println("Pose Fixation");
        System.out.println("**************");
    }

    public void onCompleteTraining() {
        System.out.println("**************");
        System.out.println("End Training");
        System.out.println("**************");
    }

    
    public void noCommand() {
        System.out.println("**************");
        System.out.println("No Command");
        System.out.println("**************");
    }
}
