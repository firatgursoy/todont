package joy.todont.config.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import joy.todont.domain.Todo;
import joy.todont.domain.Topic;
import joy.todont.web.TodoJsonMixin;
import joy.todont.web.TopicJsonMixin;
import org.springframework.boot.jackson.JsonComponent;

/**
 * = DomainModelModule
 TODO
 *
 */
@JsonComponent
public class DomainModelModule  extends SimpleModule {
    /**
     * TODO Auto-generated constructor documentation
     *
     */
    public DomainModelModule() {
        // Mixin registration

        setMixInAnnotation(Todo.class, TodoJsonMixin.class);
        setMixInAnnotation(Topic.class, TopicJsonMixin.class);
    }
}
