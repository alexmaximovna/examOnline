package net.thumbtack.netexam.database;


import net.thumbtack.netexam.dao.*;
import net.thumbtack.netexam.daoImpl.*;
import net.thumbtack.netexam.utils.MyBatisUtils;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;

import java.util.*;

import static org.junit.Assert.assertNotEquals;

public class TestBase {

    private CommonDao commonDao = new CommonDaoImpl();
    protected TeacherDao teacherDao = new TeacherDaoImpl();
    protected StudentDao studentDao = new StudentDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected SessionDao sessionDao = new SessionDaoImpl();

    private static boolean setUpIsDone = false;


    @BeforeClass()
    public static void setUp() {
        if (!setUpIsDone) {
            Assume.assumeTrue(MyBatisUtils.initSqlSessionFactory());
            setUpIsDone = true;
        }
    }

    @Before()
    public void clearDatabase() {
        commonDao.clear();
    }




}
