import com.cy.www.numberUtils;
import com.lzh.Node;
import org.junit.Test;

/**
 * @Description
 * @Author 林泽鸿
 * @Date 2020/4/9 21:30
 */
public class ExpressionTest {
    @Test
    public void nodeGenerate() {
        System.out.println(numberUtils.changeNumber(-2, 6));
        System.out.println(numberUtils.changeNumber(2, 6));
        System.out.println(numberUtils.changeNumber(6, 6));
        System.out.println(numberUtils.changeNumber(-2, -6));
    }

    @Test
    public void trim() {
        String Str = new String("    www   runoob   com    ");
        System.out.print("原始值 :");
        System.out.println(Str);

        System.out.print("删除头尾空白 :");
        System.out.println(Str.trim());
    }
}
