package com.mahdy.cryptoarbitrage.util.command.registry;

import com.mahdy.cryptoarbitrage.util.command.model.HandlerMethod;
import com.mahdy.cryptoarbitrage.util.command.model.annotation.CommandMethod;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Mehdi Kamali
 * @since 21/10/2025
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class HandlerRegistryImpl implements HandlerRegistry {

    @Value("${service.command.handler-registry.base-package}")
    private String basePackage;
    private final ApplicationContext applicationContext;

    private final Map<String, HandlerMethod> handlers = new HashMap<>();

    @PostConstruct
    public void fillRegistry() {
        List<Method> methods = scanForMethods(List.of(basePackage), CommandMethod.class);
        for (Method method : methods) {
            CommandMethod commandMethod = method.getAnnotation(CommandMethod.class);
            Object instance = applicationContext.getBean(method.getDeclaringClass());
            handlers.put(commandMethod.value(), new HandlerMethod(instance, method));
        }
    }

    @Override
    public HandlerMethod getHandlerMethod(String commandName) {
        return handlers.get(commandName);
    }

    private List<Method> scanForMethods(List<String> basePackages, Class<? extends Annotation> annotationClass) {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*")));

        List<Method> methods = new ArrayList<>();
        try {
            for (String basePackage : basePackages) {
                for (BeanDefinition bean : scanner.findCandidateComponents(basePackage)) {
                    Class<?> clazz = Class.forName(bean.getBeanClassName());
                    for (Method method : clazz.getDeclaredMethods()) {
                        if (method.isAnnotationPresent(annotationClass)) {
                            methods.add(method);
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return methods;
    }
}
