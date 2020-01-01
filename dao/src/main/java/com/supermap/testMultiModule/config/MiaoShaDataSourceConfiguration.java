package com.supermap.testMultiModule.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * 多数据源实现类，配置miaosha数据库数据源的配置类
 * @author wenyutun
 */
@Configuration
@MapperScan(basePackages = "com.supermap.testMultiModule.dao.miaosha",sqlSessionTemplateRef  = "sqlSessionTemplate1")
public class MiaoShaDataSourceConfiguration {

    /**
     * mybatis-config.xml配置文件路径
     */
    @Value("${mybatis_config_miaosha_file}")
    private String myBatisConfigFilePath;

    /**
     * mybatis mapper文件路径
     */
    @Value("${mapper_miaosha_Path}")
    private String miaoshaMapperPath;

    /**
     * 实体类所在路劲
     */
    @Value("${pojo_miaosha_package}")
    private String miaoshaPojoPackage;

    /**
     * miaosha数据库的数据源配置类
     * @return
     */
    @Primary
    @Bean(name = "dataSource1")
    @ConfigurationProperties(prefix = "spring.datasource.test1")
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    /**
     * miaosha数据库的sqlSessionFactory的配置类
     * @param dataSource1
     * @return
     * @throws IOException
     */
    @Primary
    @Bean(name = "sqlSessionFactory1")
    public SqlSessionFactory sqlSessionFactory1(@Qualifier("dataSource1") DataSource dataSource1) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(myBatisConfigFilePath));
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String packageSearchPath = PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + miaoshaMapperPath;
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(packageSearchPath));
        sqlSessionFactoryBean.setDataSource(dataSource1);
        sqlSessionFactoryBean.setTypeAliasesPackage(miaoshaPojoPackage);
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * miaosha数据库的transactionManager事务管理配置类
     * @param dataSource1
     * @return
     */
    @Primary
    @Bean(name = "transactionManager1")
    public DataSourceTransactionManager transactionManager1(@Qualifier("dataSource1") DataSource dataSource1) {
        return new DataSourceTransactionManager(dataSource1);
    }

    /**
     * sqlSessionTemplate配置
     * @param sqlSessionFactory1
     * @return
     * @throws Exception
     */
    @Primary
    @Bean(name = "sqlSessionTemplate1")
    public SqlSessionTemplate sqlSessionTemplate1(@Qualifier("sqlSessionFactory1") SqlSessionFactory sqlSessionFactory1) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory1);
    }

}
