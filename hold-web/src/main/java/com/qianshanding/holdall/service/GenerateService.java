package com.qianshanding.holdall.service;

import com.qianshanding.holdall.entity.CodeBO;
import com.qianshanding.holdall.entity.ProjectBO;

/**
 * @author fish
 */
public interface GenerateService {
    /**
     * 自动生成代码，返回zip路径
     *
     * @param generateBO 自动生成代码信息
     * @param basePath   存放路径
     * @return
     * @throws Exception
     */
    String generateCode(CodeBO generateBO, String basePath) throws Exception;

    /**
     * 自动生成工程
     *
     * @param projectBO 自动生成代码信息
     * @param basePath   存放路径
     * @return
     * @throws Exception
     */
    String generateProject(ProjectBO projectBO, String basePath) throws Exception;
}
