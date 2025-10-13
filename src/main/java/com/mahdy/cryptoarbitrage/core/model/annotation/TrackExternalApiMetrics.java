package com.mahdy.cryptoarbitrage.core.model.annotation;

import com.mahdy.cryptoarbitrage.core.model.enumeration.ExternalApi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Mehdi Kamali
 * @since 13/10/2025
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TrackExternalApiMetrics {

    ExternalApi value();
}
