package com.gistandard.transport.tools.util;

import org.csource.fastdfs.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.InetSocketAddress;

/**
 * Created by zhuming on 2016/12/21.
 */
public class FastDFSClient {

    private TrackerClient trackerClient = null;
    private TrackerServer trackerServer = null;
    private StorageClient1 storageClient = null;
    private StorageServer storageServer = null;


    public FastDFSClient(String transportDfsUrl) throws Exception {
        String[] url = transportDfsUrl.split(",");
        InetSocketAddress[] tracker_servers = new InetSocketAddress[url.length];
        for (int i = 0; i < url.length; i++) {
            String[] adress = url[i].split(":");
            if (adress.length == 2) {
                InetSocketAddress addr = new InetSocketAddress(adress[0], Integer.valueOf(adress[1]));
                tracker_servers[i] = addr;
            }
        }
        ClientGlobal.setG_charset("UTF-8");
        ClientGlobal.setG_connect_timeout(20000);
        ClientGlobal.setG_network_timeout(30000);
        ClientGlobal.setG_tracker_group(new TrackerGroup(tracker_servers));
    }

    public String uploadFile(MultipartFile multipartFile) throws Exception {


        trackerClient = new TrackerClient();
        trackerServer = trackerClient.getConnection();
        storageServer = trackerClient.getStoreStorage(trackerServer);
        storageClient = new StorageClient1(trackerServer, storageServer);
        String originalFilename = multipartFile.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
        String result = storageClient.upload_file1(multipartFile.getBytes(), extName, null);
        storageServer.close();
        trackerServer.close();
        return result;
    }

    public String uploadFile(byte[] multipartFile, String suffix) throws Exception {


        trackerClient = new TrackerClient();
        trackerServer = trackerClient.getConnection();
        storageServer = trackerClient.getStoreStorage(trackerServer);
        storageClient = new StorageClient1(trackerServer, storageServer);
        String result = storageClient.upload_file1(multipartFile, suffix, null);
        storageServer.close();
        trackerServer.close();
        return result;
    }

    public String uploadFile(String path, String suffix) throws Exception {

        trackerClient = new TrackerClient();
        trackerServer = trackerClient.getConnection();
        storageServer = trackerClient.getStoreStorage(trackerServer);
        storageClient = new StorageClient1(trackerServer, storageServer);
        String result = storageClient.upload_file1(path, suffix, null);
        storageServer.close();
        trackerServer.close();
        return result;
    }

}
