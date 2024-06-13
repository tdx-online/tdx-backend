package edu.hit.tdxbackend.controller;

import edu.hit.tdxbackend.entity.Comment;
import edu.hit.tdxbackend.entity.ResultInfo;
import edu.hit.tdxbackend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

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
