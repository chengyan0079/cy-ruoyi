package com.cy.ruoyi.tool.activiti.cover;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.image.ProcessDiagramGenerator;

import java.awt.*;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

/**
 * <p>Title: 自定义流程图画笔</p>
 * <p>Description:重写ProcessDiagramGenerator接口中的generateDiagram方法，增加color参数</p>
 */
public interface ICustomProcessDiagramGenerator extends ProcessDiagramGenerator
{
    InputStream generateDiagram(BpmnModel bpmnModel, String imageType, List<String> highLightedActivities,
                                List<String> highLightedFlows, String activityFontName, String labelFontName, String annotationFontName,
                                ClassLoader customClassLoader, double scaleFactor, Color[] colors, Set<String> currIds);
}