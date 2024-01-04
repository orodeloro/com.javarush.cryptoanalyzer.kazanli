import java.io.IOException;
import java.util.Scanner;

public class CaesarCipher {

    public static void main(String[] args) throws IOException {

        System.out.println("Вы находитесь в приложении \"Код Цезаря\"!");
        System.out.println("Выберите режим работы :");
        System.out.println("1. Шифруем.");
        System.out.println("2. Дешифруем.");
        System.out.println("3. Взламываем");
        System.out.println("4. Интеллектуальный взлом.(Не рекомендуется для коротких текстов)");

        Scanner console = new Scanner(System.in);
        int n = Integer.parseInt(console.nextLine());

        if (n == 1) {
            Encryption.doEncrypt();
        } else if (n == 2) {
            Decryption.doDecrypt();
        } else if (n == 3) {
            BruteForse.doBruteForse();
        } else if (n == 4) {
            IntellectualBruteForce.doIntellectualBruteForce();
        } else {
            System.out.println("Вы ввели не корректное число.");
        }
    }
}
