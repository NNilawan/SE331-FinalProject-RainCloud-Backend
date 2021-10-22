package se331.lab.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import se331.lab.rest.entity.Comment;
import se331.lab.rest.entity.Event;
import se331.lab.rest.security.JwtTokenUtil;
import se331.lab.rest.security.entity.User;
import se331.lab.rest.security.repository.UserRepository;
import se331.lab.rest.service.CommentService;
import se331.lab.rest.service.UserService;
import se331.lab.rest.util.LabMapper;

import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.time.LocalDate;

@Controller
public class CommentController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @PostMapping("/comments/{id}")
    public ResponseEntity<?> addComment(HttpServletRequest request, @RequestBody Comment comment, @PathVariable("id") Long id) {
        String authToken = request.getHeader(this.tokenHeader);
        if (authToken != null && authToken.startsWith("Bearer ")) {
            authToken = authToken.substring(7);
        }
        String username = jwtTokenUtil.getUsernameFromToken(authToken);
        User doctor = userRepository.findByUsername(username);
        User patient = userService.getUser(id);
        comment.setDate(LocalDate.now());
        comment.setTime(new Time(System.currentTimeMillis()));
        comment.setCommentBy(doctor);
        comment.setCommentTo(patient);

        Comment output = commentService.save(comment);
        return ResponseEntity.ok(LabMapper.INSTANCE.getCommentDTO(output));
    }
}
