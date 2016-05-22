import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.primesense.nite.JointType;
import com.primesense.nite.Skeleton;
import com.primesense.nite.SkeletonJoint;

import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

public class —lassifier {
	
	private NaiveBayes m—lassifier;
	
	public —lassifier(String dataFilePath) {
		
		BufferedReader datafile = readDataFile(dataFilePath);

        Instances data = null;
        try {
            data = new Instances(datafile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        data.setClassIndex(data.numAttributes() - 1);
        
        m—lassifier = new NaiveBayes();

        try {
        	m—lassifier.buildClassifier(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
	}
	
	public String predict(Skeleton skeleton) {
		
		int attributeCount = 46;

        Attribute headX = new Attribute("headX");
        Attribute headY = new Attribute("headY");
        Attribute headZ = new Attribute("headZ");


        Attribute neckX = new Attribute("neckX");
        Attribute neckY = new Attribute("neckY");
        Attribute neckZ = new Attribute("neckZ");

        Attribute leftShoulderX = new Attribute("leftShoulderX");
        Attribute leftShoulderY = new Attribute("leftShoulderY");
        Attribute leftShoulderZ = new Attribute("leftShoulderZ");

        Attribute leftElbowX = new Attribute("leftElbowX");
        Attribute leftElbowY = new Attribute("leftElbowY");
        Attribute leftElbowZ = new Attribute("leftElbowZ");

        Attribute leftHandX = new Attribute("leftHandX");
        Attribute leftHandY = new Attribute("leftHandY");
        Attribute leftHandZ = new Attribute("leftHandZ");

        Attribute rightShoulderX = new Attribute("rightShoulderX");
        Attribute rightShoulderY = new Attribute("rightShoulderY");
        Attribute rightShoulderZ = new Attribute("rightShoulderZ");

        Attribute rightElbowX = new Attribute("rightElbowX");
        Attribute rightElbowY = new Attribute("rightElbowY");
        Attribute rightElbowZ = new Attribute("rightElbowZ");

        Attribute rightHandX = new Attribute("rightHandX");
        Attribute rightHandY = new Attribute("rightHandY");
        Attribute rightHandZ = new Attribute("rightHandZ");

        Attribute torsoX = new Attribute("torsoX");
        Attribute torsoY = new Attribute("torsoY");
        Attribute torsoZ = new Attribute("torsoZ");

        Attribute rightHipX = new Attribute("rightHipX");
        Attribute rightHipY = new Attribute("rightHipY");
        Attribute rightHipZ = new Attribute("rightHipZ");

        Attribute rightKneeX = new Attribute("rightKneeX");
        Attribute rightKneeY = new Attribute("rightKneeY");
        Attribute rightKneeZ = new Attribute("rightKneeZ");

        Attribute rightFootX = new Attribute("rightFootX");
        Attribute rightFootY = new Attribute("rightFootY");
        Attribute rightFootZ = new Attribute("rightFootZ");

        Attribute leftHipX = new Attribute("leftHipX");
        Attribute leftHipY = new Attribute("leftHipY");
        Attribute leftHipZ = new Attribute("leftHipZ");

        Attribute leftKneeX = new Attribute("leftKneeX");
        Attribute leftKneeY = new Attribute("leftKneeY");
        Attribute leftKneeZ = new Attribute("leftKneeZ");

        Attribute leftFootX = new Attribute("leftFootX");
        Attribute leftFootY = new Attribute("leftFootY");
        Attribute leftFootZ = new Attribute("leftFootZ");

        FastVector fvClassVal = new FastVector(3);
        fvClassVal.addElement("stay");
        fvClassVal.addElement("seat");
        fvClassVal.addElement("hello");
        fvClassVal.addElement("phone");

        Attribute state = new Attribute("state", fvClassVal);


        // Declare the feature vector
        FastVector fvWekaAttributes = new FastVector(attributeCount);
        // Add attributes
        fvWekaAttributes.addElement(headX);
        fvWekaAttributes.addElement(headY);
        fvWekaAttributes.addElement(headZ);

        fvWekaAttributes.addElement(neckX);
        fvWekaAttributes.addElement(neckY);
        fvWekaAttributes.addElement(neckZ);

        fvWekaAttributes.addElement(leftShoulderX);
        fvWekaAttributes.addElement(leftShoulderY);
        fvWekaAttributes.addElement(leftShoulderZ);

        fvWekaAttributes.addElement(leftElbowX);
        fvWekaAttributes.addElement(leftElbowY);
        fvWekaAttributes.addElement(leftElbowZ);

        fvWekaAttributes.addElement(leftHandX);
        fvWekaAttributes.addElement(leftHandY);
        fvWekaAttributes.addElement(leftHandZ);

        fvWekaAttributes.addElement(rightShoulderX);
        fvWekaAttributes.addElement(rightShoulderY);
        fvWekaAttributes.addElement(rightShoulderZ);

        fvWekaAttributes.addElement(rightElbowX);
        fvWekaAttributes.addElement(rightElbowY);
        fvWekaAttributes.addElement(rightElbowZ);

        fvWekaAttributes.addElement(rightHandX);
        fvWekaAttributes.addElement(rightHandY);
        fvWekaAttributes.addElement(rightHandZ);

        fvWekaAttributes.addElement(torsoX);
        fvWekaAttributes.addElement(torsoY);
        fvWekaAttributes.addElement(torsoZ);

        fvWekaAttributes.addElement(rightHipX);
        fvWekaAttributes.addElement(rightHipY);
        fvWekaAttributes.addElement(rightHipZ);

        fvWekaAttributes.addElement(rightKneeX);
        fvWekaAttributes.addElement(rightKneeY);
        fvWekaAttributes.addElement(rightKneeZ);

        fvWekaAttributes.addElement(rightFootX);
        fvWekaAttributes.addElement(rightFootY);
        fvWekaAttributes.addElement(rightFootZ);

        fvWekaAttributes.addElement(leftHipX);
        fvWekaAttributes.addElement(leftHipY);
        fvWekaAttributes.addElement(leftHipZ);

        fvWekaAttributes.addElement(leftKneeX);
        fvWekaAttributes.addElement(leftKneeY);
        fvWekaAttributes.addElement(leftKneeZ);

        fvWekaAttributes.addElement(leftFootX);
        fvWekaAttributes.addElement(leftFootY);
        fvWekaAttributes.addElement(leftFootZ);

        fvWekaAttributes.addElement(state);

        // Declare Instances which is required since I want to use classification/Prediction
        Instances dataset = new Instances("gesture", fvWekaAttributes, 0);

        //Create the new instance i1
        Instance i1 = new Instance(attributeCount);

        SkeletonJoint HeadPosition = skeleton.getJoint(JointType.HEAD);
        i1.setValue(headX, HeadPosition.getPosition().getX());
        i1.setValue(headY, HeadPosition.getPosition().getY());
        i1.setValue(headZ, HeadPosition.getPosition().getZ());
        
        SkeletonJoint NeckPosition = skeleton.getJoint(JointType.NECK);
        i1.setValue(neckX, NeckPosition.getPosition().getX());
        i1.setValue(neckY, NeckPosition.getPosition().getY());
        i1.setValue(neckZ, NeckPosition.getPosition().getZ());
        
        SkeletonJoint LeftShoulderP = skeleton.getJoint(JointType.LEFT_SHOULDER);
        i1.setValue(leftShoulderX, LeftShoulderP.getPosition().getX());
        i1.setValue(leftShoulderY, LeftShoulderP.getPosition().getY());
        i1.setValue(leftShoulderZ, LeftShoulderP.getPosition().getZ());
        
        SkeletonJoint LeftElbowP = skeleton.getJoint(JointType.LEFT_ELBOW);
        i1.setValue(leftElbowX, LeftElbowP.getPosition().getX());
        i1.setValue(leftElbowY, LeftElbowP.getPosition().getY());
        i1.setValue(leftElbowZ, LeftElbowP.getPosition().getZ());
        
        SkeletonJoint LeftHandP = skeleton.getJoint(JointType.LEFT_HAND);
        i1.setValue(leftHandX, LeftHandP.getPosition().getX());
        i1.setValue(leftHandY, LeftHandP.getPosition().getY());
        i1.setValue(leftHandZ, LeftHandP.getPosition().getZ());
        
        SkeletonJoint rightShoulderP = skeleton.getJoint(JointType.RIGHT_SHOULDER);
        i1.setValue(rightShoulderX, rightShoulderP.getPosition().getX());
        i1.setValue(rightShoulderY, rightShoulderP.getPosition().getY());
        i1.setValue(rightShoulderZ, rightShoulderP.getPosition().getZ());
        
        SkeletonJoint rightElbowP = skeleton.getJoint(JointType.RIGHT_ELBOW);
        i1.setValue(rightElbowX, rightElbowP.getPosition().getX());
        i1.setValue(rightElbowY, rightElbowP.getPosition().getY());
        i1.setValue(rightElbowZ, rightElbowP.getPosition().getZ());
        
        SkeletonJoint rightHandP = skeleton.getJoint(JointType.RIGHT_HAND);
        i1.setValue(rightHandX, rightHandP.getPosition().getX());
        i1.setValue(rightHandY, rightHandP.getPosition().getY());
        i1.setValue(rightHandZ, rightHandP.getPosition().getZ());
        
        SkeletonJoint torsoP = skeleton.getJoint(JointType.TORSO);
        i1.setValue(torsoX, torsoP.getPosition().getX());
        i1.setValue(torsoY, torsoP.getPosition().getY());
        i1.setValue(torsoZ, torsoP.getPosition().getZ());
        
        SkeletonJoint rightHipP = skeleton.getJoint(JointType.RIGHT_HIP);
        i1.setValue(rightHipX, rightHipP.getPosition().getX());
        i1.setValue(rightHipY, rightHipP.getPosition().getY());
        i1.setValue(rightHipZ, rightHipP.getPosition().getZ());
        
        SkeletonJoint rightKneeP = skeleton.getJoint(JointType.RIGHT_KNEE);
        i1.setValue(rightKneeX, rightKneeP.getPosition().getX());
        i1.setValue(rightKneeY, rightKneeP.getPosition().getY());
        i1.setValue(rightKneeZ, rightKneeP.getPosition().getZ());
        
        SkeletonJoint rightFootP = skeleton.getJoint(JointType.RIGHT_FOOT);
        i1.setValue(rightFootX, rightFootP.getPosition().getX());
        i1.setValue(rightFootY, rightFootP.getPosition().getY());
        i1.setValue(rightFootZ, rightFootP.getPosition().getZ());
        
        SkeletonJoint leftHipP = skeleton.getJoint(JointType.LEFT_HIP);
        i1.setValue(leftHipX, leftHipP.getPosition().getX());
        i1.setValue(leftHipY, leftHipP.getPosition().getY());
        i1.setValue(leftHipZ, leftHipP.getPosition().getZ());
        
        SkeletonJoint leftKneeP = skeleton.getJoint(JointType.LEFT_KNEE);
        i1.setValue(leftKneeX, leftKneeP.getPosition().getX());
        i1.setValue(leftKneeY, leftKneeP.getPosition().getY());
        i1.setValue(leftKneeZ, leftKneeP.getPosition().getZ());
        
        SkeletonJoint leftFootP = skeleton.getJoint(JointType.LEFT_FOOT);
        i1.setValue(leftFootX, leftFootP.getPosition().getX());
        i1.setValue(leftFootY, leftFootP.getPosition().getY());
        i1.setValue(leftFootZ, leftFootP.getPosition().getZ());
        
        i1.setValue(state, "stay");

        //Add the instance to the dataset (Instances) (first element 0)
        dataset.add(i1);
        
        System.out.println(dataset);

        //Define class attribute position
        dataset.setClassIndex(dataset.numAttributes()-1);

        for (int i = 0; i < dataset.numInstances(); i++) {
            double pred = 0;
            try {
            	Instance _inst = dataset.instance(i);
                pred = m—lassifier.classifyInstance(dataset.instance(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.print("ID: " + dataset.instance(i).value(0));
            System.out.print(", actual: " + dataset.classAttribute().value((int) dataset.instance(i).classValue()));
            System.out.println(", predicted: " + dataset.classAttribute().value((int) pred));
        
        
        String currentState = (dataset.classAttribute().value((int) pred)).toString();
        
		return currentState;
		
        }
        
        return null;
	}
	
    private static BufferedReader readDataFile(String filename) {
        BufferedReader inputReader = null;

        try {
            inputReader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException ex) {
            System.err.println("File not found: " + filename);
        }

        return inputReader;
    }

}
