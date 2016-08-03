package com.sg.dvdlibrarymvc.model;

import java.util.List;
import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author apprentice
 */
public class DVD {

    private int DVDId;
    @NotEmpty(message = "You must supply a value for Title")
    @Length(max = 50, message = "Title must be no more than 50 characters in length.")
    private String title;
    @NotEmpty(message = "You must supply a value for Release Date.")
    @Length(max = 4, message = "Release Date must be no more than 4 characters in length.")
    private String releaseDate;
    @NotEmpty(message = "You must supply a value for MPAA Rating.")
    @Length(max = 5, message = "MPAA Rating must be no more than 5 characters in length.")
    private String mpaaRating;
    @NotEmpty(message = "You must supply a value for Director Name.")
    @Length(max = 50, message = "Director Name must be no more than 50 characters in length.")
    private String directorName;
    @NotEmpty(message = "You must supply a value for Studio.")
    @Length(max = 50, message = "Studio must be no more than 50 characters in length.")
    private String studio;
    @NotEmpty(message = "You must supply a value for User Rating.")
    @Length(max = 2, message = "User Rating must be no more than 2 characters in length.")
    private String userRating;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.title);
        hash = 29 * hash + Objects.hashCode(this.mpaaRating);
        hash = 29 * hash + Objects.hashCode(this.directorName);
        hash = 29 * hash + Objects.hashCode(this.studio);
        hash = 29 * hash + Objects.hashCode(this.releaseDate);
        hash = 29 * hash + Objects.hashCode(this.userRating);
        hash = 29 * hash + this.DVDId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DVD other = (DVD) obj;
        if (!Objects.equals(this.DVDId, other.DVDId)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.releaseDate, other.releaseDate)) {
            return false;
        }
        if (!Objects.equals(this.mpaaRating, other.mpaaRating)) {
            return false;
        }
        if (!Objects.equals(this.directorName, other.directorName)) {
            return false;
        }
        if (!Objects.equals(this.studio, other.studio)) {
            return false;
        }
        if (!Objects.equals(this.userRating, other.userRating)) {
            return false;
        }
        return true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public int getDVDId() {
        return DVDId;
    }

    public void setDVDId(int dvdId) {
        this.DVDId = dvdId;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }
}
