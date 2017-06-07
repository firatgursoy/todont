package joy.todont.domain;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;
import io.springlets.format.EntityFormat;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Calendar;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * = Todo
 TODO
 *
 */
public class Todo {

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
    private String text;

    /**
     * TODO
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @EntityFormat
    private Topic topic;

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
