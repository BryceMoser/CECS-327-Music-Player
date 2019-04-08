package csulb.cecs327.DFS;

import java.time.LocalDateTime;
import java.util.*;
import java.nio.file.*;
import java.math.BigInteger;
import java.security.*;
import com.google.gson.Gson;

/* JSON Format

{"file":
  [
     {"name":"MyFile",
      "size":128000000,
      "pages":
      [
         {
            "guid":11,
            "size":64000000
         },
         {
            "guid":13,
            "size":64000000
         }
      ]
      }
   ]
}
*/

public class DFS {

    /**
     * This class is for Meta Page
     */
    public class PagesJson {
        Long guid;
        Long size;
        String CreateTimeStamp;
        String WriteTimeStamp;
        String ReadTimeStamp;
        int counter;
        //int pageNumber;
        public PagesJson(Long guid, Long size, String CreationTimeStamp, String ReadTimeStamp, String WriteTimeStamp,int counter) {
            this.guid = guid;
            this.size = size;
            this.CreateTimeStamp = CreationTimeStamp;
            this.ReadTimeStamp = ReadTimeStamp;
            this.WriteTimeStamp = WriteTimeStamp;
            this.counter = counter;
        }
        // getters
        public long getSize()
        {

            return size;
        }
        public long getGUID()
        {

            return this.guid;
        }
        public String getCreateTimeStamp(){
            return this.CreateTimeStamp;
        }
        public int getCounter(){
            return this.counter;
        }
        // setters
        public void setSize(Long size){
            this.size = size;
        }
        public void setGuid(Long guid)
        {
            this.guid = guid;
        }
        public void setCreateTimeStamp(String CreateTimeStamp)
        {
            this.CreateTimeStamp = CreateTimeStamp ;
        }

        public void setWriteTimeStamp(String WriteTimeStamp)
        {
            this.WriteTimeStamp = WriteTimeStamp;
        }

        public void setReadTimeStamp(String ReadTimeStamp)
        {
            this.ReadTimeStamp = ReadTimeStamp;
        }
        public void setCounter(int counter)
        {
            this.counter = counter;
        }
    };

    /**
     * This class is for Meta File
     */
    public class FileJson {
        String name;
        Long size;
        String CreateTimeStamp;
        String WriteTimeStamp;
        String ReadTimeStamp;
        int counter;
        int PageNumber;
        int MaxPageNumber;
        ArrayList<PagesJson> pages = new ArrayList<PagesJson>();

        public FileJson() {
            this.size = new Long(0);
            this.PageNumber = 0;
            this.counter = 0;
            //this.creationTS = new Long(date.getTime());
            this.CreateTimeStamp = LocalDateTime.now().toString();
            this.ReadTimeStamp = "0";
            this.WriteTimeStamp = "0";
            this.MaxPageNumber = 0;
            this.pages = new ArrayList<PagesJson>();
        }
        public void addPage(Long guid, Long size, String CreateTimeStamp, String ReadTimeStamp, String WriteTimeStamp,int counter)
        {
            PagesJson newPage = new PagesJson(guid, size,CreateTimeStamp,ReadTimeStamp, WriteTimeStamp,counter);
            pages.add(newPage);
        }
        // Getters
        public String getName()
        {
            return this.name;
        }
        public Long getSize(){
            return this.size;
        }
        public int getMaxPageNumber(){
            return this.MaxPageNumber;
        }
        public int getPageNumber(){
            return this.PageNumber;
        }
        public ArrayList<PagesJson> getPages() {
            return pages;
        }
        public String getCreateTimeStamp() {
            return CreateTimeStamp;
        }

        // Setters
        public void setName(String name)
        {
            this.name = name;
        }
        public void setSize(Long size){
            this.size = size;
        }
        public void setMaxPageNumber(int MaxPageNumber){
            this.MaxPageNumber = MaxPageNumber;
        }
        public int getNumOfPages()
        {
            return pages.size();
        }
        public void addSize(Long size){
            this.size += size;
        }
        public void setCounter(int counter){
            this.counter = counter;
        }
        public void setPageNumber(int PageNumber){
            this.PageNumber = PageNumber;
        }
        public void addPageNumber( int PageNumber){
            this.PageNumber += PageNumber;
        }
        public void setWriteTimeStamp(String WriteTimeStamp){
            this.WriteTimeStamp = WriteTimeStamp;
        }
        public void setReadTimeStamp(String ReadTimeStamp){
            this.ReadTimeStamp = ReadTimeStamp;
        }


        public void printListOfPages()
        {
            System.out.printf("\n%-5s%-15s%-15s\n", "", "Page Number", "GUID");
            for (int i = 0; i < pages.size(); i++)
            {
                PagesJson temp = pages.get(i);

                System.out.printf("%-5s%-15s%-15d\n", "", temp.getCounter(), temp.getGUID());
            }
            System.out.println("");
        }
        // getters
        // setters
    };

    /**
     * This class is for Meta Data
     */
    public class FilesJson {
        List<FileJson> metaFile;
        //ArrayList<FileJson> file = new ArrayList<FileJson>();
        public FilesJson() {
            metaFile = new ArrayList<FileJson>();
        }
        // Getter
        public FileJson getFile(int index){
            return metaFile.get(index);
        }
        public int getSize(){
            return metaFile.size();
        }
        public void addFile(FileJson newFile) {
            metaFile.add(newFile);
        }


        public boolean fileExists(String filename)
        {
            for(int i = 0; i < metaFile.size(); i++)
            {
                FileJson temp = metaFile.get(i);

                if (temp.getName().equals(filename))
                {
                    return true;
                }
            }
            return false;
        }
        public void deleteFile(String filename)
        {
            ListIterator<FileJson> listIterator = metaFile.listIterator();

            while (listIterator.hasNext())
            {
                FileJson temp = listIterator.next();

                if (temp.getName().equals(filename))
                    listIterator.remove();
            }
        }
        public void clear()
        {
            metaFile.clear();
        }
        public void printListOfFiles()
        {
            System.out.printf("\n%-15s%-15s\n", "Filename", "Number of Pages");
            for (int i = 0; i < metaFile.size(); i++)
            {
                FileJson temp = metaFile.get(i);

                System.out.printf("%-15s%-15d\n", temp.getName(), temp.getNumOfPages());

                if (temp.getNumOfPages() > 0)
                    temp.printListOfPages();
            }
            System.out.println("");
        }
        // getters
        // setters
    };

    int port;
    Chord chord;
    FilesJson MetaData;

    private long md5(String objectName) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(objectName.getBytes());
            BigInteger bigInt = new BigInteger(1, m.digest());
            return Math.abs(bigInt.longValue());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

        }
        return 0;
    }

    public DFS(int port) throws Exception {
        this.port = port;
        long guid = md5("" + port);
        this.MetaData = new FilesJson();
        chord = new Chord(port, guid);
        Files.createDirectories(Paths.get(guid + "/repository"));
        Files.createDirectories(Paths.get(guid + "/tmp"));
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                chord.leave();
            }
        });

    }

    /**
     * Join the chord
     *
     */
    public void join(String Ip, int port) throws Exception {
        chord.joinRing(Ip, port);
        chord.print();
    }

    /**
     * leave the chord
     *
     */
    public void leave() throws Exception {
        chord.leave();
    }

    /**
     * print the status of the peer in the chord
     *
     */
    public void print() throws Exception {
        chord.print();
    }

    /**
     * readMetaData read the metadata from the chord
     *
     */
    public FilesJson readMetaData() throws Exception {
        FilesJson filesJson = null;
        try {
            Gson gson = new Gson();
            long guid = md5("Metadata");
            ChordMessageInterface peer = chord.locateSuccessor(guid);
            RemoteInputFileStream metadataraw = peer.get(guid);
            metadataraw.connect();
            Scanner scan = new Scanner(metadataraw);
            scan.useDelimiter("\\A");
            String strMetaData = scan.next();
            System.out.println(strMetaData);
            filesJson = gson.fromJson(strMetaData, FilesJson.class);
        } catch (NoSuchElementException ex) {
            filesJson = new FilesJson();
        }
        return filesJson;
    }

    /**
     * writeMetaData write the metadata back to the chord
     *
     */
    public void writeMetaData(FilesJson filesJson) throws Exception {
        long guid = md5("Metadata");
        ChordMessageInterface peer = chord.locateSuccessor(guid);

        Gson gson = new Gson();
        peer.put(guid, gson.toJson(filesJson));
    }


    /**
     * DONE
     * Changes the name of the file. Asks for input of index, specify index of file, if only one file is there then
     * index 0. The enter the new file name. EX, "touch wang.js", "move", "0", "bang.js"
     */
    public void move() throws Exception {
        FilesJson md = readMetaData();
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the index of the old file:");
        int indexOfOldFile = scan.nextInt();
        FileJson file1 = md.getFile(indexOfOldFile);
        System.out.println("Please enter the name of the new file:");
        String newName = scan.next();
        file1.setName(newName);
        writeMetaData(md);
        System.out.println("Name changed successfully!");
    }

    /**
     * create an empty file
     */
    public void create(String fileName) throws Exception {
        FileJson MetaFile= new FileJson();
        MetaFile.setName(fileName);
        MetaData.addFile(MetaFile);
        writeMetaData(MetaData);
    }

    /**
     * list
     * @return
     * @throws Exception
     */
    public void lists() throws Exception{
        String FileList = " ";
        for(int i = 0; i<MetaData.getSize();i++){
            String FileName = MetaData.getFile(i).getName();
            FileList += " " + FileName + "\n";
        }
        System.out.println(FileList);
        //return FileList;
    }
    /*public void lists() throws Exception {
        MetaData = readMetaData();
        if (MetaData.getSize() > 0 || MetaData == null)
        {
            FilesJson metadata = readMetaData();
            metadata.printListOfFiles();
        }
        else
            System.out.println("No files found in metadata.");
    }*/


    /**
     * DONE
     * Deletes the file, must specify index, if only one file then index 0
     */
    public void delete() throws Exception {
        FilesJson md = readMetaData();
        Scanner scan = new Scanner(System.in);
        int indexOfFile = scan.nextInt();
        FileJson file1 = md.getFile(indexOfFile);
        if (file1.getNumOfPages() > 0) {
            for (int i = 0; i < file1.getNumOfPages(); i++) {
                PagesJson page = file1.pages.get(i);
                long guid = page.getGUID();
                ChordMessageInterface peer = chord.locateSuccessor(guid);
                peer.delete(guid);
            }
        }
        writeMetaData(md);
    }

    /**
     * tail - to read from the last page
     * @param filename
     * @return
     * @throws Exception
     */
    public RemoteInputFileStream tail(String filename) throws Exception {
        RemoteInputFileStream tail = null;
        for(int i = 0; i < MetaData.getSize(); i++) {
            if(MetaData.getFile(i).getName().equalsIgnoreCase(filename)) {
                ArrayList<PagesJson> pagesList = MetaData.getFile(i).getPages();
                int last = pagesList.size() - 1;
                PagesJson pageToRead = pagesList.get(last);
                String timeOfRead = LocalDateTime.now().toString();
                pageToRead.setReadTimeStamp(timeOfRead);
                MetaData.getFile(i).setReadTimeStamp(timeOfRead);
                Long pageGUID = md5(filename + pageToRead.getCreateTimeStamp());
                ChordMessageInterface peer = chord.locateSuccessor(pageGUID);
                tail = peer.get(pageGUID);
                writeMetaData(MetaData);
            }
        }
        return tail;
    }

    /**
     * to read from the first page
     * @param filename
     * @return
     * @throws Exception
     */
    public RemoteInputFileStream head(String filename) throws Exception
    {
        RemoteInputFileStream head = null;
        for(int i = 0; i < MetaData.getSize(); i++) {
            if(MetaData.getFile(i).getName().equalsIgnoreCase(filename)) {
                ArrayList<PagesJson> pagesList = MetaData.getFile(i).getPages();
                int first = 0;
                PagesJson pageToRead = pagesList.get(first);
                String timeOfRead = LocalDateTime.now().toString();
                pageToRead.setReadTimeStamp(timeOfRead);
                MetaData.getFile(i).setReadTimeStamp(timeOfRead);
                Long pageGUID = md5(filename + pageToRead.getCreateTimeStamp());
                ChordMessageInterface peer = chord.locateSuccessor(pageGUID);
                head = peer.get(pageGUID);
                writeMetaData(MetaData);
            }
        }
        return head;
    }

    public RemoteInputFileStream read(String fileName, int pageNumber) throws Exception {
        pageNumber--;
        RemoteInputFileStream InputStream = null;
        FilesJson md = readMetaData();
        for(int i = 0; i < MetaData.getSize(); i++){
            if(MetaData.getFile(i).getName().equalsIgnoreCase(fileName)){
                ArrayList<PagesJson> pagesList = MetaData.getFile(i).getPages();
                for(int k = 0; k < pagesList.size(); k++){
                    if(k == pageNumber){
                        PagesJson pageToRead = pagesList.get(k);
                        String timeOfRead = LocalDateTime.now().toString();
                        pageToRead.setReadTimeStamp(timeOfRead);
                        MetaData.getFile(i).setReadTimeStamp(timeOfRead);
                        Long pageGUID = md5(fileName + pageToRead.getCreateTimeStamp());
                        ChordMessageInterface peer = chord.locateSuccessor(pageGUID);
                        InputStream = peer.get(pageGUID);
                    }
                }
                writeMetaData(MetaData);
            }
        }
        return InputStream;
    }

    public void append(String filename, RemoteInputFileStream data) throws Exception
    {

        for(int i = 0; i < MetaData.getSize();i++)
        {
            //append the page to the file specified by the user
            if(MetaData.getFile(i).getName().equalsIgnoreCase(filename))
            {

                //update information in the file we are going to append
                //data.connect();
                //This is used to get the size of the file
                Long sizeOfFile = new Long(data.available());
                String timeOfAppend = LocalDateTime.now().toString();
                MetaData.getFile(i).setWriteTimeStamp(timeOfAppend);
                MetaData.getFile(i).addPageNumber(1);
                MetaData.getFile(i).addSize(sizeOfFile);

                //create the page metadata information
                String objectName = filename + LocalDateTime.now();
                Long guid = md5(objectName);

                ChordMessageInterface peer = chord.locateSuccessor(guid);
                peer.put(guid, data);
                //chord locate successor , then put

                //filesJson.getFileJson(i).addPageInfo(guid, size, creationTS, readTS, writeTs, referenceCount);
                Long defaultZero = new Long(0);
                MetaData.getFile(i).addPage(guid,sizeOfFile,timeOfAppend,"0","0",0);

            }

        }
        writeMetaData(MetaData);


    }




}