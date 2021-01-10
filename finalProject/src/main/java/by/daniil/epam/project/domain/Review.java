package by.daniil.epam.project.domain;

public class Review extends Entity {
    private User reviewedUser;
    private String comment;
    private String DateOfReviewing;

    public User getReviewedUser() {
        return reviewedUser;
    }

    public void setReviewedUser(User reviewedUser) {
        this.reviewedUser = reviewedUser;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDateOfReviewing() {
        return DateOfReviewing;
    }

    public void setDateOfReviewing(String dateOfReviewing) {
        DateOfReviewing = dateOfReviewing;
    }
}
