package joy.todont.domain;
import joy.todont.domain.dod.TodoFactory;
import joy.todont.domain.dod.TopicFactory;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * = TopicTest
 TODO Auto-generated class documentation
 *
 */
public class TopicTest {
    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private TodoFactory todoFactory = new TodoFactory();
    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private TopicFactory topicFactory = new TopicFactory();

    /**
     * TODO Auto-generated method documentation
     *
     * @return TodoFactory
     */
    public TodoFactory getTodoFactory() {
        return todoFactory;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param todoFactory
     */
    public void setTodoFactory(TodoFactory todoFactory) {
        this.todoFactory = todoFactory;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return TopicFactory
     */
    public TopicFactory getTopicFactory() {
        return topicFactory;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param topicFactory
     */
    public void setTopicFactory(TopicFactory topicFactory) {
        this.topicFactory = topicFactory;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @throws Exception
     */
    @Test
    public void addToTodosShouldAddTheTodoToThetodosRelationship() throws Exception {
        // Setup
        Topic topic = getTopicFactory().create(0);
        Todo todo1 = getTodoFactory().create(0);
        Todo todo2 = getTodoFactory().create(1);

        // Exercise
        topic.addToTodos(Arrays.asList(todo1, todo2));

        // Verify
        assertThat(topic.getTodos()).as("Check 'addToTodos' adds the todos to the relationship")
            .contains(todo1).contains(todo2);
        assertThat(topic).as("Check 'addToTodos' updates the Todo relationship side")
            .isEqualTo(todo1.getTopic()).isEqualTo(todo2.getTopic());
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @throws Exception
     */
    @Test
    public void addToTodosShouldRemoveTheTodoFromThetodosRelationship() throws Exception {
        // Setup
        Topic topic = getTopicFactory().create(0);
        Todo todo1 = getTodoFactory().create(0);
        Todo todo2 = getTodoFactory().create(1);
        topic.addToTodos(Arrays.asList(todo1, todo2));

        // Exercise
        topic.removeFromTodos(Collections.singleton(todo1));

        // Verify
        assertThat(todo1.getTopic()).as("Check 'removeFromTodos' updates the Todo relationship side")
            .isNull();
        assertThat(topic.getTodos()).as("Check 'removeFromTodos' removes a Todo from the relationship")
            .doesNotContain(todo1).contains(todo2);
    }
}
