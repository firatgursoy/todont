
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package joy.todont.domain;

import io.springlets.format.EntityFormat;
import javax.persistence.Entity;
import joy.todont.domain.Todo;

privileged aspect Todo_Jpa_Entity {
    
    declare @type: joy.todont.domain.Todo: @Entity;
    
    declare @type: joy.todont.domain.Todo: @EntityFormat("#{text}");
    
    /**
     * TODO
     * 
     */
    public static final String Todo.ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE = "The given Iterable of items to add can't be null!";
    
    /**
     * TODO
     * 
     */
    public static final String Todo.ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE = "The given Iterable of items to add can't be null!";
    
}
