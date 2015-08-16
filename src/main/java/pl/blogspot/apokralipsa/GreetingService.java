package pl.blogspot.apokralipsa;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GreetingService implements Serializable {

	private static final Logger LOG = LoggerFactory.getLogger(GreetingService.class);

	private final String name;

	public GreetingService(String name) {
		super();
		this.name = name;
	};

	public void greet() {
		LOG.info("Hello, " + name + "!");
	}
}
