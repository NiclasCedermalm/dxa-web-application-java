package com.sdl.tridion.referenceimpl.common.model.entity;

import com.sdl.tridion.referenceimpl.common.mapping.SemanticProperties;
import com.sdl.tridion.referenceimpl.common.mapping.SemanticProperty;

public class Link extends EntityBase {

    @SemanticProperties({
            @SemanticProperty("internalLink"),
            @SemanticProperty("externalLink")
    })
    private String url;

    private String linkText;

    private String alternateText;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLinkText() {
        return linkText;
    }

    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }

    public String getAlternateText() {
        return alternateText;
    }

    public void setAlternateText(String alternateText) {
        this.alternateText = alternateText;
    }
}
