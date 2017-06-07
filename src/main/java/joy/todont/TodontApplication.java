package joy.todont;

import io.springlets.boot.autoconfigure.data.jpa.SpringletsDataJpaAuthenticationAuditorAutoConfiguration;
import io.springlets.boot.autoconfigure.security.jpa.SpringletsSecurityJpaAuthenticationAutoConfiguration;
import io.springlets.boot.autoconfigure.web.SpringletsWebAuthenticationAutoConfiguration;
import io.springlets.boot.autoconfigure.web.mvc.SpringletsWebMvcAutoConfiguration;
import io.springlets.data.domain.AuthenticationAuditorAware;
import io.springlets.data.jpa.config.EnableSpringletsDataJpaAuthenticationAuditorSupport;
import io.springlets.data.web.config.EnableSpringletsDataWebSupport;
import io.springlets.format.EntityFormatAnnotationFormatterFactory;
import io.springlets.format.EnumToMessageConverter;
import io.springlets.format.config.EnableSpringletsEntityFormatWebSupport;
import io.springlets.format.config.SpringletsEntityFormatWebConfiguration;
import io.springlets.security.jpa.domain.UserLogin;
import io.springlets.security.jpa.service.api.UserLoginService;
import io.springlets.security.web.config.EnableSpringletsWebSecurity;
import io.springlets.web.config.EnableSpringletsWebJacksonSupport;
import org.postgresql.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import io.springlets.security.config.EnableSpringletsSecurityAuthentication;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * = TodontApplication
 * <p>
 * TODO
 */
@SpringBootApplication
@Configuration
//@EnableWebSecurity//bunu yazmazsak db den almaz
//@EnableAutoConfiguration
/*@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
@EnableSpringDataWebSupport
@EnableSpringletsDataWebSupport
@EnableSpringletsWebJacksonSupport
@EnableSpringletsEntityFormatWebSupport
@ConditionalOnWebApplication
@ConditionalOnClass({AuthenticationAuditorAware.class, EnableJpaAuditing.class, AuditorAware.class,
        SecurityContext.class,EntityFormatAnnotationFormatterFactory.class, WebMvcConfigurerAdapter.class})
@AutoConfigureAfter({WebMvcAutoConfiguration.class,SpringDataWebAutoConfiguration.class})
@EnableSpringletsDataJpaAuthenticationAuditorSupport
@ConditionalOnMissingBean(AuditorAware.class)*/
/*
@EnableSpringletsDataJpaAuthenticationAuditorSupport
@EnableSpringletsSecurityAuthentication 
@EnableSpringletsWebSecurity
*/

public class TodontApplication {

    /**
     * TODO
     *
     * @param args
     */
    @Bean
    public SpringletsWebAuthenticationAutoConfiguration springletsWebAuthenticationAutoConfiguration() {

        return new SpringletsWebAuthenticationAutoConfiguration();
    }

    @Bean
    public SpringletsWebMvcAutoConfiguration springletsWebMvcAutoConfiguration() {
        return new SpringletsWebMvcAutoConfiguration();
    }

    @Bean
    public SpringletsSecurityJpaAuthenticationAutoConfiguration springletsSecurityJpaAuthenticationAutoConfiguration() {
        return new SpringletsSecurityJpaAuthenticationAutoConfiguration();
    }

    @Bean
    public SpringletsDataJpaAuthenticationAuditorAutoConfiguration springletsDataJpaAuthenticationAuditorAutoConfiguration() {
        return new SpringletsDataJpaAuthenticationAuditorAutoConfiguration();
    }

    @Autowired
    @Lazy
    UserLoginService userLoginService;
    /*
     *Creating demo users.
     *
     */
    @Autowired
    @PostConstruct
    public void init() {
        UserLogin userLogin = new UserLogin();
        userLogin.setUsername("firat");
        userLogin.setPassword(new BCryptPasswordEncoder().encode("firat"));
        userLogin.setLocked(false);
        userLogin.setFromDate(Calendar.getInstance());

        UserLogin userLogin2 = new UserLogin();
        userLogin2.setUsername("firat2");
        userLogin2.setPassword(new BCryptPasswordEncoder().encode("firat2"));
        userLogin2.setLocked(false);
        userLogin2.setFromDate(Calendar.getInstance());

        try {
            userLoginService.save(userLogin);
            userLoginService.save(userLogin2);
        } catch (Exception e) {
            //e.printStackTrace();
            Logger.getGlobal().log(Level.INFO, "Demo users already registered or you have trouble.");
        }

    }

    /*    @Bean(name = "dataSource")
        public DataSource dataSource() {
            SimpleDriverDataSource driverManagerDataSource = new SimpleDriverDataSource();
            //driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
            driverManagerDataSource.setDriverClass(Driver.class);
            driverManagerDataSource.setUrl("jdbc:mysql://ec2-54-247-189-64.eu-west-1.compute.amazonaws.com:3306/db6m0mcog5oh6k?sslmode=require");
            driverManagerDataSource.setUsername("fouarwrolocaov");
            driverManagerDataSource.setPassword("94238893f70392fa4bc30ec90c6d2b44b4a052661f146460fdef768fc8dbaf16");//oa1qa8tbascbfe
            return driverManagerDataSource;
        }*/
    public static void main(String[] args) {
        SpringApplication.run(TodontApplication.class, args);
    }
}