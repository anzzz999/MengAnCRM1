import com.mengan.sys.domain.User;
import com.mengan.sys.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

public class LoginTest {

    @Test
    public void login(){
        Reader reader= null;
        try {
            reader = Resources.getResourceAsReader("application-dao.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user =new User();
        user.setRealname("admin");
        user.setPwd("e10adc3949ba59abbe56e057f20f883e");
        User login = userMapper.login(user);
        System.out.println(login);
    }
}
