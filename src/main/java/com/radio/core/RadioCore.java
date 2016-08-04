package com.radio.core;

import com.radio.impl.RadioConstants;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.binding.internal.libvlc_media_t;
import uk.co.caprica.vlcj.medialist.MediaList;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.list.MediaListPlayer;
import uk.co.caprica.vlcj.player.list.MediaListPlayerEventAdapter;
import uk.co.caprica.vlcj.player.list.MediaListPlayerMode;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by Jek on 05.08.16.
 */
@Component
public class RadioCore extends AbstractVlcj {

    @Autowired
    private PlayList playList;

    private Logger log = LoggerFactory.getLogger(RadioCore.class);


    public void initStream() {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(new File("src/main/resources/config.properties")));
        } catch (IOException e) {
            log.error("Not find properties", e);
        }


//        NativeLibrary.addSearchPath(
//                RuntimeUtil.getLibVlcLibraryName(), props.getProperty(RadioConstants.VLC_LIB)
//        );
//        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);


        String dir = props.getProperty(RadioConstants.MEDIA_DIR);
        String address = props.getProperty(RadioConstants.ADDRESS);
        int port = Integer.parseInt(props.getProperty(RadioConstants.PORT));

        try {
            playList.start(dir,address,port);
        } catch (Exception e) {
            log.error("Not start Stream",e);
        }

//        try {
//            new Main().start(dir, address, port);
//        } catch (Exception e) {
//
//        }
    }

}


