import java.util.ArrayList;

public class RobotManager {
	Grid grid;
	ArrayList<Robot> robots;
	ConfigProperties configProperties;

	public RobotManager(int end_x, int end_y, int origin_x, int origin_y) {

		configProperties = new ConfigProperties();
		validateGrid(end_x, end_y);

		this.grid = new Grid(end_x, end_y, origin_x, origin_y);
		this.robots = new ArrayList<Robot>();

	}

	public void validateGrid(int end_x, int end_y) {
		Integer maxx = (Integer) configProperties.get("MAX_X");
		Integer maxy = (Integer) configProperties.get("MAX_Y");

		if (end_x > maxx) {
			throw new Error("The maximum value for X coordinate is " + maxx);
		}
		if (end_y > maxy) {
			throw new Error("The maximum value for Y coordinate is " + maxy);
		}
	}

	public void canSteer(String instructions) {

		Integer maxi = (Integer) configProperties.get("MAX_INSTRUCTIONS");

		if (instructions.split("").length >= maxi) {
			throw new Error("Max length of instruction strings will be less than " + maxi + " characters.");
		}
	}

	public void addBot(int x, int y, Robot.Orientations orientation) {
		if (orientation == null)
			throw new Error("Orientation is invalid");
		this.robots.add(new Robot(x, y, orientation, this.grid));
	}

	public String steer(String instructions) {

		canSteer(instructions);

		Robot robot = this.robots.get(robots.size() - 1);

		String[] commands = instructions.split("");
		for (String command : commands) {
			robot.sendMessage(command);
		}

		return robot.getStatus();
	}

	public void setConfig(String propertyName, Object value) {
		this.configProperties.set(propertyName, value);

	}

}
