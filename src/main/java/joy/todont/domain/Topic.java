package joy.todont.domain;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Calendar;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * = Topic
 TODO
 *
 */
//@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
public class Topic {

    /**
     * TODO
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * TODO
     *
     */
    @Version
    private Integer version;

    /**
     * TODO
     *
     */
    @NotNull
    private String name;

    /**
     * TODO
     *
     */
    @OneToMany(cascade = { javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST }, fetch = FetchType.LAZY, mappedBy = "topic")
    private Set<Todo> todos = new HashSet<Todo>();

    /**
     * TODO
     *
     */
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm a z")
    private Calendar createdDate;

    /**
     * TODO
     *
     */
    @CreatedBy
    private String createdBy;

    /**
     * TODO
     *
     */
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm a z")
    private Calendar modifiedDate;

    /**
     * TODO
     *
     */
    @LastModifiedBy
    private String modifiedBy;
}
