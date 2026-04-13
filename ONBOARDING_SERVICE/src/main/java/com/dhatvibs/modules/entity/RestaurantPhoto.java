package com.dhatvibs.modules.entity;

import com.dhatvibs.modules.entity.RestaurantPhotoType;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;
 
@Entity
@Table(name = "restaurant_photo")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RestaurantPhoto {
 
   @Id @GeneratedValue(strategy = GenerationType.AUTO)
   private UUID id;
 
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "restaurant_id", nullable = false)
   private RestaurantInfo restaurantInfo;
 
   @Enumerated(EnumType.STRING)
   @Column(name = "photo_type", nullable = false)
   private RestaurantPhotoType photoType;
 
   @Column(name = "photo_url", nullable = false)
   private String photoUrl;
}  