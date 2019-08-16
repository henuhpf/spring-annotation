import com.hmoro.bean.Boss;
import com.hmoro.bean.Car;
import com.hmoro.bean.Color;
import com.hmoro.config.MainConfigOfAutowired;
import com.hmoro.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_Autowired {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);

    @Test
    public void test01(){
        BookService bookService = applicationContext.getBean(BookService.class);
        System.out.println(bookService);
//        BookDao bookDao = applicationContext.getBean(BookDao.class);
//        System.out.println(bookDao);
        applicationContext.close();
    }

    @Test
    public void test02(){
        Boss boss = applicationContext.getBean(Boss.class);
        System.out.println(boss);
        Car car = applicationContext.getBean(Car.class);
        System.out.println(car);
        Color color = applicationContext.getBean(Color.class);
        System.out.println(color);
        applicationContext.close();
    }

    private void printBeans(AnnotationConfigApplicationContext applicationContext){
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}
