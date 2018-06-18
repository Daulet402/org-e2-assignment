package org.e2.assessment;

import org.apache.commons.lang3.StringUtils;
import org.e2.assessment.entity.Privilege;
import org.e2.assessment.entity.Role;
import org.e2.assessment.entity.User;
import org.e2.assessment.exception.SystemException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SecuritySystemImpl implements ISecuritySystem {
    private List<User> users = new ArrayList<>();
    private List<Role> roles = new ArrayList<>();
    private List<Privilege> privileges = new ArrayList<>();


    @Override
    public boolean doesUserExistByUsername(String username) {
        return users.stream().anyMatch(user -> StringUtils.equals(user.getUserName(), username));
    }

    @Override
    public boolean doesRoleExistByRolename(String roleName) {
        return roles.stream().anyMatch(role -> StringUtils.equals(role.getRoleName(), roleName));
    }

    @Override
    public void addUser(String username) throws SystemException {
        checkInputParamsInternal(username);
        if (doesUserExistByUsername(username)) {
            throw new SystemException(
                    SystemException.ExceptionCode.USER_ALREADY_EXISTS,
                    String.format("user %s already exists in the system", username)
            );
        }

        User user = new User();
        user.setUserName(username);
        users.add(user);
        System.out.println(String.format("User %s added into the system", username));
    }

    @Override
    public void addRole(String roleName) throws SystemException {
        checkInputParamsInternal(roleName);
        if (doesRoleExistByRolename(roleName)) {
            throw new SystemException(
                    SystemException.ExceptionCode.ROLE_ALREADY_EXISTS,
                    String.format("role %s already exists in the system", roleName)
            );
        }

        Role role = new Role();
        role.setRoleName(roleName);
        roles.add(role);
        System.out.println(String.format("role %s added into the system", roleName));
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roles.stream().filter(role -> StringUtils.equals(role.getRoleName(), roleName)).findFirst().orElse(null);
    }

    @Override
    public User findUserByUsername(String username) {
        return users.stream().filter(user -> StringUtils.equals(user.getUserName(), username)).findFirst().orElse(null);
    }

    @Override
    public Privilege findPrivilegeByPrivilegeName(String privilegeName) {
        return privileges.stream().filter(privilege -> StringUtils.equals(privilege.getPrivilegeName(), privilegeName)).findFirst().orElse(null);
    }

    @Override
    public void assignUsernameToRolename(String username, String roleName) throws SystemException {
        checkInputParamsInternal(username, roleName);
        if (!doesRoleExistByRolename(roleName)) {
            throw new SystemException(
                    SystemException.ExceptionCode.ROLE_DOES_NOT_EXIST,
                    String.format("role %s does not exist in the system", roleName)
            );
        }

        if (!doesUserExistByUsername(username)) {
            throw new SystemException(
                    SystemException.ExceptionCode.USER_DOES_NOT_EXIST,
                    String.format("user %s does not exist in the system", username)
            );
        }

        User user = findUserByUsername(username);
        if (isUserAllowedForRole(username, roleName)) {
            throw new SystemException(
                    SystemException.ExceptionCode.USER_ALREADY_ASSIGNED_TO_ROLE,
                    String.format("%s already assigned to %s", username, roleName)
            );
        }
        Role role = findRoleByRoleName(roleName);
        user.getRoles().add(role);
        System.out.println(String.format("%s assigned to %s", username, roleName));
    }

    @Override
    public void grantPrivilegeToUsername(String privilegeName, String username) throws SystemException {
        checkInputParamsInternal(privilegeName, username);
        if (!doesUserExistByUsername(username)) {
            throw new SystemException(
                    SystemException.ExceptionCode.USER_DOES_NOT_EXIST,
                    String.format("username %s does not exist in the system", username)
            );
        }
        User user = findUserByUsername(username);
        if (isUserAllowedForPrivilege(username, privilegeName)) {
            throw new SystemException(
                    SystemException.ExceptionCode.USER_ALREADY_GRANTED,
                    String.format("%s already granted to privilege %s", username, privilegeName)
            );
        }

        Privilege privilege = findPrivilegeByPrivilegeName(privilegeName);
        if (privilege == null) {
            privilege = new Privilege();
            privilege.setPrivilegeName(privilegeName);
            privileges.add(privilege);
        }
        user.getPrivileges().add(privilege);
        System.out.println(String.format("%s granted to %s", username, privilegeName));
    }

    @Override
    public void grantPrivilegeToRoleName(String privilegeName, String roleName) throws SystemException {
        checkInputParamsInternal(privilegeName, roleName);
        if (!doesRoleExistByRolename(roleName)) {
            throw new SystemException(
                    SystemException.ExceptionCode.ROLE_DOES_NOT_EXIST,
                    String.format("role %s does not exist in the system", roleName)
            );
        }

        Role role = findRoleByRoleName(roleName);
        if (isRoleAllowedForPrivilege(roleName, privilegeName)) {
            throw new SystemException(
                    SystemException.ExceptionCode.ROLE_ALREADY_GRANTED,
                    String.format("%s already granted to privilege %s", roleName, privilegeName)
            );
        }
        Privilege privilege = findPrivilegeByPrivilegeName(privilegeName);
        if (privilege == null) {
            privilege = new Privilege();
            privilege.setPrivilegeName(privilegeName);
            privileges.add(privilege);
        }
        role.getPrivileges().add(privilege);
        System.out.println(String.format("%s granted to %s", privilegeName, roleName));
    }

    @Override
    public boolean isUserAllowedForPrivilege(String username, String privilegeName) throws SystemException {
        checkInputParamsInternal(username, privilegeName);
        if (!doesUserExistByUsername(username)) {
            throw new SystemException(
                    SystemException.ExceptionCode.USER_DOES_NOT_EXIST,
                    String.format("user %s does not exist in the system", username)
            );
        }

        User user = findUserByUsername(username);
        boolean hasPrivilege = user.getPrivileges()
                .stream()
                .anyMatch(privilege -> StringUtils.equals(privilege.getPrivilegeName(), privilegeName));

        if (!hasPrivilege) {
            hasPrivilege = user.getRoles()
                    .stream()
                    .filter(role -> !role.getPrivileges().isEmpty())
                    .map(Role::getPrivileges)
                    .map(Iterable::iterator)
                    .map(Iterator::next)
                    .anyMatch(privilege -> StringUtils.equals(privilege.getPrivilegeName(), privilegeName));
        }
        return hasPrivilege;
    }

    @Override
    public boolean isRoleAllowedForPrivilege(String roleName, String privilegeName) throws SystemException {
        checkInputParamsInternal(roleName, privilegeName);
        if (!doesRoleExistByRolename(roleName)) {
            throw new SystemException(
                    SystemException.ExceptionCode.ROLE_DOES_NOT_EXIST,
                    String.format("role %s does not exist in the system", roleName)
            );
        }

        Role role = findRoleByRoleName(roleName);
        return role.getPrivileges()
                .stream()
                .anyMatch(privilege -> StringUtils.equals(privilege.getPrivilegeName(), privilegeName));
    }

    @Override
    public boolean isUserAllowedForRole(String username, String roleName) throws SystemException {
        checkInputParamsInternal(username, roleName);
        if (!doesUserExistByUsername(username)) {
            throw new SystemException(
                    SystemException.ExceptionCode.USER_DOES_NOT_EXIST,
                    String.format("user %s does not exist in the system", username)
            );
        }

        User user = findUserByUsername(username);
        return user.getRoles().stream().anyMatch(role -> StringUtils.equals(role.getRoleName(), roleName));
    }

    private void checkInputParamsInternal(String... params) {
        if (StringUtils.isAnyEmpty(params)) {
            throw new IllegalArgumentException("mandatory parameter(-s) missed");
        }
    }
}
