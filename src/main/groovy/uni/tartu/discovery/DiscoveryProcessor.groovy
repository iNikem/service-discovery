package uni.tartu.discovery

import uni.tartu.storage.ResultSetWithStats

/**
 * author: lkokhreidze
 * date: 2/22/16
 * time: 8:32 PM
 **/

public interface DiscoveryProcessor {

	public DiscoveryProcessor analyze()

	public DiscoveryProcessor group()

	public DiscoveryType getType()

	public List<ResultSetWithStats> toTree()

	public void init(List<String> services)
}