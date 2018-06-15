package org.e2.assessment;

import org.apache.commons.lang3.StringUtils;
import org.e2.assessment.exception.SystemException;

import java.util.Scanner;
import java.util.StringTokenizer;

public class SecuritySystemApplication {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SecuritySystemImpl securitySystem = new SecuritySystemImpl();

        while (true) {
            System.out.println("waiting command ...");
            String input = scanner.nextLine();
            StringTokenizer tokenizer = new StringTokenizer(input);

            if (tokenizer.hasMoreTokens()) {
                String command = tokenizer.nextToken().toLowerCase();
                if (StringUtils.equals(Constants.ADD_USER_COMMAND, command)) {
                    if (tokenizer.countTokens() != 1) {
                        System.err.println("Not enough parameters. Input should be adduser username");
                        continue;
                    }
                    String username = tokenizer.nextToken();
                    try {
                        securitySystem.addUser(username);
                    } catch (SystemException e) {
                        System.err.println(e.getMessage());
                    }
                } else if (StringUtils.equals(Constants.ADD_ROLE_COMMAND, command)) {
                    if (tokenizer.countTokens() != 1) {
                        System.err.println("Not enough parameters. Input should be addrole rolename");
                        continue;
                    }
                    String rolename = tokenizer.nextToken();
                    try {
                        securitySystem.addRole(rolename);
                    } catch (SystemException e) {
                        System.err.println(e.getMessage());
                    }
                } else if (StringUtils.equals(Constants.ASSIGN_COMMAND, command)) {
                    if (tokenizer.countTokens() != 2) {
                        System.err.println("Not enough parameters. Input should be assign username rolename");
                        continue;
                    }
                    String username = tokenizer.nextToken();
                    String rolename = tokenizer.nextToken();
                    try {
                        securitySystem.assignUsernameToRolename(username, rolename);
                    } catch (SystemException e) {
                        System.err.println(e.getMessage());
                    }
                } else if (StringUtils.equals(Constants.GRANT_COMMAND, command)) {
                    if (tokenizer.countTokens() != 3) {
                        System.err.println("Not enough parameters. Input should be grant privilege (-u | -r) (username | rolename)");
                        continue;
                    }
                    String privilege = tokenizer.nextToken();
                    String type = tokenizer.nextToken();
                    String value = tokenizer.nextToken();
                    if ("-u".equals(type)) {
                        try {
                            securitySystem.grantPrivilegeToUsername(privilege, value);
                        } catch (SystemException e) {
                            System.err.println(e.getMessage());
                        }
                    } else if ("-r".equals(type)) {
                        try {
                            securitySystem.grantPrivilegeToRoleName(privilege, value);
                        } catch (SystemException e) {
                            System.err.println(e.getMessage());
                        }
                    } else {
                        System.err.println("invalid type to grant privilege to");
                    }
                } else if (StringUtils.equals(Constants.CAN_COMMAND, command)) {
                    if (tokenizer.countTokens() != 2) {
                        System.err.println("Not enough parameters. Input should be can username privilege");
                        continue;
                    }
                    String username = tokenizer.nextToken();
                    String privilege = tokenizer.nextToken();
                    try {
                        if (securitySystem.isUserAllowedForPrivilege(username, privilege)) {
                            System.out.println(String.format("Yes, %s can %s", username, privilege));
                        } else {
                            System.out.println(String.format("No, %s cannot %s", username, privilege));
                        }
                    } catch (SystemException e) {
                        System.err.println(e.getMessage());
                    }
                } else {
                    System.err.println("invalid input");
                }
            } else {
                System.err.println("invalid input");
            }

        }


    }
}
