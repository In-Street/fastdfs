package cyf.fast.util;

import org.csource.fastdfs.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

/**
 * Created by Administrator on 2016-12-14.
 */
public class FastfdfsFileutil {

    private static TrackerClient trackerClient;
    private static TrackerServer trackerServer;
    private static StorageServer storageServer;
    private static StorageClient1 storageClient1 ;

    public static final String FDFS_HTTP_SERVER_HTTP_URL = "http_serverhttp_url";

    static {
        try {
            String fdfsClientConfigFilePath = FastfdfsFileutil.class.getResource(SysConstans.FDFS_CONFIG_LOCATION).getPath();
            ClientGlobal.init(fdfsClientConfigFilePath);
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
//            storageClient1 = new StorageClient1(trackerServer, storageServer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传
     * @param file_buff
     * @param file_ext_name
     * @return
     * @throws Exception
     */
    public static String uploadFile(byte[] file_buff, String file_ext_name)  {

        String result = null;
        try {
            result = getStorageClient1().upload_file1(file_buff, file_ext_name, null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 下载
     * @param groupName
     * @param remoteFileName
     * @param specFileName
     * @return
     */
    public static ResponseEntity<byte[]> download(String groupName, String remoteFileName, String specFileName) {
        byte[] content = null;
        HttpHeaders headers = new HttpHeaders();
        try {
            content = getStorageClient1().download_file(groupName, remoteFileName);
            headers.setContentDispositionFormData("attachment",  new String(specFileName.getBytes("UTF-8"),"iso-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(content, headers, HttpStatus.CREATED);
    }

    public static StorageClient1 getStorageClient1() {
        return new StorageClient1(trackerServer,storageServer);
    }

    public static String getFdfsHttpServerUrl(){
        return PropertiesUtil.getPropertyValueByKeyAndLocation(SysConstans.FDFS_CONFIG_LOCATION,FDFS_HTTP_SERVER_HTTP_URL);
    }

}
