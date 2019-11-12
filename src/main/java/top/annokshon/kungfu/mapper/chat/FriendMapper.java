package top.annokshon.kungfu.mapper.chat;


import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;
import top.annokshon.kungfu.entity.chat.Friend;

import java.util.List;

/**
 * @author kshon
 * @description
 * @date 2019-10-10 9:11
 */
@Repository
public interface FriendMapper{
    List<Friend> getFriend(@Param("id") int personId);
    void save(Friend friend);
}
