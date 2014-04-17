package org.springframework.data.orient.repository.object.query;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.persistence.Tuple;
import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Selection;
import javax.persistence.criteria.Subquery;

public class OrientCriteriaBuilderImpl implements OrientCriteriaBuilder {

    @Override
    public <T> CriteriaQuery<T> createQuery(Class<T> resultClass) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public CriteriaQuery<Tuple> createTupleQuery() {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <Y> CompoundSelection<Y> construct(Class<Y> resultClass, Selection<?>... selections) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public CompoundSelection<Tuple> tuple(Selection<?>... selections) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public CompoundSelection<Object[]> array(Selection<?>... selections) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Order asc(Expression<?> x) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Order desc(Expression<?> x) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <N extends Number> Expression<Double> avg(Expression<N> x) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <N extends Number> Expression<N> sum(Expression<N> x) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<Long> sumAsLong(Expression<Integer> x) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<Double> sumAsDouble(Expression<Float> x) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <N extends Number> Expression<N> max(Expression<N> x) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <N extends Number> Expression<N> min(Expression<N> x) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <X extends Comparable<? super X>> Expression<X> greatest(Expression<X> x) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <X extends Comparable<? super X>> Expression<X> least(Expression<X> x) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<Long> count(Expression<?> x) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<Long> countDistinct(Expression<?> x) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate exists(Subquery<?> subquery) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <Y> Expression<Y> all(Subquery<Y> subquery) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <Y> Expression<Y> some(Subquery<Y> subquery) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <Y> Expression<Y> any(Subquery<Y> subquery) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate and(Expression<Boolean> x, Expression<Boolean> y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate and(Predicate... restrictions) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate or(Expression<Boolean> x, Expression<Boolean> y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate or(Predicate... restrictions) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate not(Expression<Boolean> restriction) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate conjunction() {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate disjunction() {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate isTrue(Expression<Boolean> x) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate isFalse(Expression<Boolean> x) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate isNull(Expression<?> x) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate isNotNull(Expression<?> x) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate equal(Expression<?> x, Expression<?> y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate equal(Expression<?> x, Object y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate notEqual(Expression<?> x, Expression<?> y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate notEqual(Expression<?> x, Object y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <Y extends Comparable<? super Y>> Predicate greaterThan(Expression<? extends Y> x, Expression<? extends Y> y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <Y extends Comparable<? super Y>> Predicate greaterThan(Expression<? extends Y> x, Y y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <Y extends Comparable<? super Y>> Predicate greaterThanOrEqualTo(Expression<? extends Y> x, Expression<? extends Y> y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <Y extends Comparable<? super Y>> Predicate greaterThanOrEqualTo(Expression<? extends Y> x, Y y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <Y extends Comparable<? super Y>> Predicate lessThan(Expression<? extends Y> x, Expression<? extends Y> y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <Y extends Comparable<? super Y>> Predicate lessThan(Expression<? extends Y> x, Y y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <Y extends Comparable<? super Y>> Predicate lessThanOrEqualTo(Expression<? extends Y> x, Expression<? extends Y> y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <Y extends Comparable<? super Y>> Predicate lessThanOrEqualTo(Expression<? extends Y> x, Y y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <Y extends Comparable<? super Y>> Predicate between(Expression<? extends Y> v, Expression<? extends Y> x, Expression<? extends Y> y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <Y extends Comparable<? super Y>> Predicate between(Expression<? extends Y> v, Y x, Y y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate gt(Expression<? extends Number> x, Expression<? extends Number> y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate gt(Expression<? extends Number> x, Number y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate ge(Expression<? extends Number> x, Expression<? extends Number> y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate ge(Expression<? extends Number> x, Number y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate lt(Expression<? extends Number> x,Expression<? extends Number> y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate lt(Expression<? extends Number> x, Number y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate le(Expression<? extends Number> x, Expression<? extends Number> y) {
        throw new UnsupportedOperationException("Not Implemented!");   
    }

    @Override
    public Predicate le(Expression<? extends Number> x, Number y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <N extends Number> Expression<N> neg(Expression<N> x) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <N extends Number> Expression<N> abs(Expression<N> x) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <N extends Number> Expression<N> sum(Expression<? extends N> x, Expression<? extends N> y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <N extends Number> Expression<N> sum(Expression<? extends N> x, N y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <N extends Number> Expression<N> sum(N x, Expression<? extends N> y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <N extends Number> Expression<N> prod(Expression<? extends N> x, Expression<? extends N> y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <N extends Number> Expression<N> prod(Expression<? extends N> x, N y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <N extends Number> Expression<N> prod(N x, Expression<? extends N> y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <N extends Number> Expression<N> diff(Expression<? extends N> x, Expression<? extends N> y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <N extends Number> Expression<N> diff(Expression<? extends N> x, N y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <N extends Number> Expression<N> diff(N x, Expression<? extends N> y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<Number> quot(Expression<? extends Number> x, Expression<? extends Number> y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<Number> quot(Expression<? extends Number> x, Number y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<Number> quot(Number x, Expression<? extends Number> y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<Integer> mod(Expression<Integer> x, Expression<Integer> y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<Integer> mod(Expression<Integer> x, Integer y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<Integer> mod(Integer x, Expression<Integer> y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<Double> sqrt(Expression<? extends Number> x) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<Long> toLong(Expression<? extends Number> number) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<Integer> toInteger(Expression<? extends Number> number) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<Float> toFloat(Expression<? extends Number> number) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<Double> toDouble(Expression<? extends Number> number) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<BigDecimal> toBigDecimal(Expression<? extends Number> number) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<BigInteger> toBigInteger(Expression<? extends Number> number) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<String> toString(Expression<Character> character) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <T> Expression<T> literal(T value) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <T> Expression<T> nullLiteral(Class<T> resultClass) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <T> ParameterExpression<T> parameter(Class<T> paramClass) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <T> ParameterExpression<T> parameter(Class<T> paramClass, String name) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <C extends Collection<?>> Predicate isEmpty(Expression<C> collection) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <C extends Collection<?>> Predicate isNotEmpty(Expression<C> collection) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <C extends Collection<?>> Expression<Integer> size(Expression<C> collection) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <C extends Collection<?>> Expression<Integer> size(C collection) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <E, C extends Collection<E>> Predicate isMember(Expression<E> elem, Expression<C> collection) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <E, C extends Collection<E>> Predicate isMember(E elem, Expression<C> collection) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <E, C extends Collection<E>> Predicate isNotMember(Expression<E> elem, Expression<C> collection) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <E, C extends Collection<E>> Predicate isNotMember(E elem, Expression<C> collection) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <V, M extends Map<?, V>> Expression<Collection<V>> values(M map) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <K, M extends Map<K, ?>> Expression<Set<K>> keys(M map) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate like(Expression<String> x, Expression<String> pattern) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate like(Expression<String> x, String pattern) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate like(Expression<String> x, Expression<String> pattern, Expression<Character> escapeChar) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate like(Expression<String> x, Expression<String> pattern, char escapeChar) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate like(Expression<String> x, String pattern, Expression<Character> escapeChar) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate like(Expression<String> x, String pattern, char escapeChar) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate notLike(Expression<String> x, Expression<String> pattern) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate notLike(Expression<String> x, String pattern) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate notLike(Expression<String> x, Expression<String> pattern, Expression<Character> escapeChar) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate notLike(Expression<String> x, Expression<String> pattern, char escapeChar) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate notLike(Expression<String> x, String pattern, Expression<Character> escapeChar) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Predicate notLike(Expression<String> x, String pattern, char escapeChar) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<String> concat(Expression<String> x, Expression<String> y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<String> concat(Expression<String> x, String y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<String> concat(String x, Expression<String> y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<String> substring(Expression<String> x, Expression<Integer> from) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<String> substring(Expression<String> x, int from) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<String> substring(Expression<String> x, Expression<Integer> from, Expression<Integer> len) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<String> substring(Expression<String> x, int from, int len) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<String> trim(Expression<String> x) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<String> trim(Trimspec ts, Expression<String> x) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<String> trim(Expression<Character> t, Expression<String> x) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<String> trim(Trimspec ts, Expression<Character> t, Expression<String> x) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<String> trim(char t, Expression<String> x) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<String> trim(Trimspec ts, char t, Expression<String> x) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<String> lower(Expression<String> x) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<String> upper(Expression<String> x) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<Integer> length(Expression<String> x) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<Integer> locate(Expression<String> x, Expression<String> pattern) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<Integer> locate(Expression<String> x, String pattern) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<Integer> locate(Expression<String> x, Expression<String> pattern, Expression<Integer> from) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<Integer> locate(Expression<String> x, String pattern, int from) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<Date> currentDate() {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<Timestamp> currentTimestamp() {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public Expression<Time> currentTime() {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <T> In<T> in(Expression<? extends T> expression) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <Y> Expression<Y> coalesce(Expression<? extends Y> x, Expression<? extends Y> y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <Y> Expression<Y> coalesce(Expression<? extends Y> x, Y y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <Y> Expression<Y> nullif(Expression<Y> x, Expression<?> y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <Y> Expression<Y> nullif(Expression<Y> x, Y y) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <T> Coalesce<T> coalesce() {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <C, R> SimpleCase<C, R> selectCase(Expression<? extends C> expression) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <R> Case<R> selectCase() {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public <T> Expression<T> function(String name, Class<T> type, Expression<?>... args) {
        throw new UnsupportedOperationException("Not Implemented!");
    }

    @Override
    public OrientCriteriaQuery<Object> createQuery() {
        throw new UnsupportedOperationException("Not Implemented!");
    }
}
