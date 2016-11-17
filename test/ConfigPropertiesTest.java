import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ConfigPropertiesTest {

	ConfigProperties configProperties;

	@Before
	public void setUp() throws Exception {
		configProperties = new ConfigProperties();
	}

	@Test
	public void get_an_existing_config() throws Exception {

		assertEquals(configProperties.get("MAX_X"), 50);
	}

	@Test
	public void get_an_non_existing_config() throws Exception {

		assertEquals(configProperties.get("MAX_Z"), null);
	}

	@Test
	public void set_an_existing_config() throws Exception {
		configProperties.set("MAX_X", 52);
		assertEquals(configProperties.get("MAX_X"), 52);
	}

	@Test
	public void set_an_non_existing_config() throws Exception {
		configProperties.set("MAX_Z", 500);
		assertEquals(configProperties.get("MAX_Z"), 500);
	}

}
