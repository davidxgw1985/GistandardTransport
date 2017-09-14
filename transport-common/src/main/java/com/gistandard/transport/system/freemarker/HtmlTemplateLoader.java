package com.gistandard.transport.system.freemarker;

import freemarker.cache.TemplateLoader;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * Manage System.
 * author yujie  2015-10-24
 * version 1.0.1
 */
public class HtmlTemplateLoader implements TemplateLoader {

    private static final String HTML_ESCAPE_PREFIX= "<#escape valueX as valueX?html>";
    private static final String HTML_ESCAPE_SUFFIX = "</#escape>";

    private final TemplateLoader delegate;

    public HtmlTemplateLoader(TemplateLoader delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object findTemplateSource(String name) throws IOException {
        return delegate.findTemplateSource(name);
    }

    @Override
    public long getLastModified(Object templateSource) {
        return delegate.getLastModified(templateSource);
    }

    @Override
    public Reader getReader(Object templateSource, String encoding) throws IOException {
        Reader reader = delegate.getReader(templateSource, encoding);
        String templateText = IOUtils.toString(reader);
        return new StringReader(HTML_ESCAPE_PREFIX + templateText + HTML_ESCAPE_SUFFIX);
    }

    @Override
    public void closeTemplateSource(Object templateSource) throws IOException {
        delegate.closeTemplateSource(templateSource);
    }

}