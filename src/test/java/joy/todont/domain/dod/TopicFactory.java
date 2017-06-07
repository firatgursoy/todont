package joy.todont.domain.dod;
import joy.todont.domain.Topic;

import java.util.Calendar;

/**
 * = TopicFactory
 TODO Auto-generated class documentation
 *
 */
public class TopicFactory {
    /**
     * Creates a new {@link Topic} with the given index.
     *
     * @param index position of the Topic
     * @return a new transient Topic
     */
    public Topic create(int index) {
        Topic obj = new Topic();
        setCreatedBy(obj, index);
        setCreatedDate(obj, index);
        setModifiedBy(obj, index);
        setModifiedDate(obj, index);
        setName(obj, index);
        return obj;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param obj
     * @param index
     */
    public void setCreatedBy(Topic obj, int index) {
        String createdBy = "createdBy_" + index;
        obj.setCreatedBy(createdBy);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param obj
     * @param index
     */
    public void setCreatedDate(Topic obj, int index) {
        Calendar createdDate = Calendar.getInstance();
        obj.setCreatedDate(createdDate);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param obj
     * @param index
     */
    public void setModifiedBy(Topic obj, int index) {
        String modifiedBy = "modifiedBy_" + index;
        obj.setModifiedBy(modifiedBy);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param obj
     * @param index
     */
    public void setModifiedDate(Topic obj, int index) {
        Calendar modifiedDate = Calendar.getInstance();
        obj.setModifiedDate(modifiedDate);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param obj
     * @param index
     */
    public void setName(Topic obj, int index) {
        String name = "name_" + index;
        obj.setName(name);
    }
}
