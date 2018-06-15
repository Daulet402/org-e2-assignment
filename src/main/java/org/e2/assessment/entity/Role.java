package org.e2.assessment.entity;

import java.util.ArrayList;
import java.util.List;

public class Role {
    private String roleName;
    private List<Privilege> privileges = new ArrayList<>();

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }
}
