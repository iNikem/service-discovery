package uni.tartu.algorithm.tree

import groovy.json.JsonBuilder
import org.codehaus.groovy.control.CompilerConfiguration

import static uni.tartu.utils.TextDumper.dump

/**
 * author: lkokhreidze
 * date: 3/16/16
 * time: 6:22 PM
 **/

class TreeBuilder {

	private def tree

	public def toJsonTree(List<String> reducedUrls) {
		tree = { -> return [:].withDefault { tree() } }
		def root = tree()
		reducedUrls.each { url ->
			root.putAll(evalMe(root, url))
		}
		dump('/Users/lkokhreidze/Desktop', [new JsonBuilder(root).toPrettyString()])
	}

	private Map evalMe(def root, String property) {
		def evalMe = "this.binding.root.$property".toString(),
			 cc = new CompilerConfiguration(),
			 binding = new Binding(root: root)
		cc.scriptBaseClass = TreeBuilderScript.class.name
		new GroovyShell(this.class.classLoader, binding, cc).evaluate("""get($evalMe)""")
		root
	}

}
