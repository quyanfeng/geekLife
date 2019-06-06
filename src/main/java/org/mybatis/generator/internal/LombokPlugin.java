package org.mybatis.generator.internal;


import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * @Description: lombok插件使用
 * @Author: 屈艳锋
 * @Date: 2019/6/5 10:30
 */

@SuppressWarnings("unused")
public class LombokPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        //实体类的import
        topLevelClass.addImportedType("lombok.Data");

        //实体类的注解
        topLevelClass.addAnnotation("@Data");

        return true;
    }

    @Override
    public boolean clientGenerated(Interface anInterface, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        //Mapper接口,添加@Repository注解
        anInterface.addImportedType(new FullyQualifiedJavaType("org.springframework.stereotype.Repository"));
        anInterface.addAnnotation("@Repository");

        //Mapper接口,继承MySqlMapper
        anInterface.addImportedType(new FullyQualifiedJavaType("tk.mybatis.mapper.common.MySqlMapper"));
        anInterface.addSuperInterface(new FullyQualifiedJavaType("MySqlMapper<"+introspectedTable.getBaseRecordType()+">"));
        return true;
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        //不生成getter
        return false;
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        //不生成setter
        return false;
    }

}
