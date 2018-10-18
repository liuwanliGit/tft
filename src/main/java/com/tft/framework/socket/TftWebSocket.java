package com.tft.framework.socket;

import com.tft.framework.config.HttpSessionConfigurator;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/8/23 11:38
 *
 * @ClassName MyWebSocket
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@ServerEndpoint(value = "/myWebSocket",configurator = HttpSessionConfigurator.class)
@Component
public class TftWebSocket {

//    @Autowired
//    private SimpMessageHeaderAccessor simpMessageHeaderAccessor;

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全map,存储用户的session
    private static CopyOnWriteArraySet<TftWebSocket> userSession = new CopyOnWriteArraySet<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //用户编码，与session绑定
    private String userName;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,EndpointConfig config) {
        HttpSession httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        this.session = session;
//        User user = (User)httpSession.getAttribute("loginUser");
//        if(user==null){
//            throw new TftBaseBizException("请先登录");
//        }
        userSession.add(this);
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
        try {
            sendInfo("用户["+session.getId()+"]加入聊天室！");
//            sendMessage("欢迎您！session="+session.getId());
        } catch (IOException e) {
            System.out.println("IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() throws Exception{
        if(userSession!=null){
            userSession.remove(this);
        }
        if(userSession.size()<1){
            subOnlineCount();           //在线数减1
        }
        sendInfo("用户["+session.getId()+"]退出聊天室！");
        System.out.println(session.getId()+"连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session)throws Exception {
        System.out.println("来自客户端["+session.getId()+"]的消息:" + message);
        sendInfo(session.getId()+"说："+message);
    }

    /**
     * 发生错误时调用
     */
     @OnError
     public void onError(Session session, Throwable error) {
     System.out.println("发生错误");
     error.printStackTrace();
     }


     public void sendMessage(String message) throws IOException {
     this.session.getBasicRemote().sendText(message);
     //this.session.getAsyncRemote().sendText(message);
     }

     /**
      * 群发自定义消息
      * */
    public static void sendInfo(String message) throws IOException {
        for (TftWebSocket item :userSession) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        TftWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        TftWebSocket.onlineCount--;
    }
}
