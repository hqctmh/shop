import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"classpath:spring-context.xml"})
public class BaseTest extends AbstractJUnit4SpringContextTests {
	public static final Logger logger = Logger.getLogger(BaseTest.class);
}
