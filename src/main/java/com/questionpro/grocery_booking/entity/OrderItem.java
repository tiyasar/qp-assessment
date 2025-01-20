package com.questionpro.grocery_booking.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "orderItems")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "orderId", nullable = false, foreignKey = @ForeignKey(name="fk_orderItem_orderId"))
    @JsonBackReference
    private Order order;
    @ManyToOne
    @JoinColumn(name = "groceryItemId", nullable = false, foreignKey = @ForeignKey(name="fk_orderItem_groceryItemId"))
    @JsonManagedReference
    private GroceryItem groceryItem;
    @Column(nullable = false)
    private Long count;
    @Column(columnDefinition = "DATETIME default CURRENT_TIMESTAMP", nullable = false, updatable = false)
    @CreationTimestamp
    private Instant createdAt;
    @Column(columnDefinition = "DATETIME default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP", nullable = false)
    @UpdateTimestamp
    private Instant updatedAt;
}
