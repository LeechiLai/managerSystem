package com.cndc.business.mapper;

import java.util.List;
import com.cndc.business.domain.Project;

/**
 * 项目信息Mapper接口
 *
 * @author laizhiying
 * @date 2024-12-03
 */
public interface ProjectMapper
{
    // ...其他已有方法...

    /**
     * 查询所有有效项目（未删除的项目）
     * @return 项目列表
     */
    List<Project> selectAllProjects();
    /**
     * 查询项目信息
     *
     * @param id 项目信息主键
     * @return 项目信息
     */
    public Project selectProjectById(Long id);

    /**
     * 查询项目信息列表
     *
     * @param project 项目信息
     * @return 项目信息集合
     */
    public List<Project> selectProjectList(Project project);

    /**
     * 新增项目信息
     *
     * @param project 项目信息
     * @return 结果
     */
    public int insertProject(Project project);

    /**
     * 修改项目信息
     *
     * @param project 项目信息
     * @return 结果
     */
    public int updateProject(Project project);

    /**
     * 删除项目信息
     *
     * @param id 项目信息主键
     * @return 结果
     */
    public int deleteProjectById(Long id);

    /**
     * 批量删除项目信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectByIds(Long[] ids);
}
