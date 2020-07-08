package com.xie.myblog.listener;

import com.xie.myblog.service.CountService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @description:
 * @author: 谢
 * @time: 2020/7/3 12:27
 */
@WebListener
public class MyServletListener implements HttpSessionListener {

    private CountService countService = null;

    /**
     * 监听session创建
     * @param event
     */
    @Override
    public void sessionCreated(HttpSessionEvent event){
        countService = (CountService) this.getObjectFromApplication(event.getSession().getServletContext(),"countServiceImpl");

        System.out.println("=============== sessionCreated  ===============");
        if(countService.addCount()){
            System.out.println("访问次数加一");
        }else {
            System.out.println("访问次数添加失败");
        }

    }

    /**
     * 监听session销毁
     * @param event
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        System.out.println("=============== sessionDestroyed ===============");
    }

    /**
     * 通过WebApplicationContextUtils 得到Spring容器的实例。根据bean的名称返回bean的实例。
     * @param servletContext  ：ServletContext上下文。
     * @param beanName  :要取得的Spring容器中Bean的名称。
     * @return 返回Bean的实例。
     */
    private Object getObjectFromApplication(ServletContext servletContext,String beanName){
        //通过WebApplicationContextUtils 得到Spring容器的实例。
        WebApplicationContext application = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        //返回Bean的实例。
        return application.getBean(beanName);
    }

}