package org.e2.assessment;

import org.e2.assessment.entity.Privilege;
import org.e2.assessment.entity.Role;
import org.e2.assessment.entity.User;
import org.e2.assessment.exception.SystemException;

public interface ISecuritySystem {

    boolean doesUserExistByUsername(String username);

    boolean doesRoleExistByRolename(String roleName);

    void addUser(String username) throws SystemException;

    void addRole(String roleName) throws SystemException;

    Role findRoleByRoleName(String roleName);

    User findUserByUsername(String username);

    Privilege findPrivilegeByPrivilegeName(String privilegeName);

    void assignUsernameToRolename(String username, String roleName) throws SystemException;

    void grantPrivilegeToUsername(String privilegeName, String username) throws SystemException;

    void grantPrivilegeToRoleName(String privilegeName, String roleName) throws SystemException;

    boolean isUserAllowedForPrivilege(String username, String privilegeName) throws SystemException;

    boolean isRoleAllowedForPrivilege(String roleName, String privilegeName) throws SystemException;

    boolean isUserAllowedForRole(String username, String roleName) throws SystemException;
}
