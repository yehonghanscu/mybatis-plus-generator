package top.yehonghan.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author yehonghan
 * @2022/6/19 11:14
 */
public class Generator {
    //Project package
    private static String projectPackage;
    //author
    private static String author;
    //Database url
    private static String url;
    //Database username
    private static String username;
    //Database password
    private static String password;
    //Database driver class
    private static String driverClass;
    //tableName
    private static String tableName;
    //tablePrefix
    private static String tablePrefix;

    /**
     * Init database information
     */
    static {
        Properties properties = new Properties();
        try (
                InputStream mybatisPlusConfig= Generator.class.getResourceAsStream("/mybatis-plus.properties")
                ){
            properties.load(mybatisPlusConfig);
            author = properties.getProperty("author");
            projectPackage = properties.getProperty("generator.parent.package");
            driverClass = properties.getProperty("generator.jdbc.driverClass");
            url = properties.getProperty("generator.jdbc.url");
            username = properties.getProperty("generator.jdbc.username");
            password = properties.getProperty("generator.jdbc.password");
            tableName = properties.getProperty("generator.tableName");
            tablePrefix = properties.getProperty("generator.tablePrefix");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //获取生成代码的对象
        AutoGenerator autoGenerator=new AutoGenerator();
        //设置数据库相关配置
        DataSourceConfig dataSource=new DataSourceConfig();
        dataSource.setDriverName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        autoGenerator.setDataSource(dataSource);

        //设置全局配置
        GlobalConfig globalConfig=new GlobalConfig();
        globalConfig.setOutputDir(System.getProperty("user.dir")+"/src/main/java");
        globalConfig.setOpen(false);
        globalConfig.setAuthor(author);
        globalConfig.setFileOverride(true);
        //设置数据层接口名%s为占位符，代指模块名称
        globalConfig.setMapperName("%sMapper");
        globalConfig.setServiceName("%sService");
        globalConfig.setServiceImplName("%sServiceImpl");
        globalConfig.setIdType(IdType.ASSIGN_ID);//设置ID生成策略
        autoGenerator.setGlobalConfig(globalConfig);

        //包名相关配置
        PackageConfig packageConfig=new PackageConfig();
        packageConfig.setParent(projectPackage);
        packageConfig.setEntity("entity");
        packageConfig.setMapper("mapper");
        packageConfig.setController("controller");
        autoGenerator.setPackageInfo(packageConfig);

        //策略设置
        StrategyConfig strategyConfig=new StrategyConfig();
        strategyConfig.setInclude(tableName);//设置参与生成的表名
        strategyConfig.setTablePrefix(tablePrefix);//去掉前缀
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);//表名对应实体类生成策略
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);//列名对应成员变量生成策略
        strategyConfig.setRestControllerStyle(true);//是否使用rest风格
        strategyConfig.setVersionFieldName("version");//设置乐观锁字段名
        strategyConfig.setLogicDeleteFieldName("deleted");//设置逻辑删除字段
        strategyConfig.setEntityLombokModel(true);//设置是否使用lombok
        autoGenerator.setStrategy(strategyConfig);

        //执行生成操作
        autoGenerator.execute();
    }
}
