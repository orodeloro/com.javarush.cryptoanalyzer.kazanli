import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Encryption {
//    private static final char[] LOW_RUSALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з',
//            'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
//            'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};
//    private static final char[] UP_RUSALPHABET = {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З',
//            'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ',
//            'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    static void doEncrypt() throws IOException {
        System.out.println("Введиде ссылку к файлу :");

        Scanner console = new Scanner(System.in);
        Path path = Path.of(console.nextLine());

        if (Files.exists(path)) {

            System.out.println("Введиде ключ :");
            int key = Integer.parseInt(console.next());
            if (key < 0) {
                key = (key % Alphabet.LOW_RUSALPHABET.length) + Alphabet.LOW_RUSALPHABET.length;
            }

            BufferedReader buff = new BufferedReader(Files.newBufferedReader(path));
            char[] inputText = new char[(int) Files.size(path)];
            buff.read(inputText);

            char[] cipherText = new char[inputText.length];
            for (int i = 0; i < cipherText.length; i++) {
                for (int j = 0; j < Alphabet.LOW_RUSALPHABET.length; j++) {
                    if (inputText[i] == Alphabet.LOW_RUSALPHABET[j]) {
                        cipherText[i] = Alphabet.LOW_RUSALPHABET[(j + key) % Alphabet.LOW_RUSALPHABET.length];
                    } else if (inputText[i] == Alphabet.UP_RUSALPHABET[j]) {
                        cipherText[i] = Alphabet.UP_RUSALPHABET[(j + key) % Alphabet.LOW_RUSALPHABET.length];
                    } else {
                        System.out.println("Символ " + inputText[i] + " не содержится в данном алфавите.");
                    }
                }
            }
            outputConsole(cipherText);
        } else {
            System.out.println("По введенной ссылке файл не найден!");
        }
    }

    private static void outputConsole(char[] chars) {
        for (char ch : chars) {
            System.out.print(ch);
        }
    }
}