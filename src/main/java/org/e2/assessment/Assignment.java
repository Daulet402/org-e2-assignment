package org.e2.assessment;

import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Assignment {

    private Set<String> userNames = new HashSet<>();
    private Set<String> roleNames = new HashSet<>();
    private Set<String> privileges = new HashSet<>();
    private ConcurrentMap<String, Set<String>> rolenameUserNames = new ConcurrentHashMap<>();
    private ConcurrentMap<String, Set<String>> usernamePrivileges = new ConcurrentHashMap<>();
    private ConcurrentMap<String, Set<String>> rolenamePrivileges = new ConcurrentHashMap<>();

    public void addUser(String username) {
        checkInputParamsInternal(username);
        userNames.add(username);
    }

    public void addRole(String rolename) {
        checkInputParamsInternal(rolename);
        roleNames.add(rolename);
    }

    public void assignUsernameToRolename(String username, String rolename) {
        checkInputParamsInternal(username, rolename);
        if (!doRoleNamesContain(rolename)) {
            roleNames.add(rolename);
        }

        if (!doUserNamesContain(username)) {
            userNames.add(username);
        }
        rolenameUserNames.putIfAbsent(rolename, new HashSet<>());
        rolenameUserNames.get(rolename).add(username);
    }

    public boolean doRoleNamesContain(String rolename) {
        return roleNames.contains(rolename);
    }

    public boolean doUserNamesContain(String username) {
        return userNames.contains(username);
    }

    public boolean doPrivilegesContain(String privilege) {
        return privileges.contains(privilege);
    }

    public void grantPrivilegeToUsername(String privilege, String username) {
        checkInputParamsInternal(privilege, username);
        if (!doUserNamesContain(username)) {
            userNames.add(username);
        }
        if (!doPrivilegesContain(privilege)) {
            privileges.add(privilege);
        }
        usernamePrivileges.putIfAbsent(username, new HashSet<>());
        usernamePrivileges.get(username).add(privilege);
    }

    public void grantPrivilegeToRolename(String privilege, String rolename) {
        checkInputParamsInternal(privilege, rolename);
        if (!doRoleNamesContain(rolename)) {
            roleNames.add(rolename);
        }
        if (!doPrivilegesContain(privilege)) {
            privileges.add(privilege);
        }

        rolenamePrivileges.putIfAbsent(rolename, new HashSet<>());
        rolenamePrivileges.get(rolename).add(privilege);
    }

    public boolean canUsername(String username, String privilege) {
        checkInputParamsInternal(username, privilege);
        if (usernamePrivileges.containsKey(username) && usernamePrivileges.get(username).contains(privilege)) {
            return true;
        }

        Set<Map.Entry<String, Set<String>>> entries = rolenamePrivileges.entrySet();
        for (Map.Entry<String, Set<String>> entry : entries) {
            if (entry.getValue() != null && entry.getValue().contains(privilege)) {
                Set<String> usernames = rolenameUserNames.get(entry.getKey());
                if (usernames != null && usernames.contains(username)) {
                    return true;
                }
            }
        }

        return false;
    }

    private void checkInputParamsInternal(String... params) {
        if (StringUtils.isAnyEmpty(params)) {
            throw new IllegalArgumentException("mandatory parameter(-s) missed");
        }
    }
}
