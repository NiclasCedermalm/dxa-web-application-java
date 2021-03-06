package com.sdl.webapp.common.api.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sdl.webapp.common.api.mapping.annotations.SemanticEntity;
import com.sdl.webapp.common.api.mapping.annotations.SemanticProperties;
import com.sdl.webapp.common.api.mapping.annotations.SemanticProperty;

import static com.sdl.webapp.common.api.mapping.config.SemanticVocabulary.SDL_CORE;

@SemanticEntity(entityName = "EmbeddedLink", vocabulary = SDL_CORE, prefix = "e")
public class Link extends AbstractEntityModel {

    @SemanticProperties({
            @SemanticProperty("internalLink"),
            @SemanticProperty("externalLink"),
            @SemanticProperty("e:internalLink"),
            @SemanticProperty("e:externalLink")
    })
    @JsonProperty("Url")
    private String url;

    @JsonProperty("LinkText")
    @SemanticProperty("e:linkText")
    private String linkText;

    @SemanticProperty("e:alternateText")
    @JsonProperty("AlternateText")
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

    @Override
    public String toString() {
        return "Link{" +
                "url='" + url + '\'' +
                ", linkText='" + linkText + '\'' +
                ", alternateText='" + alternateText + '\'' +
                '}';
    }
}
