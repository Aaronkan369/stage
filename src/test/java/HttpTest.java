import com.xiaojian.stage.serviceImpl.PersonServiceImpl;
import com.xiaojian.stage.util.HttpUtil;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by xiaojian on 2017/9/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-mvc.xml"})
public class HttpTest {
private static Logger logger = Logger.getLogger(HttpTest.class);
 /*  @Test
    public void test1(){
       String data = HttpUtil.getData("{\"id\":\"1\"}","personUrl","GET");
       //System.out.println(data);
       logger.info("data---------->"+data);
    }*/

   @Test
    public void test2(){

   }
}
