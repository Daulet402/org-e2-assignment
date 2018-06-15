package org.e2;

import org.e2.assessment.exception.SystemException;
import org.junit.Test;

public class TestAddRole extends AbstractSecuritySystemTest {

    @Override
    public void before() throws SystemException {
        securitySystem.addRole(CTO_ROLENAME);
    }

    @Test
    public void testAddRole() throws SystemException {
        securitySystem.addRole(CEO_ROLENAME);
    }

    @Test(expected = SystemException.class)
    public void testAlreadyAddedRole() throws SystemException {
        securitySystem.addRole(CTO_ROLENAME);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInputParameters() throws SystemException {
        securitySystem.addRole(null);
    }
}
