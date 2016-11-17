import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Main m = new Main();

		BufferedReader reader = new BufferedReader(new FileReader(args[0]));

		StringBuilder out = new StringBuilder();
		String line;
		boolean firstLine = true;
		RobotManager robotManager = null;

		while ((line = reader.readLine()) != null) {

			if (line.isEmpty())
				continue;
			if (firstLine) {
				firstLine = false;
				robotManager = new RobotManager(new Integer(line.split(" ")[0]), new Integer(line.split(" ")[1]), 0, 0);
				continue;
			}

			robotManager.addBot(new Integer(line.split(" ")[0]), new Integer(line.split(" ")[1]),
					Robot.Orientations.valueOf(line.split(" ")[2]));

			out.append(robotManager.steer(reader.readLine()) + "\n");
		}
		System.out.println(out.toString()); // Prints the string content read
											// from input stream
		reader.close();

	}

}
