package com.GenSpark.BugTracker.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class OrganizationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int organizationId;
    private String imageUrl;
    private String organizationName;
    private String organizationDescription;


    @Lob //BLOB (binary data)
    @Column(name="CONTENT", length=512)
    private String data;

    //Relational Mapping. Creating a column called bugs_id for every bug in an organization
    @OneToMany(cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
    @JoinColumn(name = "bugs_id")
    private List<BugsEntity> organizationBug;

    public int getOrganizationId() {return organizationId;}
    public void setOrganizationId(int organizationId) {this.organizationId = organizationId;}

    public String getOrganizationName() {return organizationName;}
    public void setOrganizationName(String organizationName) {this.organizationName = organizationName;}

    public List<BugsEntity> getOrganizationBug() {return organizationBug;}
    public void setOrganizationBug(List<BugsEntity> organizationBug) {this.organizationBug = organizationBug;}

    public String getImageUrl() {return imageUrl;}
    public void setImageUrl(String imageUrl) {this.imageUrl = imageUrl;}

    public String getOrganizationDescription() {return organizationDescription;}
    public void setOrganizationDescription(String organizationDescription) {this.organizationDescription = organizationDescription;}

    public String getData() {return data;}
    public void setData(String data) {this.data = data;}

    public OrganizationEntity() {
    }

    public OrganizationEntity(int organizationId, String imageUrl, String organizationName, String organizationDescription, String data, List<BugsEntity> organizationBug) {
        this.organizationId = organizationId;
        this.imageUrl = imageUrl;
        this.organizationName = organizationName;
        this.organizationDescription = organizationDescription;
        this.data = data;
        this.organizationBug = organizationBug;
    }
}
