package service;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dao.UserDao;
import entity.User;

public class UserService {
	public boolean validateLogin(User user) {
		String resource="SqlMapConfig.xml";
		
		InputStream is=User.class.getClassLoader().getResourceAsStream(resource);
		
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
		
		SqlSession session=factory.openSession();
		
		UserDao userDao=session.getMapper(UserDao.class);
		
		User userDB=userDao.findByNameAndPwd(user);
		
		if(userDB==null) {
			return false;
		}else {
			return true;
		}
	}
	
}
