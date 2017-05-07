package com.ntq.baseMgr.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>@description:用于登录后创建简历</p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.vo
 * @className:
 * @author: shuangyang
 * @date: 17-5-7 下午2:05
 */
@Getter
@Setter
public class JobSeekerResumeWithFile {

    /*文件*/
    private MultipartFile file;
    /*职位编号*/
    private Long positionNo;

}
