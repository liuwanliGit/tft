package com.tft.forum.topic.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/8/6 9:40
 *
 * @ClassName SpringMvc
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@RestController
@RequestMapping("/api/demo/*")
public class SpringMvcDemoController {

    @RequestMapping("getInfo")
    @ResponseBody
    public void getInfo(Integer[] strList){
//        System.out.println(topicType.getId());
        System.out.println(strList[0]);
    }

}
