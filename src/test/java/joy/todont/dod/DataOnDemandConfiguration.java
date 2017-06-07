package joy.todont.dod;

import joy.todont.domain.dod.TodoDataOnDemand;
import joy.todont.domain.dod.TopicDataOnDemand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;

/**
 * = DataOnDemandConfiguration
 TODO Auto-generated class documentation
 *
 */
@TestConfiguration
public class DataOnDemandConfiguration {
    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private EntityManager entityManager;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param entityManager
     */
    @Autowired
    public DataOnDemandConfiguration(EntityManager entityManager) {
        setEntityManager(entityManager);
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
     * @param entityManager
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return TodoDataOnDemand
     */
    @Bean
    public TodoDataOnDemand todoDataOnDemand() {
        return new TodoDataOnDemand(getEntityManager());
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return TopicDataOnDemand
     */
    @Bean
    public TopicDataOnDemand topicDataOnDemand() {
        return new TopicDataOnDemand(getEntityManager());
    }
}
