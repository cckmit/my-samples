package demo;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * The class description.
 *
 * @author yangzq80@gmail.com
 * @date 2019-08-30
 * @see
 * @since 1.0.0
 */
@Component
@Slf4j
public class MyBeanPostProcessor implements BeanPostProcessor {


    @Autowired
    AbstractEnvironment environment;

    @PostConstruct
    public void init() {
        log.info("--------postconstruct");
        Map map = new HashMap();
        map.put("test","inboundOrders");
        environment.getPropertySources().addFirst(new DynamicLoadPropertySource("for",null));
    }


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //log.info("-----------{}",beanName);
        if (bean.getClass().isAnnotationPresent(EventDriver.class)){
            log.info("-----1111------{}",beanName);
            Class c = bean.getClass();
                Arrays.stream(c.getDeclaredMethods()).forEach(f->{
                    log.info("{}",f.getName());
                });


            ProxyFactory factory = new ProxyFactory(bean);
            factory.setProxyTargetClass(true);
            factory.addAdvice(new EventPublishBeanInterceptor());
            return factory.getProxy();
        }
        return bean;
    }


    private  class EventPublishBeanInterceptor implements MethodInterceptor {

        @Override
        public Object invoke(final MethodInvocation i) throws Throwable {

            Object ret= null;

            try {

                ret=i.proceed();

            }catch (Exception e){
                log.error(e.getMessage());
                throw new Throwable(e.getMessage());
            }
            log.info("------method "+i.getMethod()+" is called on "+ i.getThis()+" with args "+i.getArguments());


            log.info("------method "+i.getMethod()+" returns "+ret);

            return ret;
        }
    }
    /*@testA("a")
    public static void main(String[] args) throws Exception {
        Method method = MyBeanPostProcessor.class.getMethod("main", String[].class);
        testA testA = method.getAnnotation(testA.class);
        if (testA == null)
            throw new RuntimeException("please add testA");
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(testA);
        Field value = invocationHandler.getClass().getDeclaredField("memberValues");
        value.setAccessible(true);
        Map<String, Object> memberValues = (Map<String, Object>) value.get(invocationHandler);
        String val = (String) memberValues.get("value");
        System.out.println("改变前：" + val);
        val = "b";
        memberValues.put("value", val);
        System.out.println("改变后：" + testA.value());

    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface testA {
        String value();
    }*/

}
