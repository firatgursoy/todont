package joy.todont.web;
import io.springlets.web.NotFoundException;
import io.springlets.web.mvc.util.ControllerMethodLinkBuilderFactory;
import io.springlets.web.mvc.util.MethodLinkBuilderFactory;
import joy.todont.domain.Todo;
import joy.todont.service.api.TodoService;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;

import javax.validation.Valid;
import java.util.Locale;

/**
 * = TodoesItemThymeleafController
 TODO
 *
 */
@RequestMapping(value = "/todoes/{todo}", name = "TodoesItemThymeleafController", produces = MediaType.TEXT_HTML_VALUE)
@Controller
public class TodoesItemThymeleafController {
    /**
     * TODO
     *
     */
    private MessageSource messageSource;
    /**
     * TODO
     *
     */
    private MethodLinkBuilderFactory<TodoesItemThymeleafController> itemLink;
    /**
     * TODO
     *
     */
    private TodoService todoService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param todoService
     * @param messageSource
     * @param linkBuilder
     */
    @Autowired
    public TodoesItemThymeleafController(TodoService todoService, MessageSource messageSource, ControllerMethodLinkBuilderFactory linkBuilder) {
        setTodoService(todoService);
        setMessageSource(messageSource);
        setItemLink(linkBuilder.of(TodoesItemThymeleafController.class));
    }

    /**
     * TODO
     *
     * @param dataBinder
     */
    @InitBinder("todo")
    public void initTodoBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
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
     * @param id
     * @param locale
     * @param method
     * @return Todo
     */
    @ModelAttribute
    public Todo getTodo(@PathVariable("todo") Long id, Locale locale, HttpMethod method) {
        Todo todo = null;
        if (HttpMethod.PUT.equals(method)) {
            todo = todoService.findOneForUpdate(id);
        } else {
            todo = todoService.findOne(id);
        }

        if (todo == null) {
            String message = messageSource.getMessage("error_NotFound", new Object[] {"Todo", id}, "The record couldn't be found", locale);
            throw new NotFoundException(message);
        }
        return todo;
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
     * @return MessageSource
     */
    public MessageSource getMessageSource() {
        return messageSource;
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
     * @param todo
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/inline", name = "showInline")
    public ModelAndView showInline(@ModelAttribute Todo todo, Model model) {
        model.addAttribute("todo", todo);
        return new ModelAndView("todoes/showInline :: inline-content");
    }

    /**
     * TODO
     *
     * @param todo
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/edit-form", name = "editForm")
    public ModelAndView editForm(@ModelAttribute Todo todo, Model model) {
        populateForm(model);

        model.addAttribute("todo", todo);
        return new ModelAndView("todoes/edit");
    }

    /**
     * TODO
     *
     * @return MethodLinkBuilderFactory
     */
    public MethodLinkBuilderFactory<TodoesItemThymeleafController> getItemLink() {
        return itemLink;
    }

    /**
     * TODO
     *
     * @param todo
     * @param version
     * @param concurrencyControl
     * @param result
     * @param model
     * @return ModelAndView
     */
    @PutMapping(name = "update")
    public ModelAndView update(@Valid @ModelAttribute Todo todo, @RequestParam("version") Integer version, @RequestParam(value = "concurrency", required = false, defaultValue = "") String concurrencyControl, BindingResult result, Model model) {
        // Check if provided form contain errors
        if (result.hasErrors()) {
            populateForm(model);

            return new ModelAndView("todoes/edit");
        }
        // Concurrency control
        Todo existingTodo = getTodoService().findOne(todo.getId());
        if(todo.getVersion() != existingTodo.getVersion() && StringUtils.isEmpty(concurrencyControl)){
            populateForm(model);
            model.addAttribute("todo", todo);
            model.addAttribute("concurrency", true);
            return new ModelAndView("todoes/edit");
        } else if(todo.getVersion() != existingTodo.getVersion() && "discard".equals(concurrencyControl)){
            populateForm(model);
            model.addAttribute("todo", existingTodo);
            model.addAttribute("concurrency", false);
            return new ModelAndView("todoes/edit");
        } else if(todo.getVersion() != existingTodo.getVersion() && "apply".equals(concurrencyControl)){
            // Update the version field to be able to override the existing values
            todo.setVersion(existingTodo.getVersion());
        }
        Todo savedTodo = getTodoService().save(todo);
        UriComponents showURI = getItemLink().to(TodoesItemThymeleafLinkFactory.SHOW).with("todo", savedTodo.getId()).toUri();
        return new ModelAndView("redirect:" + showURI.toUriString());
    }

    /**
     * TODO
     *
     * @param todo
     * @return ResponseEntity
     */
    @ResponseBody
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute Todo todo) {
        getTodoService().delete(todo);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO
     *
     * @param itemLink
     */
    public void setItemLink(MethodLinkBuilderFactory<TodoesItemThymeleafController> itemLink) {
        this.itemLink = itemLink;
    }

    /**
     * TODO
     *
     * @param todo
     * @param model
     * @return ModelAndView
     */
    @GetMapping(name = "show")
    public ModelAndView show(@ModelAttribute Todo todo, Model model) {
        model.addAttribute("todo", todo);
        return new ModelAndView("todoes/show");
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
}
