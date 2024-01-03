
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Decryption {
    static void doDecrypt() throws IOException {

        System.out.println("Введиде ссылку к файлу :");
        Scanner console = new Scanner(System.in);
        Path path = Path.of(console.nextLine());

        if (Files.exists(path)) {

            System.out.println("Введиде ключ :");
            int key = -Integer.parseInt(console.next());
            if (key < 0) {
                key = (key % Alphabet.RUSALPHABET.length) + Alphabet.RUSALPHABET.length;
            }

            BufferedReader buff = new BufferedReader(Files.newBufferedReader(path));
            char[] inputChars = new char[(int) Files.size(path)];
            int sizeDecipherArrayChars = buff.read(inputChars);

            char[] deCipherChars = new char[sizeDecipherArrayChars];
            for (int i = 0; i < deCipherChars.length; i++) {
                for (int j = 0; j < Alphabet.RUSALPHABET.length; j++) {
                    if (inputChars[i] == Alphabet.RUSALPHABET[j]) {
                        deCipherChars[i] = Alphabet.RUSALPHABET[(j + key) % Alphabet.RUSALPHABET.length];
                    } else if (inputChars[i] == '\n' || inputChars[i] == '\r') {
                        deCipherChars[i] = inputChars[i];
                    }
                }
                if (deCipherChars[i] == '\0') {
                    deCipherChars[i] = inputChars[i];
                    System.out.println("Символ " + inputChars[i] + " не содержится в данном алфавите.");
                    System.out.println("Символ " + inputChars[i] + " не был зашифрован.");
                    System.out.println();
                }
            }
            outputConsole(deCipherChars);
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
