import java.net.URI;

/**
 * Created by zhangyw on 2017/8/29.
 */
public class UrlTest {
    public static void main(String[] args) {
        System.out.println(URI.create("http://www.1123.baidu.com/asdf").getHost());
    }
}