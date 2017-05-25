package cyf.fast.fdfs.service;


import com.luhuiguo.fastdfs.conn.FdfsConnectionPool;
import com.luhuiguo.fastdfs.conn.TrackerConnectionManager;
import com.luhuiguo.fastdfs.domain.StorageNode;
import com.luhuiguo.fastdfs.domain.StorePath;
import com.luhuiguo.fastdfs.service.DefaultFastFileStorageClient;
import com.luhuiguo.fastdfs.service.DefaultTrackerClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-5-25.
 */
@Service
public class FdfsService {
    private TrackerConnectionManager trackerConnectionManager;
    private DefaultTrackerClient trackerClient;

    @PostConstruct
    void init() {
        try {
            FdfsConnectionPool pool = new FdfsConnectionPool();
            List<String> trackerList = new ArrayList<>();
            trackerList.add("127.0.0.1:22122");
//            trackerList.add("192.168.1.210:22122");
            trackerConnectionManager = new TrackerConnectionManager(pool, trackerList);
            trackerClient = new DefaultTrackerClient(trackerConnectionManager);
            InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 22122);
            trackerConnectionManager.dumpPoolInfo(inetSocketAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void get() {
        StorageNode storageNode = trackerClient.getStoreStorage();
        storageNode.getGroupName();
        InetSocketAddress address = storageNode.getInetSocketAddress();
    }

    public String upload(String groupName, InputStream inputStream, long fileSize, String fileExtName,byte[] bytes) {
        DefaultFastFileStorageClient storageClient = new DefaultFastFileStorageClient(trackerClient, trackerConnectionManager);
       // StorePath storePath = storageClient.uploadFile(groupName, inputStream, fileSize, fileExtName);
        StorePath storePath_1 = storageClient.uploadFile(bytes,fileExtName);
        return storePath_1.getPath();

    }
}
