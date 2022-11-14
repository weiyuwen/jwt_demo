package com.chixing.jwt_demo;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class Test {
    @org.junit.jupiter.api.Test
    public void aaa(){
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/mydb", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("baomidou") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("E:\\MybatisTest"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.chixing") // 设置父包名
                            .moduleName("") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "E:\\MybatisTest")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("customer") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
