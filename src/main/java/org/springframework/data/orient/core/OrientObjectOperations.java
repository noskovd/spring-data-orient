package org.springframework.data.orient.core;

import com.orientechnologies.orient.core.db.ODatabase;
import com.orientechnologies.orient.core.record.ORecordInternal;

public interface OrientObjectOperations extends OrientOperations {

    <RET> RET detachAll(Object iPojo, boolean returnNonProxiedInstance);

    ODatabase<Object> delete(ORecordInternal iRecord);

}
