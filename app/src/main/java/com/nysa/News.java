package com.nysa;

public class News {
    private String title;
    private String description;
    private String tagname;
    private String autor;
    private String paragraphM;
    private String subtitle;
    private String paragraphS;
    private String citat;

    public News(){

    }
    public News(String  title, String description, String tagname, String autor,String paragraphM, String paragraphS,String subtitle,String citat){

        this.title = title;
        this.description = description;
        this.tagname = tagname;
        this.autor = autor;
        this.paragraphM = paragraphM;
        this.paragraphS = paragraphS;
        this.subtitle=subtitle;
        this.citat=citat;


    }

    public String getCitat() {
        return citat;
    }

    public void setCitat(String citat) {
        this.citat = citat;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setParagraphM(String paragraphM) {
        this.paragraphM = paragraphM;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setParagraphS(String paragraphS) {
        this.paragraphS = paragraphS;
    }

    public String getAutor() {
        return autor;
    }

    public String getParagraphM() {
        return paragraphM;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getParagraphS() {
        return paragraphS;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTagname() {
        return tagname;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }
}
