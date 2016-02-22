package uni.tartu.discovery

/**
 * author: lkokhreidze
 * date: 2/22/16
 * time: 8:32 PM
 **/

interface DiscoveryProcessor {
	int getSize()
	void tokenize()
	DiscoveryType getType()
	void init(List<String> services)
}