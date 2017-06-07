package joy.todont.repository;

import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import joy.todont.domain.Todo;

/**
 * = TodoRepositoryImpl
 *
 * TODO
 *
 */
public class TodoRepositoryImpl extends QueryDslRepositorySupportExt<Todo> {

    /**
     * TODO Auto-generated constructor documentation
     */
    TodoRepositoryImpl() {
        super(Todo.class);
    }
}