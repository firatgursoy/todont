package joy.todont.web;
import io.springlets.web.NotFoundException;
import io.springlets.web.mvc.util.ControllerMethodLinkBuilderFactory;
import io.springlets.web.mvc.util.MethodLinkBuilderFactory;
import joy.todont.domain.Topic;
import joy.todont.service.api.TopicService;
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
 * = TopicsItemThymeleafController
 TODO
 *
 */
@RequestMapping(value = "/topics/{topic}", name = "TopicsItemThymeleafController", produces = MediaType.TEXT_HTML_VALUE)
@Controller
public class TopicsItemThymeleafController {
    /**
     * TODO
     *
     */
    private TopicService topicService;
    /**
     * TODO
     *
     */
    private MethodLinkBuilderFactory<TopicsItemThymeleafController> itemLink;
    /**
     * TODO
     *
     */
    private MessageSource messageSource;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param topicService
     * @param messageSource
     * @param linkBuilder
     */
    @Autowired
    public TopicsItemThymeleafController(TopicService topicService, MessageSource messageSource, ControllerMethodLinkBuilderFactory linkBuilder) {
        setTopicService(topicService);
        setMessageSource(messageSource);
        setItemLink(linkBuilder.of(TopicsItemThymeleafController.class));
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
     * @param topic
     * @param version
     * @param concurrencyControl
     * @param result
     * @param model
     * @return ModelAndView
     */
    @PutMapping(name = "update")
    public ModelAndView update(@Valid @ModelAttribute Topic topic, @RequestParam("version") Integer version, @RequestParam(value = "concurrency", required = false, defaultValue = "") String concurrencyControl, BindingResult result, Model model) {
        // Check if provided form contain errors
        if (result.hasErrors()) {
            populateForm(model);

            return new ModelAndView("topics/edit");
        }
        // Concurrency control
        Topic existingTopic = getTopicService().findOne(topic.getId());
        if(topic.getVersion() != existingTopic.getVersion() && StringUtils.isEmpty(concurrencyControl)){
            populateForm(model);
            model.addAttribute("topic", topic);
            model.addAttribute("concurrency", true);
            return new ModelAndView("topics/edit");
        } else if(topic.getVersion() != existingTopic.getVersion() && "discard".equals(concurrencyControl)){
            populateForm(model);
            model.addAttribute("topic", existingTopic);
            model.addAttribute("concurrency", false);
            return new ModelAndView("topics/edit");
        } else if(topic.getVersion() != existingTopic.getVersion() && "apply".equals(concurrencyControl)){
            // Update the version field to be able to override the existing values
            topic.setVersion(existingTopic.getVersion());
        }
        Topic savedTopic = getTopicService().save(topic);
        UriComponents showURI = getItemLink().to(TopicsItemThymeleafLinkFactory.SHOW).with("topic", savedTopic.getId()).toUri();
        return new ModelAndView("redirect:" + showURI.toUriString());
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
     * @param model
     */
    public void populateForm(Model model) {
        populateFormats(model);
    }

    /**
     * TODO
     *
     * @param topic
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/inline", name = "showInline")
    public ModelAndView showInline(@ModelAttribute Topic topic, Model model) {
        model.addAttribute("topic", topic);
        return new ModelAndView("topics/showInline :: inline-content");
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
     * @param topic
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/edit-form", name = "editForm")
    public ModelAndView editForm(@ModelAttribute Topic topic, Model model) {
        populateForm(model);

        model.addAttribute("topic", topic);
        return new ModelAndView("topics/edit");
    }

    /**
     * TODO
     *
     * @param topic
     * @param model
     * @return ModelAndView
     */
    @GetMapping(name = "show")
    public ModelAndView show(@ModelAttribute Topic topic, Model model) {
        model.addAttribute("topic", topic);
        return new ModelAndView("topics/show");
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
     * @return MethodLinkBuilderFactory
     */
    public MethodLinkBuilderFactory<TopicsItemThymeleafController> getItemLink() {
        return itemLink;
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
     * @param dataBinder
     */
    @InitBinder("topic")
    public void initTopicBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    /**
     * TODO
     *
     * @param topic
     * @return ResponseEntity
     */
    @ResponseBody
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute Topic topic) {
        getTopicService().delete(topic);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO
     *
     * @param itemLink
     */
    public void setItemLink(MethodLinkBuilderFactory<TopicsItemThymeleafController> itemLink) {
        this.itemLink = itemLink;
    }
}
