package joy.todont.config;

import io.springlets.data.jpa.repository.support.DetachableJpaRepositoryImpl;
import joy.todont.TodontApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * = SpringDataJpaDetachableRepositoryConfiguration
 TODO
 *
 */
@EntityScan(basePackageClasses = TodontApplication.class)
@EnableJpaRepositories(repositoryBaseClass = DetachableJpaRepositoryImpl.class, basePackageClasses = TodontApplication.class)
@Configuration
public class SpringDataJpaDetachableRepositoryConfiguration {
}
