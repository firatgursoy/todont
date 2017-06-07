
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package joy.todont.domain;

import java.util.Objects;
import joy.todont.domain.Topic;

privileged aspect Topic_Equals {
    
    /**
     * This `equals` implementation is specific for JPA entities and uses 
     * the entity identifier for it
     * @param obj
     * @return Boolean
     */
    public boolean Topic.equals(Object obj) {
        if (this == obj) {
            return true;
        }
        // instanceof is false if the instance is null
        if (!(obj instanceof Topic)) {
            return false;
        }
        return getId() != null && Objects.equals(getId(), ((Topic) obj).getId());
    }
    
    /**
     * This `hashCode` implementation is specific for JPA entities and uses a fixed `int` value to be able 
     * to identify the entity in collections after a new id is assigned to the entity
     * @return Integer
     */
    public int Topic.hashCode() {
        return 31;
    }
    
}
