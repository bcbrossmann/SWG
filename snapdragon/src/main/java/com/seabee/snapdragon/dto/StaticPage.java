/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seabee.snapdragon.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author apprentice
 */
public class StaticPage {
    private int spId;
    @NotEmpty(message="You must supply a Title.")
    @Length(max=30, message="the Title must be no more than 30 characters in length.") 
    private String spTitle;
    @NotEmpty(message="You must supply a page body.")
    private String spBody;
    private String spPath;
    private Picture picture;
    
   
    public String getSpBody() {
        return spBody;
    }

    public int getSpId() {
        return spId;
    }

    public String getSpPath() {
        return spPath;
    }

    public String getSpTitle() {
        return spTitle;
    }

    //---
    public Picture getPicture() {
        return picture;
    }   
    
    public void setSpBody(String spBody) {
        this.spBody = spBody;
    }

    public void setSpId(int spId) {
        this.spId = spId;
    }

    public void setSpPath(String spPath) {
        this.spPath = spPath;
    }

    public void setSpTitle(String spTitle) {
        this.spTitle = spTitle;
    }
    
    //--
    //Take in picture elemenets and constuct the picture or construct the picture first
    public void setPicture(Picture picture){
        this.picture = picture;
    }
    
}
