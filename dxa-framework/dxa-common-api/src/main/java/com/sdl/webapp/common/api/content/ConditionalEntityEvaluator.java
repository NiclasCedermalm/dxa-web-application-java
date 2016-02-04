package com.sdl.webapp.common.api.content;

import com.sdl.webapp.common.api.model.EntityModel;

/**
 * <p>ConditionalEntityEvaluator interface.</p>
 *
 * @author azarakovskiy
 * @version 1.3-SNAPSHOT
 */
public interface ConditionalEntityEvaluator {

    /**
     * <p>includeEntity.</p>
     *
     * @param entity a {@link com.sdl.webapp.common.api.model.EntityModel} object.
     * @return a boolean.
     */
    boolean includeEntity(EntityModel entity);
}
