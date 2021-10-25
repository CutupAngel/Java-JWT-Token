package com.chainhaus.mtg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by Asad Sarwar on 11/06/2020.
 */
public class UserModel {

    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private String userName;

    private String password;

    private boolean isActive;

    private String img;

    private String img2x;

    private Set<String> roles;

    private String authToken;

    private String defaultPage;

    private List<MenuLink> menuLinks;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg2x() {
        return img2x;
    }

    public void setImg2x(String img2x) {
        this.img2x = img2x;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getDefaultPage() {
        return defaultPage;
    }

    public void setDefaultPage(String defaultPage) {
        this.defaultPage = defaultPage;
    }

    public List<MenuLink> getMenuLinks() {
        return menuLinks;
    }

    public void setMenuLinks(List<MenuLink> menuLinks) {
        this.menuLinks = menuLinks;
    }



    public void addRole(String role) {
        if (this.roles == null) {
            this.roles = new HashSet<>();
        }
        this.roles.add(role);
    }

    public void adddMenuLink(Long id, Long parentMenuId, String label, String details, String routerLink, String iconType, String iconName, String toggle, Boolean visible) {
        if (this.menuLinks == null) {
            this.menuLinks = new LinkedList<>();
        }

        if (parentMenuId != null) {
            for (MenuLink menuLink : this.menuLinks) {
                if (menuLink.getId().equals(parentMenuId)) {
                    if (menuLink.submenu == null) {
                        menuLink.submenu = new LinkedList<>();
                    }
                    menuLink.submenu.add(new MenuLink(id, label, details, routerLink, iconType, iconName, toggle, visible));
                }
            }
        } else {
            this.menuLinks.add(new MenuLink(id, label, details, routerLink, iconType, iconName, toggle, visible));
        }

    }

    class MenuLink {

        @JsonIgnore
        private Long id;

        private String label;
        private String details;
        private String routerLink;
        private String iconType;
        private String iconName;
        private String toggle;
        private Boolean visible;
        private List<MenuLink> submenu;

        public MenuLink(Long id, String label, String details, String routerLink, String iconType, String iconName, String toggle, Boolean visible) {
            this.id = id;
            this.label = label;
            this.details = details;
            this.routerLink = routerLink;
            this.iconType = iconType;
            this.iconName = iconName;
            this.toggle = toggle;
            this.visible = visible;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getRouterLink() {
            return routerLink;
        }

        public void setRouterLink(String routerLink) {
            this.routerLink = routerLink;
        }

        public String getIconType() {
            return iconType;
        }

        public void setIconType(String iconType) {
            this.iconType = iconType;
        }

        public String getIconName() {
            return iconName;
        }

        public void setIconName(String iconName) {
            this.iconName = iconName;
        }

        public String getToggle() {
            return toggle;
        }

        public void setToggle(String toggle) {
            this.toggle = toggle;
        }

        public List<MenuLink> getSubmenu() {
            return submenu;
        }

        public void setSubmenu(List<MenuLink> submenu) {
            this.submenu = submenu;
        }

        public Boolean getVisible() {
            return visible;
        }

        public void setVisible(Boolean visible) {
            this.visible = visible;
        }
    }
}
