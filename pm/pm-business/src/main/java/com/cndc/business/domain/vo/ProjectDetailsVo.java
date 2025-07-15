package com.cndc.business.domain.vo;

import com.cndc.business.domain.Project;
import com.cndc.business.domain.ProjectDetails;
import com.cndc.business.domain.ProjectSubitems;
import lombok.Data;

import java.util.List;

/**
 * @Author laizhiying
 * @Create 2024-12-18 15:29
 */
@Data
public class ProjectDetailsVo extends ProjectDetails {

    /** 项目信息 */
    private Project project;

    /** 子项信息集合 */
    private List<ProjectSubitems> projectSubitemsList ;

}
