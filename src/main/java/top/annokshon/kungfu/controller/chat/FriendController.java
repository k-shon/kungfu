package top.annokshon.kungfu.controller.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.annokshon.kungfu.entity.User;
import top.annokshon.kungfu.entity.chat.Friend;
import top.annokshon.kungfu.service.chat.FriendService;
import top.annokshon.kungfu.utils.JSONResult;

import java.util.Map;

/**
 * @author kshon
 * @description
 * @date 2019-10-10 15:01
 */
@RestController
@RequestMapping("friend")
public class FriendController {
    @Autowired
    private FriendService friendService;
    @RequestMapping("/get")
    public JSONResult getFriend(@RequestParam("id") int userId){
        return JSONResult.ok(friendService.getFriend(userId));
    }
    @RequestMapping("/save")
    public JSONResult saveFriend(@RequestBody Friend friend){
        friendService.save(friend);
        return JSONResult.ok();
    }
}
