package uni.tartu.utils

import groovy.transform.CompileStatic
import uni.tartu.storage.AnalyzedUrlData

/**
 * author: lkokhreidze
 * date: 3/2/16
 * time: 6:03 PM
 **/

@CompileStatic
class TextDumper {

	private static final String LINE_SEPARATOR = System.getProperty("line.separator")

	public static void dump(String dir = '/Users/lkokhreidze/Desktop', List<Object>... set) {
		set.eachWithIndex { List<Object> data, i ->
			def file = new File("$dir/data-${i}.txt".toString()),
				 path = file.getAbsolutePath()
			if (file.exists()) {
				println "file: ${file.getName()} in directory: $dir already exists. deleting..."
				file.delete()
			}
			println "dumping text file in $path, with size: ${data.size()}"
			data
				.collect { it.toString() }
				.each { file << it; file << LINE_SEPARATOR }
		}
	}
}
