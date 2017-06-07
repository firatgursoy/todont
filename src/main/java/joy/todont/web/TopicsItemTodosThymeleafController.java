package joy.todont.web;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.web.datatables.ConvertedDatatablesData;
import io.springlets.data.web.datatables.Datatables;
import io.springlets.data.web.datatables.DatatablesColumns;
import io.springlets.data.web.datatables.DatatablesPageable;
import io.springlets.web.NotFoundException;
import io.springlets.web.mvc.util.ControllerMethodLinkBuilderFactory;
import io.springlets.web.mvc.util.MethodLinkBuilderFactory;
import joy.todont.domain.Todo;
import joy.todont.domain.Topic;
import joy.todont.service.api.TodoService;
import joy.todont.service.api.TopicService;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * = TopicsItemTodosThymeleafController
 TODO
 *
 */
@RequestMapping(value = "/topics/{topic}/todos", name = "TopicsItemTodosThymeleafController", produces = MediaType.TEXT_HTML_VALUE)
@Controller
public class TopicsItemTodosThymeleafController {
    /**
     * TODO
     *
     */
    private TopicService topicService;
    /**
     * TODO
     *
     */
    private TodoService todoService;
    /**
     * TODO
     *
     */
    private MessageSource messageSource;
    /**
     * TODO
     *
     */
    private MethodLinkBuilderFactory<TopicsCollectionThymeleafController> collectionLink;
    /**
     * TODO
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param topicService
     * @param todoService
     * @param conversionService
     * @param messageSource
     * @param linkBuilder
     */
    @Autowired
    public TopicsItemTodosThymeleafController(TopicService topicService, TodoService todoService, ConversionService conversionService, MessageSource messageSource, ControllerMethodLinkBuilderFactory linkBuilder) {
        setTopicService(topicService);
        setTodoService(todoService);
        setConversionService(conversionService);
        setMessageSource(messageSource);
        setCollectionLink(linkBuilder.of(TopicsCollectionThymeleafController.class));
    }

    /**
     * TODO
     *
     * @return TopicService
     */
    public TopicService getTopicService() {
        return topicService;
    }

    /**
     * TODO
     *
     * @param topicService
     */
    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
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
     * @param topic
     * @param datatablesColumns
     * @param search
     * @param pageable
     * @param draw
     * @return ResponseEntity
     */
    @GetMapping(name = "datatables", produces = Datatables.MEDIA_TYPE, value = "/dt")
    @ResponseBody
    public ResponseEntity<ConvertedDatatablesData<Todo>> datatables(@ModelAttribute Topic topic, DatatablesColumns datatablesColumns, GlobalSearch search, DatatablesPageable pageable, @RequestParam("draw") Integer draw) {

        Page<Todo> todos = getTodoService().findByTopic(topic, search, pageable);
        long totalTodosCount = getTodoService().countByTopic(topic);
        ConvertedDatatablesData<Todo> data =  new ConvertedDatatablesData<Todo>(todos, totalTodosCount, draw, getConversionService(), datatablesColumns);
        return ResponseEntity.ok(data);
    }

    /**
     * TODO
     *
     * @param topic
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/create-form", name = "createForm")
    public ModelAndView createForm(@ModelAttribute Topic topic, Model model) {
        populateForm(model);
        model.addAttribute("todo", new Todo());
        return new ModelAndView("topics/todos/create");
    }

    /**
     * TODO
     *
     * @param topic
     * @param todosToRemove
     * @return ResponseEntity
     */
    @DeleteMapping(name = "removeFromTodosBatch", value = "/batch/{todosToRemove}")
    @ResponseBody
    public ResponseEntity<?> removeFromTodosBatch(@ModelAttribute Topic topic, @PathVariable("todosToRemove") Collection<Long> todosToRemove) {
        getTopicService().removeFromTodos(topic, todosToRemove);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO
     *
     * @param model
     */
    public void populateFormats(Model model) {
        model.addAttribute("application_locale", LocaleContextHolder.getLocale().getLanguage());
        model.addAttribute("createdDate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        model.addAttribute("modifiedDate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }

    /**
     * TODO
     *
     * @param topic
     * @param todos
     * @param version
     * @param concurrencyControl
     * @param model
     * @return ModelAndView
     */
    @PostMapping(name = "create")
    public ModelAndView create(@ModelAttribute Topic topic, @RequestParam(value = "todosIds", required = false) List<Long> todos, @RequestParam("parentVersion") Integer version, @RequestParam(value = "concurrency", required = false, defaultValue = "") String concurrencyControl, Model model) {
        // Remove empty values
        if (todos != null) {
            for (Iterator<Long> iterator = todos.iterator(); iterator.hasNext();) {
                if (iterator.next() == null) {
                    iterator.remove();
                }
            }
        }
        // Concurrency control
        if(version != topic.getVersion() && StringUtils.isEmpty(concurrencyControl)){
            populateForm(model);
            // Obtain the selected books and include them in the author that will be
            // included in the view
            if (todos != null) {
                topic.setTodos(new HashSet<Todo>(getTodoService().findAll(todos)));
            }else{
                topic.setTodos(new HashSet<Todo>());
            }
            // Reset the version to prevent update
             topic.setVersion(version);
            // Include the updated element in the model
            model.addAttribute("topic", topic);
            model.addAttribute("concurrency", true);
            return new ModelAndView("topics/todos/create");
        }else if(version != topic.getVersion() && "discard".equals(concurrencyControl)){
            populateForm(model);
            // Provide the original element from the Database
            model.addAttribute("topic", topic);
            model.addAttribute("concurrency", false);
            return new ModelAndView("topics/todos/create");
        }
        getTopicService().setTodos(topic,todos);
        return new ModelAndView("redirect:" + getCollectionLink().to(TopicsCollectionThymeleafLinkFactory.LIST).toUriString());
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
     * @param topic
     * @param todosToRemove
     * @return ResponseEntity
     */
    @DeleteMapping(name = "removeFromTodos", value = "/{todosToRemove}")
    @ResponseBody
    public ResponseEntity<?> removeFromTodos(@ModelAttribute Topic topic, @PathVariable("todosToRemove") Long todosToRemove) {
        getTopicService().removeFromTodos(topic, Collections.singleton(todosToRemove));
        return ResponseEntity.ok().build();
    }

    /**
     * TODO
     *
     * @return MessageSource
     */
    public MessageSource getMessageSource() {
        return messageSource;
    }

    /**
     * TODO
     *
     * @param collectionLink
     */
    public void setCollectionLink(MethodLinkBuilderFactory<TopicsCollectionThymeleafController> collectionLink) {
        this.collectionLink = collectionLink;
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
     * @param model
     */
    public void populateForm(Model model) {
        populateFormats(model);
    }

    /**
     * TODO
     *
     * @param messageSource
     */
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * TODO
     *
     * @param id
     * @param locale
     * @param method
     * @return Topic
     */
    @ModelAttribute
    public Topic getTopic(@PathVariable("topic") Long id, Locale locale, HttpMethod method) {
        Topic topic = null;
        if (HttpMethod.PUT.equals(method)) {
            topic = topicService.findOneForUpdate(id);
        } else {
            topic = topicService.findOne(id);
        }

        if (topic == null) {
            String message = messageSource.getMessage("error_NotFound", new Object[] {"Topic", id}, "The record couldn't be found", locale);
            throw new NotFoundException(message);
        }
        return topic;
    }

    /**
     * TODO
     *
     * @return MethodLinkBuilderFactory
     */
    public MethodLinkBuilderFactory<TopicsCollectionThymeleafController> getCollectionLink() {
        return collectionLink;
    }
}
