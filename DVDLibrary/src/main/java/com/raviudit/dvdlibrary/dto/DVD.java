/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raviudit.dvdlibrary.dto;

/**
 *
 * @author raviu
 */

    /*
    ** Class Name: DVD
    ** Properties: title, releaseDate, rating, director, studio, userComments
    */
public class DVD {
    
    private String title;           //DVD TItle
    private String releaseDate;     //DVD Release Date
    private String rating;          //MPAA Rating 
    private String director;        //Director
    private String studio;          //Studio the produced the DVD
    private String userComments;    //Additional User Comments. 
    
    public DVD(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserComments() {
        return userComments;
    }

    public void setUserComments(String userComments) {
        this.userComments = userComments;
    }
    
}
