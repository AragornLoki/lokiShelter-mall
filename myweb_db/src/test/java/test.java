import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.lokiShelter.db.Application;
import tk.lokiShelter.db.dao.LsPermissionMapper;
import tk.lokiShelter.db.domain.LsPermission;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class test {
    @Autowired
    Environment environment;
//    @Autowired
   // private UserMapper admin;
    @Autowired
    private LsPermissionMapper permissionMapper;
    @Test
    public void test(){
        //System.out.println(environment.getProperty("spring.datasource.url"));
        //User aa=admin.selectByPrimaryKey(1);
        //System.out.println(aa.toString());
    }
    @Test
    public void testSQL() {
    }
}
