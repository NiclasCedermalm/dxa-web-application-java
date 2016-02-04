package com.sdl.webapp.common.api.model;

import java.util.Map;

/**
 * <p>EntityModel interface.</p>
 *
 * @author azarakovskiy
 * @version 1.3-SNAPSHOT
 */
public interface EntityModel extends ViewModel {

    /**
     * <p>getId.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getId();

    /**
     * <p>getXpmPropertyMetadata.</p>
     *
     * @return a {@link java.util.Map} object.
     */
    Map<String, String> getXpmPropertyMetadata();

}
