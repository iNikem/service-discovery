package uni.tartu.configuration

import groovy.util.logging.Slf4j

import java.lang.reflect.Method

/**
 * author: lkokhreidze
 * date: 4/27/16
 * time: 10:30 AM
 **/

@Slf4j
abstract class AbstractConfiguration {

	public void configure() {
		Properties properties = getPropertiesFromClasspath("discovery.properties")
		if (properties != null) {
			configureFromProperties(properties)
		}
	}

	private AbstractConfiguration configureFromProperties(Properties p) {
		for (Method m : this.getClass().getMethods()) {
			if (m.isAnnotationPresent(Property.class)) {
				Property propertyAnnotation = m.getAnnotation(Property.class)
				String prop = propertyAnnotation.value()
				Class<?>[] parameterTypes = m.getParameterTypes()
				if (parameterTypes.length <= 1) {
					try {
						if (parameterTypes[0] == Double.class || parameterTypes[0] == double.class) {
							String strVal = p.get(prop).toString()
							double val = Double.parseDouble(strVal)
							m.invoke(this, val)
						} else if (parameterTypes[0] == Integer.class || parameterTypes[0] == int.class) {
							String strVal = p.get(prop).toString()
							int val = Integer.parseInt(strVal)
							m.invoke(this, val)
						} else if (parameterTypes[0] == String.class) {
							String strVal = p.get(prop).toString()
							m.invoke(this, strVal)
						}
					} catch (Exception e) {
						log.error("failed to configure properties from classpath", e)
						return null
					}
				}
			}
		}
		return this
	}

	private static Properties getPropertiesFromClasspath(String propertyFileName) {
		Properties properties = new Properties()
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader()
		InputStream is = classLoader.getResourceAsStream(propertyFileName)
		if (is == null) {
			log.warn("couldn't get properties from classpath")
			return null
		}
		log.info("found discovery.properties file in path: {}", classLoader.getResource(propertyFileName).toURI().getPath())
		try {
			properties.load(is)
		} catch (IOException e) {
			log.error("failed to configure properties from classpath", e)
		}
		return properties
	}
}
