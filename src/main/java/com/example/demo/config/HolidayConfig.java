package com.example.demo.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ConfigurationProperties(prefix = "holidays")
public class HolidayConfig implements InitializingBean, ApplicationContextAware {

    private List<String> holidayDates = new ArrayList<>();
    private ApplicationContext applicationContext;

    public List<String> getHolidayDates() {
        return holidayDates;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        holidayDates = applicationContext.getEnvironment().getProperty("holidays", List.class, List.of());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
