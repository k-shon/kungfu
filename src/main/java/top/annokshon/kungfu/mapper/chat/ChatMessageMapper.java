package top.annokshon.kungfu.mapper.chat;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;
import top.annokshon.kungfu.entity.chat.ChatMessage;
import top.annokshon.kungfu.entity.chat.Friend;

import java.util.List;

/**
 * @author kshon
 * @description
 * @date 2019-10-10 20:43
 */
@Repository
public interface ChatMessageMapper {
    List<ChatMessage> findByPidAndFid(@Param("fromPersonId")int fromPersonId, @Param("toPersonId")int toPersonId);
    int save(ChatMessage chatMessage);
    int getUnReadCount(Friend friend);
    void setRead(@Param("fromPersonId")int fromPersonId, @Param("toPersonId")int toPersonId);
}
