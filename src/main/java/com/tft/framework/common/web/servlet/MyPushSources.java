package com.tft.framework.common.web.servlet;

import nl.justobjects.pushlet.core.Dispatcher;
import nl.justobjects.pushlet.core.Event;
import nl.justobjects.pushlet.core.EventPullSource;
import nl.justobjects.pushlet.core.SessionManager;
import org.springframework.stereotype.Controller;

import java.io.Serializable;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/8/23 15:27
 *
 * @ClassName MyPush
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@Controller
public class MyPushSources implements Serializable {


    static public class SystemPushlet extends EventPullSource {

        public long getSleepTime() {
            return 5000;
        }

        public Event pullEvent() {
            Event event = Event.createDataEvent("/system/pushlet");
            event.setField("msg", "" + SessionManager.getInstance().getStatus());
            return event;
        }
    }



    private void myUnicast()
    {
        if(SessionManager.getInstance().hasSession("piero")){
            Event  event = Event.createDataEvent("myevent1");
            event.setField("msg", "houhou_yesttttt....");
            Dispatcher.getInstance().unicast(event,"piero"); //向ID为piero的用户推送
            System.out.println("success....");
        }
        else {
            System.out.println("piero do not login....%%%%%%%%%%%%");
        }
    }
    private void myMulticast()
    {
        Event  event = Event.createDataEvent("myevent1");
        event.setField("msg", "houhou....");
        Dispatcher.getInstance().multicast(event);  //向所有和myevent1名称匹配的事件推送

        System.out.println("wa success....");
    }
    private void myBroadcast()
    {
        Event  event = Event.createDataEvent("myevent1"); //向所有的事件推送，不要求和这儿的myevent1名称匹配
        event.setField("msg", "dig hole....");
        Dispatcher.getInstance().broadcast(event);

        System.out.println("asw success....");
    }
}
