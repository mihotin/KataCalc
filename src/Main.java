import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = calc(scanner.nextLine());
        System.out.println(input);
    }
    public static String calc(String input) {
        int num1;
        int num2;
        int result;
        char operator = '0';
        String output;

        if (input.indexOf('*') != -1) { // Ловим оператор
            operator = '*'; }
        if (input.indexOf('/') != -1) {
            operator = '/'; }
        if (input.indexOf('+') != -1) {
            operator = '+'; }
        if (input.indexOf('-') != -1) {
            operator = '-'; }

        String noSpace = input.replaceAll("\\s*", ""); // Убираем пробелы
        String [] split = noSpace.split("[+-/*]"); // Разделение строки

        if (split.length < 2) throw new RuntimeException("// Исключение: т.к. строка не является математической операцией");
        if (split.length > 2) throw new RuntimeException("//т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");

        num1 = romanToNumber(split[0]); // Римские в арабские
        num2 = romanToNumber(split[1]); // Римские в арабские

        if (num1 == -1 && num2 > -1 || num2 == -1 && num1 > -1) throw new RuntimeException("//т.к. используются одновременно разные системы счисления");

        if (num1 == -1 && num2 == -1) { // Вычисление арабских цифр
            num1 = Integer.parseInt(split[0]);
            num2 = Integer.parseInt(split[1]);
            result = calculation(num1, num2, operator);
            output = Integer.toString(result);
        } else {
            result = calculation(num1, num2, operator); // Вычисление римских цифр
            if (result <= 0) throw new RuntimeException("//т.к. в римской системе нет отрицательных чисел"); // Исключение при отрицательном результате
            output = numberToRoman(result);
        }

        return output; // Возврат результата
    }
    public static int romanToNumber(String roman) { // Метод конвертирования римских в арабские
        int result = -1;
        String[] romanian = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        for (int i = 0; i < romanian.length; i++) {
            if (romanian[i].equals(roman)) {
                result = i;
            }
        }
        return result;
    }
    private static String numberToRoman (int numArabian) { // Метод конвертирования результат вычисления из арабчких в рисмкие
        String[] romanian = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        String result = romanian[numArabian];
        return result;
    }
    public static int calculation(int num1, int num2, char op) { // Метод расчета
        int resulte = 0;
        if (num1 <= 0 || num1 > 10 || num2 <= 0 || num2 > 10) throw new RuntimeException("//т.к. нужно ввести числа от 1 до 10 включительно, не более");

        switch (op) {
            case '*' :
                resulte = num1 * num2;
                break;
            case '/' :
                resulte = num1 / num2;
                break;
            case '+' :
                resulte = num1 + num2;
                break;
            case '-' :
                resulte = num1 - num2;
                break;
        }
        return resulte;
    }
}