package com.lnr.ecom.order.infrastrature.secondary.repository;

import com.lnr.ecom.order.infrastrature.secondary.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<OrderEntity,Long> {
}
