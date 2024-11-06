package DCS_FuzzyLab.OETPN_C;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

//For testing the PI controller alone
public class PIControllerInput {
	public static void main(String[] args) throws InterruptedException, IOException {
		File file = new File("D:\\PetriInputData\\PIController.txt");
		Files.deleteIfExists(file.toPath());
		FileWriter fw = new FileWriter(file.getPath());
		Float a, b, c, d, command, currentStatus, x, xNew;
		a = 0.5f;
		b = 0.7f;
		c = 0.2f;
		d = 0.3f;
		command = 0.55f;
		x = 0.0f;
		for (float i = 0; i < 100; i++) {
			if (i > 50)
				command = 0.35f;
			xNew = a * x + b * command;
			currentStatus = c * x + d * command;
			x = xNew;
			fw.write("P2:" + currentStatus + "F" + "," + "P4:" + command + "F\n"); 
		}
		fw.close();
		System.out.println("Done!");
	}
}
