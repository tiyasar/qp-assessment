package com.questionpro.grocery_booking.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "groceryItems")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroceryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Long inventory;
    @OneToMany(mappedBy = "groceryItem", orphanRemoval = true)
    @JsonBackReference
    private List<OrderItem> orderItems;
    @Column(columnDefinition = "DATETIME default CURRENT_TIMESTAMP", nullable = false, updatable = false)
    @CreationTimestamp
    private Instant createdAt;
    @Column(columnDefinition = "DATETIME default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP", nullable = false)
    @UpdateTimestamp
    private Instant updatedAt;
}
