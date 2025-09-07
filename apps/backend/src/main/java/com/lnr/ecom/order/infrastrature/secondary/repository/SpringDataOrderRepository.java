package com.lnr.ecom.order.infrastrature.secondary.repository;


import com.lnr.ecom.order.domian.order.aggrigate.Order;
import com.lnr.ecom.order.domian.order.mapper.OrderMapper;
import com.lnr.ecom.order.domian.order.repository.OrderRepository;
import com.lnr.ecom.order.infrastrature.secondary.entity.OrderEntity;
import com.lnr.ecom.order.infrastrature.secondary.mapper.OrderEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
}
