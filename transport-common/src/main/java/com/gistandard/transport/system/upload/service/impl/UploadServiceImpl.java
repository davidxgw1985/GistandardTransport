package com.gistandard.transport.system.upload.service.impl;

import com.gistandard.transport.base.define.SysState;
import com.gistandard.transport.base.entity.bean.BizAttachment;
import com.gistandard.transport.base.entity.bean.ComAccessoryRelation;
import com.gistandard.transport.base.entity.bean.ExpreessFileUploadRecord;
import com.gistandard.transport.base.entity.dao.BizAttachmentDao;
import com.gistandard.transport.base.entity.dao.ComAccessoryRelationDao;
import com.gistandard.transport.base.entity.dao.ExpreessFileUploadRecordDao;
import com.gistandard.transport.base.entity.dao.ex.ComAccessoryRelationDaoEx;
import com.gistandard.transport.system.upload.define.UploadDefine;
import com.gistandard.transport.system.upload.service.UploadService;
import com.gistandard.transport.tools.util.Base64Util;
import com.gistandard.transport.tools.util.FastDFSClient;
import com.gistandard.transport.tools.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yujie
 * @ClassName UploadServiceImpl
 * @Description
 * @Version 1.0
 * @Date 2015-10-13
 */
@Service
public class UploadServiceImpl implements UploadService {

    private static final Logger logger = LoggerFactory.getLogger(UploadServiceImpl.class);

    private static final String SEPARATOR = "/";

    @Autowired
    private BizAttachmentDao bizAttachmentDao;

    @Autowired
    private ComAccessoryRelationDao comAccessoryRelationDao;

    @Autowired
    private ComAccessoryRelationDaoEx comAccessoryRelationDaoEx;

    @Autowired
    private ExpreessFileUploadRecordDao expreessFileUploadRecordDao;

    @Value("#{spring.msAppUrl}")
    private String msAppUrl;

    @Value("#{spring.transportDfsUrl}")
    private String transportDfsUrl;

    @Value("#{spring.fastDFSUrl}")
    private String fastDFSUrl;


    @Override
    public BizAttachment uploadFile(MultipartFile multipartFile, int fileType, int userId) throws Exception {
        BizAttachment bizAttachment = transferFile(multipartFile, fileType);
        if (bizAttachment != null) {
            saveAttachInfo(bizAttachment, userId);
        }
        return bizAttachment;
    }

    @Override
    public BizAttachment uploadFile(MultipartFile multipartFile, int fileType) throws Exception {
        BizAttachment bizAttachment = transferFile(multipartFile, fileType);
        if (bizAttachment != null) {
            saveAttachInfo(bizAttachment, null);
        }
        return bizAttachment;
    }

    @Override
    public BizAttachment uploadFile(String base64FileStr, int fileType) throws Exception {
        BizAttachment bizAttachment = transferFile(base64FileStr, fileType);
        if (bizAttachment != null) {
            saveAttachInfo(bizAttachment, null);
        }
        return bizAttachment;
    }

    public BizAttachment transferFile(MultipartFile multipartFile, int fileType) throws Exception {
        BizAttachment bizAttachment = null;
        String fileOldName = multipartFile.getOriginalFilename();
        String fileNewName = generateFileNewName(fileOldName, multipartFile.getSize());
        String fileDirectory = generateDirectory(fileType);
        String filePath = fileDirectory + fileNewName;
        logger.info("{} create file path is {}", multipartFile.getName(), filePath);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String projectRootPath = request.getSession().getServletContext().getRealPath("/");
        File directoryFile = new File(projectRootPath + fileDirectory);
        File newFile = new File(projectRootPath + filePath);
        if (!newFile.exists()) {
            if (!directoryFile.exists()) {
                directoryFile.mkdirs();
            }
            multipartFile.transferTo(newFile);
            bizAttachment = new BizAttachment();
            bizAttachment.setAttachDomain(msAppUrl);
            bizAttachment.setAttachName(fileNewName);
            bizAttachment.setAttachOldName(multipartFile.getOriginalFilename());
            bizAttachment.setAttachPath(SEPARATOR + filePath);
            if (projectRootPath.endsWith(SEPARATOR)) {
                projectRootPath = projectRootPath.substring(0, projectRootPath.lastIndexOf(SEPARATOR));
            }
            bizAttachment.setProjectPath(projectRootPath);
            bizAttachment.setAttachSize(StringUtil.getObjStr(multipartFile.getSize()));
            bizAttachment.setFileType(fileType);
            bizAttachment.setAttachType(multipartFile.getContentType());
        }
        return bizAttachment;
    }

    public BizAttachment transferFile(String base64FileStr, int fileType) throws Exception {
        BizAttachment bizAttachment = null;
        String fileOldName = "portrait.jpg";
        String fileNewName = "ocr_portrait.jpg";
        String fileDirectory = generateDirectory(fileType);
        String filePath = fileDirectory + fileNewName;
        logger.info("{} create file path is {}", fileOldName, filePath);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String projectRootPath = request.getSession().getServletContext().getRealPath("/");
        File directoryFile = new File(projectRootPath + fileDirectory);
        File newFile = new File(projectRootPath + filePath);
        if (!newFile.exists()) {
            if (!directoryFile.exists()) {
                directoryFile.mkdirs();
            }
            byte[] bb = Base64Util.decode(base64FileStr);
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(newFile));
            out.write(bb);
            out.close();
            bizAttachment = new BizAttachment();
            bizAttachment.setAttachDomain(msAppUrl);
            bizAttachment.setAttachName(fileNewName);
            bizAttachment.setAttachOldName(fileOldName);
            bizAttachment.setAttachPath(SEPARATOR + filePath);
            if (projectRootPath.endsWith(SEPARATOR)) {
                projectRootPath = projectRootPath.substring(0, projectRootPath.lastIndexOf(SEPARATOR));
            }
            bizAttachment.setProjectPath(projectRootPath);
            bizAttachment.setAttachSize(StringUtil.getObjStr(bb.length));
            bizAttachment.setFileType(fileType);
            bizAttachment.setAttachType("image/jpeg");
        }
        return bizAttachment;
    }

    public void saveAttachInfo(BizAttachment bizAttachment, Integer userId) {
        if (userId != null) {
            bizAttachment.setUploadUser(userId);
        }
        bizAttachment.setAttachState(SysState.USABLE.getValue());
        bizAttachment.setUploadTime(new Date());
        bizAttachmentDao.insert(bizAttachment);
    }

    @Override
    public String generateDirectory(int fileType) {
        StringBuilder format = new StringBuilder();
        format.append(SEPARATOR).append("yyyyMM")
                .append(SEPARATOR).append("dd")
                .append(SEPARATOR).append("HH_mm_ss")
                .append(SEPARATOR);
        SimpleDateFormat sdf = new SimpleDateFormat(format.toString());
        StringBuilder builder = new StringBuilder();
        builder.append(UploadDefine.UPLOAD_ROOT_DIRECTORY)
                .append(sdf.format(new Date()))
                .append(new Date().getTime())
                .append(SEPARATOR);
        return builder.toString();
    }

    @Override
    public String generateDirectory(String busiBookNo) {
        StringBuilder format = new StringBuilder();
        format.append(SEPARATOR).append("yyyy")
                .append(SEPARATOR).append("yyyyMM")
                .append(SEPARATOR).append("yyyyMMdd")
                .append(SEPARATOR).append(busiBookNo);
        SimpleDateFormat sdf = new SimpleDateFormat(format.toString());
        StringBuilder builder = new StringBuilder();
        builder.append(UploadDefine.UPLOAD_ROOT_DIRECTORY)
                .append(sdf.format(new Date()))
                .append(SEPARATOR);
        return builder.toString();
    }

    @Override
    public String generateFileNewName(String fileName, long fileSize) {
        String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        StringBuilder builder = new StringBuilder();
        builder.append(new Date().getTime())
                .append("_")
                .append(fileSize)
                .append(fileSuffix.indexOf(".") == 0 ? fileSuffix : ("." + fileSuffix));
        return builder.toString();
    }

    @Override
    public BizAttachment transferFile(int id, MultipartFile multipartFile, int fileType) throws Exception {
        BizAttachment bizAttachment = null;
        String fileOldName = multipartFile.getOriginalFilename();
        String fileNewName = generateFileNewName(fileOldName, multipartFile.getSize());
        String fileDirectory = generateDirectory(fileType);
        String filePath = fileDirectory + fileNewName;
        logger.info("{} create file path is {}", multipartFile.getName(), filePath);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String projectRootPath = request.getSession().getServletContext().getRealPath("/");
        File directoryFile = new File(projectRootPath + fileDirectory);
        File newFile = new File(projectRootPath + filePath);
        if (!newFile.exists()) {
            if (!directoryFile.exists()) {
                directoryFile.mkdirs();
            }
            multipartFile.transferTo(newFile);
            bizAttachment = new BizAttachment();
            bizAttachment.setAttachDomain(msAppUrl);
            bizAttachment.setAttachName(fileNewName);
            bizAttachment.setAttachOldName(multipartFile.getOriginalFilename());
            bizAttachment.setAttachPath(SEPARATOR + filePath);
            bizAttachment.setProjectPath(projectRootPath);
            bizAttachment.setAttachRelId(id);
            bizAttachment.setAttachSize(StringUtil.getObjStr(multipartFile.getSize()));
            bizAttachment.setFileType(fileType);
            bizAttachment.setAttachType(multipartFile.getContentType());
        }
        return bizAttachment;
    }

    @Override
    public BizAttachment uploadFile(int id, MultipartFile multipartFile, int fileType, String tableName) throws Exception {
        BizAttachment bizAttachment = transferFile(id, multipartFile, fileType);
        if (bizAttachment != null) {
            saveAttachInfo(bizAttachment, null);

            ComAccessoryRelation comAccessoryRelation = new ComAccessoryRelation();
            comAccessoryRelation.setRelaId(id);
            comAccessoryRelation.setAttachId(bizAttachment.getAttachId());
            comAccessoryRelation.settTab(tableName);
            comAccessoryRelationDao.insert(comAccessoryRelation);
        }

        return bizAttachment;
    }

    @Override
    public boolean deleteFile(BizAttachment bizAttachment) throws Exception {
        logger.info("file path and name is {}", bizAttachment.getAttachPath());
        //先删除数据库记录
        if (bizAttachmentDao.deleteByPrimaryKey(bizAttachment.getAttachId()) > 0) {
            comAccessoryRelationDaoEx.deleteAttachID(bizAttachment.getAttachId());
            File newFile = new File(bizAttachment.getProjectPath() + bizAttachment.getAttachPath());
            if (newFile.exists()) {
                //再删除文件
                logger.info("delete file result is {}", newFile.delete());
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ExpreessFileUploadRecord uploadPicFile(MultipartFile multipartFile, ExpreessFileUploadRecord record) throws Exception {

        String fileName = record.getBusiBookNo() + "_" + record.getUploadPeople() + "_" + new Date().getTime();
        String oldFileName = multipartFile.getOriginalFilename();
        if (!StringUtils.isEmpty(oldFileName)) {
            String fileType = oldFileName.substring(oldFileName.lastIndexOf("."), oldFileName.length()).toLowerCase();
            fileName += fileType;
        }
        String fileDirectory = generateDirectory(record.getBusiBookNo());
        String filePath = fileDirectory + fileName;
        logger.info("{} create file path is {}", fileName, filePath);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String projectRootPath = request.getSession().getServletContext().getRealPath("/");
        File directoryFile = new File(projectRootPath + fileDirectory);
        File newFile = new File(projectRootPath + filePath);
//        File directoryFile = new File(fileDirectory);
//        File newFile = new File(filePath);
        if (!newFile.exists()) {
            if (!directoryFile.exists()) {
                directoryFile.mkdirs();
            }
            multipartFile.transferTo(newFile);
        }
        record.setFilePath(SEPARATOR + filePath);
        expreessFileUploadRecordDao.insert(record);
        return record;
    }

    @Override
    public BizAttachment transferDfsFile(MultipartFile multipartFile, int fileType) throws Exception {
        BizAttachment bizAttachment = null;
        FastDFSClient fastDFSClient = new FastDFSClient(transportDfsUrl);
        String filePath = fastDFSClient.uploadFile(multipartFile);
        logger.info("{} create picture file path is {}" , multipartFile.getName() , filePath);
        bizAttachment = new BizAttachment();
        String fileNewName =filePath.substring(filePath.lastIndexOf("/")+1);
        bizAttachment.setAttachDomain(fastDFSUrl);
        bizAttachment.setAttachName(fileNewName);
        bizAttachment.setAttachOldName(multipartFile.getOriginalFilename());
        bizAttachment.setAttachPath(SEPARATOR + filePath);
        bizAttachment.setAttachSize(StringUtil.getObjStr(multipartFile.getSize()));
        bizAttachment.setFileType(fileType);
        bizAttachment.setAttachType(multipartFile.getContentType());
        return bizAttachment;
    }

    @Override
    public BizAttachment uploadDfsFile(MultipartFile multipartFile, int fileType, int userId) throws Exception {
        BizAttachment bizAttachment = transferDfsFile(multipartFile, fileType);
        if (bizAttachment != null) {
            saveAttachInfo(bizAttachment, userId);
        }
        return bizAttachment;
    }

    @Override
    public BizAttachment uploadDfsFile(MultipartFile multipartFile, int fileType) throws Exception {
        BizAttachment bizAttachment = transferDfsFile(multipartFile, fileType);

        //附件是身份证正,反,手持不传附件表
        if (bizAttachment != null&&fileType!=1&&fileType!=2&&fileType!=17) {
            saveAttachInfo(bizAttachment, null);
        }
        return bizAttachment;
    }
}

