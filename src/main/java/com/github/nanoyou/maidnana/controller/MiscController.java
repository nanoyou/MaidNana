package com.github.nanoyou.maidnana.controller;

import com.github.nanoyou.maidnana.constant.Usage;
import net.mamoe.mirai.event.events.FriendMessageEvent;

public class MiscController {
    private static final MiscController instance = new MiscController();

    public static MiscController getInstance() {
        return instance;
    }

    public void usage(FriendMessageEvent event) {
        if (!event.getMessage().contentToString().trim().equals("帮助")) {
            return;
        }
        event.getSender().sendMessage(Usage.USAGE);
    }

    public void p() {

    }
}
