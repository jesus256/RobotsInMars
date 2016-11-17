
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CoordTest {

	Coord coord;

	@Before
	public void setUp() throws Exception {
		coord = new Coord(0, 0, 0);
	}

	@Test
	public void should_be_the_same_point() {

		assertEquals(coord, new Coord(0, 0, 0));

	}

}
