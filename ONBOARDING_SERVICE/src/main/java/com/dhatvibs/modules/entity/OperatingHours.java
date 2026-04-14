package com.dhatvibs.modules.entity;

import com.dhatvibs.modules.entity.WeekDay;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalTime;
import java.util.UUID;
 
@Entity
@Table(name = "operating_hours",
      uniqueConstraints = @UniqueConstraint(columnNames = {"restaurant_id", "day_of_week"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OperatingHours {
 
   @Id @GeneratedValue(strategy = GenerationType.AUTO)
   private UUID id;
 
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "restaurant_id", nullable = false)
   private RestaurantInfo restaurantInfo;
 
   @Enumerated(EnumType.STRING)
   @Column(name = "week_day", nullable = false)
   private WeekDay Weekday;
 
   /** false = Closed for that day (as shown in the UI toggle) */
   @Column(name = "is_open", nullable = false)
   private boolean isOpen = true;
 
   @Column(name = "opens_at")
   private LocalTime opensAt;
 
   @Column(name = "closes_at")
   private LocalTime closesAt;
}   
