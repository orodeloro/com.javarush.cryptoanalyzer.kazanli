import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;

public class IntellectualBruteForce {
    static void doIntellectualBruteForce() throws IOException {

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

            int key = getKey(realSizeInputChars, inputChars);

            System.out.println("Ключ шифра: " + key);

            key = -key;

            buffWriter.write(Encryption.getCipherChars(inputChars, realSizeInputChars, key));
            buffWriter.flush();
            buffWriter.close();

        } else {
            System.out.println("По введенной ссылке файл не найден!");
        }
    }

    private static int getKey(int size, char[] inputChars) {

        HashMap<Character, Integer> cod = new HashMap<>();

        for (int i = 1; i < size; i++) {
            cod.put(inputChars[0], 1);
            if (!cod.containsKey(inputChars[i])) {
                cod.put(inputChars[i], 1);
            } else {
                int value = cod.get(inputChars[i]);
                cod.put(inputChars[i], value + 1);
            }
        }

        int indexMaxMeetChar = 0;
        int temp = 0;

        for (int i = 0; i < cod.size(); i++) {
            if (cod.get(inputChars[i]) > temp) {
                temp = cod.get(inputChars[i]);
                indexMaxMeetChar = i;
            }
        }

        int indexCharSpaceInAlphabet = 0;
        int indexCharChangeSpaceInAlphabet = 0;

        for (int i = 0; i < Alphabet.RUSALPHABET.length; i++) {
            if (inputChars[indexMaxMeetChar] == Alphabet.RUSALPHABET[i]) {
                indexCharChangeSpaceInAlphabet = i;
            }
            if (Alphabet.RUSALPHABET[i] == ' ') {
                indexCharSpaceInAlphabet = i;
            }
        }
        return Alphabet.RUSALPHABET.length - (indexCharSpaceInAlphabet - indexCharChangeSpaceInAlphabet);
    }
}


