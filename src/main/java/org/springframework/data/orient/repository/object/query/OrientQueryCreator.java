package org.springframework.data.orient.repository.object.query;

import static org.jooq.impl.DSL.field;

import java.util.Iterator;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.SelectConditionStep;
import org.jooq.impl.DSL;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.ParameterAccessor;
import org.springframework.data.repository.query.parser.AbstractQueryCreator;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.data.repository.query.parser.PartTree;

public class OrientQueryCreator extends AbstractQueryCreator<String, SelectConditionStep<Record>> {

    private final Class<?> domainClass;
    
    private final DSLContext context;
    
    public OrientQueryCreator(PartTree tree, Class<?> domainClass, ParameterAccessor parameters) {
        super(tree, parameters);
        
        this.domainClass = domainClass;
        this.context = DSL.using(SQLDialect.MYSQL);
    }

    @Override
    protected SelectConditionStep<Record> create(Part part, Iterator<Object> iterator) {
        return context.select().from(domainClass.getSimpleName()).where(toCondition(part, iterator));
    }

    @Override
    protected SelectConditionStep<Record> and(Part part, SelectConditionStep<Record> base, Iterator<Object> iterator) {
        return base.and(toCondition(part, iterator));
    }

    @Override
    protected SelectConditionStep<Record> or(SelectConditionStep<Record> base, SelectConditionStep<Record> criteria) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    protected String complete(SelectConditionStep<Record> criteria, Sort sort) {
        String query = context.renderNamedParams(criteria);
        System.out.println(query);
        
        return query;
    }
    
    protected Condition toCondition(Part part, Iterator<Object> iterator) {
        String property = part.getProperty().toDotPath();
        
        switch (part.getType()) {
            case LIKE: return field(property).like(iterator.next().toString());
            case SIMPLE_PROPERTY: return field(property).eq(iterator.next());
            default: throw new IllegalArgumentException("Unsupported keyword!");
        }
    }
}
