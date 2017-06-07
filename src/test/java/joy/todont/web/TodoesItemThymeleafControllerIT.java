package joy.todont.web;
import io.springlets.boot.test.autoconfigure.web.servlet.SpringletsWebMvcTest;
import joy.todont.domain.dod.TodoFactory;
import joy.todont.service.api.TodoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * = TodoesItemThymeleafControllerIT
 TODO Auto-generated class documentation
 *
 */
@SpringletsWebMvcTest(controllers = TodoesItemThymeleafController.class, secure = true)
@RunWith(SpringRunner.class)
@WithMockUser
public class TodoesItemThymeleafControllerIT {
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
    private TodoService todoServiceService;
    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private TodoFactory factory = new TodoFactory();

    /**
     * TODO Auto-generated method documentation
     *
     * @param factory
     */
    public void setFactory(TodoFactory factory) {
        this.factory = factory;
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
     * @return TodoService
     */
    public TodoService getTodoServiceService() {
        return todoServiceService;
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
     * TODO Auto-generated method documentation
     *
     * @return TodoFactory
     */
    public TodoFactory getFactory() {
        return factory;
    }
}
