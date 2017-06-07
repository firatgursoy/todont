
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package joy.todont.repository;

import io.springlets.data.domain.GlobalSearch;
import joy.todont.domain.Topic;
import joy.todont.repository.TopicRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

privileged aspect TopicRepositoryCustom_Jpa_Repository_Custom {
    
    /**
     * TODO
     * 
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Topic> TopicRepositoryCustom.findAll(GlobalSearch globalSearch, Pageable pageable);
    
}
