package com.dminc.show.domain;

//import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
@Document(collection = "shows")
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Show {
    
    @Id
    @Field(value="_id")
    private String id;
    private String name;
    private Integer editionYear;
    private String startDate;
    private String endDate;
    private String status;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getEditionYear() {
        return editionYear;
    }
    public void setEditionYear(Integer editionYear) {
        this.editionYear = editionYear;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public Show() {
          
    }
    public Show(String id, String name, Integer editionYear, String startDate, String endDate, String status) {
        this.id = id;
        this.name = name;
        this.editionYear = editionYear;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    
}
