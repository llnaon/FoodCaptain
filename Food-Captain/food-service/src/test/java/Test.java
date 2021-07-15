import com.cityu.foodcaptain.constants.Constants;
import com.cityu.foodcaptain.utils.FileUtils;

public class Test {
    public static void main(String[] args) {
        System.out.println(FileUtils.readAll(Constants.CART));
    }
}
