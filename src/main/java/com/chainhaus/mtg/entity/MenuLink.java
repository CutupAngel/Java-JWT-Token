package com.chainhaus.mtg.entity;

import javax.persistence.*;

/**
 * Created by Asad Sarwar on 11/06/2020.
 */
@Entity
@Table(name = "menu_link_tl")
public class MenuLink extends BaseEntity{

    @Column(name = "label", nullable = false)
    private String lable;

    @Column(name = "icon_type")
    private String iconType;

    @Column(name = "icon_name")
    private String iconName;

    @Column(name = "toggle")
    private String toggle;

    @Column(name = "router_link", nullable = false)
    private String routerLink;

    @Column(name = "is_visible")
    private Boolean isVisible;

    @ManyToOne
    @JoinColumn(name = "parent_menu_id")
    private MenuLink parentMenu;

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
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

    public String getRouterLink() {
        return routerLink;
    }

    public void setRouterLink(String routerLink) {
        this.routerLink = routerLink;
    }

    public MenuLink getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(MenuLink parentMenu) {
        this.parentMenu = parentMenu;
    }

    public Boolean getVisible() {
        return isVisible;
    }

    public void setVisible(Boolean visible) {
        isVisible = visible;
    }
}
