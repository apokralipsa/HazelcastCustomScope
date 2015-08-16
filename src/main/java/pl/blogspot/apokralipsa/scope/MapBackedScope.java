package pl.blogspot.apokralipsa.scope;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * A {@link Scope} that holds beans in a {@link Map} that is provided to it at
 * construction time.
 * 
 * @author Apokralipsa
 *
 */
public class MapBackedScope implements Scope, DisposableBean {

	protected final Map<Object, Object> beans;
	protected final Map<Object, Runnable> destructionCallbacks = new ConcurrentHashMap<>();

	public MapBackedScope(Map<Object, Object> beans) {
		super();
		this.beans = beans;
	}

	@Override
	public Object get(String objectName, ObjectFactory<?> objectFactory) {
		return beans.computeIfAbsent(objectName, k -> objectFactory.getObject());
	}

	@Override
	public String getConversationId() {
		return null;
	}

	@Override
	public void registerDestructionCallback(String objectName, Runnable callback) {
		destructionCallbacks.put(objectName, callback);
	}

	@Override
	public Object remove(String objectName) {
		return beans.remove(objectName);
	}

	@Override
	public Object resolveContextualObject(String objectName) {
		return null;
	}

	@Override
	public void destroy() throws Exception {
		destructionCallbacks.values().forEach(r -> r.run());
	}

}