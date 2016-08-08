**REST API**
   
    /createStream
    
Example http://127.0.0.1:8080/createStream?musicList=/Users/Jek/Music/iTunes/iTunesMedia/Music/RHCP
    
    If attribute musicList = null, else play list path from config.properties. key = MEDIA_DIR 
    
    /stopStream
    /pauseStream
    /playStream
    /playNext
    /playPrevious
    /getStatus
    
**File config.properties**

VLC_LIB=/Applications/VLC.app/Contents/MacOS/lib  - this lib from VLC Player.
MEDIA_DIR=/Users/Jek/Mydoc/GIT/onlineRadio/VK     - this default PlayList.   
ADDRESS=10.0.0.121                                - Addres online radio.  
PORT=5555                                         - Port online radio.  
    
 
**Recommendation**    
In order to play online radio, you must install VLC Player.
    Download player - https://www.videolan.org/vlc/
    
Online Radio tested on VLC Version 2.1.2 