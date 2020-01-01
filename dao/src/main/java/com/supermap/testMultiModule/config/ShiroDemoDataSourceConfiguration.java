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
 * 多数据源实现类，配置shiro_demo数据库数据源的配置类
 *
 * @author wenyutun
 */
@Configuration
@MapperScan(basePackages = "com.supermap.testMultiModule.dao.shirodemo", sqlSessionTemplateRef = "sqlSessionTemplate1")
public class ShiroDemoDataSourceConfiguration {

    /**
     * mybatis-config.xml配置文件路径
     */
    @Value("${mybatis_config_shirodemo_file}")
    private String myBatisConfigFilePath;

    /**
     * mybatis mapper文件路径
     */
    @Value("${mapper_shirodemo_Path}")
    private String shiroDemoMapperPath;

    /**
     * 实体类所在路劲
     */
    @Value("${pojo_shirodemo_package}")
    private String shiroDemoPojoPackage;

    /**
     * shirodemo数据库的数据源配置类
     *
     * @return
     */
    @Bean(name = "dataSource3")
    @ConfigurationProperties(prefix = "spring.datasource.test3")
    public DataSource dataSource3() {
        return DataSourceBuilder.create().build();
    }

    /**
     * shirodemo数据库的sqlSessionFactory的配置类
     *
     * @param dataSource3
     * @return
     * @throws IOException
     */
    @Bean(name = "sqlSessionFactory3")
    public SqlSessionFactory sqlSessionFactory1(@Qualifier("dataSource3") DataSource dataSource3) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(myBatisConfigFilePath));
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String packageSearchPath = PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + shiroDemoMapperPath;
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(packageSearchPath));
        sqlSessionFactoryBean.setDataSource(dataSource3);
        sqlSessionFactoryBean.setTypeAliasesPackage(shiroDemoPojoPackage);
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * shirodemo数据库的transactionManager事务管理配置类
     *
     * @param dataSource3
     * @return
     */
    @Bean(name = "transactionManager3")
    public DataSourceTransactionManager transactionManager1(@Qualifier("dataSource3") DataSource dataSource3) {
        return new DataSourceTransactionManager(dataSource3);
    }

    /**
     * sqlSessionTemplate配置
     *
     * @param sqlSessionFactory3
     * @return
     * @throws Exception
     */
    @Bean(name = "sqlSessionTemplate3")
    public SqlSessionTemplate sqlSessionTemplate1(@Qualifier("sqlSessionFactory3") SqlSessionFactory sqlSessionFactory3) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory3);
    }

}
