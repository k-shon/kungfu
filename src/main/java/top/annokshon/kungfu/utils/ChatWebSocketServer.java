package top.annokshon.kungfu.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.SpringConfigurator;
import top.annokshon.kungfu.entity.chat.ChatMessage;
import top.annokshon.kungfu.service.chat.ChatMessageService;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author kshon
 * @description
 * @date 2019-10-09 16:31
 */
@Component
@ServerEndpoint("/socket/chat/{personId}")  //连接url
public class ChatWebSocketServer {

    @Autowired
    private JsonUtils jsonUtils = new JsonUtils();
    //此处是解决无法注入的关键
    private static ApplicationContext applicationContext;
    private ChatMessageService  chatMessageService;
    public static void setApplicationContext(ApplicationContext applicationContext) {
        ChatWebSocketServer.applicationContext = applicationContext;
    }
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象
    private static CopyOnWriteArraySet<ChatWebSocketServer> webSocketSet = new CopyOnWriteArraySet<ChatWebSocketServer>();
    //全部在线会话
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();
    //当前连接人的id
    private Integer currentId;

    /**
     * 当客户端打开连接：1.添加会话对象 2.更新在线人数
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("personId") int currentId) {
        if(onlineSessions.containsKey(session.getId())){  //当前用户已连接，不用重复连接
            return;
        }
        this.currentId = currentId;  //保存当前连接人的id
        onlineSessions.put(Integer.toString(currentId), session);  //保存当前会话
        System.out.println("新连接："+this.currentId);
        System.out.println("当前在线人数：" + onlineSessions);
    }

    /**
     * 当客户端发送消息：1.获取它的用户名和消息 2.发送消息给所有人
     * PS: 这里约定传递的消息为JSON字符串 方便传递更多参数！
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) {
        ChatMessage chatMessage = jsonUtils.parseToChatMessage(jsonStr);
        System.out.println("收到消息："+chatMessage);
        this.sendMessage(jsonStr,chatMessage);
    }

    /**
     * 当关闭连接：1.移除会话对象 2.更新在线人数
     */
    @OnClose
    public void onClose(Session session) {
        System.out.println("关闭websocket:"+session.getId());
        onlineSessions.remove(session.getId());
    }

    /**
     * 当通信发生异常：打印错误日志
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * 广播消息：发送信息给所有人
     */
    public void sendMessageToAll(String jsonMsg) {
        onlineSessions.forEach((id, session) -> {
            try {
                session.getBasicRemote().sendText(jsonMsg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    /**
     * 单点消息：给当前用户发送消息
     */
    public void sendMessage(String jsonMsg){
        onlineSessions.get(Integer.toString(this.currentId)).getAsyncRemote().sendText(jsonUtils.addMeTag(jsonMsg));
    }
    /**
     * 单点消息：给指定用户发送消息
     */
    public void sendMessage(String jsonMsg, ChatMessage chatMessage){
        chatMessageService = applicationContext.getBean(ChatMessageService.class);
        System.out.println("发送消息给："+chatMessage.getToPerson());
        if(onlineSessions.containsKey(Integer.toString(chatMessage.getToPerson().getId()))
            && onlineSessions.get(Integer.toString(chatMessage.getToPerson().getId())).isOpen()) {
            onlineSessions.get(Integer.toString(chatMessage.getToPerson().getId())).getAsyncRemote().sendText(jsonMsg);
        }
        //保存到数据库
        chatMessage.setStatus(0);  //设置为未读消息
        chatMessageService.save(chatMessage);
        this.sendMessage(jsonMsg);  //发消息给自己以确定消息发送成功
    }
}
