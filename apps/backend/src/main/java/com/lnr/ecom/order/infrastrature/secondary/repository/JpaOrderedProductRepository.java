package com.lnr.ecom.order.infrastrature.secondary.repository;
import com.lnr.ecom.order.infrastrature.secondary.entity.OrderedProductEntity;
import com.lnr.ecom.order.infrastrature.secondary.entity.OrderedProductEntityPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderedProductRepository extends JpaRepository<OrderedProductEntity, OrderedProductEntityPk> {
}
