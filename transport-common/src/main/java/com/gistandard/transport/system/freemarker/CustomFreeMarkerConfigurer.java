package com.gistandard.transport.system.freemarker;

import freemarker.cache.TemplateLoader;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.List;

/**
 * Manage System.
 * author yujie  2015-10-24
 * version 1.0.1
 */
public class CustomFreeMarkerConfigurer extends FreeMarkerConfigurer {

    @Override
    protected TemplateLoader getAggregateTemplateLoader(List<TemplateLoader> templateLoaders) {

        return new HtmlTemplateLoader(super.getAggregateTemplateLoader(templateLoaders));

    }

}