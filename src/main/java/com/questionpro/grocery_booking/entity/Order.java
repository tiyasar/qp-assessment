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
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false, foreignKey = @ForeignKey(name="fk_order_userId"))
    @JsonBackReference
    private User user;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderItem> orderItems;
    @Column(columnDefinition = "DATETIME default CURRENT_TIMESTAMP", nullable = false, updatable = false)
    @CreationTimestamp
    private Instant createdAt;
    @Column(columnDefinition = "DATETIME default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP", nullable = false)
    @UpdateTimestamp
    private Instant updatedAt;
}
