package com.dhruv.demo.persistance.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="Name")
    private String name;

    @Column(name ="UserName")
    private String userName;

    @Column(name ="Email")
    private String email;

    @Column(name ="Password")
    private String password;

    @Column(name="ContactNumber")
    private BigInteger contactNumber;

    @Column(name ="city")
    private String city;

    @Column(name ="DateOfBirth")
    private Date dateOfBirth;

    @Column(name ="Gender")
    private String gender;

    @Column(name ="UpdatedBy")
    private Integer updatedBy;

    @Column(name ="CreatedBy")
    private Integer createdBy;

    @Column(name ="CreatedAt")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name ="UpdatedAt")
    @UpdateTimestamp
    private Timestamp updatedAt;

    @Column(name ="Deleted")
    private byte deleted;

}

