
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package joy.todont.domain;

import io.springlets.format.EntityFormat;
import javax.persistence.Entity;
import joy.todont.domain.Todo;
import joy.todont.domain.Topic;
import org.springframework.util.Assert;

privileged aspect Topic_Jpa_Entity {
    
    declare @type: joy.todont.domain.Topic: @Entity;
    
    declare @type: joy.todont.domain.Topic: @EntityFormat("#{name}");
    
    /**
     * TODO
     * 
     */
    public static final String Topic.ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE = "The given Iterable of items to add can't be null!";
    
    /**
     * TODO
     * 
     */
    public static final String Topic.ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE = "The given Iterable of items to add can't be null!";
    
    /**
     * TODO
     * 
     * @param todosToAdd
     */
    public void Topic.addToTodos(Iterable<Todo> todosToAdd) {
        Assert.notNull(todosToAdd, ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE);
        for (Todo item : todosToAdd) {
            this.todos.add(item);
            item.setTopic(this);
        }
    }
    
    /**
     * TODO
     * 
     * @param todosToRemove
     */
    public void Topic.removeFromTodos(Iterable<Todo> todosToRemove) {
        Assert.notNull(todosToRemove, ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE);
        for (Todo item : todosToRemove) {
            this.todos.remove(item);
            item.setTopic(null);
        }
    }
    
}
