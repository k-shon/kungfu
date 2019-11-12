package top.annokshon.kungfu.controller.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.annokshon.kungfu.entity.chat.ChatMessage;
import top.annokshon.kungfu.service.chat.ChatMessageService;
import top.annokshon.kungfu.utils.JSONResult;
import top.annokshon.kungfu.utils.ChatWebSocketServer;

/**
 * @author kshon
 * @description
 * @date 2019-10-10 15:01
 */
@RestController
@RequestMapping("chatMessage")
public class ChatMessageController {
    @Autowired
    private ChatMessageService chatMessageService;
    @RequestMapping("/get")
    public JSONResult getChatMessage(@RequestParam("fromPersonId") int fromPersonId,@RequestParam("toPersonId") int toPersonId){
        return JSONResult.ok(chatMessageService.getChatMessage(fromPersonId,toPersonId));
    }

    @RequestMapping("/read")
    public JSONResult setRead(@RequestParam("fromPersonId") int fromPersonId,@RequestParam("toPersonId") int toPersonId){
        chatMessageService.setRead(fromPersonId,toPersonId);
        return JSONResult.ok();
    }
}
