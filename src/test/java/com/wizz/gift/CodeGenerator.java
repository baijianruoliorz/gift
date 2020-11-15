package com.wizz.gift;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

/**
 * @author
 * @since 2018/12/13
 */
//代码生成器放到test里面，GAV和main类的一样就行。。。，就是com,,那里
public class CodeGenerator {
//直接run 就完事了，第一次会慢点的
    @Test
    public void run() {

        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        //这里projectPath换成绝对路径更好,这个地方就是输出目录,outPutDir... 绝对路径D:\\...
        gc.setOutputDir("D:/gift" + "/src/main/java");
        
        gc.setAuthor("liqiqiorz");
        gc.setOpen(false); //生成后是否打开资源管理器，就是生成的代码是否自动打开
        gc.setFileOverride(true); //重新生成时文件是否覆盖，第一次代码生成，你再生成第一次会被覆盖
        gc.setServiceName("%sService");	//去掉Service接口的首字母I ，比如Iservice
        gc.setIdType(IdType.ID_WORKER); //主键策略,snowFlake算法生成的19位是long,如果是string类型，就是ID_WORKER_STR char就是string..
        gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型,没啥好说的，用就完事了
        gc.setSwagger2(true);//开启Swagger2模式，API啊啊啊

        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        //要加个时区的。。数据库配置嘛
        dsc.setUrl("jdbc:mysql://112.126.78.122:3306/gift?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT");
        
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("admin");
        //数据库类型
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
        //三个包名(有人习惯两个，就是G
        pc.setParent("com.wizz");
        //A
        pc.setModuleName("gift"); //模块名
        //生成包的名字
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5、策略配置，逆向工程，根据表构造代码
        StrategyConfig strategy = new StrategyConfig();
        //表名称如果有多张表请用：strategy.setInclude("edu_teacher","",""....,"")这样
        strategy.setInclude("product");
        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
        //下划线策略，表中_换成实体类中的大写字母
        strategy.setTablePrefix(pc.getModuleName() + "_"); //生成实体时去掉表前缀

        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        //加data注解
        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作

        strategy.setRestControllerStyle(true); //restful api风格控制器
        strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符

        mpg.setStrategy(strategy);


        // 6、执行
        mpg.execute();
    }
}
