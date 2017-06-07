package joy.todont.web;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import io.springlets.web.NotFoundException;
import joy.todont.domain.Todo;
import joy.todont.service.api.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;

import java.io.IOException;

/**
 * = TodoDeserializer
 TODO
 *
 */
@JsonComponent
public class TodoDeserializer extends JsonObjectDeserializer<Todo> {

    /**
     * TODO
     *
     */
    private TodoService todoService;

    /**
     * TODO
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param todoService
     * @param conversionService
     */
    @Autowired
    public TodoDeserializer(@Lazy TodoService todoService, ConversionService conversionService) {
        this.todoService = todoService;
        this.conversionService = conversionService;
    }

    /**
     * TODO
     *
     * @return TodoService
     */
    public TodoService getTodoService() {
        return todoService;
    }

    /**
     * TODO
     *
     * @param todoService
     */
    public void setTodoService(TodoService todoService) {
        this.todoService = todoService;
    }

    /**
     * TODO
     *
     * @return ConversionService
     */
    public ConversionService getConversionService() {
        return conversionService;
    }

    /**
     * TODO
     *
     * @param conversionService
     */
    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    /**
     * TODO
     *
     * @param jsonParser
     * @param context
     * @param codec
     * @param tree
     * @return Todo
     * @throws IOException
     */
    public Todo deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) throws IOException {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        Todo todo = todoService.findOne(id);
        if (todo == null) {
            throw new NotFoundException("Todo not found");
        }
        return todo;
    }
}
