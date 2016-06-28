package uni.tartu.discovery

import uni.tartu.configuration.Configuration
import uni.tartu.storage.IntermediateResultSet

/**
 * author: lkokhreidze
 * date: 2/22/16
 * time: 8:32 PM
 **/

public interface DiscoveryProcessor {

	public void init(List<String> services, Configuration configuration)

	public DiscoveryProcessor tokenize()

	public DiscoveryProcessor analyze()

	public IntermediateResultSet reduce()

	public DiscoveryType getType()

}