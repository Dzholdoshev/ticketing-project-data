package com.cydeo.entity;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass // not create table for this class
public class BaseEntity {

    @Id //  creating primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    private Long id;
    private LocalDateTime insertDateTime;
    private Long insertUserId;
    private Boolean isDeleted;
    private LocalDateTime lastUpdateDateTime;
    private Long lastUpdateUserId;

}
