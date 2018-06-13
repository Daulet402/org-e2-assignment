package org.e2.assessment;

import java.util.Scanner;
import java.util.StringTokenizer;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Assignment assignment = new Assignment();

        while (true) {
            System.out.println("waiting command ...");
            String input = scanner.nextLine();
            StringTokenizer tokenizer = new StringTokenizer(input);

            if (tokenizer.hasMoreTokens()) {
                String command = tokenizer.nextToken().toLowerCase();
                if ("adduser".equals(command)) {
                    if (tokenizer.countTokens() != 1) {
                        System.err.println("Not enough parameters. Input should be adduser username");
                        continue;
                    }
                    String username = tokenizer.nextToken();
                    assignment.addUser(username);
                    System.out.println(String.format("%s added to the system", username));
                } else if ("addrole".equals(command)) {
                    if (tokenizer.countTokens() != 1) {
                        System.err.println("Not enough parameters. Input should be addrole rolename");
                        continue;
                    }
                    String rolename = tokenizer.nextToken();
                    assignment.addRole(rolename);
                    System.out.println(String.format("%s added to the system", rolename));
                } else if ("assign".equals(command)) {
                    if (tokenizer.countTokens() != 2) {
                        System.err.println("Not enough parameters. Input should be assign username rolename");
                        continue;
                    }
                    String username = tokenizer.nextToken();
                    String rolename = tokenizer.nextToken();
                    assignment.assignUsernameToRolename(username, rolename);
                    System.out.println(String.format("%s assigned to %s", username, rolename));
                } else if ("grant".equals(command)) {
                    if (tokenizer.countTokens() != 3) {
                        System.err.println("Not enough parameters. Input should be grant privilege (-u | -r) (username | rolename)");
                        continue;
                    }
                    String privilege = tokenizer.nextToken();
                    String type = tokenizer.nextToken();
                    String value = tokenizer.nextToken();
                    if ("-u".equals(type)) {
                        assignment.grantPrivilegeToUsername(privilege, value);
                        System.out.println(String.format("%s granted to %s", privilege, value));
                    } else if ("-r".equals(type)) {
                        assignment.grantPrivilegeToRolename(privilege, value);
                        System.out.println(String.format("%s granted to %s", privilege, value));
                    } else {
                        System.err.println("invalid type to grant privilege to");
                    }
                } else if ("can".equals(command)) {
                    if (tokenizer.countTokens() != 2) {
                        System.err.println("Not enough parameters. Input should be can username privilege");
                        continue;
                    }
                    String username = tokenizer.nextToken();
                    String privilege = tokenizer.nextToken();
                    if (assignment.canUsername(username, privilege)) {
                        System.out.println(String.format("Yes, %s can %s", username, privilege));
                    } else {
                        System.out.println(String.format("No, %s cannot %s", username, privilege));
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
