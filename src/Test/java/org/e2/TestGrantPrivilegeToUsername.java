package org.e2;

import org.e2.assessment.exception.SystemException;
import org.junit.Test;

public class TestGrantPrivilegeToUsername extends AbstractSecuritySystemTest {

    @Override
    public void before() throws SystemException {
        securitySystem.addUser(ELON_USERNAME);
        securitySystem.grantPrivilegeToUsername(CREATE_USERS_PRIVILEGE, ELON_USERNAME);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testInputParameters() throws SystemException {
        securitySystem.grantPrivilegeToUsername(DOWNLOAD_MOVIE_PRIVILEGE, null);
    }

    @Test(expected = SystemException.class)
    public void testUserExistence() throws SystemException {
        securitySystem.grantPrivilegeToUsername(DOWNLOAD_MOVIE_PRIVILEGE, TOM_USERNAME);
    }

    @Test(expected = SystemException.class)
    public void testIsUserAlreadyAllowedForPrivilege() throws SystemException {
        securitySystem.grantPrivilegeToUsername(CREATE_USERS_PRIVILEGE, ELON_USERNAME);
    }

    @Test
    public void testGrantPrivilegeToUsername() throws SystemException {
        securitySystem.grantPrivilegeToUsername(DELETE_USERS_PRIVILEGE, ELON_USERNAME);
    }
}
