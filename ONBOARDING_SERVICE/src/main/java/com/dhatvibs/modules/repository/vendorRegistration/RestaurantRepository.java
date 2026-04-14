package com.dhatvibs.modules.repository.vendorRegistration;

import com.dhatvibs.modules.entity.RestaurantInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RestaurantRepository extends JpaRepository<RestaurantInfo, UUID> {
}