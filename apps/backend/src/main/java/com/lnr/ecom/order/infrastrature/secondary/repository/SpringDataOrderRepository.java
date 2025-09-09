package com.lnr.ecom.order.infrastrature.secondary.repository;


import com.lnr.ecom.order.domian.order.aggrigate.Order;
import com.lnr.ecom.order.domian.order.aggrigate.RazorpayPaymentInformation;
import com.lnr.ecom.order.domian.order.mapper.OrderMapper;
import com.lnr.ecom.order.domian.order.repository.OrderRepository;
import com.lnr.ecom.order.domian.order.vo.OrderStatus;
import com.lnr.ecom.order.domian.user.vo.UserPublicId;
import com.lnr.ecom.order.infrastrature.secondary.entity.OrderEntity;
import com.lnr.ecom.order.infrastrature.secondary.mapper.OrderEntityMapper;
import com.lnr.ecom.product.domain.vo.PublicId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class SpringDataOrderRepository implements OrderRepository {

  private final JpaOrderRepository jpaOrderRepository;

  private final JpaOrderedProductRepository jpaOrderedProductRepository;

  @Override
  public void save(Order order) {
//convert domain to entity
    OrderEntity orderEntityToCreate = OrderEntityMapper.toEntity(order);
//save entity
    OrderEntity savedOrderEntity = jpaOrderRepository.save(orderEntityToCreate);

    savedOrderEntity.getOrderedProducts().forEach(
      orderedProductEntity -> orderedProductEntity.getId().setOrder(savedOrderEntity)
    );

    jpaOrderedProductRepository.saveAll(savedOrderEntity.getOrderedProducts());
  }

  @Override
  public void updateStatus(OrderStatus orderStatus, PublicId orderPublicId) {
//update payment status in db
    jpaOrderRepository.updateStatusByPublicId(orderStatus,orderPublicId.value());
  }

  @Override
  public Optional<Order> findByRazorPayId(RazorpayPaymentInformation stripeSessionInformation) {
    jpaOrderRepository.findByRazorpayId(stripeSessionInformation.razorpayId().value());
    //find Order using razor pay from db   note:when init payment order created with razorpay id
    return jpaOrderRepository.findByRazorpayId(stripeSessionInformation.razorpayId().value()).map(OrderEntityMapper::toDomain);
  }

  @Override
  public Page<Order> findAllByPublicId(UserPublicId userPublicId, Pageable pageable) {
    //find orders using using (user-id)
    return jpaOrderRepository.findAllByUserPublicId(userPublicId.value(),pageable).map(OrderEntityMapper::toDomain);
  }

  @Override
  public Page<Order> findAll(Pageable pageable) {
    //find all orders (admin)
    return jpaOrderRepository.findAll(pageable).map(OrderEntityMapper::toDomain);
  }
}
