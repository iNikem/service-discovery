package uni.tartu.algorithm.tree

import org.codehaus.groovy.control.CompilerConfiguration
import uni.tartu.utils.TextDumper

/**
 * author: lkokhreidze
 * date: 3/16/16
 * time: 6:22 PM
 **/

class TreeBuilder {

	private def tree = { ->
		return [:].withDefault {
			tree()
		}
	}

	public def transform(List<String> reducedUrls) {
		TextDumper.dump([reducedUrls])
		def nodes = tree(reducedUrls)
		constructNode('/ROOT/', collectNodes(nodes), false)
	}

	private def tree(List<String> reducedUrls) {
		def root = tree()
		reducedUrls.each { url ->
			root.putAll(evalMe(root, url))
		}
		root
	}

	private def collectNodes(e) {
		e.with {
			if (!(it instanceof Map)) {
				return []
			}
			it.collect { k, v ->
				if ('children'.equals(k)) {
					return [:]
				}
				def children = []
				children.addAll(collectNodes(v))
				constructNode(k as String, children)
			}
		}
	}

	private def constructNode(String k, List children, boolean collapsed = true) {
		def node = tree()
		node.text.name = k
		if (children) {
			node.HTMLclass = 'the-parent'
			node.children = children
			node.collapsed = collapsed
		}
		node
	}

	private Map evalMe(def root, String nodes) {
		def node = "this.binding.root.$nodes".toString(),
			 cc = new CompilerConfiguration(),
			 binding = new Binding(root: root)
		cc.scriptBaseClass = TreeBuilderScript.class.name
		new GroovyShell(this.class.classLoader, binding, cc).evaluate("""get($node)""")
		root
	}

}
