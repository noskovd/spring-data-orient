package org.springframework.data.orient.core;

public interface OrientObjectOperations extends OrientOperations {

    <RET> RET detachAll(Object iPojo, boolean returnNonProxiedInstance);
}
