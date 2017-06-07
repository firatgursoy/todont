package joy.todont.service.impl;
import io.springlets.data.domain.GlobalSearch;
import joy.todont.domain.Todo;
import joy.todont.domain.Topic;
import joy.todont.repository.TodoRepository;
import joy.todont.service.api.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * = TodoServiceImpl
 TODO
 *
 */
@Transactional(readOnly = true)
@Service
public class TodoServiceImpl implements TodoService {
    /**
     * TODO
     *
     */
    private TodoRepository todoRepository;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param todoRepository
     */
    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository) {
        setTodoRepository(todoRepository);
    }

    /**
     * TODO
     *
     * @param todo
     */
    @Transactional
    public void delete(Todo todo) {
        // Clear bidirectional many-to-one child relationship with Topic
        if (todo.getTopic() != null) {
            todo.getTopic().getTodos().remove(todo);
        }

        getTodoRepository().delete(todo);
    }

    /**
     * TODO
     *
     * @return TodoRepository
     */
    public TodoRepository getTodoRepository() {
        return todoRepository;
    }

    /**
     * TODO
     *
     * @return Class
     */
    public Class<Long> getIdType() {
        return Long.class;
    }

    /**
     * TODO
     *
     * @param id
     * @return Todo
     */
    public Todo findOneForUpdate(Long id) {
        return getTodoRepository().findOneDetached(id);
    }

    /**
     * TODO
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Todo> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getTodoRepository().findAll(globalSearch, pageable);
    }

    /**
     * TODO
     *
     * @param todoRepository
     */
    public void setTodoRepository(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    /**
     * TODO
     *
     * @param topic
     * @return Long
     */
    public long countByTopic(Topic topic) {
        return getTodoRepository().countByTopic(topic);
    }

    /**
     * TODO
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<Todo> save(Iterable<Todo> entities) {
        return getTodoRepository().save(entities);
    }

    /**
     * TODO
     *
     * @param ids
     * @return List
     */
    public List<Todo> findAll(Iterable<Long> ids) {
        return getTodoRepository().findAll(ids);
    }

    /**
     * TODO
     *
     * @param topic
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Todo> findByTopic(Topic topic, GlobalSearch globalSearch, Pageable pageable) {
        return getTodoRepository().findByTopic(topic, globalSearch, pageable);
    }

    /**
     * TODO
     *
     * @return Long
     */
    public long count() {
        return getTodoRepository().count();
    }

    /**
     * TODO
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<Todo> toDelete = getTodoRepository().findAll(ids);
        getTodoRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO
     *
     * @return List
     */
    public List<Todo> findAll() {
        return getTodoRepository().findAll();
    }

    /**
     * TODO
     *
     * @param topic
     * @param pageable
     * @return Page
     */
    public Page<Todo> findByTopic(Topic topic, Pageable pageable) {
        return getTodoRepository().findByTopic(topic, pageable);
    }

    /**
     * TODO
     *
     * @param entity
     * @return Todo
     */
    @Transactional
    public Todo save(Todo entity) {
        return getTodoRepository().save(entity);
    }

    /**
     * TODO
     *
     * @param id
     * @return Todo
     */
    public Todo findOne(Long id) {
        return getTodoRepository().findOne(id);
    }

    /**
     * TODO
     *
     * @return Class
     */
    public Class<Todo> getEntityType() {
        return Todo.class;
    }
}
