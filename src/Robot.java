import java.util.HashMap;

public class Robot {

	HashMap<Orientations, Orientations> left, right;

	public enum Orientations {
		N, E, W, S
	};

	Coord pos;
	Orientations orientation;
	boolean alive;
	boolean off;
	Grid grid;

	public Robot(int x, int y, Orientations orientation, Grid grid) {
		left = new HashMap<Orientations, Orientations>();
		right = new HashMap<Orientations, Orientations>();

		left.put(Orientations.N, Orientations.W);
		left.put(Orientations.W, Orientations.S);
		left.put(Orientations.S, Orientations.E);
		left.put(Orientations.E, Orientations.N);

		right.put(Orientations.N, Orientations.E);
		right.put(Orientations.E, Orientations.S);
		right.put(Orientations.S, Orientations.W);
		right.put(Orientations.W, Orientations.N);

		this.pos = new Coord(x, y, 0);
		if (!grid.isInBounds(this.pos)) {
			throw new Error("Cannot place Robot outside the grid");
		}

		this.orientation = orientation;
		this.alive = true;
		this.off = false;
		this.grid = grid;

	}

	public void sendMessage(String message) {
		switch (message) {
		case "L":
			this.turnLeft();
			break;
		case "R":
			this.turnRight();
			break;
		case "F":
			this.moveForward();
			break;
		case "OFF":
			this.switchOff();
			break;

		default:
			throw new IllegalArgumentException("Invalid message: " + message);
		}
	}

	public Coord nextForwardPos(Coord coord, Orientations or) {

		switch (or) {
		case N:
			return new Coord(coord.x, coord.y + 1, 0);
		case E:
			return new Coord(coord.x + 1, coord.y, 0);
		case W:
			return new Coord(coord.x - 1, coord.y, 0);
		case S:
			return new Coord(coord.x, coord.y - 1, 0);
		}
		throw new Error("Invalid orientation");
	}

	public boolean turnLeft() {

		if (this.alive && !this.off) {

			this.orientation = left.get(this.orientation);
			return true;

		} else {

			return false;
		}
	}

	public boolean turnRight() {

		if (this.alive && !this.off) {

			this.orientation = right.get(this.orientation);
			return true;

		} else {

			return false;
		}
	}

	public boolean moveForward() {

		if (!this.alive || this.off) {
			return false;
		}

		Coord next_pos = nextForwardPos(this.pos, this.orientation);

		if (this.grid.hasScent(next_pos)) {

			this.sendMessage("OFF");

		}

		if (this.grid.isInBounds(next_pos)) {
			this.pos = next_pos;
			return true;
		} else {
			this.alive = false;
			this.grid.setScent(this.pos);
			return false;
		}
	}

	public boolean switchOff() {
		boolean ignoreOff = (boolean) new ConfigProperties().get("IGNORE_OFF");
		if (!ignoreOff) {
			this.off = true;
			return false;
		} else {
			return true;
		}
	}

	public String getStatus() {

		String status = "";
		if (!this.alive) {
			status = " LOST";
		} else if (this.off) {
			status = " OFF";
		}

		return "" + this.pos.x + " " + this.pos.y + " " + this.orientation + status;
	}

}
