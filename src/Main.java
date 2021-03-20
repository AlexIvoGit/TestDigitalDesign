
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    private static class Helper {

        private static boolean isDigit(char c) { //Проверка, что символ является числом
            return ((c > 47) && (c < 58));
        }

        private static boolean isLetter(char c) { //Проверка, что символ является буквой
            return (((c > 64) && (c < 91)) || ((c > 96) && (c < 123)));
        }

        private static boolean isSqBrOpen(char c) { //Проверка, что символ является открывающейся квадратной скобкой
            return (c == 91);
        }

        private static boolean isSqBrClose(char c) { //Проверка, что символ является закрывающейся квадратной скобкой
            return (c == 93);
        }

        public static StringBuilder repeater(StringBuilder s, int n) { //Метод для повторения строк, принимает на вход строку и количество повторений
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(s);
            }
            return sb;
        }

    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Введите пожалуйста строку: ");
            System.out.println("Пример: \"3[xyz]4[xy]z\" \"2[3[x]y]\"");

            String line = reader.readLine();
            ArrayList<Integer> sqBrOpen = new ArrayList<>(); //массив с индексами открывающихся скобок
            ArrayList<Integer> sqBrClose = new ArrayList<>(); //массив с индексами закрывающихся скобок

            if (line.isEmpty()) { //проверяем, что введеная строка не пустая
                System.out.println("Строка не соответствует формату!");
                System.exit(1);
            }

            for (int i = 0; i < line.length(); i++) {
                if (Helper.isSqBrOpen(line.charAt(i))) {
                    sqBrOpen.add(i);
                } else if (Helper.isSqBrClose(line.charAt(i))) {
                    sqBrClose.add(i);
                }
            }
            if (sqBrOpen.size() != sqBrClose.size()) { //Проверяем количество открывающихся и закрывающихся скобок
                System.out.println("Строка не соответствует формату!");
                System.exit(1);
            }

            ArrayList<Integer> arrayDigit = new ArrayList<>(); // массив для хранения количества повторений
            ArrayList<StringBuilder> arrayLetters = new ArrayList<>(); // массив для хранения букв


            char[] chArr = line.toCharArray(); //парсим входящую строку на символы
            for (int i = 0; i < chArr.length; i++) {

                // получение числа повторений
                if (Helper.isDigit(chArr[i])) {
                    StringBuilder sb = new StringBuilder();
                    while ((i < chArr.length) && (Helper.isDigit(chArr[i]))) {
                        sb.append(chArr[i]);
                        i++;
                    }


                    if (!Helper.isSqBrOpen(chArr[i])) {
                        System.out.println("Строка не соответствует формату!");
                        System.exit(1);
                        return;
                    } else {
                        try {
                            arrayDigit.add(Integer.parseInt(sb.toString()));
                            arrayLetters.add(new StringBuilder());
                        } catch (NumberFormatException e) {
                            System.out.println("Строка не соответствует формату!");
                            System.exit(1);
                            return;
                        }
                    }
                }
                // добавление букв в массив букв
                if (Helper.isLetter(chArr[i])) {
                    arrayLetters.get(arrayLetters.size() - 1).append(chArr[i]);
                }

                if (Helper.isSqBrClose(chArr[i])) {
                    int sizeDigit = arrayDigit.size();
                    int sizeLetter = arrayLetters.size();
                    if ((sizeDigit > 0) || (sizeLetter > 0)) {
                        int m = arrayDigit.remove(sizeDigit - 1);
                        StringBuilder sb = arrayLetters.remove(sizeLetter - 1);
                        if (arrayLetters.size() > 0) {
                            arrayLetters.get(arrayLetters.size() - 1).append(Helper.repeater(sb, m));
                        } else {
                            arrayLetters.add(Helper.repeater(sb, m));
                        }
                    } else {
                        System.out.println("Строка не соответствует формату!");
                        System.exit(1);
                        return;
                    }
                }
            }
            for (StringBuilder result : arrayLetters) { //выводим на экран результат
                System.out.println(result.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}