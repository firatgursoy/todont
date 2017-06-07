
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package joy.todont.domain;

import java.util.Calendar;
import java.util.Set;
import joy.todont.domain.Todo;
import joy.todont.domain.Topic;

privileged aspect Topic_JavaBean {
    
    /**
     * TODO
     * 
     * @return Long
     */
    public Long Topic.getId() {
        return this.id;
    }
    
    /**
     * TODO
     * 
     * @param id
     */
    public void Topic.setId(Long id) {
        this.id = id;
    }
    
    /**
     * TODO
     * 
     * @return Integer
     */
    public Integer Topic.getVersion() {
        return this.version;
    }
    
    /**
     * TODO
     * 
     * @param version
     */
    public void Topic.setVersion(Integer version) {
        this.version = version;
    }
    
    /**
     * TODO
     * 
     * @return String
     */
    public String Topic.getName() {
        return this.name;
    }
    
    /**
     * TODO
     * 
     * @param name
     */
    public void Topic.setName(String name) {
        this.name = name;
    }
    
    /**
     * TODO
     * 
     * @return Set
     */
    public Set<Todo> Topic.getTodos() {
        return this.todos;
    }
    
    /**
     * TODO
     * 
     * @param todos
     */
    public void Topic.setTodos(Set<Todo> todos) {
        this.todos = todos;
    }
    
    /**
     * TODO
     * 
     * @return Calendar
     */
    public Calendar Topic.getCreatedDate() {
        return this.createdDate;
    }
    
    /**
     * TODO
     * 
     * @param createdDate
     */
    public void Topic.setCreatedDate(Calendar createdDate) {
        this.createdDate = createdDate;
    }
    
    /**
     * TODO
     * 
     * @return String
     */
    public String Topic.getCreatedBy() {
        return this.createdBy;
    }
    
    /**
     * TODO
     * 
     * @param createdBy
     */
    public void Topic.setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    /**
     * TODO
     * 
     * @return Calendar
     */
    public Calendar Topic.getModifiedDate() {
        return this.modifiedDate;
    }
    
    /**
     * TODO
     * 
     * @param modifiedDate
     */
    public void Topic.setModifiedDate(Calendar modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    
    /**
     * TODO
     * 
     * @return String
     */
    public String Topic.getModifiedBy() {
        return this.modifiedBy;
    }
    
    /**
     * TODO
     * 
     * @param modifiedBy
     */
    public void Topic.setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    
}
