
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package joy.todont.repository;

import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import joy.todont.domain.QTodo;
import joy.todont.domain.Todo;
import joy.todont.domain.Topic;
import joy.todont.repository.TodoRepositoryCustom;
import joy.todont.repository.TodoRepositoryImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

privileged aspect TodoRepositoryImpl_Jpa_Repository_Impl {
    
    declare parents: TodoRepositoryImpl implements TodoRepositoryCustom;
    
    declare @type: joy.todont.repository.TodoRepositoryImpl: @Transactional(readOnly = true);
    
    /**
     * TODO
     * 
     */
    public static final String TodoRepositoryImpl.TEXT = "text";
    
    /**
     * TODO
     * 
     */
    public static final String TodoRepositoryImpl.TOPIC = "topic";
    
    /**
     * TODO
     * 
     */
    public static final String TodoRepositoryImpl.CREATED_DATE = "createdDate";
    
    /**
     * TODO
     * 
     */
    public static final String TodoRepositoryImpl.CREATED_BY = "createdBy";
    
    /**
     * TODO
     * 
     */
    public static final String TodoRepositoryImpl.MODIFIED_DATE = "modifiedDate";
    
    /**
     * TODO
     * 
     */
    public static final String TodoRepositoryImpl.MODIFIED_BY = "modifiedBy";
    
    /**
     * TODO
     * 
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Todo> TodoRepositoryImpl.findAll(GlobalSearch globalSearch, Pageable pageable) {
        
        QTodo todo = QTodo.todo;
        
        JPQLQuery<Todo> query = from(todo);
        query.where(todo.createdBy.eq(SecurityContextHolder.getContext().getAuthentication().getName()));// principal filter

        Path<?>[] paths = new Path<?>[] {todo.text,todo.topic,todo.createdDate,todo.createdBy,todo.modifiedDate,todo.modifiedBy};        
        applyGlobalSearch(globalSearch, query, paths);
        
        AttributeMappingBuilder mapping = buildMapper()
			.map(TEXT, todo.text)
			.map(TOPIC, todo.topic)
			.map(CREATED_DATE, todo.createdDate)
			.map(CREATED_BY, todo.createdBy)
			.map(MODIFIED_DATE, todo.modifiedDate)
			.map(MODIFIED_BY, todo.modifiedBy);
        
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        
        return loadPage(query, pageable, todo);
    }
    
    /**
     * TODO
     * 
     * @param topic
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Todo> TodoRepositoryImpl.findByTopic(Topic topic, GlobalSearch globalSearch, Pageable pageable) {
        
        QTodo todo = QTodo.todo;
        
        JPQLQuery<Todo> query = from(todo);
        query.where(todo.createdBy.eq(SecurityContextHolder.getContext().getAuthentication().getName()));// principal filter

        Assert.notNull(topic, "topic is required");
        
        query.where(todo.topic.eq(topic));
        Path<?>[] paths = new Path<?>[] {todo.text,todo.topic,todo.createdDate,todo.createdBy,todo.modifiedDate,todo.modifiedBy};        
        applyGlobalSearch(globalSearch, query, paths);
        
        AttributeMappingBuilder mapping = buildMapper()
			.map(TEXT, todo.text)
			.map(TOPIC, todo.topic)
			.map(CREATED_DATE, todo.createdDate)
			.map(CREATED_BY, todo.createdBy)
			.map(MODIFIED_DATE, todo.modifiedDate)
			.map(MODIFIED_BY, todo.modifiedBy);
        
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        
        return loadPage(query, pageable, todo);
    }
    
}
