import com.cy.www.numberUtils;
import com.lzh.Fraction;
import com.lzh.GenerateUtils;
import org.junit.Test;

import java.util.HashMap;

import static com.lzh.GenerateUtils.generateMap;

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

    @Test
    public void generator() {
        HashMap hashMap = generateMap(1000, 20);
        System.out.println(hashMap);
        System.out.println(hashMap.size());
    }


}
