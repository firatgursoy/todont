
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package joy.todont.repository;

import io.springlets.data.jpa.repository.DetachableJpaRepository;
import joy.todont.domain.Topic;
import joy.todont.repository.TopicRepository;
import joy.todont.repository.TopicRepositoryCustom;
import org.springframework.transaction.annotation.Transactional;

privileged aspect TopicRepository_Jpa_Repository {
    
    declare parents: TopicRepository extends DetachableJpaRepository<Topic, Long>;
    
    declare parents: TopicRepository extends TopicRepositoryCustom;
    
    declare @type: joy.todont.repository.TopicRepository: @Transactional(readOnly = true);
    
}
