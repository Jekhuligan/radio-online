package com.radio.api.controller;

import com.radio.core.PlayList;
import com.radio.core.RadioCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jek on 05.08.16.
 */
@RestController
public class RadioController
{

    @Autowired
    private RadioCore radioCore;

    @Autowired
    private PlayList playList;

    @RequestMapping("/createStream")
    public String createStream(@RequestParam String musicList)
    {

        try {
            playList.start(musicList,"10.0.0.121",5555);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "OK";
    }

    @RequestMapping("/stopStream")
    public String stopStream()
    {
        playList.stop();
        return "Stream stoped";
    }

    @RequestMapping("/pauseStream")
    public String pauseStream()
    {
        playList.pause();
        return "Stream pause";
    }

    @RequestMapping("/playStream")
    public String playStream()
    {
        playList.play();
        return "Stream play";
    }


}
