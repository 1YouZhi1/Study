package com.example.study.controller.front;

import java.util.List;
import com.example.study.core.common.resp.RestResp;
import com.example.study.core.constant.ApiRouterConsts;
import com.example.study.dto.resp.ClassInfoRespDto;
import com.example.study.dto.resp.ClassTypeInfoRespDto;
import com.example.study.dto.resp.ClassTypeRespDto;
import com.example.study.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * 课程 接口
 *
 * @Author YouZhi
 * @Date 2023 - 09 - 18 - 15:56
 */
@RestController
@RequestMapping(ApiRouterConsts.API_FRONT_CLASS_URL_PREFIX)
@RequiredArgsConstructor
public class ClassController {

    private final ClassService classService;

    @GetMapping("type/{id}")
    public RestResp<List<ClassTypeRespDto>> getType(@PathVariable("id") Long id) {
        return classService.getType(id);
    }

    @GetMapping("info/{id}")
    public RestResp<List<ClassTypeInfoRespDto>> getTypeInfo(@PathVariable("id") Long id) {
        return classService.getTypeInfo(id);
    }

    @GetMapping("class/{id}")
    public RestResp<ClassInfoRespDto> getClass(@PathVariable("id") Long id) {
        return classService.getClassInfo(id);
    }

    @PostMapping("love/{id}")
    public RestResp loveClass(@PathVariable("id") Long id) {
        return classService.loveClass(id);
    }

    @GetMapping("love")
    public RestResp<List<ClassTypeInfoRespDto>> getLoveClass() {
        return classService.getLoveClass();
    }
}
