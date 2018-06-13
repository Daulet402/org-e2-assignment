package org.e2;

import org.e2.assessment.Assignment;
import org.junit.Test;

public class TestGeneral {

    private Assignment assignment = new Assignment();

    @Test
    public void testAddUser() {
        assignment.addUser("isabella");
        System.out.println("user added");
    }

    @Test
    public void testAddRole() {
        assignment.addRole("send_sms");
        System.out.println("role added");
    }

    @Test
    public void testAssignUsernameToRolename() {
        assignment.assignUsernameToRolename("john", "download_movie");
        System.out.println("assigned added");
    }

    @Test
    public void testGrantPrivilegeToUsername() {
        assignment.grantPrivilegeToUsername("admin", "john");
        System.out.println("granted to username");
    }

    @Test
    public void testGrantPrivilegeToRolename() {
        assignment.grantPrivilegeToRolename("developer", "upload_file");
        System.out.println("granted to role");
    }
}
