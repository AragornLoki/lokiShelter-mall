import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tk.lokiShelter.db.dao.LsMenuMapper;
import tk.lokiShelter.db.domain.LsMenu;
import tk.lokiShelter.db.domain.LsMenuExample;
import tk.lokiShelter.manage.Application;
import tk.lokiShelter.server.dao.AdminDao;
import tk.lokiShelter.server.dao.RoleDao;
import tk.lokiShelter.server.service.RoleService;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class test {
    @Autowired
    private RoleService roleService ;
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private LsMenuMapper menuMapper;


    @Test
    public void password(){

        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String hashPass = bcryptPasswordEncoder.encode("1234");
        System.out.println(hashPass);

    }
    @Test
    public void test1(){
        LsMenuExample menuExample=new LsMenuExample();
//        menuExample.or().andIconIsNotNull();
//        List<LsMenu> menus = menuMapper.selectByExample(menuExample);
        roleDao.getMenuListByRoleId((long)1);
//        adminDao.getRoleList((long)1);

    }
}
