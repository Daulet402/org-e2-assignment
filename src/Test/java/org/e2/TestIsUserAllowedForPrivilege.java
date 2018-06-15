package org.e2;

import org.e2.assessment.exception.SystemException;
import org.junit.Assert;
import org.junit.Test;

public class TestIsUserAllowedForPrivilege extends AbstractSecuritySystemTest {

    @Override
    public void before() throws SystemException {
        securitySystem.addUser(TOM_USERNAME);
        securitySystem.grantPrivilegeToUsername(DOWNLOAD_MOVIE_PRIVILEGE, TOM_USERNAME);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInputParameters() throws SystemException {
        securitySystem.isUserAllowedForPrivilege(null, null);
    }

    @Test(expected = SystemException.class)
    public void testUserExistence() throws SystemException {
        securitySystem.isUserAllowedForPrivilege(MARY_USERNAME, UPLOAD_FILES_PRIVILEGE);
    }

    @Test
    public void testIsUserAllowedForPrivilegeFalse() throws SystemException {
        boolean isAllowed = securitySystem.isUserAllowedForPrivilege(TOM_USERNAME, UPLOAD_FILES_PRIVILEGE);
        Assert.assertFalse(isAllowed);
    }

    @Test
    public void testIsUserAllowedForPrivilegeTrue() throws SystemException {
        boolean isAllowed = securitySystem.isUserAllowedForPrivilege(TOM_USERNAME, DOWNLOAD_MOVIE_PRIVILEGE);
        Assert.assertTrue(isAllowed);
    }
}
