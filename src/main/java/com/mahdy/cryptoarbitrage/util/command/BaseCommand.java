package com.mahdy.cryptoarbitrage.util.command;

import com.mahdy.cryptoarbitrage.util.command.model.HandlerMethod;
import com.mahdy.cryptoarbitrage.util.command.registry.HandlerRegistry;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import picocli.CommandLine.Command;

/**
 * @author Mehdi Kamali
 * @since 21/10/2025
 */
public abstract class BaseCommand implements Runnable, ApplicationContextAware {

    private ApplicationContext applicationContext;
    private HandlerRegistry handlerRegistry;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        this.handlerRegistry = applicationContext.getBean(HandlerRegistry.class);
    }

    @Override
    public void run() {
        String command = this.getClass().getAnnotation(Command.class).name();
        HandlerMethod handlerMethod = handlerRegistry.getHandlerMethod(command);
        if(handlerMethod != null) {
            handlerMethod.invokeWithoutResults(this);
        }
    }
}
