package com.qianshanding.holdall.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zhengyu on 2017/4/1.
 */
@Data
public class ProjectBO implements Serializable {
    private String groupId;
    private String artifactId;
    private String version;
    private String archetypeGroupId;
    private String archetypeArtifactId;
    private String archetypeVersion;
    private String type;
}
