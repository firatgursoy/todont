package joy.todont.web;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import joy.todont.domain.Todo;
import joy.todont.domain.Topic;

/**
 * = TodoJsonMixin
 TODO
 *
 */
public abstract class TodoJsonMixin {
    /**
     * TODO
     *
     */
    @JsonDeserialize(using = TopicDeserializer.class)
    private Topic topic;

    /**
     * TODO
     *
     * @return Topic
     */
    public Topic getTopic() {
        return topic;
    }

    /**
     * TODO
     *
     * @param topic
     */
    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
