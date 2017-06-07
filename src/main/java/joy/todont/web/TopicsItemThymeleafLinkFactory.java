package joy.todont.web;
import io.springlets.web.mvc.util.MethodLinkFactory;
import io.springlets.web.mvc.util.SpringletsMvcUriComponentsBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;

import java.util.Map;

/**
 * = TopicsItemThymeleafLinkFactory
 TODO
 *
 */
@Component
public class TopicsItemThymeleafLinkFactory implements MethodLinkFactory<TopicsItemThymeleafController> {
    /**
     * TODO
     *
     */
    public static final String SHOW = "show";
    /**
     * TODO
     *
     */
    public static final String SHOWINLINE = "showInline";
    /**
     * TODO
     *
     */
    public static final String EDITFORM = "editForm";
    /**
     * TODO
     *
     */
    public static final String UPDATE = "update";
    /**
     * TODO
     *
     */
    public static final String DELETE = "delete";

    /**
     * TODO
     *
     * @return Class
     */
    public Class<TopicsItemThymeleafController> getControllerClass() {
        return TopicsItemThymeleafController.class;
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
        if (methodName.equals(SHOW)) {
            return SpringletsMvcUriComponentsBuilder.fromMethodCall(SpringletsMvcUriComponentsBuilder.on(getControllerClass()).show(null, null)).buildAndExpand(pathVariables);
        }
        if (methodName.equals(SHOWINLINE)) {
            return SpringletsMvcUriComponentsBuilder.fromMethodCall(SpringletsMvcUriComponentsBuilder.on(getControllerClass()).showInline(null, null)).buildAndExpand(pathVariables);
        }
        if (methodName.equals(EDITFORM)) {
            return SpringletsMvcUriComponentsBuilder.fromMethodCall(SpringletsMvcUriComponentsBuilder.on(getControllerClass()).editForm(null, null)).buildAndExpand(pathVariables);
        }
        if (methodName.equals(UPDATE)) {
            return SpringletsMvcUriComponentsBuilder.fromMethodCall(SpringletsMvcUriComponentsBuilder.on(getControllerClass()).update(null, null, null, null, null)).buildAndExpand(pathVariables);
        }
        if (methodName.equals(DELETE)) {
            return SpringletsMvcUriComponentsBuilder.fromMethodCall(SpringletsMvcUriComponentsBuilder.on(getControllerClass()).delete(null)).buildAndExpand(pathVariables);
        }
        throw new IllegalArgumentException("Invalid method name: " + methodName);
    }
}
