package org.e2;

import org.e2.assessment.exception.SystemException;
import org.junit.Test;

public class TestAssignUsernameToRolename extends AbstractSecuritySystemTest {

    @Override
    public void before() throws SystemException {
        securitySystem.addUser(MARY_USERNAME);
        securitySystem.addUser(ELON_USERNAME);
        securitySystem.addRole(CTO_ROLENAME);
        securitySystem.assignUsernameToRolename(MARY_USERNAME, CTO_ROLENAME);
    }

    @Test
    public void testAssignUsernameToRolename() throws SystemException {
        securitySystem.assignUsernameToRolename(ELON_USERNAME, CTO_ROLENAME);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInputParameters() throws SystemException {
        securitySystem.assignUsernameToRolename("", CTO_ROLENAME);
    }

    @Test(expected = SystemException.class)
    public void testRoleDoesNotExist() throws SystemException {
        securitySystem.assignUsernameToRolename(MARY_USERNAME, CEO_ROLENAME);
    }

    @Test(expected = SystemException.class)
    public void testUserDoesNotExist() throws SystemException {
        securitySystem.assignUsernameToRolename(ANGELINA_USERNAME, CTO_ROLENAME);
    }

    @Test(expected = SystemException.class)
    public void testAlreadyAssigned() throws SystemException {
        securitySystem.assignUsernameToRolename(MARY_USERNAME, CTO_ROLENAME);
    }
}
