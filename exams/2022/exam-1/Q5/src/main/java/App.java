/**
 * Entry point of the application.
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * You MUST use this file for the real dependencies of the JaaS users client.
 * You CAN add new constructors, methods, and nested classes to this class.
 * You MUST NOT edit the name, parameters, checked exceptions or return type of the main method.
 * You MUST NOT delete the main method.
 * You MUST NOT rename this file.
 * You MUST NOT delete this file.
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */
public class App {

    private static final String SERVER_URL = "https://jaas-users.example.org";

    public static void main(String[] args) {
        System.out.println("Hello there! I'm a JaaS client, and I can tell you who uses the platform.");
        System.out.println("Write a list user roles and I'll give you a list of all corresponding users:");
        new JaasClient(SERVER_URL).run();
    }
}
