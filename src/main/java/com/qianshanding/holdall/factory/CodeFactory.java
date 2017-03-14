package com.qianshanding.holdall.factory;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 代码生成
 *
 * @author fish
 */
public class CodeFactory {

    private static Configuration cfg;
    public static final int DO = 1;
    public static final int BO = 2;
    public static final int TRANSFER = 3;
    public static final int MAPPER = 4;
    public static final int MAPPER_XML = 5;
    public static final String templateUrl = CodeFactory.class.getResource("/").getPath() + "template";

    public static Configuration getConfiguration() {
        if (cfg == null) {
            cfg = new Configuration();
            File file = new File(templateUrl);
            try {
                cfg.setDirectoryForTemplateLoading(file);
                cfg.setObjectWrapper(new DefaultObjectWrapper());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cfg;
    }

    /**
     * @param cfg          解析对象
     * @param templateName 模板名称
     * @param table        表对象
     * @param fileName     文件名称
     * @param filePath     生成路径
     */
    public static void dataSourceOut(Configuration cfg, String templateName, Object table,
                                     String fileName, String filePath) {

        Template temp = null;
        try {
            temp = cfg.getTemplate(templateName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Writer out = null;
        try {
            //初始化路径
            File path = new File(filePath);
            if (!path.exists()) {
                path.mkdirs();
            }

            String url = filePath + "/" + fileName;
            File file = new File(url);
            out = new FileWriter(file);
            temp.process(table, out);
            out.flush();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}