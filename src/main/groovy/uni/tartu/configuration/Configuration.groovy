package uni.tartu.configuration

import groovy.util.logging.Slf4j

/**
 * author: lkokhreidze
 * date: 4/6/16
 * time: 9:52 AM
 **/

@Slf4j
//TODO add filter configuration from configuration discovery.properties file
public class Configuration extends AbstractConfiguration {
	private double parameterThreshold
	private double importanceThreshold
	private String filters
	private int maxGroupSize

	Configuration() {
		configure()
	}

	public double getImportanceThreshold() {
		return importanceThreshold
	}

	@Property(value = "discovery.importanceThreshold")
	public void setImportanceThreshold(double importanceThreshold) {
		this.importanceThreshold = importanceThreshold > 0 ? importanceThreshold : 0.005
	}

	public double getParameterThreshold() {
		return parameterThreshold
	}

	@Property(value = "discovery.parameterThreshold")
	public void setParameterThreshold(double parameterThreshold) {
		this.parameterThreshold = parameterThreshold > 0 ? parameterThreshold : 0.003
	}

	public String[] getFilters() {
		def defaultFilters = ['.html', '$', '.php', '.js', '.txt', '.css', '.jtp', '.ico', '.gif', '.text', '.pdf']
		if (filters == null) {
			return defaultFilters
		}
		def filters = filters.split(",")
		log.info("got filters from properties with size: {}", filters.size())
		return filters.size() > 0 ? filters : defaultFilters
	}

	@Property(value = "discovery.filters")
	public void setFilters(String filters) {
		this.filters = filters
	}

	public int getMaxGroupSize() {
		return maxGroupSize
	}

	@Property(value = "discovery.maxGroupSize")
	public void setMaxGroupSize(int maxGroupSize) {
		this.maxGroupSize = maxGroupSize > 0 ? maxGroupSize : 1000
	}

}
