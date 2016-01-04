package com.sdl.webapp.tridion.xpm.markup;

import com.sdl.webapp.common.api.WebRequestContext;
import com.sdl.webapp.common.api.localization.Localization;
import com.sdl.webapp.common.api.model.EntityModel;
import com.sdl.webapp.common.api.model.ViewModel;
import com.sdl.webapp.common.markup.MarkupDecorator;
import com.sdl.webapp.common.markup.html.HtmlCommentNode;
import com.sdl.webapp.common.markup.html.HtmlNode;
import com.sdl.webapp.common.markup.html.ParsableHtmlNode;
import com.sdl.webapp.common.markup.html.builders.HtmlBuilders;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

/**
 * Entity XPM Markup
 *
 * @author nic
 */
public class EntityXpmMarkup implements MarkupDecorator {

    private static final String COMPONENT_PRESENTATION_PATTERN = "Start Component Presentation: " +
            "{\"ComponentID\":\"%s\",\"ComponentModified\":\"%s\",\"ComponentTemplateID\":\"%s\"," +
            "\"ComponentTemplateModified\":\"%s\",\"IsRepositoryPublished\":%s}";

    private static final String FIELD_PATTERN = "Start Component Field: {\"XPath\":\"%s\"}";

    @Override
    public HtmlNode process(HtmlNode markup, ViewModel model, WebRequestContext webRequestContext) {

        if (webRequestContext.isPreview()) {
            EntityModel entity = (EntityModel) model;

            boolean markupInjected = false;

            if (markup instanceof ParsableHtmlNode) {

                // Inject the XPM markup inside the entity markup
                //
                ParsableHtmlNode entityMarkup = (ParsableHtmlNode) markup;
                Elements html = entityMarkup.getHtmlElements();
                if (html != null) {   // If an HTML element (not a comment etc)

                    if ( html.size() == 0 ) {
                        // Empty markup -> skip generating XPM markup
                        //
                        return markup;
                    }
                    if ( html.size() == 1 ) {
                        // Only inject the XPM entity markup when there is one surrounding tag around all content (recommended approach)
                        //
                        html.prepend(buildXpmMarkup(entity, webRequestContext.getLocalization()).toHtml());
                        markupInjected = true;
                    }

                    Elements properties = html.select("[data-entity-property-xpath]");
                    for (Element property : properties) {
                        processProperty(property);
                    }
                }
            }

            if (!markupInjected) {
                // Surround the entity markup with the XPM markup
                //
                markup = HtmlBuilders.span()
                        .withContent(buildXpmMarkup(entity, webRequestContext.getLocalization()))
                        .withContent(markup).build();
            }
        }

        return markup;
    }

    protected void processProperty(Element propertyElement) {

        String xpath = propertyElement.attr("data-entity-property-xpath");

        HtmlNode xpmMarkup = new HtmlCommentNode(String.format(FIELD_PATTERN, xpath));
        if (propertyElement.childNodes().size() > 0) {

            if (!propertyXpmMarkupAlreadyGenerated(propertyElement)) {
                propertyElement.prepend(xpmMarkup.toHtml());
            }
        } else {
            propertyElement.before(xpmMarkup.toHtml());
        }
        propertyElement.removeAttr("data-entity-property-xpath");
    }

    protected boolean propertyXpmMarkupAlreadyGenerated(Element propertyElement) {
        int index = 0;
        Node node = null;
        while (index < propertyElement.childNodes().size()) {
            node = propertyElement.childNode(index);
            if (!(node instanceof TextNode)) {
                break;
            }
            index++;
        }
        if (node != null && node instanceof Comment) {
            Comment comment = (Comment) node;
            if (comment.getData().contains("Start Component Field:")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getPriority() {
        return 1;
    }

    private HtmlNode buildXpmMarkup(EntityModel entity, Localization localization) {
        return new HtmlCommentNode(entity.getXpmMarkup(localization));
    }

}
