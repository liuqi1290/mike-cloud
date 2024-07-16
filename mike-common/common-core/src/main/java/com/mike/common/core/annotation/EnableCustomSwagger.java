package com.mike.common.core.annotation;

import com.mike.common.core.config.SwaggerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ SwaggerConfig.class })
public @interface EnableCustomSwagger {
}
