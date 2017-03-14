package com.qianshanding.holdall.controller;

import com.qianshanding.holdall.entity.GenerateBO;
import com.qianshanding.holdall.service.GenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author fish
 */
@Controller
public class GenerateController {

    @Autowired
    private GenerateService generateService;

    @RequestMapping(value = "/generate_code", method = RequestMethod.GET)
    public ModelAndView autoGenerate(GenerateBO generateBO) {
        String zipFilePath = null;
        try {
            generateBO = new GenerateBO();
            generateBO.setUrl("jdbc:mysql://127.0.0.1:3306/springbootdb?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true");
            generateBO.setDriverClassName("com.mysql.jdbc.Driver");
            generateBO.setUserName("root");
            generateBO.setPassword("123123123");
            generateBO.setCreateDal(true);
            generateBO.setDoPackage("com.qianshanding.test.dataobject");
            generateBO.setDaoPackage("com.qianshanding.test.dal");
            generateBO.setMybatisXmlPath("sqlmap");
            generateBO.setCreateBo(true);
            generateBO.setBoPackage("com.qianshanding.test.entity");
            generateBO.setCreateTransfer(true);
            generateBO.setTransferPackage("com.qianshanding.test.transfer");
            zipFilePath = generateService.generateCode(generateBO);
        } catch (Exception e) {
//            return "error";
        }
        return new ModelAndView(new RedirectView("/download_zip?zipFilePath=" + zipFilePath));
//        return "success";
    }

    /**
     * 下载zip文件
     *
     * @param response
     * @param zipFilePath
     * @throws IOException
     */
    @RequestMapping(value = "/download_zip", method = RequestMethod.GET)
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