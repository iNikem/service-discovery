package uni.tartu.discovery

/**
 * author: lkokhreidze
 * date: 2/22/16
 * time: 8:32 PM
 **/

public interface DiscoveryProcessor {
	public int getSize()

	public Map analyze()

	public DiscoveryProcessor group()

	public DiscoveryType getType()

	public void init(List<String> services)
}