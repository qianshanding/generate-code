package com.qianshanding.holdall.controller;

import com.qianshanding.holdall.config.HoldConfig;
import com.qianshanding.holdall.entity.CodeBO;
import com.qianshanding.holdall.entity.ProjectBO;
import com.qianshanding.holdall.service.GenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author fish
 */
@Controller
public class GenerateController {

    @Autowired
    private GenerateService generateService;

    @Autowired
    HoldConfig holdConfig;

    @RequestMapping(value = "/generate_code")
    @ResponseBody
    public String generateCode(CodeBO generateBO) {
        String zipFilePath = null;
        try {
            generateBO.setUrl("jdbc:mysql://" + generateBO.getDatabaseIp() + ":" + generateBO.getPort()
                    + "/" + generateBO.getDatabase()
                    + "?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true");
            zipFilePath = generateService.generateCode(generateBO, holdConfig.getBasePath());
        } catch (Exception e) {
            return "error";
        }
        return zipFilePath;
    }

    @RequestMapping(value = "/generate_project")
    @ResponseBody
    public String generateProject(ProjectBO projectBO) {
        try {
            projectBO.setGroupId("com.qianshanding.test");
            projectBO.setArtifactId("test");
            projectBO.setVersion("1.0.0");

            projectBO.setArchetypeGroupId("com.qsd.maven.archetypes");
            projectBO.setArchetypeArtifactId("maven-archetype-soa");
            projectBO.setVersion("1.0.0");
            generateService.generateProject(projectBO, holdConfig.getBasePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 下载zip文件
     *
     * @param response
     * @param zipFilePath
     * @throws IOException
     */
    @RequestMapping(value = "/download_zip")
    public void testDownload(HttpServletResponse response, String zipFilePath) throws Exception {
        try {
            File file = new File(zipFilePath);
            if (file.exists()) {
                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;filename=source.zip");
                OutputStream os = response.getOutputStream();
                BufferedOutputStream bos = new BufferedOutputStream(os);
                InputStream is = null;
                is = new FileInputStream(zipFilePath);
                BufferedInputStream bis = new BufferedInputStream(is);
                int length = 0;
                byte[] temp = new byte[1 * 1024 * 10];
                while ((length = bis.read(temp)) != -1) {
                    bos.write(temp, 0, length);
                }
                bos.flush();
                bis.close();
                bos.close();
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}