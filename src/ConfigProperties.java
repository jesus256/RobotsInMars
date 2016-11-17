import java.util.HashMap;

public class ConfigProperties {
	HashMap<String, Object> properties;

	public ConfigProperties() {

		properties = new HashMap<String, Object>();
		this.set("IGNORE_OFF", true);
		this.set("MAX_X", 50);
		this.set("MAX_Y", 50);
		this.set("MAX_INSTRUCTIONS", 100);

	}

	public Object get(String propertyName) {
		return properties.get(propertyName);
	}

	public void set(String propertyName, Object value) {
		properties.put(propertyName, value);
	}

}
