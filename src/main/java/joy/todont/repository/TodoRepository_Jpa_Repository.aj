
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package joy.todont.repository;

import io.springlets.data.jpa.repository.DetachableJpaRepository;
import joy.todont.domain.Todo;
import joy.todont.domain.Topic;
import joy.todont.repository.TodoRepository;
import joy.todont.repository.TodoRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

privileged aspect TodoRepository_Jpa_Repository {
    
    declare parents: TodoRepository extends DetachableJpaRepository<Todo, Long>;
    
    declare parents: TodoRepository extends TodoRepositoryCustom;
    
    declare @type: joy.todont.repository.TodoRepository: @Transactional(readOnly = true);
    
    /**
     * TODO
     * 
     * @param topic
     * @return Long
     */
    public abstract long TodoRepository.countByTopic(Topic topic);
    
    /**
     * TODO
     * 
     * @param topic
     * @param pageable
     * @return Page
     */
    public abstract Page<Todo> TodoRepository.findByTopic(Topic topic, Pageable pageable);
    
}
