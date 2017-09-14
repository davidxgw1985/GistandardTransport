package com.gistandard.transport.system.upload.service;

import com.gistandard.transport.base.entity.bean.BizAttachment;
import com.gistandard.transport.base.entity.bean.ExpreessFileUploadRecord;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yjf
 * @ClassName UploadService
 * @Description
 * @Version 1.0
 * @Date 2015-10-13
 */
public interface UploadService {

    /**
     * 文件上传
     * @param multipartFile
     * @param fileBizType
     * @param userId 上传用户Id
     * @return
     * @throws Exception
     */
    BizAttachment uploadFile(MultipartFile multipartFile, int fileBizType, int userId) throws Exception;

    /**
     * 文件上传
     * @param multipartFile
     * @param fileBizType 文件业务类型
     * @return
     */
    BizAttachment uploadFile(MultipartFile multipartFile, int fileBizType) throws Exception;

    /**
     * 文件上传
     * @param base64FileStr
     * @param fileBizType 文件业务类型
     * @return
     */
    BizAttachment uploadFile(String base64FileStr, int fileBizType) throws Exception;

    /**
     * 文件传输
     * @param multipartFile
     * @param fileType
     * @return
     * @throws Exception
     */
    BizAttachment transferFile(MultipartFile multipartFile, int fileType) throws Exception;

    /**
     * 文件传输
     * @param id
     * @param multipartFile
     * @param fileType
     * @return
     * @throws Exception
     */
    BizAttachment transferFile(int id, MultipartFile multipartFile, int fileType) throws Exception;

    /**
     * 文件上传
     * @param id
     * @param multipartFile
     * @param fileBizType
     * @param tableName
     * @return
     * @throws Exception
     */
    BizAttachment uploadFile(int id, MultipartFile multipartFile, int fileBizType, String tableName) throws Exception;


    /**
     * 保存附件信息
     * @param bizAttachment
     * @param userId
     */
    void saveAttachInfo(BizAttachment bizAttachment, Integer userId);

    /**
     * 生成文件目录
     * @return
     */
    String generateDirectory(int fileType);

    /**
     * 生成图片文件目录
     * @return
     */
    String generateDirectory(String busiBookNo);

    /**
     * 生成文件名称
     * @param fileName
     * @param fileSize
     * @return
     */
    String generateFileNewName(String fileName, long fileSize);

    /**
     * 删除文件
     * @param bizAttachment
     * @return
     */
    boolean deleteFile(BizAttachment bizAttachment) throws Exception;


    /**
     * 文件上传
     * @param multipartFile
     * @param record
     * @return
     * @throws Exception
     */
    ExpreessFileUploadRecord uploadPicFile(MultipartFile multipartFile, ExpreessFileUploadRecord record) throws Exception;

    /**
     * 文件传输到DFS
     * @param multipartFile
     * @param fileType 文件业务类型
     * @return
     */
    BizAttachment transferDfsFile(MultipartFile multipartFile, int fileType) throws Exception;

    /**
     * 文件上传到DFS
     * @param multipartFile
     * @param fileBizType
     * @param userId 上传用户Id
     * @return
     * @throws Exception
     */
    BizAttachment uploadDfsFile(MultipartFile multipartFile, int fileBizType, int userId) throws Exception;

    /**
     * 文件上传到DFS
     * @param multipartFile
     * @param fileBizType 文件业务类型
     * @return
     */
    BizAttachment uploadDfsFile(MultipartFile multipartFile, int fileBizType) throws Exception;
}
