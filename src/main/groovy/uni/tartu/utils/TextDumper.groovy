package uni.tartu.utils

/**
 * author: lkokhreidze
 * date: 3/2/16
 * time: 6:03 PM
 **/

class TextDumper {
	public static void dump(String path, Map data) {
		def file = new File(path)
		if (file.exists()) {
			println "file in directory: $path already exists. deleting..."
			file.delete()
		}
		println "dumping text file in directory: $path"
		data.each { k, _ ->
			file << k
			file << System.lineSeparator()
		}
	}
}
