package com.radio.core;

import com.radio.impl.RadioConstants;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Jek on 05.08.16.
 */
@Component
public class PlayList
{
    private Logger log = LoggerFactory.getLogger(PlayList.class);

    private MediaPlayerFactory factory;

    private MediaListPlayer mediaListPlayer;

    private MediaList playList;

    public PlayList() {
        NativeLibrary.addSearchPath(
                RuntimeUtil.getLibVlcLibraryName(), "/Applications/VLC.app/Contents/MacOS/lib"
        );
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        initPlayList();
    }

    private void initPlayList()
    {
        factory = new MediaPlayerFactory();
        mediaListPlayer = factory.newMediaListPlayer();
        mediaListPlayer.addMediaListPlayerEventListener(new MediaListPlayerEventAdapter() {
            @Override
            public void nextItem(MediaListPlayer mediaListPlayer, libvlc_media_t item, String itemMrl) {
                System.out.println("Playing next item: " + itemMrl + " (" + item + ")");

            }
        });
        playList = factory.newMediaList();

    }


    public void start(String dir, String address, int port) throws Exception {

        System.out.println("Scanning for audio files...");
        // Scan for media files
        List<File> files = scanForMedia(dir);
        // Randomise the order
        Collections.shuffle(files);
        // Prepare the media options for streaming
        String options = formatHttpStream(address, port);

        // Add each media file to the play-list...
        playList.clear();
        for (File file : files) {
            // You could instead set standard options on the media list player rather
            // than setting options each time you add media
            System.out.println(file.getPath());
            playList.addMedia(file.getAbsolutePath(), options);

        }
        // Loop the play-list over and over
        mediaListPlayer.setMode(MediaListPlayerMode.LOOP);
        // Attach the play-list to the media list player
        mediaListPlayer.setMediaList(playList);
        // Finally, start the media player

        mediaListPlayer.play();
        // Wait forever...
//        Thread.currentThread().join();
        System.out.println("Thread Id = "+String.valueOf(Thread.currentThread().getId()));

    }

    public void stop()
    {
        mediaListPlayer.stop();
    }

    public void pause()
    {
        mediaListPlayer.pause();
    }

    public void play()
    {
        mediaListPlayer.play();
    }

    /**
     * Search a directory, recursively, for mp3 files.
     *
     * @param root root directory
     * @return collection of mp3 files
     */
    private List<File> scanForMedia(String root) {
        List<File> result = new ArrayList<File>(400);
        scanForMedia(new File(root), result);
        return result;
    }

    private void scanForMedia(File root, List<File> result) {
        if (root.exists() && root.isDirectory()) {
            // List all matching mp3 files...
            File[] files = root.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.isFile() && pathname.getName().toLowerCase().endsWith(".mp3");
                }
            });
            // Add them to the collection
            result.addAll(Arrays.asList(files));
            // List all nested directories...
            File[] dirs = root.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.isDirectory();
                }
            });
            // Recursively scan each nested directory...
            for (File dir : dirs) {
                scanForMedia(dir, result);
            }
        }
    }

    private static String formatHttpStream(String serverAddress, int serverPort) {
        StringBuilder sb = new StringBuilder(60);
        sb.append(":sout=#duplicate{dst=std{access=http,mux=ts,");
        sb.append("dst=");
        sb.append(serverAddress);
        sb.append(':');
        sb.append(serverPort);
        sb.append("}}");
        return sb.toString();
    }
}
