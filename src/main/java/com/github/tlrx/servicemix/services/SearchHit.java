package com.github.tlrx.servicemix.services;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Simple class that represents a result of a given search.
 */
@XmlRootElement(name = "hit")
public class SearchHit {
    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
