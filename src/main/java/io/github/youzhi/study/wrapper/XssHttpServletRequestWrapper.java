package io.github.youzhi.study.wrapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Xss过滤处理
 *
 * @Author YouZhi
 * @Date 2023 - 09 - 24 - 11:36
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private static final Map<String, String> REPLACE_RULE = new HashMap<>();

    static {
        REPLACE_RULE.put("<", "&lt;");
        REPLACE_RULE.put(">", "&gt;");
    }

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values != null) {
            int length = values.length;
            String[] escapeValues = new String[length];
            for (int i = 0; i < length; i++) {
                escapeValues[i] = values[i];
                int index = i;
                REPLACE_RULE.forEach(
                        (k, v) -> escapeValues[index] = escapeValues[index].replaceAll(k, v));
            }
            return escapeValues;
        }
        return new String[0];
    }
}