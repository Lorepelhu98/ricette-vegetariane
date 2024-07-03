package com.vegan.ricette_vegetariane.service;

import com.vegan.ricette_vegetariane.dto.ReviewDTO;
import com.vegan.ricette_vegetariane.entity.Review;
import com.vegan.ricette_vegetariane.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<ReviewDTO> findByRecipeId(Long recipeId) {
        List<Review> reviews = reviewRepository.findByRecipeId(recipeId);
        return reviews.stream()
                .map(this::mapReviewToDTO)
                .collect(Collectors.toList());
    }

    public ReviewDTO saveReview(ReviewDTO reviewDTO) {
        Review review = mapDTOToReview(reviewDTO);
        Review savedReview = reviewRepository.save(review);
        return mapReviewToDTO(savedReview);
    }

    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }

    // Metodo di mapping da Review a ReviewDTO
    private ReviewDTO mapReviewToDTO(Review review) {
        return new ReviewDTO(
                review.getId(),
                review.getComment(),
                review.getUser().getUsername(), // Supponendo che l'autore sia l'username dell'utente
                review.getRecipe().getId() // Id della ricetta associata alla recensione
        );
    }

    // Metodo di mapping da ReviewDTO a Review
    private Review mapDTOToReview(ReviewDTO reviewDTO) {
        Review review = new Review();
        review.setId(reviewDTO.getId());
        review.setComment(reviewDTO.getComment());
        return review;
    }
}
