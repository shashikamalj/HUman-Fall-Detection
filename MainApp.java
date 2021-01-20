package UserModule;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.scs.analysis.*;

public class MainApp {
	public static String getUserID(Path pathName) {
		System.out.println(pathName.toString());
		String fileName = pathName.getFileName().toString();
		String userID = fileName.split("_")[1];
		return userID;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Accelerometer> accelerometers = new ArrayList<Accelerometer>();
		ReadClass readData = new ReadClass();
		ComputationGeneratorParentClass computor=new ComputationGeneratorParentClass();
		String fileNames[] = {"F03_SA01_R01.txt","F03_SA01_R02.txt","F03_SA01_R03.txt","F03_SA01_R05.txt","F03_SA01_R01.txt"};
		//for (String file : fileNames) {
			
			Path pathName = Paths.get("C:\\Users\\Chinmay\\Documents\\Masters\\SCS\\Project\\SisFall_dataset\\SisFall_dataset\\SA01\\D03_SA01_R01.txt");
			String userID = getUserID(pathName);
			User user = new User();
			String filePath = "C:\\Users\\Chinmay\\eclipse-workspace\\SCSProject_UserModule\\src\\UserModule\\UserData.json";			
			user = JSONOperations.readJSON(filePath, userID);
			
			readData.ReadData(pathName);
			Accelerometer accelerometerFirst = new Accelerometer(16,13,readData.getAcc1_x(),readData.getAcc1_y(),readData.getAcc1_z());
	        Accelerometer accelerometerSecond = new Accelerometer(8,14,readData.getAcc2_x(),readData.getAcc2_y(),readData.getAcc2_z());
			accelerometerFirst.calculateValuesOfSensorsForEachAxis();
			accelerometerSecond.calculateValuesOfSensorsForEachAxis();
	        
			accelerometers.add(accelerometerFirst);
			accelerometers.add(accelerometerSecond);
			
	        computor.analyseAlgorithm(accelerometers,user);
	        //System.out.println(file);
		//}
	}

}
