package org.springframework.data.orient.repository.object.query;

import static org.jooq.impl.DSL.field;

import java.util.ArrayList;
import java.util.List;

import org.jooq.SortField;
import org.jooq.SortOrder;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
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
    
    public static List<SortField<?>> toOrders(Sort sort) {
        List<SortField<?>> orders = new ArrayList<SortField<?>>();
        
        for (Order order : sort) {
            orders.add(field(order.getProperty()).sort(order.getDirection() == Direction.ASC ? SortOrder.ASC : SortOrder.DESC)); 
        }

        return orders;
    }
}
