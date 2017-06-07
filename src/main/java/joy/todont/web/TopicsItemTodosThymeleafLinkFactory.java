package joy.todont.web;
import io.springlets.web.mvc.util.MethodLinkFactory;
import io.springlets.web.mvc.util.SpringletsMvcUriComponentsBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;

import java.util.Map;

/**
 * = TopicsItemTodosThymeleafLinkFactory
 TODO
 *
 */
@Component
public class TopicsItemTodosThymeleafLinkFactory implements MethodLinkFactory<TopicsItemTodosThymeleafController> {
    /**
     * TODO
     *
     */
    public static final String DATATABLES = "datatables";
    /**
     * TODO
     *
     */
    public static final String CREATEFORM = "createForm";
    /**
     * TODO
     *
     */
    public static final String REMOVEFROMTODOS = "removeFromTodos";
    /**
     * TODO
     *
     */
    public static final String REMOVEFROMTODOSBATCH = "removeFromTodosBatch";
    /**
     * TODO
     *
     */
    public static final String CREATE = "create";

    /**
     * TODO
     *
     * @return Class
     */
    public Class<TopicsItemTodosThymeleafController> getControllerClass() {
        return TopicsItemTodosThymeleafController.class;
    }

    /**
     * TODO
     *
     * @param methodName
     * @param parameters
     * @param pathVariables
     * @return UriComponents
     */
    public UriComponents toUri(String methodName, Object[] parameters, Map<String, Object> pathVariables) {
        if (methodName.equals(DATATABLES)) {
            return SpringletsMvcUriComponentsBuilder.fromMethodCall(SpringletsMvcUriComponentsBuilder.on(getControllerClass()).datatables(null, null, null, null, null)).buildAndExpand(pathVariables);
        }
        if (methodName.equals(CREATEFORM)) {
            return SpringletsMvcUriComponentsBuilder.fromMethodCall(SpringletsMvcUriComponentsBuilder.on(getControllerClass()).createForm(null, null)).buildAndExpand(pathVariables);
        }
        if (methodName.equals(REMOVEFROMTODOS)) {
            return SpringletsMvcUriComponentsBuilder.fromMethodCall(SpringletsMvcUriComponentsBuilder.on(getControllerClass()).removeFromTodos(null, null)).buildAndExpand(pathVariables);
        }
        if (methodName.equals(REMOVEFROMTODOSBATCH)) {
            return SpringletsMvcUriComponentsBuilder.fromMethodCall(SpringletsMvcUriComponentsBuilder.on(getControllerClass()).removeFromTodosBatch(null, null)).buildAndExpand(pathVariables);
        }
        if (methodName.equals(CREATE)) {
            return SpringletsMvcUriComponentsBuilder.fromMethodCall(SpringletsMvcUriComponentsBuilder.on(getControllerClass()).create(null, null, null, null, null)).buildAndExpand(pathVariables);
        }
        throw new IllegalArgumentException("Invalid method name: " + methodName);
    }
}
