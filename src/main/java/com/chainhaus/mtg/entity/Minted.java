package com.chainhaus.mtg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Jamali on 9/13/2021.
 */
@Entity
@Table(name = "minted_tl")
public class Minted extends BaseEntity{

    @Column(name = "image_url", nullable = false, columnDefinition = "VARCHAR")
    private String imageUrl;

    @Column(name = "ipfs_hash", nullable = false, columnDefinition = "VARCHAR")
    private String ipfsHash;

    @Column(name = "ipfs_url", nullable = false, columnDefinition = "VARCHAR")
    private String ipfsUrl;

    @Column(name = "caption", columnDefinition = "VARCHAR")
    private String caption;

    @Column(name = "description", columnDefinition = "VARCHAR")
    private String description;

    @Column(name = "address", columnDefinition = "VARCHAR")
    private String address;

    @Column(name = "meta_data", columnDefinition = "VARCHAR")
    private String metaData;

    @Column(name = "meta_data_ipfs_hash", columnDefinition = "VARCHAR")
    private String metaDataIpfsHash;

    @Column(name = "asking_price")
    private Double askingProce;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIpfsHash() {
        return ipfsHash;
    }

    public void setIpfsHash(String ipfsHash) {
        this.ipfsHash = ipfsHash;
    }

    public String getIpfsUrl() {
        return ipfsUrl;
    }

    public void setIpfsUrl(String ipfsUrl) {
        this.ipfsUrl = ipfsUrl;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    public String getMetaDataIpfsHash() {
        return metaDataIpfsHash;
    }

    public void setMetaDataIpfsHash(String metaDataIpfsHash) {
        this.metaDataIpfsHash = metaDataIpfsHash;
    }

    public Double getAskingProce() {
        return askingProce;
    }

    public void setAskingProce(Double askingProce) {
        this.askingProce = askingProce;
    }
}
