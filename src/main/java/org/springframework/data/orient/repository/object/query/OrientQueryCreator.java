package org.springframework.data.orient.repository.object.query;

import static org.jooq.impl.DSL.field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.ParameterAccessor;
import org.springframework.data.repository.query.parser.AbstractQueryCreator;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.data.repository.query.parser.PartTree;

public class OrientQueryCreator extends AbstractQueryCreator<String, Condition> {

    private final Class<?> domainClass;
    
    private final DSLContext context;
    
    public OrientQueryCreator(PartTree tree, Class<?> domainClass, ParameterAccessor parameters) {
        super(tree, parameters);
        
        this.domainClass = domainClass;
        this.context = DSL.using(SQLDialect.MYSQL);
    }

    @Override
    protected Condition create(Part part, Iterator<Object> iterator) {
        return toCondition(part, iterator);
    }

    @Override
    protected Condition and(Part part, Condition base, Iterator<Object> iterator) {
        return base.and(toCondition(part, iterator));
    }

    @Override
    protected Condition or(Condition base, Condition criteria) {
        return base.or(criteria);
    }

    @Override
    protected String complete(Condition criteria, Sort sort) {
        String query = context.renderNamedParams(context.select().from(domainClass.getSimpleName()).where(criteria));
        System.out.println(query);
        
        return query;
    }
    
    protected Condition toCondition(Part part, Iterator<Object> iterator) {
        String property = part.getProperty().toDotPath();
        Field<Object> field = field(property);
        
        switch (part.getType()) {
            case AFTER: 
            case GREATER_THAN: return field.gt(iterator.next());
            case GREATER_THAN_EQUAL: return field.ge(iterator.next());
            case BEFORE:
            case LESS_THAN: return field.lt(iterator.next());
            case LESS_THAN_EQUAL: return field.le(iterator.next());
            case BETWEEN: return field.between(iterator.next(), iterator.next());
            case IS_NULL: return field.isNull();
            case IS_NOT_NULL: return field.isNotNull();
            case IN: return field.in(toList(iterator));
            case NOT_IN: return field.notIn(toList(iterator));
            case LIKE: return lowerIfIgnoreCase(part, field, iterator);
            case NOT_LIKE: return lowerIfIgnoreCase(part, field, iterator).not();
            case STARTING_WITH: return field.startsWith(iterator.next());
            case ENDING_WITH: return field.endsWith(iterator.next());
            case CONTAINING: return field.contains(iterator.next());
            case SIMPLE_PROPERTY: return field.eq(iterator.next());
            case NEGATING_SIMPLE_PROPERTY: return field.ne(iterator.next());
            case TRUE: return field.isTrue();
            case FALSE: return field.isFalse();
            default: throw new IllegalArgumentException("Unsupported keyword!");
        }
    }
    
    @SuppressWarnings("incomplete-switch")
    private Condition lowerIfIgnoreCase(Part part, Field<Object> field, Iterator<Object> iterator) {
        switch (part.shouldIgnoreCase()) {
            case ALWAYS:
            case WHEN_POSSIBLE: return field.likeIgnoreCase(iterator.next().toString());
        }
        
        return field.like(iterator.next().toString());
    }
    
    private List<Object> toList(Iterator<Object> iterator) {
        if (iterator == null || !iterator.hasNext()) {
            return Collections.emptyList();
        }
        
        List<Object> list = new ArrayList<Object>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        
        return list;
    }
}
