package com.ntq.baseMgr.service;

import com.ntq.baseMgr.vo.UploadFileVo;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {
	 /**
     * 上传文件到指定路径
     * destinationDir 目标路径
     * 2014年6月10日
     */
    public String uploadFile(String destinationDir, MultipartFile file, String filename) throws Exception;

	/**
	 * 返回附件的存储路径
	 * @param demo
	 * @return
	 * @throws Exception
	 */
	public String uploadForm(UploadFileVo demo) throws Exception;
	
	
	
}
