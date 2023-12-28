public class stringbilder1 {
    public static void main(String[] args) {
        String str = "Куда уехал цирк, он был еще вчера ";
        StringBuilder stB = new StringBuilder();
        stB.append(str);
        char ch = 'и';
        stB.append(ch);
        String str1 = " ветер ";
        stB.append(str1);
        String str2 = "не успел, со стен сорвать афиши.";
        stB.append(str2);
        System.out.println(stB);
        System.out.println(stB.length());


    }
}
