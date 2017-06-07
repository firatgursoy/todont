package joy.todont.web;
import com.fasterxml.jackson.annotation.JsonIgnore;
import joy.todont.domain.Todo;
import joy.todont.domain.Topic;

import java.util.Set;

/**
 * = TopicJsonMixin
 TODO
 *
 */
public abstract class TopicJsonMixin {
    /**
     * TODO
     *
     */
    @JsonIgnore
    private Set<Todo> todos;

    /**
     * TODO
     *
     * @return Set
     */
    public Set<Todo> getTodos() {
        return todos;
    }

    /**
     * TODO
     *
     * @param todos
     */
    public void setTodos(Set<Todo> todos) {
        this.todos = todos;
    }
}
