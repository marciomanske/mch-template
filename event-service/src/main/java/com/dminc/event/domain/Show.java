package com.dminc.event.domain;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Show {

    @NotNull
    private String id;
    @NotNull
    private String name;
    @NotNull
    private Integer editionYear;
    
}
