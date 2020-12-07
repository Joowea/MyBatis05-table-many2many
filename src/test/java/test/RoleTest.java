package test;

import com.joo.dao.IRoleDao;
import domain.Role;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class RoleTest {

    private InputStream in;
    private SqlSession session;
    private IRoleDao roleDao;


    @Before
    public void before() throws IOException {

        //1.获取SqlMapConfig.xml配置
        in = Resources.getResourceAsStream("SqlMapConfig.xml");

        //2.获取SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

        //3.获取SqlSession对象
        session = factory.openSession(true);

        //4.获取dao代理对象
        roleDao = session.getMapper(IRoleDao.class);

    }

    @After
    public void destroy() throws IOException {

        //5.释放资源
        session.close();
        in.close();
    }

    @Test
    public void testFindAllRole(){

        List<Role> allRoles = roleDao.findAllRole();

        for (Role role: allRoles){

            System.out.println("------------");

            System.out.println(role);
        }
    }
}
