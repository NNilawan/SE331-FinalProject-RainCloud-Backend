package se331.lab.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.lab.rest.entity.Comment;
import se331.lab.rest.entity.Event;
import se331.lab.rest.repository.CommentRepository;
import se331.lab.rest.repository.EventRepository;

@Repository
@Profile("db")
public class CommentDaoImpl implements CommentDao {
    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

}
