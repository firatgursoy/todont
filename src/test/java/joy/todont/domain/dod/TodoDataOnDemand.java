package joy.todont.domain.dod;
import joy.todont.domain.Todo;
import org.springframework.beans.factory.annotation.Configurable;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * = TodoDataOnDemand
 TODO Auto-generated class documentation
 *
 */
@Configurable
public class TodoDataOnDemand {
    /**
     * Number of elements to create and persist.
     *
     */
    private int size;
    /**
     * Random generator for the entities index.
     *
     */
    private Random rnd = new SecureRandom();
    /**
     * List of created entities.
     *
     */
    private List<Todo> data;
    /**
     * EntityManager to persist the entities.
     *
     */
    private EntityManager entityManager;
    /**
     * Factory to create entity instances.
     *
     */
    private TodoFactory factory = new TodoFactory();

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param entityManager
     */
    public TodoDataOnDemand(EntityManager entityManager) {
        this(entityManager, 10);
    }

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param entityManager
     * @param size
     */
    public TodoDataOnDemand(EntityManager entityManager, int size) {
        setEntityManager(entityManager);
        setSize(size);
    }

    /**
     * Returns a generated and persisted {@link Todo} in a random index.
     *
     * @return Todo a random {@link Todo}
     */
    public Todo getRandomTodo() {
        init();
        return getData().get(getRnd().nextInt(getData().size()));
    }

    /**
     * Returns a generated and persisted {@link Todo} in a given index.
     *
     * @param index the position of the {@link Todo} to return
     * @return Todo the specific {@link Todo}
     */
    public Todo getSpecificTodo(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (getData().size() - 1)) {
            index = getData().size() - 1;
        }
        return getData().get(index);
    }

    /**
     * Creates the initial list of generated entities.
     *
     */
    public void init() {
        int from = 0;
        int to = 10;

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Todo> cq = cb.createQuery(Todo.class);
        Root<Todo> rootEntry = cq.from(Todo.class);
        CriteriaQuery<Todo> all = cq.select(rootEntry);
        TypedQuery<Todo> allQuery =
            getEntityManager().createQuery(all).setFirstResult(from).setMaxResults(to);
        setData(allQuery.getResultList());
        if (getData() == null) {
            throw new IllegalStateException(
                "Find entries implementation for 'Todo' illegally returned null");
        }
        if (!getData().isEmpty()) {
            return;
        }

        setData(new ArrayList<Todo>());
        for (int i = from; i < to; i++) {
            Todo obj = getFactory().create(i);
            try {
                getEntityManager().persist(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter
                      .hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".")
                    .append(cv.getPropertyPath()).append(": ").append(cv.getMessage())
                    .append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            getEntityManager().flush();
            getData().add(obj);
        }
    }

    /**
     * Creates a new transient Todo in a random index out of the initial list of the created entities,
     * with an index greater than {@link TodoDataOnDemand#getSize()} - 1.
     *
     * @return Todo the generated transient {@link Todo}
     */
    public Todo getNewRandomTransientTodo() {
        int randomIndex = getSize() + getRnd().nextInt(Integer.MAX_VALUE - getSize());
        return getFactory().create(randomIndex);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entityManager
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Integer
     */
    public int getSize() {
        return size;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Random
     */
    public Random getRnd() {
        return rnd;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param factory
     */
    public void setFactory(TodoFactory factory) {
        this.factory = factory;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param rnd
     */
    public void setRnd(Random rnd) {
        this.rnd = rnd;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return TodoFactory
     */
    public TodoFactory getFactory() {
        return factory;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return EntityManager
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<Todo> getData() {
        return data;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param data
     */
    public void setData(List<Todo> data) {
        this.data = data;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param size
     */
    public void setSize(int size) {
        this.size = size;
    }
}
