package com.qianshanding.holdall.service.impl;

import com.qianshanding.holdall.entity.GenerateBO;
import com.qianshanding.holdall.service.GenerateService;
import com.qianshanding.holdall.service.support.AutoGenerateCode;
import org.springframework.stereotype.Service;

/**
 * 自动生成代码
 *
 * @author fish
 */
@Service("generateService")
public class GenerateServiceImpl implements GenerateService {

    @Override
    public String generateCode(GenerateBO generateBO) throws Exception {
        AutoGenerateCode gc = new AutoGenerateCode();
        return gc.generate(generateBO);
    }
}
