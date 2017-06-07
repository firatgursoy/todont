package joy.todont.web;
import io.springlets.web.mvc.util.MethodLinkFactory;
import io.springlets.web.mvc.util.SpringletsMvcUriComponentsBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;

import java.util.Map;

/**
 * = TopicsCollectionThymeleafLinkFactory
 TODO
 *
 */
@Component
public class TopicsCollectionThymeleafLinkFactory implements MethodLinkFactory<TopicsCollectionThymeleafController> {
    /**
     * TODO
     *
     */
    public static final String LIST = "list";
    /**
     * TODO
     *
     */
    public static final String DATATABLES = "datatables";
    /**
     * TODO
     *
     */
    public static final String SELECT2 = "select2";
    /**
     * TODO
     *
     */
    public static final String EXPORTXLS = "exportXls";
    /**
     * TODO
     *
     */
    public static final String CREATE = "create";
    /**
     * TODO
     *
     */
    public static final String CREATEFORM = "createForm";
    /**
     * TODO
     *
     */
    public static final String DELETEBATCH = "deleteBatch";
    /**
     * TODO
     *
     */
    public static final String EXPORTCSV = "exportCsv";
    /**
     * TODO
     *
     */
    public static final String EXPORTPDF = "exportPdf";

    /**
     * TODO
     *
     * @param methodName
     * @param parameters
     * @param pathVariables
     * @return UriComponents
     */
    public UriComponents toUri(String methodName, Object[] parameters, Map<String, Object> pathVariables) {
        if (methodName.equals(LIST)) {
            return SpringletsMvcUriComponentsBuilder.fromMethodCall(SpringletsMvcUriComponentsBuilder.on(getControllerClass()).list(null)).buildAndExpand(pathVariables);
        }
        if (methodName.equals(DATATABLES)) {
            return SpringletsMvcUriComponentsBuilder.fromMethodCall(SpringletsMvcUriComponentsBuilder.on(getControllerClass()).datatables(null, null, null, null)).buildAndExpand(pathVariables);
        }
        if (methodName.equals(SELECT2)) {
            return SpringletsMvcUriComponentsBuilder.fromMethodCall(SpringletsMvcUriComponentsBuilder.on(getControllerClass()).select2(null, null, null)).buildAndExpand(pathVariables);
        }
        if (methodName.equals(CREATE)) {
            return SpringletsMvcUriComponentsBuilder.fromMethodCall(SpringletsMvcUriComponentsBuilder.on(getControllerClass()).create(null, null, null)).buildAndExpand(pathVariables);
        }
        if (methodName.equals(CREATEFORM)) {
            return SpringletsMvcUriComponentsBuilder.fromMethodCall(SpringletsMvcUriComponentsBuilder.on(getControllerClass()).createForm(null)).buildAndExpand(pathVariables);
        }
        if (methodName.equals(DELETEBATCH)) {
            return SpringletsMvcUriComponentsBuilder.fromMethodCall(SpringletsMvcUriComponentsBuilder.on(getControllerClass()).deleteBatch(null)).buildAndExpand(pathVariables);
        }
        if (methodName.equals(EXPORTCSV)) {
            return SpringletsMvcUriComponentsBuilder.fromMethodCall(SpringletsMvcUriComponentsBuilder.on(getControllerClass()).exportCsv(null, null, null, null, null)).buildAndExpand(pathVariables);
        }
        if (methodName.equals(EXPORTPDF)) {
            return SpringletsMvcUriComponentsBuilder.fromMethodCall(SpringletsMvcUriComponentsBuilder.on(getControllerClass()).exportPdf(null, null, null, null, null)).buildAndExpand(pathVariables);
        }
        if (methodName.equals(EXPORTXLS)) {
            return SpringletsMvcUriComponentsBuilder.fromMethodCall(SpringletsMvcUriComponentsBuilder.on(getControllerClass()).exportXls(null, null, null, null, null)).buildAndExpand(pathVariables);
        }
        throw new IllegalArgumentException("Invalid method name: " + methodName);
    }

    /**
     * TODO
     *
     * @return Class
     */
    public Class<TopicsCollectionThymeleafController> getControllerClass() {
        return TopicsCollectionThymeleafController.class;
    }
}
