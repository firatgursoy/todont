
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package joy.todont.repository;

import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import joy.todont.domain.QTopic;
import joy.todont.domain.Topic;
import joy.todont.repository.TopicRepositoryCustom;
import joy.todont.repository.TopicRepositoryImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

privileged aspect TopicRepositoryImpl_Jpa_Repository_Impl {
    
    declare parents: TopicRepositoryImpl implements TopicRepositoryCustom;
    
    declare @type: joy.todont.repository.TopicRepositoryImpl: @Transactional(readOnly = true);
    
    /**
     * TODO
     * 
     */
    public static final String TopicRepositoryImpl.NAME = "name";
    
    /**
     * TODO
     * 
     */
    public static final String TopicRepositoryImpl.CREATED_DATE = "createdDate";
    
    /**
     * TODO
     * 
     */
    public static final String TopicRepositoryImpl.CREATED_BY = "createdBy";
    
    /**
     * TODO
     * 
     */
    public static final String TopicRepositoryImpl.MODIFIED_DATE = "modifiedDate";
    
    /**
     * TODO
     * 
     */
    public static final String TopicRepositoryImpl.MODIFIED_BY = "modifiedBy";
    
    /**
     * TODO
     * 
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Topic> TopicRepositoryImpl.findAll(GlobalSearch globalSearch, Pageable pageable) {
        
        QTopic topic = QTopic.topic;
        
        JPQLQuery<Topic> query = from(topic);
        query.where(topic.createdBy.eq(SecurityContextHolder.getContext().getAuthentication().getName()));// principal filter
        Path<?>[] paths = new Path<?>[] {topic.name,topic.createdDate,topic.createdBy,topic.modifiedDate,topic.modifiedBy};        
        applyGlobalSearch(globalSearch, query, paths);
        
        AttributeMappingBuilder mapping = buildMapper()
			.map(NAME, topic.name)
			.map(CREATED_DATE, topic.createdDate)
			.map(CREATED_BY, topic.createdBy)
			.map(MODIFIED_DATE, topic.modifiedDate)
			.map(MODIFIED_BY, topic.modifiedBy);
        
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        
        return loadPage(query, pageable, topic);
    }
    
}
