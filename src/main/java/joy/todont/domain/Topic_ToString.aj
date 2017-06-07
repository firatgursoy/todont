
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package joy.todont.domain;

import joy.todont.domain.Topic;

privileged aspect Topic_ToString {
    
    /**
     * TODO
     * 
     * @return String
     */
    public String Topic.toString() {
        return "Topic {" + 
                "id='" + id + '\'' + 
                ", version='" + version + '\'' + 
                ", name='" + name + '\'' + 
                ", createdDate='" + createdDate + '\'' + 
                ", createdBy='" + createdBy + '\'' + 
                ", modifiedDate='" + modifiedDate + '\'' + 
                ", modifiedBy='" + modifiedBy + '\'' + 
                ", ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE='" + ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE + '\'' + 
                ", ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE='" + ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE + '\'' + "}" + super.toString();
    }
    
}
