package us.webdb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;


@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(value = "us.webdb")
public class HibernateConfig {

   private Environment env;

   public HibernateConfig(Environment env) {
      this.env = env;
   }

   Properties getHibernateProperties() {
      Properties properties = new Properties();
      try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream("hiber.properties")) {
         properties.load(inputStream);
         return properties;
      } catch(IOException e){
         throw new IllegalArgumentException("no file has been found");
      }
   }

   @Bean
   public DataSource getDataSource() {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("db.driver")));
      dataSource.setUrl(env.getProperty("db.url"));
      dataSource.setUsername(env.getProperty("db.username"));
      dataSource.setPassword(env.getProperty("db.password"));
      return dataSource;
   }

   @Bean
   public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
      LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

      factoryBean.setDataSource(getDataSource());
      factoryBean.setPackagesToScan("us.webdb.model");

      factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
      factoryBean.setJpaProperties(getHibernateProperties());

      return factoryBean;
   }

   @Bean
   public PlatformTransactionManager getTransactionManager() {
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(getEntityManagerFactory().getObject());
      return transactionManager;
   }
}
