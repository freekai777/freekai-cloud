package freekaicloud.eurekaserver.mybeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

/**
 * 单例中引用 原型bean， 让原型bean每次都不一样==>  引用原型bean的类 增加方法
 *  public abstract 【原型bean的类名】 getPrototypeBeanB();
 */
@Component
public  class SingletonA {

    @Autowired
    PrototypeBeanB prototypeBeanB;

    public void getBeanB () {
//        final PrototypeBeanB prototypeBeanB = getPrototypeBeanB();
        System.out.println(prototypeBeanB.hashCode());
    }

//    @Lookup
//    public abstract PrototypeBeanB getPrototypeBeanB();
}
