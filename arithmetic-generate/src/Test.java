/**
 * @Description 测试
 * @Author 林泽鸿
 * @Date 2020/4/3 9:06
 */
public class Test {
    public static void main(String[] args) {
        String string="Myapp.exe -e <exercisefile>.txt -a <answerfile>.txt";
        args=string.split("\\s+");
        System.out.println(args[0]);System.out.println(args[1]);
        System.out.println(args[2]);System.out.println(args[3]);

    }
}
