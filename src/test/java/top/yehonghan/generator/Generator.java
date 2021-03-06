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
        //???????????????????????????
        AutoGenerator autoGenerator=new AutoGenerator();
        //???????????????????????????
        DataSourceConfig dataSource=new DataSourceConfig();
        dataSource.setDriverName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        autoGenerator.setDataSource(dataSource);

        //??????????????????
        GlobalConfig globalConfig=new GlobalConfig();
        globalConfig.setOutputDir(System.getProperty("user.dir")+"/src/main/java");
        globalConfig.setOpen(false);
        globalConfig.setAuthor(author);
        globalConfig.setFileOverride(true);
        //????????????????????????%s?????????????????????????????????
        globalConfig.setMapperName("%sMapper");
        globalConfig.setServiceName("%sService");
        globalConfig.setServiceImplName("%sServiceImpl");
        globalConfig.setIdType(IdType.ASSIGN_ID);//??????ID????????????
        autoGenerator.setGlobalConfig(globalConfig);

        //??????????????????
        PackageConfig packageConfig=new PackageConfig();
        packageConfig.setParent(projectPackage);
        packageConfig.setEntity("entity");
        packageConfig.setMapper("mapper");
        packageConfig.setController("controller");
        autoGenerator.setPackageInfo(packageConfig);

        //????????????
        StrategyConfig strategyConfig=new StrategyConfig();
        strategyConfig.setInclude(tableName);//???????????????????????????
        strategyConfig.setTablePrefix(tablePrefix);//????????????
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);//?????????????????????????????????
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);//????????????????????????????????????
        strategyConfig.setRestControllerStyle(true);//????????????rest??????
        strategyConfig.setVersionFieldName("version");//????????????????????????
        strategyConfig.setLogicDeleteFieldName("deleted");//????????????????????????
        strategyConfig.setEntityLombokModel(true);//??????????????????lombok
        autoGenerator.setStrategy(strategyConfig);

        //??????????????????
        autoGenerator.execute();
    }
}
