package joy.todont.domain.dod;
import joy.todont.domain.Todo;
import joy.todont.domain.Topic;

import java.util.Calendar;

/**
 * = TodoFactory
 TODO Auto-generated class documentation
 *
 */
public class TodoFactory {
    /**
     * Creates a new {@link Todo} with the given index.
     *
     * @param index position of the Todo
     * @return a new transient Todo
     */
    public Todo create(int index) {
        Todo obj = new Todo();
        setCreatedBy(obj, index);
        setCreatedDate(obj, index);
        setModifiedBy(obj, index);
        setModifiedDate(obj, index);
        setText(obj, index);
        setTopic(obj, index);
        return obj;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param obj
     * @param index
     */
    public void setCreatedBy(Todo obj, int index) {
        String createdBy = "createdBy_" + index;
        obj.setCreatedBy(createdBy);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param obj
     * @param index
     */
    public void setCreatedDate(Todo obj, int index) {
        Calendar createdDate = Calendar.getInstance();
        obj.setCreatedDate(createdDate);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param obj
     * @param index
     */
    public void setModifiedBy(Todo obj, int index) {
        String modifiedBy = "modifiedBy_" + index;
        obj.setModifiedBy(modifiedBy);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param obj
     * @param index
     */
    public void setModifiedDate(Todo obj, int index) {
        Calendar modifiedDate = Calendar.getInstance();
        obj.setModifiedDate(modifiedDate);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param obj
     * @param index
     */
    public void setText(Todo obj, int index) {
        String text = "text_" + index;
        obj.setText(text);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param obj
     * @param index
     */
    public void setTopic(Todo obj, int index) {
        Topic topic = null;
        obj.setTopic(topic);
    }
}
