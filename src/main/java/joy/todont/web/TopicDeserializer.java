package joy.todont.web;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import io.springlets.web.NotFoundException;
import joy.todont.domain.Topic;
import joy.todont.service.api.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;

import java.io.IOException;

/**
 * = TopicDeserializer
 TODO
 *
 */
@JsonComponent
public class TopicDeserializer extends JsonObjectDeserializer<Topic> {

    /**
     * TODO
     *
     */
    private TopicService topicService;

    /**
     * TODO
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param topicService
     * @param conversionService
     */
    @Autowired
    public TopicDeserializer(@Lazy TopicService topicService, ConversionService conversionService) {
        this.topicService = topicService;
        this.conversionService = conversionService;
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
     * @return Topic
     * @throws IOException
     */
    public Topic deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) throws IOException {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        Topic topic = topicService.findOne(id);
        if (topic == null) {
            throw new NotFoundException("Topic not found");
        }
        return topic;
    }
}
