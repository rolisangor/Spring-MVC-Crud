package crud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"crud"})
@EnableTransactionManagement
public class JpaConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/my_db");
        dataSource.setUsername("root");
        dataSource.setPassword("2579");

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean factoryBean(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");

        LocalContainerEntityManagerFactoryBean localSessionFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setPackagesToScan("crud");
        localSessionFactoryBean.setJpaProperties(properties);
        localSessionFactoryBean.setJpaVendorAdapter(vendorAdapter());

        return localSessionFactoryBean;
    }

    @Bean
    public JpaVendorAdapter vendorAdapter() {
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        return vendorAdapter;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        return new JpaTransactionManager(factoryBean().getObject());
    }
}
