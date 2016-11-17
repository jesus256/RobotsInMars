import java.util.ArrayList;

public class Grid {

	ArrayList<Coord> scents = new ArrayList<Coord>();
	Coord origin, end;

	public Grid(int end_x, int end_y, int origin_x, int origin_y) {

		origin = new Coord(origin_x, origin_y, 0);
		end = new Coord(end_x, end_y, 0);

	}

	public boolean isInBounds(Coord coord, Coord origin, Coord end) {

		int x = coord.x;
		int y = coord.y;

		return (x >= origin.x && x <= end.x && y >= origin.y && y <= end.y);
	}

	public boolean isInBounds(Coord coord) {
		return isInBounds(coord, this.origin, this.end);
	}

	public boolean hasScent(Coord coord) {
		return scents.contains(coord);

	}

	public void setScent(Coord coord) {
		scents.add(coord);
	}

}