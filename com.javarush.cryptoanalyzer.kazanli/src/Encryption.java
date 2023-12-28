import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Encryption {
    static void doEncrypt() {
        System.out.println("Введиде ссылку к файлу :");
        Scanner console = new Scanner(System.in);
        Path path = Path.of(console.nextLine());
        if (Files.exists(path)) {
            System.out.println("Введиде ключ :");
            int key = Integer.parseInt(console.next());
            char[] chars = new char[1024];
            try {
                BufferedReader buff = new BufferedReader(Files.newBufferedReader(path));
                buff.read(chars);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
