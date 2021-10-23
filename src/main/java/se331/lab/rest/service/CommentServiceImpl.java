package se331.lab.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se331.lab.rest.dao.CommentDao;
import se331.lab.rest.entity.Comment;
import javax.transaction.Transactional;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    CommentDao commentDao;

    @Override
    @Transactional
    public Comment save(Comment comment) {
        return commentDao.save(comment);
    }

}
