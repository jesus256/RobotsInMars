import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RobotManagerTest {

	RobotManager robotManager;

	@Before
	public void setUp() throws Exception {
		robotManager = new RobotManager(0, 0, 0, 0);
	}

	@Test(expected = Error.class)
	public void set_the_controller_with_oversized_Grid() throws Exception {
		new RobotManager(100, 100, 0, 0);

		throw new Exception("must fail oversized Grid");
	}

	@Test(expected = Error.class)
	public void create_a_bot_outside_the_Grid() throws Exception {

		robotManager.setConfig("MAX_X", 50);
		robotManager.addBot(100, 100, Robot.Orientations.E);

		throw new Exception("must fail to set the robot");

	}

	@Test(expected = Error.class)
	public void create_a_bot_with_invalid_orientation() throws Exception {

		robotManager.addBot(0, 0, null);
		throw new Exception("must fail to set the robot");

	}

	@Test(expected = Error.class)
	public void too_many_instructions() throws Exception {

		robotManager.addBot(0, 0, Robot.Orientations.E);
		robotManager.setConfig("MAX_INSTRUCTIONS", 3);
		robotManager.steer("LLR");

		throw new Exception("must fail because too many instructions");

	}

	@Test(expected = Error.class)
	public void invalid_instruction() throws Exception {

		robotManager.addBot(0, 0, Robot.Orientations.E);
		robotManager.setConfig("MAX_INSTRUCTIONS", 3);
		robotManager.steer("LLK");

		throw new Exception("must fail because invalid instruction");

	}

	@Test
	public void valid_instruction() {

		robotManager.addBot(0, 0, Robot.Orientations.E);
		robotManager.setConfig("MAX_INSTRUCTIONS", 3);
		assertEquals(robotManager.steer("LR"), "0 0 E");

	}

	@Test
	public void lose_the_bot() {

		robotManager.addBot(0, 0, Robot.Orientations.E);
		robotManager.setConfig("MAX_INSTRUCTIONS", 3);
		assertEquals(robotManager.steer("F"), "0 0 E LOST");

	}

}
