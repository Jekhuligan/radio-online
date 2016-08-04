package com.radio;


import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.util.*;

import com.radio.impl.RadioConstants;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.binding.internal.libvlc_media_t;
import uk.co.caprica.vlcj.medialist.MediaList;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.list.MediaListPlayer;
import uk.co.caprica.vlcj.player.list.MediaListPlayerEventAdapter;
import uk.co.caprica.vlcj.player.list.MediaListPlayerMode;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;


/**
 * Created by Jek on 27.07.16.
 */
public class Main //extends AbstractVlcj
{

}
//
//    private final MediaPlayerFactory factory;
//
//    private final MediaListPlayer mediaListPlayer;
//
//    private final MediaList playList;
//
//    public static void main(String[] args) throws Exception {
//
//        Properties props = new Properties();
//        props.load(new FileInputStream(new File("src/main/resources/config.properties")));
//
//
//        NativeLibrary.addSearchPath(
//                RuntimeUtil.getLibVlcLibraryName(), props.getProperty(RadioConstants.VLC_LIB)
//        );
//        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
//
//
//
//
//        String dir = props.getProperty(RadioConstants.MEDIA_DIR);
//        String address = props.getProperty(RadioConstants.ADDRESS);
//        int port = Integer.parseInt(props.getProperty(RadioConstants.PORT));
//
//        new Main().start(dir, address, port);
//    }
//
//    public Main() {
//        factory = new MediaPlayerFactory();
//        mediaListPlayer = factory.newMediaListPlayer();
//        mediaListPlayer.addMediaListPlayerEventListener(new MediaListPlayerEventAdapter() {
//            @Override
//            public void nextItem(MediaListPlayer mediaListPlayer, libvlc_media_t item, String itemMrl) {
//                System.out.println("Playing next item: " + itemMrl + " (" + item + ")");
//
//            }
//        });
//        playList = factory.newMediaList();
//    }
//
//    private void start(String dir, String address, int port) throws Exception {
//        System.out.println("Scanning for audio files...");
//        // Scan for media files
//        List<File> files = scanForMedia(dir);
//        // Randomise the order
//        Collections.shuffle(files);
//        // Prepare the media options for streaming
//        String options = formatHttpStream(address, port);
//
//        // Add each media file to the play-list...
//        for (File file : files) {
//            // You could instead set standard options on the media list player rather
//            // than setting options each time you add media
//            System.out.println(file.getPath());
//            playList.addMedia(file.getAbsolutePath(), options);
//
//        }
//        // Loop the play-list over and over
//        mediaListPlayer.setMode(MediaListPlayerMode.LOOP);
//        // Attach the play-list to the media list player
//        mediaListPlayer.setMediaList(playList);
//        // Finally, start the media player
//        mediaListPlayer.play();
//        // Wait forever...
//        Thread.currentThread().join();
//    }
//
//    /**
//     * Search a directory, recursively, for mp3 files.
//     *
//     * @param root root directory
//     * @return collection of mp3 files
//     */
//    private List<File> scanForMedia(String root) {
//        List<File> result = new ArrayList<File>(400);
//        scanForMedia(new File(root), result);
//        return result;
//    }
//
//    private void scanForMedia(File root, List<File> result) {
//        if (root.exists() && root.isDirectory()) {
//            // List all matching mp3 files...
//            File[] files = root.listFiles(new FileFilter() {
//                @Override
//                public boolean accept(File pathname) {
//                    return pathname.isFile() && pathname.getName().toLowerCase().endsWith(".mp3");
//                }
//            });
//            // Add them to the collection
//            result.addAll(Arrays.asList(files));
//            // List all nested directories...
//            File[] dirs = root.listFiles(new FileFilter() {
//                @Override
//                public boolean accept(File pathname) {
//                    return pathname.isDirectory();
//                }
//            });
//            // Recursively scan each nested directory...
//            for (File dir : dirs) {
//                scanForMedia(dir, result);
//            }
//        }
//    }
//
//    private static String formatHttpStream(String serverAddress, int serverPort) {
//        StringBuilder sb = new StringBuilder(60);
//        sb.append(":sout=#duplicate{dst=std{access=http,mux=ts,");
//        sb.append("dst=");
//        sb.append(serverAddress);
//        sb.append(':');
//        sb.append(serverPort);
//        sb.append("}}");
//        return sb.toString();
//    }
//}