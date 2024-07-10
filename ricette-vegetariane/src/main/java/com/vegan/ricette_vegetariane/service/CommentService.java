package com.vegan.ricette_vegetariane.service;

import com.vegan.ricette_vegetariane.dto.CommentDTO;
import com.vegan.ricette_vegetariane.entity.Comment;
import com.vegan.ricette_vegetariane.entity.Recipe;
import com.vegan.ricette_vegetariane.entity.Users;
import com.vegan.ricette_vegetariane.repository.CommentRepository;
import com.vegan.ricette_vegetariane.repository.RecipeRepository;
import com.vegan.ricette_vegetariane.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    public List<CommentDTO> getCommentsByRecipeId(Long recipeId) {
        List<Comment> comments = commentRepository.findByRecipeId(recipeId);
        return comments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CommentDTO createComment(CommentDTO commentDTO, String username) {
        Comment comment = convertToEntity(commentDTO, username);
        Comment savedComment = commentRepository.save(comment);
        return convertToDTO(savedComment);
    }

    public void deleteComment(Long id, String username) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found with id: " + id));

        if (!comment.getUser().getUsername().equals(username)) {
            throw new IllegalArgumentException("You are not authorized to delete this comment");
        }

        commentRepository.deleteById(id);
    }

    private Comment convertToEntity(CommentDTO commentDTO, String username) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setComment(commentDTO.getText());

        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found with username: " + username));
        comment.setUser(user);

        Recipe recipe = recipeRepository.findById(commentDTO.getRecipe().getId())
                .orElseThrow(() -> new IllegalArgumentException("Recipe not found with id: " + commentDTO.getRecipe().getId()));
        comment.setRecipe(recipe);

        return comment;
    }

    private CommentDTO convertToDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setText(comment.getComment());
        commentDTO.setAuthor(comment.getUser().getUsername());

        return commentDTO;
    }
}
