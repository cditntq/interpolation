package com.ntq.baseMgr.service.impl;

import com.ntq.baseMgr.service.IUploadFileService;
import com.ntq.baseMgr.vo.UploadFileVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UploadFileServiceImpl implements IUploadFileService {
    protected Logger LOGGER = Logger.getLogger(UploadFileServiceImpl.class);

//    @Value("${resumPath}")
    @Value("#{configProperties['resumPath']}")
    private String resumPath;//简历存放地址

    public String uploadForm(UploadFileVo demo) throws Exception {
        return uploadFile(
                resumPath, demo.getFileName(), demo.getName());
    }


    public String uploadFile(String destinationDir, MultipartFile file, String filename) throws Exception {

        LOGGER.info("fileSize : " + file.getSize());
        //获取当前日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String str = sdf.format(new Date());

        //存储路径
        String storePath = destinationDir + "/" + str;
        try {
            SaveFileFromInputStream(file.getInputStream(), storePath, filename);//保存文件
        } catch (IOException e) {
            return null;
        }
        return storePath + "/" + filename;
    }

    /**
     * 保存文件
     *
     * @param stream
     * @param path
     * @param filename
     * @throws IOException
     */
    private void SaveFileFromInputStream(InputStream stream, String path, String filename)
            throws IOException {

        File file = new File(path);//文件路径
        if (!file.exists()) {//检查路径是否存在
            file.mkdirs();//创建新路径

        }
        FileOutputStream outputStream = new FileOutputStream(path + "/" + filename);
        int byteCount = 0;
        byte[] bytes = new byte[1024];
        while ((byteCount = stream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, byteCount);
        }
        outputStream.close();
        stream.close();
    }

}
