package com.alex.controller;

import com.alex.entity.Type;
import com.alex.entity.User;
import com.alex.entity.from.ArticleFrom;
import com.alex.entity.vo.ArticleVO;
import com.alex.entity.vo.CommentVo;
import com.alex.entity.vo.ResultVO;
import com.alex.service.ArticleService;
import com.alex.service.CommentService;
import com.alex.service.TypeService;
import com.alex.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Alex isidea@outlook.com
 * @create 2018-04-30 14:51
 **/
@RequestMapping(value = "/article")
@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private TypeService typeService;

    /**
     * 处理用户发表的文章信息发表成功后在详情页展示用户发表的文章
     * @param
     * @param request
     * @return
     */
    @PostMapping(value = "/addArticle")
    @ResponseBody
    public Map<String,String> addArticle(@RequestBody ArticleFrom articleFrom, HttpServletRequest request,Model model){

        User user = (User) request.getSession().getAttribute("user");
        // 获取并设置发表文章的用户ID
        articleFrom.setUserID(user.getId());

        // 设置文章ID
        articleFrom.setArticleID(UUIDUtils.getUUID());

        // 设置封面图信息
        articleFrom.setPhoto(articleFrom.getPhoto());

        // 设置类型ID
        articleFrom.setTypeID(articleFrom.getTypeID());

        // 插入数据后查询出用户刚才编写的文章并用户带到详情页展示文章

        Map<String,String> map = new HashMap<>(16);
        map.put("articleID",articleService.addArticle(articleFrom));
        return map;

    }

    /**
     * 根据文章ID查看文章详情信息
     * @param articleId 文章id
     * @return
     */
    @RequestMapping("/articleContent")
    public String articleContent(String articleId, Model model){

        // 通过ID获取文章内容详情
        ArticleVO articleContent = articleService.selectByArticleId(articleId);

        // 通过文章ID获取评论列表
        List<CommentVo> commentList = commentService.getCommentList(articleId);

        model.addAttribute("articleContent", articleContent);
        model.addAttribute("commentList",commentList);

        return "detail";
    }

    /**
     * 文章专栏
     * @return
     */
    @RequestMapping("/column")
    @ResponseBody
    public Map<String,Object> column(HttpServletRequest request){

        Map<String, Object> map = new HashMap<>();
        //文章类型
        List<Type> typeList = typeService.typeList();
        map.put("typeList",typeList);

        return map;

    }
}
