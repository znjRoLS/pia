/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author rols
 */
public class AuthorPresentation {
    private int authorPresentationId;
    private User author;
    private Presentation presentation;

    public int getAuthorPresentationId() {
        return authorPresentationId;
    }

    public void setAuthorPresentationId(int authorPresentationId) {
        this.authorPresentationId = authorPresentationId;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
    }
    
    
}
