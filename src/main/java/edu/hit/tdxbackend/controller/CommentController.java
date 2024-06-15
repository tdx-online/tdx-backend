package edu.hit.tdxbackend.controller;

import edu.hit.tdxbackend.entity.Comment;
import edu.hit.tdxbackend.entity.ResultInfo;
import edu.hit.tdxbackend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * 添加评论
     *
     * @param comment 评论
     * @return 添加结果
     */
    @PostMapping("/add")
    public ResultInfo addComment(@RequestBody Comment comment) {
        ResultInfo info = new ResultInfo();
        try {
            boolean insertFlag = commentService.addComment(comment);
            if (insertFlag) {
                info.setErrorMsg(null);
                info.setFlag(true);
            } else {
                info.setErrorMsg("fail to insert!(db)");
                info.setFlag(false);
            }
        } catch (Exception e) {
//            e.printStackTrace();
            info.setErrorMsg("fail to insert!(Exception)");
            info.setFlag(false);
        }
        return info;
    }
}
