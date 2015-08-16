package pl.blogspot.apokralipsa.config;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.blogspot.apokralipsa.scope.HazelcastClusterScope;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

/**
 * Declares a {@link HazelcastInstance} as a bean and registers a scope under
 * the name {@link HazelcastClusterScope#NAME} that is backed by that instance.
 * 
 * @author Apokralipsa
 *
 */
@Configuration
public class HazelcastClusterScopeConfig {

	@Bean
	public HazelcastInstance hazelcastInstance() {
		return Hazelcast.newHazelcastInstance();
	}

	@Bean
	public HazelcastClusterScope hazelcastClusterScope(
			HazelcastInstance hazelcastInstance) {
		return new HazelcastClusterScope(hazelcastInstance);
	}

	@Bean
	public BeanFactoryPostProcessor scopeConfigurer(
			HazelcastClusterScope scope) {
		return beanFactory -> beanFactory.registerScope(
				HazelcastClusterScope.NAME,
				scope);
	}

}
