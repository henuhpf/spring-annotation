import com.hmoro.bean.Blue;
import com.hmoro.bean.Person;
import com.hmoro.config.MainConfig;
import com.hmoro.config.MainConfig2;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

public class IOCTest {
    @Test
    public void test01(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
    }
    @Test
    public void test02(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
        System.out.println("IOC 容器创建完成");
        Object person = applicationContext.getBean("person");
        Object person2 = applicationContext.getBean("person");
        System.out.println(person == person2); //true(默认单实例),false(多实例)
    }

    // 可在 VM -option中添加 -Dos.name=Linux，或 -Dos.name=Windows
    @Test
    public void test03(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        Environment environment = applicationContext.getEnvironment();
        // 动态获取环境变量的值
        String property = environment.getProperty("os.name");
        System.out.println(property);
        String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);
        for (String name : beanNamesForType) {
            System.out.println(name);
        }
        Map<String, Person> beansOfType = applicationContext.getBeansOfType(Person.class);
        System.out.println(beansOfType);
    }

    @Test
    public void testImport(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        String[] beanNamesForType = applicationContext.getBeanDefinitionNames();
        for (String name : beanNamesForType) {
            System.out.println(name);
        }
        Blue bean = applicationContext.getBean(Blue.class);
        System.out.println(bean);
        // 工厂Bean，获取的是调用getObject()创建的对象
        Object colorFactoryBean = applicationContext.getBean("colorFactoryBean");
        System.out.println("Bean的类型:" + colorFactoryBean.getClass());
        Object colorFactoryBean2 = applicationContext.getBean("&colorFactoryBean");
        System.out.println("Bean的类型:" + colorFactoryBean2.getClass());
    }
}
