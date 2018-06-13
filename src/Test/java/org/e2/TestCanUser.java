package org.e2;

import org.e2.assessment.Assignment;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCanUser {

    private Assignment assignment = new Assignment();

    private static final String JOHN_USERNAME = "john";

    private static final String ADMIN_PRIVILEGE = "admin";
    private static final String DEVELOPER_PRIVILEGE = "developer";

    private static final String DELETE_USERS_ROLENAME = "delete_users";
    private static final String CREATE_TABLES_ROLENAME = "create_tableS";


    @Before
    public void before() {
        assignment.assignUsernameToRolename(JOHN_USERNAME, DELETE_USERS_ROLENAME);

        assignment.grantPrivilegeToRolename(ADMIN_PRIVILEGE, DELETE_USERS_ROLENAME);
        assignment.grantPrivilegeToRolename(ADMIN_PRIVILEGE, CREATE_TABLES_ROLENAME);
        assignment.grantPrivilegeToRolename(DEVELOPER_PRIVILEGE, CREATE_TABLES_ROLENAME);

        System.out.println("initialized in before method");
    }

    @Test
    public void testCanUsernameTrue() {
        boolean canUsername = assignment.canUsername(JOHN_USERNAME, ADMIN_PRIVILEGE);
        Assert.assertTrue(canUsername);
        System.out.println("can user");
    }

    @Test
    public void testCanUsernameFalse() {
        boolean canUsername = assignment.canUsername(JOHN_USERNAME, DEVELOPER_PRIVILEGE);
        Assert.assertFalse(canUsername);
        System.out.println("can not user");
    }
}
