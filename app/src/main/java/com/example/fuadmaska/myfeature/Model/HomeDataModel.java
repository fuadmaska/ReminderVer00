package com.example.fuadmaska.myfeature.Model;

public class HomeDataModel {

    private String Title;
    private String Subtitle;

    public HomeDataModel(String Title, String Subtitle){
        this.Title = Title;
        this.Subtitle = Subtitle;
    }


    public void setSubtitle(String subtitle) {
        Subtitle = subtitle;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSubtitle() {
        return Subtitle;
    }

    public String getTitle() {
        return Title;
    }
}
