package uni.tartu.discovery

import uni.tartu.storage.AnalyzedUrlData

/**
 * author: lkokhreidze
 * date: 2/22/16
 * time: 8:32 PM
 **/

//TODO: add method for getting tree like representation of an url
public interface DiscoveryProcessor {
	public int getSize()

	public List<AnalyzedUrlData> analyze()

	public DiscoveryProcessor group()

	public DiscoveryType getType()

	public void init(List<String> services)
}