import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;

public class IntellectualBruteForce {
    static void doIntellectualBruteForce() throws IOException {

        System.out.println("Введиде ссылку к файлу :");
        Scanner console = new Scanner(System.in);
        Path path = Path.of(console.nextLine());

        if (Files.exists(path)) {

            BufferedReader buff = new BufferedReader(Files.newBufferedReader(path));
            char[] inputChars = new char[(int) Files.size(path)];
            int realSizeInputChars = buff.read(inputChars);

            int key = getKey(realSizeInputChars, inputChars);

            System.out.println("Ключ шифра: " + key);
            System.out.println();

            key = -key;
            if (key < 0) {
                key = (key % Alphabet.RUSALPHABET.length) + Alphabet.RUSALPHABET.length;
            }

            char[] deCipherChars = getCipherChars(inputChars, realSizeInputChars, key);

            outputConsole(deCipherChars);
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

        int key = Alphabet.RUSALPHABET.length - (indexCharSpaceInAlphabet - indexCharChangeSpaceInAlphabet);
        return key;
    }
    private static char[] getCipherChars(char[] inputChars, int realSizeInputChars, int key) {
        char[] cipherChars = new char[realSizeInputChars];

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

    private static void outputConsole(char[] chars) {
        for (char ch : chars) {
            System.out.print(ch);
        }
    }
}


