package com.tft.test;

import com.tft.boot.BootApplication;
import com.tft.framework.menuRes.bean.MenuRes;
import com.tft.framework.menuRes.service.MenuResService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/9/3 15:36
 *
 * @ClassName MockMvcWebTests
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)

public class MockMvcWebTests {

    @Autowired
    private MenuResService menuResService;

    @Test
    public void testMenus(){
        try {
            List<MenuRes> menuRes =  menuResService.searchAllMenusRes();
            System.out.println(menuRes.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
