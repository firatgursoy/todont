package joy.todont.service.api;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.format.EntityResolver;
import joy.todont.domain.Todo;
import joy.todont.domain.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * = TodoService
 TODO
 *
 */
public interface TodoService  extends EntityResolver<Todo, Long> {
    /**
     * TODO
     *
     * @param id
     * @return Todo
     */
    Todo findOne(Long id);

    /**
     * TODO
     *
     * @param todo
     */
    void delete(Todo todo);

    /**
     * TODO
     *
     * @param entities
     * @return List
     */
    List<Todo> save(Iterable<Todo> entities);

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
     * @return Todo
     */
    Todo save(Todo entity);

    /**
     * TODO
     *
     * @param id
     * @return Todo
     */
    Todo findOneForUpdate(Long id);

    /**
     * TODO
     *
     * @param ids
     * @return List
     */
    List<Todo> findAll(Iterable<Long> ids);

    /**
     * TODO
     *
     * @return List
     */
    List<Todo> findAll();

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
    Page<Todo> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO
     *
     * @param topic
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    Page<Todo> findByTopic(Topic topic, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO
     *
     * @param topic
     * @return Long
     */
    long countByTopic(Topic topic);

    /**
     * TODO
     *
     * @param topic
     * @param pageable
     * @return Page
     */
    Page<Todo> findByTopic(Topic topic, Pageable pageable);
}
