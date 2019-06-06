package org.mybatis.generator.internal;


import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * @Description: swagger2插件使用
 * @Author: 屈艳锋
 * @Date: 2019/6/5 10:30
 */

@SuppressWarnings("unused")
public class Swagger2Plugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        //实体类的import
        topLevelClass.addImportedType("io.swagger.annotations.ApiModel");
        topLevelClass.addImportedType("io.swagger.annotations.ApiModelProperty");

        //实体类的注解
        topLevelClass.addAnnotation("@ApiModel");

        return true;
    }
}
