package uni.tartu.discovery

/**
 * author: lkokhreidze
 * date: 2/22/16
 * time: 8:32 PM
 **/

public interface DiscoveryProcessor {

	public DiscoveryProcessor analyze()

	public DiscoveryProcessor group()

	public DiscoveryType getType()

	public List<Map> toTree()

	public void init(List<String> services)
}