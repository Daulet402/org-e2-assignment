package org.e2;

import org.e2.assessment.exception.SystemException;
import org.junit.Test;

public class TestAddUser extends AbstractSecuritySystemTest {

    @Override
    public void before() throws SystemException {
        securitySystem.addUser(JOHN_USERNAME);
    }

    @Test
    public void testAddUser() throws SystemException {
        securitySystem.addUser(MARY_USERNAME);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInputParams() throws SystemException {
        securitySystem.addUser(null);
    }

    @Test(expected = SystemException.class)
    public void testAlreadyAddedUser() throws SystemException {
        securitySystem.addUser(JOHN_USERNAME);
    }
}
