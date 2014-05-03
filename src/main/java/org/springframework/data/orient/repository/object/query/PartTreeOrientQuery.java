package org.springframework.data.orient.repository.object.query;

import org.springframework.data.repository.query.Parameters;
import org.springframework.data.repository.query.ParametersParameterAccessor;
import org.springframework.data.repository.query.parser.PartTree;
import org.springframework.orm.orient.OrientObjectTemplate;

import com.orientechnologies.orient.core.sql.query.OSQLQuery;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;

/**
 * A {@link AbstractOrientQuery} implementation based on a {@link PartTree}.
 * 
 * @author Dzmitry_Naskou
 */
public class PartTreeOrientQuery extends AbstractOrientQuery {

    @SuppressWarnings("unused")
    private final OrientObjectTemplate template;
    
    /** The domain class. */
    private final Class<?> domainClass;
    
    /** The tree. */
    private final PartTree tree;
    
    /** The parameters. */
    private final Parameters<?, ?> parameters;
    
    /**
     * Instantiates a new {@link PartTreeOrientQuery} from given {@link OrientObjectQueryMethod} and {@link OrientObjectTemplate}.
     *
     * @param method the query method
     * @param template the orient object template
     */
    public PartTreeOrientQuery(OrientObjectQueryMethod method, OrientObjectTemplate template) {
        super(method, template);
        
        this.template = template;
        this.domainClass = method.getEntityInformation().getJavaType();
        this.tree = new PartTree(method.getName(), domainClass);
        this.parameters = method.getParameters();
    }

    /* (non-Javadoc)
     * @see org.springframework.data.orient.repository.object.query.AbstractOrientQuery#doCreateQuery(java.lang.Object[])
     */
    @Override
    @SuppressWarnings("rawtypes")
    protected OSQLQuery doCreateQuery(Object[] values) {
        ParametersParameterAccessor accessor = new ParametersParameterAccessor(parameters, values);
        
        OrientQueryCreator creator = new OrientQueryCreator(tree, domainClass, accessor);
        
        return new OSQLSynchQuery(creator.createQuery());
    }

    /* (non-Javadoc)
     * @see org.springframework.data.orient.repository.object.query.AbstractOrientQuery#doCreateCountQuery(java.lang.Object[])
     */
    @Override
    @SuppressWarnings("rawtypes")
    protected OSQLQuery doCreateCountQuery(Object[] values) {
        ParametersParameterAccessor accessor = new ParametersParameterAccessor(parameters, values);
        
        OrientQueryCreator creator = new OrientCountQueryCreator(tree, domainClass, accessor);
        
        return new OSQLSynchQuery(creator.createQuery());
    }

    /* (non-Javadoc)
     * @see org.springframework.data.orient.repository.object.query.AbstractOrientQuery#isCountQuery()
     */
    @Override
    protected boolean isCountQuery() {
        return tree.isCountProjection();
    }
}
