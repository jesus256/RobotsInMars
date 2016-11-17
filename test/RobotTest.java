import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RobotTest {

	Robot robot;
	Robot bot11;
	Grid grid00;
	Grid grid22;

	@Before
	public void setUp() throws Exception {
		grid00 = new Grid(0, 0, 0, 0);
		grid22 = new Grid(2, 2, 0, 0);
		robot = new Robot(0, 0, Robot.Orientations.E, grid00);
		bot11 = new Robot(1, 1, Robot.Orientations.E, grid22);

	}

	@Test(expected = Error.class)
	public void create_a_bot_outside_the_grid() throws Exception {

		robot = new Robot(10, 10, Robot.Orientations.E, grid00);
		throw new Exception("must fail with robot out of Grid");
	}

	@Test(expected = Error.class)
	public void create_a_bot_with_invalid_orientation() throws Exception {

		robot = new Robot(10, 10, null, grid00);
		throw new Exception("must fail with invalid orientation");
	}

	@Test
	public void turn_to_left() throws Exception {

		robot.orientation = Robot.Orientations.E;
		robot.turnLeft();
		assertEquals(robot.orientation, Robot.Orientations.N);
	}

	@Test
	public void turn_to_right() throws Exception {

		robot.orientation = Robot.Orientations.E;
		robot.turnRight();
		assertEquals(robot.orientation, Robot.Orientations.S);
	}

	@Test
	public void can_not_move_forward() throws Exception {

		robot.orientation = Robot.Orientations.E;
		robot.moveForward();
		assertEquals(robot.orientation, Robot.Orientations.E);
		assertEquals(robot.pos, new Coord(0, 0, 0));
	}

	@Test
	public void can_move_forward() throws Exception {

		robot = new Robot(10, 10, Robot.Orientations.E, new Grid(20, 20, 0, 0));
		robot.orientation = Robot.Orientations.E;
		robot.moveForward();
		assertEquals(robot.orientation, Robot.Orientations.E);
		assertEquals(robot.pos, new Coord(11, 10, 0));
	}

	@Test
	public void send_message_to_left() throws Exception {

		robot.orientation = Robot.Orientations.E;
		robot.sendMessage("L");
		assertEquals(robot.orientation, Robot.Orientations.N);
	}

	@Test
	public void send_message_to_right() throws Exception {

		robot.orientation = Robot.Orientations.E;
		robot.sendMessage("R");
		assertEquals(robot.orientation, Robot.Orientations.S);
	}

	@Test
	public void send_message_move_forward_event() throws Exception {
		robot = new Robot(10, 10, Robot.Orientations.E, new Grid(20, 20, 0, 0));
		robot.orientation = Robot.Orientations.E;
		robot.sendMessage("F");
		assertEquals(robot.orientation, Robot.Orientations.E);
		assertEquals(robot.pos, new Coord(11, 10, 0));
	}

	@Test
	public void LOST_bot_turn_to_left() throws Exception {

		robot.orientation = Robot.Orientations.E;
		robot.alive = false;
		robot.turnLeft();
		// cannot turn a LOST robot
		assertEquals(robot.orientation, Robot.Orientations.E);
	}

	@Test
	public void OFF_bot_turn_to_left() throws Exception {

		robot.orientation = Robot.Orientations.E;
		robot.off = true;
		robot.turnLeft();
		// cannot turn a OFF robot
		assertEquals(robot.orientation, Robot.Orientations.E);
	}

	@Test
	public void verify_the_status_of_LOST_bot() throws Exception {

		robot.orientation = Robot.Orientations.E;
		robot.alive = false;
		// 'robot is LOST'
		assertEquals(robot.getStatus(), "0 0 E LOST");
	}

	/*****************/

	@Test
	public void turn_to_left22() throws Exception {

		bot11.orientation = Robot.Orientations.E;
		bot11.turnLeft();
		assertEquals(bot11.orientation, Robot.Orientations.N);
	}

	@Test
	public void turn_to_right22() throws Exception {

		bot11.orientation = Robot.Orientations.E;
		bot11.turnRight();
		assertEquals(bot11.orientation, Robot.Orientations.S);
	}

	@Test
	public void can_move_forward22() throws Exception {

		bot11.orientation = Robot.Orientations.E;
		bot11.moveForward();
		assertEquals(bot11.orientation, Robot.Orientations.E);
		assertEquals(bot11.pos, new Coord(2, 1, 0));
	}

	@Test
	public void verify_the_status_of_bot() throws Exception {

		robot.orientation = Robot.Orientations.N;
		robot.pos = new Coord(1, 2, 0);
		// "robot is LOST"
		assertEquals(robot.getStatus(), "1 2 N");
	}

	@Test
	public void move_forward_UP_to_get_LOST22() throws Exception {

		robot.pos = new Coord(1, 2, 0);
		robot.orientation = Robot.Orientations.N;
		robot.moveForward();
		Coord expected = new Coord(1, 2, 0);
		// , 'Cannot move more'
		assertEquals(robot.pos, expected);
		// , 'robot is LOST'
		assertEquals(robot.alive, false);
	}

}
