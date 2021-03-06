package artoria.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Exception auto configuration.
 * @author Kahle
 */
@Configuration
@AutoConfigureBefore(ErrorMvcAutoConfiguration.class)
@EnableConfigurationProperties({ExceptionProperties.class})
@Import({DefaultExceptionHandler.class, DefaultErrorController.class})
public class ExceptionAutoConfiguration implements InitializingBean, DisposableBean {
    private static Logger log = LoggerFactory.getLogger(ExceptionAutoConfiguration.class);
    private ExceptionProperties exceptionProperties;

    @Autowired
    public ExceptionAutoConfiguration(ExceptionProperties exceptionProperties) {

        this.exceptionProperties = exceptionProperties;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }

    @Override
    public void destroy() throws Exception {
    }

    @Bean
    @ConditionalOnMissingBean
    public ErrorProcessor errorProcessor() {

        return new DefaultErrorProcessor(exceptionProperties);
    }

}
