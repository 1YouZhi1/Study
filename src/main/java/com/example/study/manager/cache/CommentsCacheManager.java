package com.example.study.manager.cache;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.study.core.constant.CacheConsts;
import com.example.study.dao.entity.Comments;
import com.example.study.dao.entity.UserInfo;
import com.example.study.dao.mapper.CommentsMapper;
import com.example.study.dao.mapper.UserInfoMapper;
import com.example.study.dto.resp.CommentsRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * 评论 缓存类
 *
 * @Author YouZhi
 * @Date 2023 - 09 - 17 - 21:08
 */
@Component
@RequiredArgsConstructor
public class CommentsCacheManager {

    private final CommentsMapper commentsMapper;

    private final UserInfoMapper userInfoMapper;

    @Cacheable(cacheManager = CacheConsts.REDIS_CACHE_MANAGER, value =CacheConsts.COMMENTS_CACHE_MANAGER)
    public List<CommentsRespDto> getComments(Long p_id){
        QueryWrapper<Comments> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("comment_id,content,user_id,create_time,likes")
                .eq("is_deleted", false);
        List<Comments> comments = commentsMapper.selectList(queryWrapper);

        List<CommentsRespDto> commentsRespDtoList = new ArrayList<>();

        for(Comments comments1 : comments){
            QueryWrapper<UserInfo> userInfoQueryWrapper = new QueryWrapper<>();
            userInfoQueryWrapper.select("nick_name");
            CommentsRespDto commentsRespDto = new CommentsRespDto(
                    null,
                    comments1.getPostId(),
                    comments1.getContent(),
                    comments1.getUserId(),
                    userInfoMapper.selectOne(userInfoQueryWrapper).getNickName(),
                    comments1.getLikes(),
                    comments1.getCreateTime()
                    );

            commentsRespDtoList.add(commentsRespDto);
        }
        return commentsRespDtoList;
    }


}