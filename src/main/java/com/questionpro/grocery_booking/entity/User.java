package com.questionpro.grocery_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    @JsonIgnore
    private String password;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;
    @Column(columnDefinition = "DATETIME default CURRENT_TIMESTAMP", nullable = false, updatable = false)
    @CreationTimestamp
    private Instant createdAt;
    @Column(columnDefinition = "DATETIME default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP", nullable = false)
    @UpdateTimestamp
    private Instant updatedAt;
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @JsonManagedReference
    private List<Order> orders;
}
