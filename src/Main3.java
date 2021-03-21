import java.io.IOException;
import java.util.Stack;
//Решение с использованием "Stack" найденное на просторах "StackOverFlow на русском" от Stanislav Volodarskiy
//https://ru.stackoverflow.com/questions/1255794/%d0%9f%d0%be%d0%b2%d1%82%d0%be%d1%80%d0%b5%d0%bd%d0%b8%d0%b5-%d1%81%d0%b8%d0%bc%d0%b2%d0%be%d0%bb%d0%be%d0%b2-n-%d0%ba%d0%be%d0%bb%d0%b8%d1%87%d0%b5%d1%81%d1%82%d0%b2%d0%be-%d1%80%d0%b0%d0%b7-%d0%b7%d0%b0%d0%b4%d0%b0%d1%87%d0%ba%d0%b0/1256706#1256706

public class Main3 {
    private static class Item {
        public final int factor;
        public final StringBuilder sb;

        public Item(int factor) {
            this.factor = factor;
            sb = new StringBuilder();
        }
    }

    private static Stack<Item> stack = new Stack<Item>();

    public static void main(String[] args) {
        int c = -1;
        int factor = 0;
        for (; ; ) {
            try {
                c = System.in.read();
            } catch (IOException e) {
                System.exit(1);
            }
            if (c == -1) {
                break;
            }
            if (Character.isDigit(c)) {
                factor = 10 * factor + Character.getNumericValue(c);
            } else if (c == '[') {
                stack.push(new Item(factor));
                factor = 0;
            } else if (c == ']') {
                Item item = stack.pop();
                String s = item.sb.toString();
                append(item.factor, s);
                factor = 0;
            } else {
                append(factor, Character.toString((char) c));
                factor = 0;
            }
        }
    }

    private static void append(int factor, String s) {
        int n = (factor == 0) ? 1 : factor;
        for (int i = 0; i < n; ++i) {
            if (stack.empty()) {
                System.out.print(s);
            } else {
                stack.peek().sb.append(s);
            }
        }
    }
}
