
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package joy.todont.domain;

import javax.persistence.EntityListeners;
import joy.todont.domain.Topic;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

privileged aspect Topic_Jpa_Audit {
    
    declare @type: joy.todont.domain.Topic: @EntityListeners(AuditingEntityListener.class);
    
}
