package com.chainhaus.mtg.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Asad Sarwar on 11/06/2020.
 */
@Entity
@Table(name = "role_tl")
public class Role extends BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "ROLE_MENU_LINK", joinColumns = {
            @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "MENU_LINK_ID") })
    private List<MenuLink> menuLinks;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MenuLink> getMenuLinks() {
        return menuLinks;
    }

    public void setMenuLinks(List<MenuLink> menuLinks) {
        this.menuLinks = menuLinks;
    }
}
