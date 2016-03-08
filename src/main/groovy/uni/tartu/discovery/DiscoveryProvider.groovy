package uni.tartu.discovery

import java.lang.annotation.ElementType
import java.lang.annotation.Inherited
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 * author: lkokhreidze
 * date: 2/22/16
 * time: 9:31 PM
 **/

@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DiscoveryProvider {}