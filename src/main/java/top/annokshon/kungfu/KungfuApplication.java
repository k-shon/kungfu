package top.annokshon.kungfu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import top.annokshon.kungfu.utils.ChatWebSocketServer;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@MapperScan("top.annokshon.kungfu.mapper")
@EnableTransactionManagement  //开启事务
public class KungfuApplication extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(KungfuApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(KungfuApplication.class);
        //解决WebSocket不能注入的问题
        ConfigurableApplicationContext configurableApplicationContext = springApplication.run(args);
        ChatWebSocketServer.setApplicationContext(configurableApplicationContext);
    }

}
