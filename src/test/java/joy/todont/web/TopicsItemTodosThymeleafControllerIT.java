package joy.todont.web;
import io.springlets.boot.test.autoconfigure.web.servlet.SpringletsWebMvcTest;
import joy.todont.domain.dod.TopicFactory;
import joy.todont.service.api.TodoService;
import joy.todont.service.api.TopicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * = TopicsItemTodosThymeleafControllerIT
 TODO Auto-generated class documentation
 *
 */
@SpringletsWebMvcTest(controllers = TopicsItemTodosThymeleafController.class, secure = true)
@RunWith(SpringRunner.class)
@WithMockUser
public class TopicsItemTodosThymeleafControllerIT {
    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Autowired
    private MockMvc mvc;
    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @MockBean
    private TopicService topicServiceService;
    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @MockBean
    private TodoService todoServiceService;
    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private TopicFactory factory = new TopicFactory();

    /**
     * TODO Auto-generated method documentation
     *
     * @param factory
     */
    public void setFactory(TopicFactory factory) {
        this.factory = factory;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param todoServiceService
     */
    public void setTodoServiceService(TodoService todoServiceService) {
        this.todoServiceService = todoServiceService;
    }

    /**
     * Test method example. To be implemented by developer.
     *
     */
    @Test
    public void testMethodExample() {
        // Setup
        // Previous tasks

        // Exercise
        // Execute method to test

        // Verify
        // Check results with assertions
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return MockMvc
     */
    public MockMvc getMvc() {
        return mvc;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return TopicService
     */
    public TopicService getTopicServiceService() {
        return topicServiceService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return TopicFactory
     */
    public TopicFactory getFactory() {
        return factory;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param topicServiceService
     */
    public void setTopicServiceService(TopicService topicServiceService) {
        this.topicServiceService = topicServiceService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return TodoService
     */
    public TodoService getTodoServiceService() {
        return todoServiceService;
    }
}
