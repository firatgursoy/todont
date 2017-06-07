package joy.todont.repository;

import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import joy.todont.domain.Topic;

/**
 * = TopicRepositoryImpl
 *
 * TODO
 *
 */
public class TopicRepositoryImpl extends QueryDslRepositorySupportExt<Topic> {

    /**
     * TODO Auto-generated constructor documentation
     */
    TopicRepositoryImpl() {
        super(Topic.class);
    }
}