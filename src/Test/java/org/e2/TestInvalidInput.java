package org.e2;

import org.e2.assessment.Assignment;
import org.junit.Test;

public class TestInvalidInput {

    private Assignment assignment = new Assignment();

    @Test(expected = IllegalArgumentException.class)
    public void testAddUser() {
        assignment.addUser(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddRole() {
        assignment.addRole(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssignUsernameToRolename() {
        assignment.assignUsernameToRolename(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGrantPrivilegeToUsername() {
        assignment.grantPrivilegeToUsername(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGrantPrivilegeToRolename() {
        assignment.grantPrivilegeToRolename(null, null);
    }
}
