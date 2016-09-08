/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seabee.snapdragon.dto;

/**
 *
 * @author apprentice
 */
public class Picture {
    
    private int pictureId;
    private String spImageUrl;
    private String spImageAltText;
    private int imageHeight;
    private int imageWidth;

    public String getSpImageUrl() {
        return spImageUrl;
    }

    public void setSpImageUrl(String spImageUrl) {
        this.spImageUrl = spImageUrl;
    }

    public String getSpImageAltText() {
        return spImageAltText;
    }

    public void setSpImageAltText(String spImageAltText) {
        this.spImageAltText = spImageAltText;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }
}
