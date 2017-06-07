
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package joy.todont.domain;

import java.util.Calendar;
import joy.todont.domain.Todo;
import joy.todont.domain.Topic;

privileged aspect Todo_JavaBean {
    
    /**
     * TODO
     * 
     * @return Long
     */
    public Long Todo.getId() {
        return this.id;
    }
    
    /**
     * TODO
     * 
     * @param id
     */
    public void Todo.setId(Long id) {
        this.id = id;
    }
    
    /**
     * TODO
     * 
     * @return Integer
     */
    public Integer Todo.getVersion() {
        return this.version;
    }
    
    /**
     * TODO
     * 
     * @param version
     */
    public void Todo.setVersion(Integer version) {
        this.version = version;
    }
    
    /**
     * TODO
     * 
     * @return String
     */
    public String Todo.getText() {
        return this.text;
    }
    
    /**
     * TODO
     * 
     * @param text
     */
    public void Todo.setText(String text) {
        this.text = text;
    }
    
    /**
     * TODO
     * 
     * @return Topic
     */
    public Topic Todo.getTopic() {
        return this.topic;
    }
    
    /**
     * TODO
     * 
     * @param topic
     */
    public void Todo.setTopic(Topic topic) {
        this.topic = topic;
    }
    
    /**
     * TODO
     * 
     * @return Calendar
     */
    public Calendar Todo.getCreatedDate() {
        return this.createdDate;
    }
    
    /**
     * TODO
     * 
     * @param createdDate
     */
    public void Todo.setCreatedDate(Calendar createdDate) {
        this.createdDate = createdDate;
    }
    
    /**
     * TODO
     * 
     * @return String
     */
    public String Todo.getCreatedBy() {
        return this.createdBy;
    }
    
    /**
     * TODO
     * 
     * @param createdBy
     */
    public void Todo.setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    /**
     * TODO
     * 
     * @return Calendar
     */
    public Calendar Todo.getModifiedDate() {
        return this.modifiedDate;
    }
    
    /**
     * TODO
     * 
     * @param modifiedDate
     */
    public void Todo.setModifiedDate(Calendar modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    
    /**
     * TODO
     * 
     * @return String
     */
    public String Todo.getModifiedBy() {
        return this.modifiedBy;
    }
    
    /**
     * TODO
     * 
     * @param modifiedBy
     */
    public void Todo.setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    
}
