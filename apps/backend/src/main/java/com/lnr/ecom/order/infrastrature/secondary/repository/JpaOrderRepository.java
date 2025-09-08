package com.lnr.ecom.order.infrastrature.secondary.repository;

import com.lnr.ecom.order.domian.order.vo.OrderStatus;
import com.lnr.ecom.order.infrastrature.secondary.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface JpaOrderRepository extends JpaRepository<OrderEntity,Long> {

@Modifying
@Query("""
  UPDATE OrderEntity order SET order.status = :orderStatus  WHERE order.publicId = :orderPublicid
  """)
  void updateStatusByPublicId(OrderStatus orderStatus, UUID orderPublicid);



Optional<OrderEntity> findByStripeSessionId(String stripeSessionId);
}
