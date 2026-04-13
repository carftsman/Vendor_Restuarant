package com.dhatvibs.modules.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;
 
@Entity
@Table(name = "restaurant_cuisine",
      uniqueConstraints = @UniqueConstraint(columnNames = {"restaurant_id", "cuisine_type"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RestaurantCuisine {
 
   @Id @GeneratedValue(strategy = GenerationType.AUTO)
   private UUID id;
 
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "restaurant_id", nullable = false)
   private RestaurantInfo restaurantInfo;
 
   @Enumerated(EnumType.STRING)
   @Column(name = "cuisine_type", nullable = false)
   private CuisineType cuisineType;
}  
