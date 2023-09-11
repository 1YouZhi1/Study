package com.example.study.core.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件拦截器
 *
 * @Author YouZhi
 * @Date 2023 - 09 - 11 - 8:39
 */
@Component
@RequiredArgsConstructor
public class FileInterceptor implements HandlerInterceptor {

    @Value("${study.file.upload.path}")
    private String fileUploadPath;

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("file!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        // 获取请求的 URI
        String requestUri = request.getRequestURI();
        // 缓存10天
        response.setDateHeader("expires", System.currentTimeMillis() + 60 * 60 * 24 * 10 * 1000);
        try (OutputStream out = response.getOutputStream(); InputStream input = new FileInputStream(
                fileUploadPath + requestUri)) {
            byte[] b = new byte[4096];
            for (int n; (n = input.read(b)) != -1; ) {
                out.write(b, 0, n);
            }
        }
        return false;
    }

}
