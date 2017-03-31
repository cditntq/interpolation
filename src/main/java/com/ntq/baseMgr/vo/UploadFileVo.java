package com.ntq.baseMgr.vo;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadFileVo {
     
    /**
     * 文件
     */
    private MultipartFile fileName;
    private String name;//用于拼接文件名
/*	public MultipartFile getFileName() {
		return fileName;
	}



	public void setFileName(MultipartFile fileName) {
		this.fileName = fileName;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}*/

	@Override
    public String toString() {
        return "UploadFileVo [imgFile=" + fileName + "]";
    }
     
//    public boolean validateFile() throws ServiceException{
//        if(!ConstantUtil.fileTypeImg.contains(this.getImgFile().getContentType())){
//            throw new ServiceException("文件类型只能是jpeg、png！");
//        }
//        if(this.getImgFile().getSize() > 1000 * 100){
//            throw new ServiceException("文件最大不能超过100KB！");
//        }
//        return true;
//    }
 
}