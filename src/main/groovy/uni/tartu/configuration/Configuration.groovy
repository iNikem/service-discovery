package uni.tartu.configuration

import groovy.util.logging.Slf4j

import java.lang.reflect.Method

/**
 * author: lkokhreidze
 * date: 4/6/16
 * time: 9:52 AM
 **/

@Slf4j
public class Configuration extends AbstractConfiguration {
	private double parameterThreshold = 0
	private double importanceThreshold = 0

	Configuration() {
		configure()
	}

	public double getImportanceThreshold() {
		return importanceThreshold
	}

	@Property(value = "discovery.importanceThreshold")
	public void setImportanceThreshold(double importanceThreshold) {
		this.importanceThreshold = importanceThreshold
	}

	public double getParameterThreshold() {
		return parameterThreshold
	}

	@Property(value = "discovery.parameterThreshold")
	public void setParameterThreshold(double parameterThreshold) {
		this.parameterThreshold = parameterThreshold
	}

}
