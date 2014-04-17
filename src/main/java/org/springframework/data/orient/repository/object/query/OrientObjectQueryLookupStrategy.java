package org.springframework.data.orient.repository.object.query;

import org.springframework.data.repository.core.NamedQueries;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.QueryLookupStrategy.Key;
import org.springframework.data.repository.query.RepositoryQuery;
import org.springframework.orm.orient.OrientObjectTemplate;

public final class OrientObjectQueryLookupStrategy {

    private OrientObjectQueryLookupStrategy() {
        super();
    }

    private abstract static class AbstractQueryLookupStrategy implements QueryLookupStrategy {

        private final OrientObjectTemplate template;

        public AbstractQueryLookupStrategy(OrientObjectTemplate template) {
            this.template = template;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.springframework.data.repository.query.QueryLookupStrategy#
         * resolveQuery(java.lang.reflect.Method,
         * org.springframework.data.repository.core.RepositoryMetadata,
         * org.springframework.data.repository.core.NamedQueries)
         */
        public final RepositoryQuery resolveQuery(java.lang.reflect.Method method, RepositoryMetadata metadata, NamedQueries namedQueries) {
            return resolveQuery(new OrientObjectQueryMethod(method, metadata), template, namedQueries);
        }

        protected abstract RepositoryQuery resolveQuery(OrientObjectQueryMethod method, OrientObjectTemplate template, NamedQueries namedQueries);
    }

    private static class CreateQueryLookupStrategy extends AbstractQueryLookupStrategy {

        /**
         * Instantiates a new {@link CreateQueryLookupStrategy} lookup strategy.
         *
         * @param db the application database service
         */
        public CreateQueryLookupStrategy(OrientObjectTemplate template) {
            super(template);
        }

        /* (non-Javadoc)
         * @see com.epam.e3s.data.repository.query.E3sQueryLookupStrategy.AbstractQueryLookupStrategy#resolveQuery(com.epam.e3s.data.repository.query.E3sQueryMethod, com.epam.e3s.core.db.AppDbService, org.springframework.data.repository.core.NamedQueries)
         */
        @Override
        protected RepositoryQuery resolveQuery(OrientObjectQueryMethod method, OrientObjectTemplate template, NamedQueries namedQueries) {
            try {
                return new PartTreeOrientQuery(method, template);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(String.format("Could not create query metamodel for method %s!", method.toString()), e);
            }
        }
    }

    private static class DeclaredQueryLookupStrategy extends AbstractQueryLookupStrategy {

        /**
         * Instantiates a new {@link DeclaredQueryLookupStrategy} lookup strategy.
         *
         * @param template the application database service
         */
        public DeclaredQueryLookupStrategy(OrientObjectTemplate template) {
            super(template);
        }

        /* (non-Javadoc)
         * @see com.epam.e3s.data.repository.query.E3sQueryLookupStrategy.AbstractQueryLookupStrategy#resolveQuery(com.epam.e3s.data.repository.query.E3sQueryMethod, com.epam.e3s.core.db.AppDbService, org.springframework.data.repository.core.NamedQueries)
         */
        @Override
        protected RepositoryQuery resolveQuery(OrientObjectQueryMethod method, OrientObjectTemplate template, NamedQueries namedQueries) {
            String query = method.getAnnotatedQuery();

            if (query != null) {
                return new StringBasedOrientQuery(query, method, template);
            }
            
            throw new IllegalStateException(String.format("Did neither find a NamedQuery nor an annotated query for method %s!", method));
        }
    }

    private static class CreateIfNotFoundQueryLookupStrategy extends AbstractQueryLookupStrategy {

        /** The declared query strategy. */
        private final DeclaredQueryLookupStrategy strategy;
        
        /** The create query strategy. */
        private final CreateQueryLookupStrategy createStrategy;

        /**
         * Instantiates a new {@link CreateIfNotFoundQueryLookupStrategy} lookup strategy.
         *
         * @param db the application database service
         */
        public CreateIfNotFoundQueryLookupStrategy(OrientObjectTemplate db) {
            super(db);
            this.strategy = new DeclaredQueryLookupStrategy(db);
            this.createStrategy = new CreateQueryLookupStrategy(db);
        }

        /* (non-Javadoc)
         * @see com.epam.e3s.data.repository.query.E3sQueryLookupStrategy.AbstractQueryLookupStrategy#resolveQuery(com.epam.e3s.data.repository.query.E3sQueryMethod, com.epam.e3s.core.db.AppDbService, org.springframework.data.repository.core.NamedQueries)
         */
        @Override
        protected RepositoryQuery resolveQuery(OrientObjectQueryMethod method, OrientObjectTemplate template, NamedQueries namedQueries) {
            try {
                return strategy.resolveQuery(method, template, namedQueries);
            } catch (IllegalStateException e) {
                return createStrategy.resolveQuery(method, template, namedQueries);
            }
        }
    }

    public static QueryLookupStrategy create(OrientObjectTemplate template, Key key) {
        if (key == null) {
            return new CreateIfNotFoundQueryLookupStrategy(template);
        }

        switch (key) {
            case CREATE:
                return new CreateQueryLookupStrategy(template);
            case USE_DECLARED_QUERY:
                return new DeclaredQueryLookupStrategy(template);
            case CREATE_IF_NOT_FOUND:
                return new CreateIfNotFoundQueryLookupStrategy(template);
            default:
                throw new IllegalArgumentException(String.format("Unsupported query lookup strategy %s!", key));
        }
    }

}
