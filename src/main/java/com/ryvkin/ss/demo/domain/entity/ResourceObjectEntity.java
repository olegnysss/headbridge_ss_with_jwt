package com.ryvkin.ss.demo.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResourceObjectEntity {

    @Id
    private Integer id;
    private String resourceValue;
    private String path;


}
