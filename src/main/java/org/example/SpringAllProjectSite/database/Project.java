package org.example.SpringAllProjectSite.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String title;
    private String text;
    private String lang;
    private String link;

    public Project() {
    }

    public Project(String name, String title, String text, String lang, String link) {
        this.name = name;
        this.title = title;
        this.text = text;
        this.lang = lang;
        this.link = link;
    }

    public Boolean checkIfSomethingEmpty() {
        return name == null || title == null || text == null || lang == null ||
                name.trim().length() == 0 || title.trim().length() == 0 || text.trim().length() == 0 || lang.trim().length() == 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
