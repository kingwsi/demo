package proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * description:  <br>
 * date: 2022/1/11 15:33 <br>
 * author: ws <br>
 */
public class CglibProxy implements MethodInterceptor {

    private final Enhancer enhancer = new Enhancer();

    private final Object bean;

    public CglibProxy(Object bean) {
        this.bean = bean;
    }

    public Object getProxy() {
        //设置需要创建子类的类
        enhancer.setSuperclass(bean.getClass());
        enhancer.setCallback(this);
        //通过字节码技术动态创建子类实例
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("proxy method: " + method.getName());
        return method.invoke(bean, args);
    }

    public static void main(String[] args) {
        Person person = new PersonImpl();
        CglibProxy cglibProxy = new CglibProxy(person);
        Person proxy = (Person) cglibProxy.getProxy();
        proxy.sayHello();

    }
}
