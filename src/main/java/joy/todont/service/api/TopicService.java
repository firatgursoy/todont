package joy.todont.service.api;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.format.EntityResolver;
import joy.todont.domain.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * = TopicService
 TODO
 *
 */
public interface TopicService extends EntityResolver<Topic, Long> {
    /**
     * TODO
     *
     * @param id
     * @return Topic
     */
    Topic findOne(Long id);

    /**
     * TODO
     *
     * @param topic
     */
    void delete(Topic topic);

    /**
     * TODO
     *
     * @param entities
     * @return List
     */
    List<Topic> save(Iterable<Topic> entities);

    /**
     * TODO
     *
     * @param ids
     */
    void delete(Iterable<Long> ids);

    /**
     * TODO
     *
     * @param entity
     * @return Topic
     */
    Topic save(Topic entity);

    /**
     * TODO
     *
     * @param id
     * @return Topic
     */
    Topic findOneForUpdate(Long id);

    /**
     * TODO
     *
     * @param ids
     * @return List
     */
    List<Topic> findAll(Iterable<Long> ids);

    /**
     * TODO
     *
     * @return List
     */
    List<Topic> findAll();

    /**
     * TODO
     *
     * @return Long
     */
    long count();

    /**
     * TODO
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    Page<Topic> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO
     *
     * @param topic
     * @param todosToAdd
     * @return Topic
     */
    Topic addToTodos(Topic topic, Iterable<Long> todosToAdd);

    /**
     * TODO
     *
     * @param topic
     * @param todosToRemove
     * @return Topic
     */
    Topic removeFromTodos(Topic topic, Iterable<Long> todosToRemove);

    /**
     * TODO
     *
     * @param topic
     * @param todos
     * @return Topic
     */
    Topic setTodos(Topic topic, Iterable<Long> todos);
}
