package com.radio.config;

import com.radio.impl.RadioConstants;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import org.springframework.stereotype.Component;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Jek on 08.08.16.
 */
@Component
public class Config
{

    private Properties props = new Properties();

    public Config()
    {
        try
        {
            props.load(new FileInputStream(new File("config.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        NativeLibrary.addSearchPath(
                RuntimeUtil.getLibVlcLibraryName(), getVLCLib()
        );
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
    }

    public String getVLCLib()
    {
        return props.getProperty(RadioConstants.VLC_LIB);
    }

    public String getAddres()
    {
        return props.getProperty(RadioConstants.ADDRESS);
    }

    public String getPort()
    {
        return props.getProperty(RadioConstants.PORT);
    }

    public String getMediaDir()
    {
        return props.getProperty(RadioConstants.MEDIA_DIR);
    }

}