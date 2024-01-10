import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Encryption {

    static void doEncrypt() throws IOException {

        Scanner console = new Scanner(System.in);

        System.out.println("Введиде ссылку к файлу:");
        Path pathIn = Path.of(console.nextLine());

        System.out.println("Введиде ссылку к файлу для записи:");
        String fileOut = console.nextLine();

        if (Files.exists(pathIn)) {

            BufferedReader buffReader = new BufferedReader(Files.newBufferedReader(pathIn));
            char[] inputChars = new char[(int) Files.size(pathIn)];
            int realSizeInputChars = buffReader.read(inputChars);
            BufferedWriter buffWriter = new BufferedWriter(new FileWriter(fileOut), realSizeInputChars);

            System.out.println("Введиде ключ:");
            int key = Integer.parseInt(console.next());

            buffWriter.write(getCipherChars(inputChars, realSizeInputChars, key));
            buffWriter.flush();
            buffWriter.close();

        } else {
            System.out.println("По введенной ссылке файл не найден!");
        }
    }

    static char[] getCipherChars(char[] inputChars, int realSizeInputChars, int key) {

        char[] cipherChars = new char[realSizeInputChars];

        if (key < 0) {
            key = (key % Alphabet.RUSALPHABET.length) + Alphabet.RUSALPHABET.length;
        }

        for (int i = 0; i < cipherChars.length; i++) {
            for (int j = 0; j < Alphabet.RUSALPHABET.length; j++) {
                if (inputChars[i] == Alphabet.RUSALPHABET[j]) {
                    cipherChars[i] = Alphabet.RUSALPHABET[(j + key) % Alphabet.RUSALPHABET.length];
                } else if (inputChars[i] == '\n' || inputChars[i] == '\r') {
                    cipherChars[i] = inputChars[i];
                }
            }
            if (cipherChars[i] == '\0') {
                cipherChars[i] = inputChars[i];
                System.out.println("Символ " + inputChars[i] + " не содержится в данном алфавите.");
                System.out.println("Символ " + inputChars[i] + " не был зашифрован.");
                System.out.println();
            }
        }
        return cipherChars;
    }
}
