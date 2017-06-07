package joy.todont.config;

import io.tracee.binding.springmvc.TraceeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.Locale;

/**
 * = WebMvcConfiguration
 TODO
 *
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter implements ApplicationContextAware {
    /**
     * TODO
     *
     */
    @Autowired
    private ThymeleafProperties thymeleafProperties;
    /**
     * TODO
     *
     */
    @Autowired
    private TemplateEngine templateEngine;
    /**
     * TODO
     *
     */
    private ApplicationContext applicationContext;

    /**
     * TODO
     *
     * @return ThymeleafProperties
     */
    public ThymeleafProperties getThymeleafProperties() {
        return thymeleafProperties;
    }

    /**
     * TODO
     *
     * @return TemplateEngine
     */
    public TemplateEngine getTemplateEngine() {
        return templateEngine;
    }

    /**
     * TODO
     *
     * @return ApplicationContext
     */
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * TODO
     *
     * @param applicationContext
     */
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * TODO
     *
     * @return ThymeleafViewResolver
     */
    @Bean
    public ThymeleafViewResolver javascriptThymeleafViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(getTemplateEngine());
        resolver.setCharacterEncoding("UTF-8");
        resolver.setContentType("application/javascript");
        resolver.setViewNames(new String[] {"*.js"});
        resolver.setCache(getThymeleafProperties().isCache());
        return resolver;
    }

    /**
     * TODO
     *
     * @return SpringResourceTemplateResolver
     */
    @Bean
    public SpringResourceTemplateResolver javascriptTemplateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(getApplicationContext());
        resolver.setPrefix("classpath:/templates/fragments/js/");
        resolver.setTemplateMode(TemplateMode.JAVASCRIPT);
        resolver.setCharacterEncoding("UTF-8");
        resolver.setCheckExistence(true);
        resolver.setCacheable(getThymeleafProperties().isCache());
        return resolver;
    }

    /**
     * TODO
     *
     * @return LocalValidatorFactoryBean
     */
    @Primary
    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    /**
     * TODO
     *
     * @return LocaleResolver
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("en"));
        return localeResolver;
    }

    /**
     * TODO
     *
     * @return LocaleChangeInterceptor
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    /**
     * TODO
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
        registry.addInterceptor(new TraceeInterceptor());
    }
}
