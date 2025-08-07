package com.lnr.ecom.product.infrastrature.seconadary.entity.repository;


import com.lnr.ecom.product.domain.aggregate.FilterQuery;
import com.lnr.ecom.product.domain.aggregate.Picture;
import com.lnr.ecom.product.domain.aggregate.Product;
import com.lnr.ecom.product.domain.repository.ProductRepository;
import com.lnr.ecom.product.domain.vo.PublicId;
import com.lnr.ecom.product.infrastrature.seconadary.entity.CategoryEntity;
import com.lnr.ecom.product.infrastrature.seconadary.entity.PictureEntity;
import com.lnr.ecom.product.infrastrature.seconadary.entity.ProductEntity;
import com.lnr.ecom.product.infrastrature.seconadary.entity.mapper.PictureEntityMapper;
import com.lnr.ecom.product.infrastrature.seconadary.entity.mapper.ProductEntityMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public class SpringDataProductRepository implements ProductRepository {

  private final JpaCategoryRepository jpaCategoryRepository;

  private final JpaProductRepository jpaProductRepository;

  private final JpaPictureRepository jpaProductPictureRepository;

  public SpringDataProductRepository(JpaCategoryRepository jpaCategoryRepository, JpaProductRepository jpaProductRepository, JpaPictureRepository jpaProductPictureRepository) {
    this.jpaCategoryRepository = jpaCategoryRepository;
    this.jpaProductRepository = jpaProductRepository;
    this.jpaProductPictureRepository = jpaProductPictureRepository;
  }

  @Override
  public Product save(Product productToCreate) {
    ProductEntity newProductEntity = ProductEntityMapper.toEntity(productToCreate);
    Optional<CategoryEntity> categoryEntityOpt = jpaCategoryRepository.findByPublicId(newProductEntity.getCategory().getPublicId());
    CategoryEntity categoryEntity = categoryEntityOpt.orElseThrow(() -> new EntityNotFoundException(String.format("No category found with Id %s", productToCreate.getCategory().getPublicId())));
    newProductEntity.setCategory(categoryEntity);
    ProductEntity savedProductEntity = jpaProductRepository.save(newProductEntity);

    saveAllPictures(productToCreate.getPictures(), savedProductEntity);
    return ProductEntityMapper.toDomin(savedProductEntity);
  }

  private void saveAllPictures(List<Picture> pictures, ProductEntity newProductEntity) {
    Set<PictureEntity> picturesEntities = PictureEntityMapper.toEntityList(pictures);

    for (PictureEntity picturesEntity : picturesEntities) {
      picturesEntity.setProduct(newProductEntity);
    }

    jpaProductPictureRepository.saveAll(picturesEntities);
  }

  @Override
  public Page<Product> findAll(Pageable pageable) {
    return jpaProductRepository.findAll(pageable).map(ProductEntityMapper::toDomin);
  }

  @Override
  public int delete(PublicId publicId) {
    return jpaProductRepository.deleteByPublicId(publicId.value());
  }

  @Override
  public Page<Product> findAllFeaturedProduct(Pageable pageable) {
    return jpaProductRepository.findAll(pageable).map(ProductEntityMapper::toDomin);
  }


  @Override
  public Optional<Product> findOne(PublicId publicId) {
    return jpaProductRepository.findByPublicId(publicId.value()).map(ProductEntityMapper::toDomin);
  }

  @Override
  public Page<Product> findByCategoryExcludingOne(Pageable pageable, PublicId categoryPublicId, PublicId productPublicId) {
    return jpaProductRepository.findByCategoryPublicIdAndPublicIdNot(pageable, categoryPublicId.value(), productPublicId.value())
      .map(ProductEntityMapper::toDomin);
  }

  @Override
  public Page<Product> findByCategoryAndSize(Pageable pageable, FilterQuery filterQuery) {
    return jpaProductRepository.findByCategoryPublicIdAndSizesIn(
      pageable, filterQuery.categoryId().value(), filterQuery.sizes()
    ).map(ProductEntityMapper::toDomin);
  }

  @Override
  public List<Product> findByPublicIds(List<PublicId> publicIds) {
    List<UUID> publicIdsUUID = publicIds.stream().map(PublicId::value).toList();
    return jpaProductRepository.findAllByPublicIdIn(publicIdsUUID)
      .stream().map(ProductEntityMapper::toDomin).toList();
  }

  @Override
  public void updateQuantity(PublicId productPublicId, long quantity) {
    jpaProductRepository.updateQuantity(productPublicId.value(), quantity);
  }
}
