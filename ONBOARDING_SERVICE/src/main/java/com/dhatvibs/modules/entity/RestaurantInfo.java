package com.dhatvibs.modules.entity;

import com.dhatvibs.modules.entity.RestaurantType;
import jakarta.persistence.*;
import lombok.*;
 
import java.util.List;
import java.util.UUID;
 
@Entity
@Table(name = "restaurant_info")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RestaurantInfo {
 
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private UUID id;
 
   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "vendor_id", nullable = false, unique = true)
   private Vendor vendor;
 
   @Column(name = "name", nullable = false)
   private String name;
 
   @Column(name = "phone_number", nullable = false, length = 15)
   private String phoneNumber;
 
   @Column(name = "email")
   private String email;
 
   @Column(name = "description", columnDefinition = "TEXT")
   private String description;
 
   @Column(name = "category")
   private String category; // e.g. Restaurant / Cafe / Cloud Kitchen
 
   @Enumerated(EnumType.STRING)
   @Column(name = "restaurant_type")
   private RestaurantType restaurantType;
 
   // Relationships
   @OneToOne(mappedBy = "restaurantInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   private RestaurantAddress address;
 
   @OneToMany(mappedBy = "restaurantInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   private List<RestaurantCuisine> cuisines;
 
   @OneToMany(mappedBy = "restaurantInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   private List<OperatingHours> operatingHours;
 
   @OneToMany(mappedBy = "restaurantInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   private List<RestaurantPhoto> photos;
}  
