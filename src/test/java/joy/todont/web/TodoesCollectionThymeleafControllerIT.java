package joy.todont.web;

import io.springlets.boot.test.autoconfigure.web.servlet.SpringletsWebMvcTest;
import io.springlets.security.jpa.service.api.UserLoginService;
import joy.todont.domain.dod.TodoFactory;
import joy.todont.service.api.TodoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.matchers.JUnitMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * = TodoesCollectionThymeleafControllerIT
 * TODO Auto-generated class documentation
 */
@SpringletsWebMvcTest(controllers = TodoesCollectionThymeleafController.class, secure = true)
@RunWith(SpringRunner.class)
@WithMockUser
public class TodoesCollectionThymeleafControllerIT {
    /**
     * TODO Auto-generated attribute documentation
     */
    @Autowired
    private MockMvc mvc;
    /**
     * TODO Auto-generated attribute documentation
     */
    @MockBean
    private TodoService todoServiceService;
    /**
     * TODO Auto-generated attribute documentation
     */
    private TodoFactory factory = new TodoFactory();

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

    /**
     * TODO Auto-generated method documentation
     *
     * @param factory
     */
    public void setFactory(TodoFactory factory) {
        this.factory = factory;
    }

    /**
     * Test method example. To be implemented by developer.
     */

    @Test
    //@WithUserDetails("manager@company.com")
    public void testMethodExample() throws Exception {
        /*.perform(formLogin().user("admin"))
                .andExpect(authenticated().withRoles("USER","ADMIN"));*/
        this.getMvc().perform(get("/todoes/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("todont")));


    }
}
