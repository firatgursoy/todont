
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package joy.todont.domain;

import javax.persistence.EntityListeners;
import joy.todont.domain.Todo;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

privileged aspect Todo_Jpa_Audit {
    
    declare @type: joy.todont.domain.Todo: @EntityListeners(AuditingEntityListener.class);
    
}
