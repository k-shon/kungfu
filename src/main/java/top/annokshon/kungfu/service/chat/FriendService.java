package top.annokshon.kungfu.service.chat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.annokshon.kungfu.entity.User;
import top.annokshon.kungfu.entity.chat.Friend;
import top.annokshon.kungfu.mapper.chat.FriendMapper;

import java.util.List;

/**
 * @author kshon
 * @description
 * @date 2019-10-10 9:14
 */
@Service
public class FriendService {
    private Log log = LogFactory.getLog(FriendService.class);
    @Autowired
    private FriendMapper friendMapper;
    @Autowired
    private ChatMessageService chatMessageService;

    public List<Friend> getFriend(int userId){
        log.info("查找【"+userId+"】的联系人");
        List<Friend> friends = friendMapper.getFriend(userId);
        for(Friend friend:friends){
            friend.setUnRead(chatMessageService.getUnReadCount(friend));
        }
        return  friends;
    }
    public void save(Friend friend){
        friendMapper.save(friend);
    }
}
