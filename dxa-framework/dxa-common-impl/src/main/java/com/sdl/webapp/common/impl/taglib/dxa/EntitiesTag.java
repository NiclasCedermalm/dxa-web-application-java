package com.sdl.webapp.common.impl.taglib.dxa;

import com.sdl.webapp.common.api.WebRequestContext;
import com.sdl.webapp.common.api.model.EntityModel;
import com.sdl.webapp.common.api.model.PageModel;
import com.sdl.webapp.common.api.model.RegionModel;
import com.sdl.webapp.common.controller.ControllerUtils;
import com.sdl.webapp.common.markup.AbstractMarkupTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import java.io.IOException;

import static com.sdl.webapp.common.controller.RequestAttributeNames.PAGE_MODEL;

public class EntitiesTag extends AbstractMarkupTag {
    private static final Logger LOG = LoggerFactory.getLogger(EntitiesTag.class);

    private int containerSize;

    public void setContainerSize(int containerSize) {
        this.containerSize = containerSize;
    }

    @Override
    public int doStartTag() throws JspException {
        final PageModel page = (PageModel) pageContext.getRequest().getAttribute(PAGE_MODEL);
        if (page == null) {
            LOG.debug("Page not found in request attributes");
            return SKIP_BODY;
        }

        WebRequestContext webRequestContext = this.getWebRequestContext();

        RegionModel parentRegion = webRequestContext.getParentRegion();

        for (EntityModel entity : parentRegion.getEntities()) {
            try {
                pageContext.getRequest().setAttribute("_entity_", entity);
                webRequestContext.pushParentRegion(parentRegion);
                webRequestContext.pushContainerSize(containerSize);
                this.decorateInclude(ControllerUtils.getIncludePath(entity), entity);
            } catch (ServletException | IOException e) {
                try {
                    LOG.error("Error while processing entity tag", e);
                    this.decorateInclude(ControllerUtils.getIncludeErrorPath(), entity);
                } catch (IOException | ServletException e1) {
                    throw new JspException("Error while processing entity tag, error view wasn't found", e1);
                }
            } finally {
                webRequestContext.popParentRegion();
                webRequestContext.popContainerSize();
            }
        }

        return SKIP_BODY;
    }
}
