package org.springframework.data.orient.repository.object.query;

import java.util.Iterator;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.ParameterAccessor;
import org.springframework.data.repository.query.parser.AbstractQueryCreator;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.data.repository.query.parser.PartTree;

public class OrientQueryCreator extends AbstractQueryCreator<OrientCriteriaQuery<Object>, Predicate> {

    private final OrientCriteriaBuilder builder;
    
    private final Root<?> root;
    
    private final OrientCriteriaQuery<Object> query;
    
    private final Class<?> domainClass;
    
    public OrientQueryCreator(PartTree tree, Class<?> domainClass, ParameterAccessor parameters, OrientCriteriaBuilder builder) {
        super(tree, parameters);
        
        this.builder = builder;
        this.domainClass = domainClass;
        this.query = (OrientCriteriaQuery<Object>) builder.createQuery().distinct(tree.isDistinct());
        this.root = query.from(this.domainClass);
    }

    @Override
    protected Predicate create(Part part, Iterator<Object> iterator) {
        return toPredicate(part);
    }

    @Override
    protected Predicate and(Part part, Predicate base, Iterator<Object> iterator) {
        return builder.and(base, toPredicate(part));
    }

    @Override
    protected Predicate or(Predicate base, Predicate criteria) {
        return builder.or(base, criteria);
    }

    @Override
    protected OrientCriteriaQuery<Object> complete(Predicate criteria, Sort sort) {
        CriteriaQuery<Object> select = query.select(root);
        
        return (OrientCriteriaQuery<Object>) (criteria == null ? select : select.where(criteria));
    }

    private Predicate toPredicate(Part part) {
        return new PredicateBuilder(part).build();
    }
    
    private class PredicateBuilder {
        
        private final Part part;
        
        public PredicateBuilder(Part part) {
            super();
            this.part = part;
        }

        public Predicate build() {
            switch (part.getType()) {
                case SIMPLE_PROPERTY: return builder.equal(null, null);

                default: throw new IllegalArgumentException("Unsupported keyword " + part.getType());
            }
        }
    }
}
