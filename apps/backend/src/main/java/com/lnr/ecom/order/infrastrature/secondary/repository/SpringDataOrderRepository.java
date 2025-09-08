package com.lnr.ecom.order.infrastrature.secondary.repository;


import com.lnr.ecom.order.domian.order.aggrigate.Order;
import com.lnr.ecom.order.domian.order.aggrigate.StripeSessionInformation;
import com.lnr.ecom.order.domian.order.mapper.OrderMapper;
import com.lnr.ecom.order.domian.order.repository.OrderRepository;
import com.lnr.ecom.order.domian.order.vo.OrderStatus;
import com.lnr.ecom.order.infrastrature.secondary.entity.OrderEntity;
import com.lnr.ecom.order.infrastrature.secondary.mapper.OrderEntityMapper;
import com.lnr.ecom.product.domain.vo.PublicId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SpringDataOrderRepository implements OrderRepository {

  private final JpaOrderRepository jpaOrderRepository;

  private final JpaOrderedProductRepository jpaOrderedProductRepository;

  @Override
  public void save(Order order) {
    OrderEntity orderEntityToCreate = OrderEntityMapper.toEntity(order);
    OrderEntity savedOrderEntity = jpaOrderRepository.save(orderEntityToCreate);

    savedOrderEntity.getOrderedProducts().forEach(
      orderedProductEntity -> orderedProductEntity.getId().setOrder(savedOrderEntity)
    );


    jpaOrderedProductRepository.saveAll(savedOrderEntity.getOrderedProducts());
  }

  @Override
  public void updateStatus(OrderStatus orderStatus, PublicId orderPublicId) {


    jpaOrderRepository.updateStatusByPublicId(orderStatus,orderPublicId.value());
  }

  @Override
  public Optional<Order> findByStripeSessionId(StripeSessionInformation stripeSessionInformation) {
    return jpaOrderRepository.findByStripeSessionId(stripeSessionInformation.stripeSessionId().value()).map(OrderEntityMapper::toDomain);
  }
}
