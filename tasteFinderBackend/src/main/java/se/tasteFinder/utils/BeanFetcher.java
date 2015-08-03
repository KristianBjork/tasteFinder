package se.tasteFinder.utils;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class BeanFetcher {
    private static BeanFetcher beanFetcher = null;
    
    private BeanFetcher() {}
    
    public static BeanFetcher getInstance() {
        if (beanFetcher == null) {
            beanFetcher = new BeanFetcher();
        }
        return beanFetcher;
    }
    
    public Object getBean(String name) throws NamingException {
        return new InitialContext().lookup(name);
    }

}
