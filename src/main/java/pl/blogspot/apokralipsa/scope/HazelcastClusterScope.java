package pl.blogspot.apokralipsa.scope;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

/**
 * A {@link MapBackedScope} that uses a distributed {@link IMap}.
 * 
 * @author Apokralipsa
 *
 */
public class HazelcastClusterScope extends MapBackedScope {

	public static final Logger LOG = LoggerFactory.getLogger(HazelcastClusterScope.class);
	public static final String NAME = "HazelcastClusterScope";

	public HazelcastClusterScope(HazelcastInstance hazelcastInstance) {
		super(hazelcastInstance.getMap(NAME));
	}

	@Override
	public void registerDestructionCallback(String objectName, Runnable callback) {
		LOG.warn("Registering destruction callbacks is not supported");
	}

}
