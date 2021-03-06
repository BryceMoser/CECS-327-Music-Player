/**
* SongDispatcher is the main responsable for obtaining the songs 
*
* @author  Oscar Morales-Ponce
* @version 0.15
* @since   02-11-2019 
*/
package csulb.cecs327.Server.RPC.components;
import csulb.cecs327.DFS.DFS;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;
import java.util.Base64;
import java.io.FileNotFoundException;

/**
 * This class handles the song transfer from server and client.
 * The file is taken in and pointed to the mp3 file contained within the project structure.
 */
public class SongDispatcher
{
    DFS dfs;
    static final int FRAGMENT_SIZE = 8192;
    public SongDispatcher(DFS dfs)
    {
        this.dfs = dfs;
    }
    
    /**
    * getSongChunk: Gets a chunk of a given song
    * @param key: Song ID. Each song has a unique ID 
    * @param fragment: The chunk corresponds to 
    * [fragment * FRAGMENT_SIZE, FRAGMENT_SIZE]
    */
    public String getSongChunk(Long key, Long fragment) throws FileNotFoundException, IOException
    {
        byte buf[] = new byte[FRAGMENT_SIZE];

        //File file = new File("./" + key);
//        File file = new File(System.getProperty("user.dir") + "\\music\\" +key);
        // To check the status and if the file is found
//        System.out.println("SongDispatcher has found file: "+key+"\t, Status: "+file.exists());
//        FileInputStream inputStream = new FileInputStream(file);
//        inputStream.skip(fragment * FRAGMENT_SIZE);
//        inputStream.read(buf);
//        inputStream.close();
//        // Encode in base64 so it can be transmitted
        try {
            buf = dfs.get(key.toString(), FRAGMENT_SIZE * fragment, FRAGMENT_SIZE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(buf);
    }
    
    /**
    * getFileSize: Gets a size of the file
    * @param key: Song ID. Each song has a unique ID*/
    public Integer getFileSize(Long key) throws FileNotFoundException, IOException
    {
        //File file = new File("./" + key);
//        File file = new File(System.getProperty("user.dir") + "\\music\\" +key);
//        Integer total =  (int)file.length();
//
//        return total;
        try {
            return dfs.getFileLength(key.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    
}
