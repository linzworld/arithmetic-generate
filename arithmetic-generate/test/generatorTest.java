import com.cy.www.numberUtils;
import com.lzh.Fraction;
import com.lzh.GenerateUtils;
import org.junit.Test;

/**
 * @Description
 * @Author 林泽鸿
 * @Date 2020/4/9 10:34
 */
public class generatorTest {
    @Test
    public void hello() {
        System.out.println(GenerateUtils.getRandomInRange(3));
        System.out.println(GenerateUtils.getRandomInRange(3));
        System.out.println(GenerateUtils.getRandomInRange(3));
        System.out.println(GenerateUtils.getRandomInRange(3));
        System.out.println(GenerateUtils.getRandomInRange(3));
        System.out.println(GenerateUtils.getRandomInRange(3));
        System.out.println(GenerateUtils.getRandomInRange(3));
    }

    @Test
    public void changeNumber() {
        System.out.println(numberUtils.changeNumber(24, 12));
    }


}
