package uni.tartu

import uni.tartu.discovery.Algorithm

import static uni.tartu.parser.Parser.parse

/**
 * author: lkokhreidze
 * date: 2/18/16
 * time: 7:33 PM
 **/

class Main {
	public static void main(String[] args) {
		URL csv = Main.class.getClassLoader().getResource("test-data-1.csv")
		Algorithm algorithm = new Algorithm(parse(new File(csv.toURI())))
		algorithm.traverse()
	}
}
