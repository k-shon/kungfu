package top.annokshon.kungfu.service.chat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import top.annokshon.kungfu.entity.chat.ChatMessage;
import top.annokshon.kungfu.entity.chat.Friend;
import top.annokshon.kungfu.mapper.chat.ChatMessageMapper;

import java.util.Date;
import java.util.List;


/**
 * @author kshon
 * @description
 * @date 2019-10-10 9:14
 */
@Service
public class ChatMessageService {
    private Log log = LogFactory.getLog(ChatMessageService.class);
    @Autowired
    private ChatMessageMapper chatMessageMapper;

    //根据发送人和接收人获取聊天记录
    public List<ChatMessage> getChatMessage(int fromPersonId, int toPersonId){
        //将好友的聊天记录未读消息设置为已读
        chatMessageMapper.setRead(fromPersonId,toPersonId);
        return  chatMessageMapper.findByPidAndFid(fromPersonId,toPersonId);
    }
    //保存聊天记录
    public ChatMessage save(ChatMessage chatMessage){
        chatMessageMapper.save(chatMessage);
        return chatMessage;
    }
    //返回未读消息
    public int getUnReadCount(Friend friend){
        return chatMessageMapper.getUnReadCount(friend);
    }
    //设置为已读消息
    public void setRead(int fromPersonId, int toPersonId){
        chatMessageMapper.setRead(fromPersonId,toPersonId);
        log.info("设置朋友"+toPersonId+"的消息为已读");
    }
}
