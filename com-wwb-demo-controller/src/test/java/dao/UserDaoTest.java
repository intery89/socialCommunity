package dao;

import com.wwb.demo.dao.UserDao;
import com.wwb.demo.domain.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.transaction.Transactional;

/**
 * Created by xiaoai on 2016/2/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-config.xml"})
@TransactionConfiguration(defaultRollback = false)
public class UserDaoTest {
    @Autowired
    UserDao userDao;
    @Test
    @Transactional
    public void addUser(){
        User user = new User();
       // user.setId(1);
        user.setEmail("saobian@162.com");
        user.setUsername("saobian");
        user.setPassword("wohensao");
        userDao.create(user);
    }

}
