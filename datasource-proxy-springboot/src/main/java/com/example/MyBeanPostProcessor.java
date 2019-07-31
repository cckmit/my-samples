package com.example;

import lombok.extern.slf4j.Slf4j;
import net.ttddyy.dsproxy.listener.logging.SLF4JLogLevel;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.sql.DataSource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author : yangzq80@gmail.com
 * @date: 2019-07-16
 */
@Component
@Slf4j
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Autowired
    PersonRepository personRepository;
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equalsIgnoreCase("personService")){

            log.info("----before----------->{}",beanName);

        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

            if (bean.getClass().isAnnotationPresent(EventDriver.class)){

                ProxyFactory factory = new ProxyFactory(bean);
                factory.setProxyTargetClass(true);
                factory.addAdvice(new ProxyBeanInterceptor((PersonService) bean));
                return factory.getProxy();
            }

        return bean;
    }


    private static class ProxyBeanInterceptor implements MethodInterceptor {
        private PersonService personService;

        public ProxyBeanInterceptor( PersonService personService) {
            this.personService = personService;
        }

        @Override
        public Object invoke(final MethodInvocation i) throws Throwable {

            /*Method proxyMethod = ReflectionUtils.findMethod(personService.getClass(), i.getMethod().getName());
            if (proxyMethod != null) {
                return proxyMethod.invoke(personService, i.getArguments());
            }*/

            personService.getPersons("a");


            if(i.getMethod().isAnnotationPresent(EventDriver.class)){
                log.info("=====");
            }
            log.info("------method "+i.getMethod()+" is called on "+ i.getThis()+" with args "+i.getArguments()[0]);

            Object ret=i.proceed();
            log.info("------method "+i.getMethod()+" returns "+ret);
            return ret;
        }
    }
}
