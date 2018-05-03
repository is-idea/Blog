package com.alex.mapper;

import com.alex.entity.vo.ArticleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Alex isidea@outlook.com
 * @Date: 2018/4/27 0027 17:55
 */
@Mapper
public interface ArticleMapper {

    List<ArticleVO> findArticleAll(@Param("begin") Integer begin, @Param("limit") Integer limit, @Param("search") String search, @Param("type") String type);

    Long findArticleCount(@Param("search") String search, @Param("type") String type);

    /**
     * 添加文章
     * @param articleVO
     * @return
     */
    int addArticle(@Param("articleVO") ArticleVO articleVO);

    /**
     * 通过文章id获取文章内容
     * @param articleId
     * @return
     */
    ArticleVO selectByArticleId(@Param("articleId") String articleId);

}
