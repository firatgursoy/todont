
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package joy.todont.repository;

import io.springlets.data.domain.GlobalSearch;
import joy.todont.domain.Todo;
import joy.todont.domain.Topic;
import joy.todont.repository.TodoRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

privileged aspect TodoRepositoryCustom_Jpa_Repository_Custom {
    
    /**
     * TODO
     * 
     * @param topic
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Todo> TodoRepositoryCustom.findByTopic(Topic topic, GlobalSearch globalSearch, Pageable pageable);
    
    /**
     * TODO
     * 
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Todo> TodoRepositoryCustom.findAll(GlobalSearch globalSearch, Pageable pageable);
    
}
