package org.springframework.data.orient.repository.object.query;

import org.springframework.data.repository.query.Parameters;
import org.springframework.data.repository.query.ParametersParameterAccessor;
import org.springframework.data.repository.query.parser.PartTree;
import org.springframework.orm.orient.OrientObjectTemplate;

import com.orientechnologies.orient.core.sql.query.OSQLQuery;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;

public class PartTreeOrientQuery extends AbstractOrientQuery {

    @SuppressWarnings("unused")
    private final OrientObjectTemplate template;
    
    private final Class<?> domainClass;
    
    private final PartTree tree;
    
    private final Parameters<?, ?> parameters;
    
    public PartTreeOrientQuery(OrientObjectQueryMethod method, OrientObjectTemplate template) {
        super(method, template);
        
        this.template = template;
        this.domainClass = method.getEntityInformation().getJavaType();
        this.tree = new PartTree(method.getName(), domainClass);
        this.parameters = method.getParameters();
    }

    @Override
    @SuppressWarnings("rawtypes")
    protected OSQLQuery doCreateQuery(Object[] values) {
        ParametersParameterAccessor accessor = new ParametersParameterAccessor(parameters, values);
        
        OrientQueryCreator creator = new OrientQueryCreator(tree, domainClass, accessor);
        
        return new OSQLSynchQuery(creator.createQuery());
    }
}
