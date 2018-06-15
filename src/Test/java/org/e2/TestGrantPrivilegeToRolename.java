package org.e2;

import org.e2.assessment.exception.SystemException;
import org.junit.Test;

public class TestGrantPrivilegeToRolename extends AbstractSecuritySystemTest {

    @Override
    public void before() throws SystemException {
        securitySystem.addRole(ACCOUNTING_ROLENAME);
        securitySystem.grantPrivilegeToRoleName(UPLOAD_FILES_PRIVILEGE, ACCOUNTING_ROLENAME);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInputParameters() throws SystemException {
        securitySystem.grantPrivilegeToRoleName(null, null);
    }

    @Test(expected = SystemException.class)
    public void testRoleExistence() throws SystemException {
        securitySystem.grantPrivilegeToRoleName(UPLOAD_FILES_PRIVILEGE, SALES_ROLENAME);
    }

    @Test(expected = SystemException.class)
    public void testIsRoleAlreadyGranted() throws SystemException {
        securitySystem.grantPrivilegeToRoleName(UPLOAD_FILES_PRIVILEGE, ACCOUNTING_ROLENAME);
    }

    @Test
    public void testGrantPrivilegeToRoleName() throws SystemException {
        securitySystem.grantPrivilegeToRoleName(DELETE_USERS_PRIVILEGE, ACCOUNTING_ROLENAME);
    }
}
