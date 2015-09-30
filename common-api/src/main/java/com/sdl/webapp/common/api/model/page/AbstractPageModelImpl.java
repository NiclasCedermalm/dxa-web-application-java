package com.sdl.webapp.common.api.model.page;

import com.google.common.collect.ImmutableMap;
import com.sdl.webapp.common.api.localization.Localization;
import com.sdl.webapp.common.api.model.MvcData;
import com.sdl.webapp.common.api.model.PageModel;
import com.sdl.webapp.common.api.model.RegionModelSet;
import com.sdl.webapp.common.api.model.region.RegionModelSetImpl;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractPageModelImpl implements PageModel {

    private final String XpmPageSettingsMarkup = "<!-- Page Settings: {\"PageID\":\"%s\",\"PageModified\":\"%s\",\"PageTemplateID\":\"%s\",\"PageTemplateModified\":\"%s\"} -->";
    private final String XpmPageScript = "<script type=\"text/javascript\" language=\"javascript\" defer=\"defer\" src=\"%s/WebUI/Editors/SiteEdit/Views/Bootstrap/Bootstrap.aspx?mode=js\" id=\"tridion.siteedit\"></script>";


    protected String id;
    protected String name;
    protected String title;
    protected String htmlClasses;
    protected Map<String, String> meta = new HashMap<>();
    protected RegionModelSet regions = new RegionModelSetImpl();
    protected Map<String, String> xpmMetadata = new HashMap<>();
    protected MvcData mvcData;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Map<String, String> getMeta() {
        return meta;
    }

    @Override
    public void setMeta(Map<String, String> meta) {
        this.meta = meta;
    }

    @Override
    public RegionModelSet getRegions() {
        return regions;
    }

    @Override
    public void setRegions(RegionModelSet regions) {
        this.regions = regions;
    }

    @Override
    public Map<String, String> getXpmMetadata() {
        return xpmMetadata;
    }

    @Override
    public void setXpmMetadata(Map<String, String> xpmMetadata) {
        this.xpmMetadata = ImmutableMap.copyOf(xpmMetadata);;
    }

    @Override
    public MvcData getMvcData() {
        return mvcData;
    }

    @Override
    public void setMvcData(MvcData mvcData) {
        this.mvcData = mvcData;
    }
    @Override
    public String getHtmlClasses()
    {
        return this.htmlClasses;
    }

    @Override
    public void setHtmlClasses(String htmlClasses)
    {
        this.htmlClasses = htmlClasses;
    }

    @Override
    public String getXpmMarkup(Localization localization)
    {
        if (getXpmMetadata() == null) {
            return "";
        }

        String cmsUrl;
        if (!getXpmMetadata().containsKey("CmsUrl")) {
            cmsUrl = localization.getConfiguration("core.cmsurl");
        }
        else
        {
            cmsUrl = getXpmMetadata().get("CmsUrl");
        }
        if (cmsUrl.endsWith("/"))
        {
            // remove trailing slash from cmsUrl if present
            cmsUrl = cmsUrl.substring(0, cmsUrl.length() - 1);
        }

        return String.format(
                XpmPageSettingsMarkup,
                getXpmMetadata().get("PageID"),
                getXpmMetadata().get("PageModified"),
                getXpmMetadata().get("PageTemplateID"),
                getXpmMetadata().get("PageTemplateModified")
        ) +
                String.format(XpmPageScript, cmsUrl);
    }


}
