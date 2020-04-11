import com.lzh.Fraction;
import org.junit.Test;

/**
 * @Description
 * @Author 林泽鸿
 * @Date 2020/4/11 13:32
 */
public class FractionTest {

    @Test
    public void Fraction() {
        String result = "1'21/22";
        Fraction fraction = new Fraction(result);
        System.out.println(fraction);
        System.out.println(new Fraction("2/6"));
        System.out.println(new Fraction("-2/6"));
        System.out.println(new Fraction("-6/6"));
        System.out.println(new Fraction("-6/3"));

    }
}
