package com.github.tlrx.servicemix.services;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Simple class that represents a result of a given search.
 */
@XmlRootElement(name = "hit")
public class SearchHit {
    Long id;
    String title;

    @XmlElement(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
