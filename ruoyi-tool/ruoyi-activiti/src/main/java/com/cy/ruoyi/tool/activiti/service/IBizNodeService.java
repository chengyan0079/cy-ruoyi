package com.cy.ruoyi.tool.activiti.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.ruoyi.tool.activiti.VO.ProcessNodeVo;
import com.cy.ruoyi.tool.activiti.entity.BizNode;

import java.util.List;
import java.util.Set;

/**
 * 节点Service接口
 */
public interface IBizNodeService extends IService<BizNode>
{
    /**
     * 查询节点
     * 
     * @param id 节点ID
     * @return 节点
     */
//    BizNode selectBizNodeById(Long id);

    /**
     * 查询节点列表
     * 
     * @param bizNode 节点
     * @return 节点集合
     */
//    List<BizNode> selectBizNodeList(BizNode bizNode);

    /**
     * 新增节点
     * 
     * @param bizNode 节点
     * @return 结果
     */
//    int insertBizNode(BizNode bizNode);

    /**
     * 设置节点视图
     * @param node
     */
    ProcessNodeVo setAuditors(ProcessNodeVo node);

    /**
     * 更新节点配置
     * @param node
     */
    int updateBizNode(ProcessNodeVo node);

    /**
     * 根据节点id获取所有审核人的编号
     * @param nodeId 流程节点编号
     * @param userId 当前用户编号
     */
    Set<String> getAuditors(String nodeId, long userId);
}
