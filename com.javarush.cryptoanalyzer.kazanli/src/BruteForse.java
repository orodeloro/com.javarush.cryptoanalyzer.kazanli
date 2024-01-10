
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class BruteForse {
    static void doBruteForse() throws IOException {

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

            for (int k = 0; k < Alphabet.RUSALPHABET.length; k++) {

                int key = -k;

                buffWriter.write(Encryption.getCipherChars(inputChars, realSizeInputChars, key));
                buffWriter.flush();
            }
            buffWriter.close();
        } else {
            System.out.println("По введенной ссылке файл не найден!");
        }
    }
}

