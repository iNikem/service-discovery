package uni.tartu.discovery

import groovy.transform.CompileStatic

/**
 * author: lkokhreidze
 * date: 2/18/16
 * time: 7:38 PM
 **/

@CompileStatic
class Algorithm {
	private final List<Map> records

	Algorithm(List<Map> records) {
		this.records = records
	}

	public void traverse() {
		def urls = records.collect { (it).serviceName }
		println urls
	}
}