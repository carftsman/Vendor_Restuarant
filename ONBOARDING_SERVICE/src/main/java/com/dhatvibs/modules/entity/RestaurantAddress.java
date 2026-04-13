package com.dhatvibs.modules.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;
 
@Entity
@Table(name = "restaurant_address")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RestaurantAddress {
 
   @Id @GeneratedValue(strategy = GenerationType.AUTO)
   private UUID id;
 
   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "restaurant_id", nullable = false, unique = true)
   private RestaurantInfo restaurantInfo;
 
   @Column(name = "address_line1", nullable = false)
   private String addressLine1;
 
   @Column(name = "address_line2")
   private String addressLine2;
 
   @Column(name = "city", nullable = false)
   private String city;
 
   @Column(name = "state", nullable = false)
   private String state;
 
   @Column(name = "postal_code", nullable = false, length = 10)
   private String postalCode;
 
   @Column(name = "latitude")
   private Double latitude;
 
   @Column(name = "longitude")
   private Double longitude;
} 

