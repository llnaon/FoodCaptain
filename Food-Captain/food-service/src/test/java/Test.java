import com.cityu.foodcaptain.constants.Constants;
import com.cityu.foodcaptain.entity.Fresh;
import com.cityu.foodcaptain.service.FreshService;
import com.cityu.foodcaptain.utils.FileUtils;

public class Test {
    public static void main(String[] args) {
//        System.out.println(FileUtils.readAll(Constants.CART));
        Fresh test = new FreshService().parseFresh("8#李记生抽 500ml# #少盐多鲜|厨房常备|知名品牌#粮油调味#生抽#14#60\n");
        System.out.println("1");
    }
}
