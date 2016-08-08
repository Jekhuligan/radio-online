package com.radio.api.controller;

import com.radio.config.Config;
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
    private Config config;

    @Autowired
    private PlayList playList;

    @RequestMapping("/createStream")
    public String createStream(@RequestParam String musicList)
    {

        if (musicList== null)
        {
            musicList = config.getMediaDir();
        }
        try {
            playList.start(musicList,config.getAddres(), Integer.parseInt(config.getPort()));
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

    @RequestMapping("/playNext")
    public String playNext()
    {
        playList.playNext();
        return "Stream play";
    }

    @RequestMapping("/playPrevious")
    public String playPrevious()
    {
        playList.playPrevious();
        return "Stream play";
    }

    @RequestMapping("/getStatus")
    public String getStatus()
    {
        return playList.getStatus();
    }


}
