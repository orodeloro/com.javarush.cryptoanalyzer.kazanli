import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class BruteForse {
    static void doBruteForse() throws IOException {

        System.out.println("Введиде ссылку к файлу :");
        Scanner console = new Scanner(System.in);
        Path path = Path.of(console.nextLine());

        if (Files.exists(path)) {

            BufferedReader buff = new BufferedReader(Files.newBufferedReader(path));
            char[] inputChars = new char[(int) Files.size(path)];
            int size = buff.read(inputChars);

            char[] deCipherChars = new char[size];

            for (int k = 0; k < Alphabet.RUSALPHABET.length; k++) {

                int key = -k;
                if (key < 0) {
                    key = (key % Alphabet.RUSALPHABET.length) + Alphabet.RUSALPHABET.length;
                }

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
                System.out.println("\n");
            }
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

