package uni.tartu.utils

import groovy.transform.CompileStatic
import org.reflections.Reflections

import java.lang.annotation.Annotation

/**
 * author: lkokhreidze
 * date: 2/23/16
 * time: 10:54 AM
 **/

@CompileStatic
class ReflectionUtils<T> {
	private ReflectionUtils() {}

	public static List<T> scan(String where, Class<? extends Annotation> what) {
		try {
			new Reflections(where).getTypesAnnotatedWith(what).collect {
				it.newInstance() as T
			}
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException("couldn't scan for classes", e)
		}
	}
}
