import com.hmoro.config.MainConfigOfProfile;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

public class IOCTest_Profile {


    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfProfile.class);
        String[] beanNamesForType = applicationContext.getBeanNamesForType(DataSource.class);
        for (String s : beanNamesForType) {
            System.out.println(s);
        }

        applicationContext.close();
    }
    @Test
    public void test02(){
        // 创建一个 applicationContext
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 设置需要激活的环境
        applicationContext.getEnvironment().setActiveProfiles("dev");
        // 注册主注册类
        applicationContext.register(MainConfigOfProfile.class);
        // 启动刷新容器
        applicationContext.refresh();

        String[] beanNamesForType = applicationContext.getBeanNamesForType(DataSource.class);
        for (String s : beanNamesForType) {
            System.out.println(s);
        }

        applicationContext.close();
    }
}
