package edu.hit.tdxbackend.service.impl;
import edu.hit.tdxbackend.entity.Comment;
import edu.hit.tdxbackend.mapper.CommentMapper;
import edu.hit.tdxbackend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public boolean addComment(Comment comment) {
        return commentMapper.addComment(comment);
    }
}
