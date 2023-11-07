package me.zhengjie.config;

import cn.hutool.core.convert.Converter;
import cn.hutool.core.convert.ConverterRegistry;
import me.zhengjie.domain.LocalStorage;
import me.zhengjie.service.dto.LocalStorageDto;
import me.zhengjie.utils.SpringContextHolder;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author Rain
 * @date 2023/11/7 13:14
 */
@Configuration
public class ConverterConfig implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
