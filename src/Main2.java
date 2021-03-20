import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Решение без вложимости и валидации входящей строки
public class Main2 {
    public static class Helper {
        public static void repeater(String in, int start, int end) {
            int count = Integer.parseInt(in.substring(start, start + 1));
            String text = in.substring(start + 2, end - 1);
            for (int i = 0; i < count; i++) {
                System.out.print(text);
            }
        }
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Введите пожалуйста строку: ");
            System.out.println("Пример: \"3[xyz]4[xy]z\" \"2[3[x]y]\"");
            String in = reader.readLine();
            String regex = "\\d\\W[a-zA-Z]+\\W";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(in);
            while (matcher.find()) {
                Helper.repeater(in, matcher.start(), matcher.end());
            }
            String s = in.replaceAll(regex, "");
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
