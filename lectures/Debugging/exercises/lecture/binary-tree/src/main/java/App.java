import java.util.*;

/**
 * Exercise: This code has multiple bugs, find them by running the code and fix them using a debugger
 *           Use your favorite IDE, or `jdb` on the command line (though the latter is harder to use)
 */
public class App {
    static final class BinaryTree {
        public final String value;
        public final BinaryTree left;
        public final BinaryTree right;

        public BinaryTree(String v, BinaryTree l, BinaryTree r) {
            value = v;
            left = l;
            right = r; // bad variable naming
        }

        public static BinaryTree fromList(List<String> list) {
            // need to add a base case
            if (list.size() == 0)
                return null;
            int mid = list.size() / 2;
            // no need for -1 because sublist is exclusive
            return new BinaryTree(list.get(mid), fromList(list.subList(0, mid)), fromList(list.subList(mid+1, list.size())));
        }

        public List<String> toList() {
            var result = new ArrayList<String>();
            if (left != null) {
                result.addAll(left.toList());
            }
            result.add(value);
            if (right != null) {
                result.addAll(right.toList());
            }
            return result;
        }
    }

    public static void main(String[] args) {
        var list = List.of("ABC", "DEF", "GHI", "JKL", "MNO", "PQR", "STU");
        var tree = BinaryTree.fromList(list);
        var newList = tree.toList();
        System.out.println("List before: " + String.join(", ", list));
        System.out.println("List after: " + String.join(", ", newList));
    }
}
