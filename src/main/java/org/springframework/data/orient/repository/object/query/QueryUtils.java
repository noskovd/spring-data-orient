package org.springframework.data.orient.repository.object.query;

import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

public final class QueryUtils {

    private QueryUtils() {
        super();
    }
    
    public static String applySorting(String query, Sort sort) {
        Assert.hasText(query);
        
        if (null == sort || !sort.iterator().hasNext()) {
            return query;
        }
        
        throw new UnsupportedOperationException("Not implemented");
    }
}
