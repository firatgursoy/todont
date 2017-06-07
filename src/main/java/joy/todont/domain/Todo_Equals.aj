
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package joy.todont.domain;

import java.util.Objects;
import joy.todont.domain.Todo;

privileged aspect Todo_Equals {
    
    /**
     * This `equals` implementation is specific for JPA entities and uses
     * the entity identifier for it
     * @param obj
     * @return Boolean
     */
    public boolean Todo.equals(Object obj) {
        if (this == obj) {
            return true;
        }
        // instanceof is false if the instance is null
        if (!(obj instanceof Todo)) {
            return false;
        }
        return getId() != null && Objects.equals(getId(), ((Todo) obj).getId());
    }
    
    /**
     * This `hashCode` implementation is specific for JPA entities and uses a fixed `int` value to be able
     * to identify the entity in collections after a new id is assigned to the entity
     * @return Integer
     */
    public int Todo.hashCode() {
        return 31;
    }
    
}
