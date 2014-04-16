package org.springframework.data.orient.object.person;

import com.orientechnologies.orient.core.annotation.OId;
import com.orientechnologies.orient.core.annotation.OVersion;

public class Person {

    @OId
    private String rid;
    
    @OVersion
    private Long version;
    
    private String firstName;
 
    private String lastName;

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
