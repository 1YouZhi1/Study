package com.example.study.manager.cache;

import com.example.study.dao.entity.UserInfo;
import com.example.study.dao.mapper.UserInfoMapper;
import com.example.study.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 用户信息 缓存类
 *
 * @Author YouZhi
 * @Date 2023 - 09 - 11 - 8:42
 */
@Component
@RequiredArgsConstructor
public class UserInfoCacheManager {

    private final UserInfoMapper userInfoMapper;

    public UserInfoDto getUser(Long userId) {
        UserInfo userInfo = userInfoMapper.selectById(userId);
        if(Objects.isNull(userInfo)){
            return null;
        }

        return UserInfoDto.builder()
                .id(userInfo.getId())
                .status(userInfo.getStatus())
                .build();
    }

}