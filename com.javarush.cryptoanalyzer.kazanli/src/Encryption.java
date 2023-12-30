import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Encryption {

    static void doEncrypt() throws IOException {

        System.out.println("Введиде ссылку к файлу :");
        Scanner console = new Scanner(System.in);
        Path path = Path.of(console.nextLine());

        if (Files.exists(path)) {

            System.out.println("Введиде ключ :");
            int key = Integer.parseInt(console.next());
            if (key < 0) {
                key = (key % Alphabet.RUSALPHABET.length) + Alphabet.RUSALPHABET.length;
            }

            BufferedReader buff = new BufferedReader(Files.newBufferedReader(path));
            char[] inputChars = new char[(int)Files.size(path)];
            int sizeCipherArrayChars = buff.read(inputChars);

            char[] cipherChars = new char[sizeCipherArrayChars];
            for (int i = 0; i < cipherChars.length; i++) {
                for (int j = 0; j < Alphabet.RUSALPHABET.length; j++) {
                    if (inputChars[i] == Alphabet.RUSALPHABET[j]) {
                        cipherChars[i] = Alphabet.RUSALPHABET[(j + key) % Alphabet.RUSALPHABET.length];
                    } else if (inputChars[i] == '\n' || inputChars[i] == '\r') {
                        cipherChars[i] = inputChars[i];
                    }
                }
                if (cipherChars[i] == '\0') {
                    System.out.println("Символ " + inputChars[i] + " не содержится в данном алфавите.");
                    System.out.println();
                }
            }
            outputConsole(cipherChars);
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