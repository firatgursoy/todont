package joy.todont.service.impl;
import io.springlets.data.domain.GlobalSearch;
import joy.todont.domain.Todo;
import joy.todont.domain.Topic;
import joy.todont.repository.TopicRepository;
import joy.todont.service.api.TodoService;
import joy.todont.service.api.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * = TopicServiceImpl
 TODO
 *
 */
@Service
@Transactional(readOnly = true)
public class TopicServiceImpl implements TopicService {
    /**
     * TODO
     *
     */
    private TopicRepository topicRepository;
    /**
     * TODO
     *
     */
    private TodoService todoService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param topicRepository
     * @param todoService
     */
    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository, @Lazy TodoService todoService) {
        setTopicRepository(topicRepository);
        setTodoService(todoService);
    }

    /**
     * TODO
     *
     * @param topic
     * @param todos
     * @return Topic
     */
    @Transactional
    public Topic setTodos(Topic topic, Iterable<Long> todos) {
        List<Todo> items = getTodoService().findAll(todos);
        Set<Todo> currents = topic.getTodos();
        Set<Todo> toRemove = new HashSet<Todo>();
        for (Iterator<Todo> iterator = currents.iterator(); iterator.hasNext();) {
            Todo nextTodo = iterator.next();
            if (items.contains(nextTodo)) {
                items.remove(nextTodo);
            } else {
                toRemove.add(nextTodo);
            }
        }
        topic.removeFromTodos(toRemove);
        topic.addToTodos(items);
        // Force the version update of the parent side to know that the parent has changed
        // because it has new books assigned
        topic.setVersion(topic.getVersion() + 1);
        return getTopicRepository().save(topic);
    }

    /**
     * TODO
     *
     * @return List
     */
    public List<Topic> findAll() {
        return getTopicRepository().findAll();
    }

    /**
     * TODO
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<Topic> toDelete = getTopicRepository().findAll(ids);
        getTopicRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO
     *
     * @param id
     * @return Topic
     */
    public Topic findOneForUpdate(Long id) {
        return getTopicRepository().findOneDetached(id);
    }

    /**
     * TODO
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Topic> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getTopicRepository().findAll(globalSearch, pageable);
    }

    /**
     * TODO
     *
     * @param topic
     * @param todosToRemove
     * @return Topic
     */
    @Transactional
    public Topic removeFromTodos(Topic topic, Iterable<Long> todosToRemove) {
        List<Todo> todos = getTodoService().findAll(todosToRemove);
        topic.removeFromTodos(todos);
        return getTopicRepository().save(topic);
    }

    /**
     * TODO
     *
     * @param entity
     * @return Topic
     */
    @Transactional
    public Topic save(Topic entity) {
        return getTopicRepository().save(entity);
    }

    /**
     * TODO
     *
     * @param ids
     * @return List
     */
    public List<Topic> findAll(Iterable<Long> ids) {
        return getTopicRepository().findAll(ids);
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
     * @return TopicRepository
     */
    public TopicRepository getTopicRepository() {
        return topicRepository;
    }

    /**
     * TODO
     *
     * @param topicRepository
     */
    public void setTopicRepository(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    /**
     * TODO
     *
     * @param topic
     * @param todosToAdd
     * @return Topic
     */
    @Transactional
    public Topic addToTodos(Topic topic, Iterable<Long> todosToAdd) {
        List<Todo> todos = getTodoService().findAll(todosToAdd);
        topic.addToTodos(todos);
        return getTopicRepository().save(topic);
    }

    /**
     * TODO
     *
     * @return Long
     */
    public long count() {
        return getTopicRepository().count();
    }

    /**
     * TODO
     *
     * @return TodoService
     */
    public TodoService getTodoService() {
        return todoService;
    }

    /**
     * TODO
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<Topic> save(Iterable<Topic> entities) {
        return getTopicRepository().save(entities);
    }

    /**
     * TODO
     *
     * @param id
     * @return Topic
     */
    public Topic findOne(Long id) {
        return getTopicRepository().findOne(id);
    }

    /**
     * TODO
     *
     * @param todoService
     */
    public void setTodoService(TodoService todoService) {
        this.todoService = todoService;
    }

    /**
     * TODO
     *
     * @return Class
     */
    public Class<Topic> getEntityType() {
        return Topic.class;
    }

    /**
     * TODO
     *
     * @param topic
     */
    @Transactional
    public void delete(Topic topic) {
        // Clear bidirectional one-to-many parent relationship with Todo
        for (Todo item : topic.getTodos()) {
            item.setTopic(null);
        }

        getTopicRepository().delete(topic);
    }
}
