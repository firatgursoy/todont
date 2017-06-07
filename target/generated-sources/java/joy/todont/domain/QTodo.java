package joy.todont.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTodo is a Querydsl query type for Todo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTodo extends EntityPathBase<Todo> {

    private static final long serialVersionUID = 1052440904L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTodo todo = new QTodo("todo");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Calendar> createdDate = createDateTime("createdDate", java.util.Calendar.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath modifiedBy = createString("modifiedBy");

    public final DateTimePath<java.util.Calendar> modifiedDate = createDateTime("modifiedDate", java.util.Calendar.class);

    public final StringPath text = createString("text");

    public final QTopic topic;

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QTodo(String variable) {
        this(Todo.class, forVariable(variable), INITS);
    }

    public QTodo(Path<? extends Todo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTodo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTodo(PathMetadata metadata, PathInits inits) {
        this(Todo.class, metadata, inits);
    }

    public QTodo(Class<? extends Todo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.topic = inits.isInitialized("topic") ? new QTopic(forProperty("topic")) : null;
    }

}

