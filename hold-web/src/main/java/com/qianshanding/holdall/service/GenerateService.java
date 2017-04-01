package com.qianshanding.holdall.service;

import com.qianshanding.holdall.entity.GenerateBO;

/**
 * @author fish
 */
public interface GenerateService {
    /**
     * 自动生成代码，返回zip路径
     *
     * @throws Exception
     */
    String generateCode(GenerateBO generateBO) throws Exception;
}
