
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package joy.todont.domain;

import joy.todont.domain.Todo;

privileged aspect Todo_ToString {
    
    /**
     * TODO
     * 
     * @return String
     */
    public String Todo.toString() {
        return "Todo {" + 
                "id='" + id + '\'' + 
                ", version='" + version + '\'' + 
                ", text='" + text + '\'' + 
                ", createdDate='" + createdDate + '\'' + 
                ", createdBy='" + createdBy + '\'' + 
                ", modifiedDate='" + modifiedDate + '\'' + 
                ", modifiedBy='" + modifiedBy + '\'' + 
                ", ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE='" + ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE + '\'' + 
                ", ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE='" + ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE + '\'' + "}" + super.toString();
    }
    
}
