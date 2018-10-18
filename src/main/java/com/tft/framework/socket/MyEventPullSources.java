package com.tft.framework.socket;

import nl.justobjects.pushlet.core.Event;
import nl.justobjects.pushlet.core.EventPullSource;
import nl.justobjects.pushlet.core.Session;
import nl.justobjects.pushlet.core.SessionManager;
import org.springframework.stereotype.Controller;

import java.io.Serializable;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/8/23 14:37
 *
 * @ClassName MyEventPullSources
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@Controller
public class MyEventPullSources implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 8397335113632699297L;

    public static class MyEvent extends EventPullSource {

        @Override
        protected long getSleepTime() {

            return 1000; //刷新时间

        }

        @Override
        protected Event pullEvent() {

            Event event = Event.createDataEvent("/pms/bxService");// 事件标识 // 注意：此处”/pms/bxService”将对应页面js代码中的PjoinListen中的参数
            // 获取当前登陆用户Id(加入事件订阅的用户)
            Session[] sessions = SessionManager.getInstance().getSessions();
            System.out.println("登录用户"+sessions.length+"位");
            event.setField("msg","this is msg");
            return event;
        }
    }
}
