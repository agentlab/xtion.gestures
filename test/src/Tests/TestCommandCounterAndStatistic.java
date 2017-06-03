package Tests;


import com.company.CommandsRecognizers.CommandsListener;

/**
 * Created by user on 06.02.17.
 */
public class TestCommandCounterAndStatistic implements CommandsListener {

    private int correctCommandCounter = 0;
    private int PoseTypecommandCounter = 0;
    private int onCapturePoseCounter = 0;
    private int onCompleteTraining = 0;
    private int noCommand = 0;
    private int commandCounter = 0;


    public void onStartTraining() {
        correctCommandCounter += 1;
        commandCounter+=1;
    }

    
    public void onPoseType(String name) {
        PoseTypecommandCounter+=1;
        commandCounter+=1;
    }

    
    public void onCapturePose() {
        onCapturePoseCounter+=1;
        commandCounter+=1;
    }

    
    public void onCompleteTraining() {
        onCompleteTraining+=1;
        commandCounter+=1;
    }

    
    public void noCommand() {
        noCommand+=1;
        commandCounter+=1;
    }

    public void printStats() {
        System.out.println("Stats");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Start " + correctCommandCounter);
        System.out.println("Pose " + PoseTypecommandCounter);
        System.out.println("Fix " + onCapturePoseCounter);
        System.out.println("End " + onCompleteTraining);
        System.out.println("No Command " + noCommand);
        System.out.println("All " + commandCounter);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }
}
