import java.util.Scanner;

public class CaesarCipher {

    public static void main(String[] args) {

        System.out.println("Вы находитесь в приложении \"Код Цезаря\"!");
        System.out.println("Выберите режим работы :");
        System.out.println("1. Шифруем.");
        System.out.println("2. Дешифруем.");
        System.out.println("3. Взламываем");

        Scanner console = new Scanner(System.in);
        int n = Integer.parseInt(console.nextLine());

        if (n == 1) {
            Encryption.doEncrypt();
        }
        if (n == 2) {
            Decryption.doDecrypt();
        }
        if (n == 3) {
            BruteForse.doBruteForse();
        } else {
            System.out.println("Вы ввели не корректное число.");
        }
    }
}
