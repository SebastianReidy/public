import java.util.*;

public class App {
    // EXERCISE: This code is not modular and mixes all kinds of concepts together,
    //           making it hard to maintain.
    //           Modularize it!
    //           Think about what modules you need, which in this case will be functions or classes, and how to organize them.
    //           You may want to start by writing your ideal "main" method that reads like self-describing code,
    //           and implementing each function as needed.
    //           You can run this app on the command line with `gradlew.bat run` on Windows or `./gradlew run` on macOS and Linux.

    public static void main(String[] args) {
    outer:
        while (true) {
            String input = get_input();
            if(input == null)
                break;

            ArrayDeque stack = compute(input);
            if(stack == null){
                System.out.println("Invalid computation");
                continue outer;
            }

            print(stack);
        }

        private String get_input(){
            var scanner = new Scanner(System.in);
            System.out.print("Computation to perform? (in reverse Polish notation; or 'exit') ");
            var text = scanner.nextLine().trim();
            if (text.equals("exit")) {
                return null;
            }
            return text;
        }

        private ArrayDeque compute(String text) {
            var parts = text.split(" ");
            var stack = new ArrayDeque<Integer>();
            for (String p : parts) {
                if (p.equals("")) {
                    continue;
                }
                if (p.equals("+")) {
                    stack.push(stack.pop() + stack.pop());
                } else if (p.equals("-")) {
                    var a = stack.pop();
                    var b = stack.pop();
                    stack.push(b - a);
                } else if (p.equals("*")) {
                    stack.push(stack.pop() + stack.pop());
                } else if (p.equals("/")) {
                    var a = stack.pop();
                    var b = stack.pop();
                    stack.push(b / a);
                } else {
                    try {
                        stack.push(Integer.parseInt(p));
                    } catch (NumberFormatException e) {
                        return null;
                    }
                }
            }
        }

        void print(ArrayDeque string) {
            if (stack.size() == 1) {
                System.out.println(stack.pop());
            } else {
                System.out.println("Invalid computation");
            }
        }

    }
}
