package com.sdl.webapp.common.impl.util.spring;

import org.springframework.beans.factory.FactoryBean;

import javax.servlet.http.HttpServletResponse;

class ServletResponseFactoryBean implements FactoryBean<HttpServletResponse> {

    private ServletResponseInScopeFilter responseInScopeFilter;

    ServletResponseFactoryBean(ServletResponseInScopeFilter responseInScopeFilter) {
        this.responseInScopeFilter = responseInScopeFilter;
    }

    @Override
    public HttpServletResponse getObject() throws Exception {
        return responseInScopeFilter.getHttpServletResponse();
    }

    @Override
    public Class<?> getObjectType() {
        return HttpServletResponse.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}