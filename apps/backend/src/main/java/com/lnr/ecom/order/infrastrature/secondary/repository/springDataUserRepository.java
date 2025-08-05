package com.lnr.ecom.order.infrastrature.secondary.repository;

import com.lnr.ecom.order.domian.user.aggrigate.User;
import com.lnr.ecom.order.domian.user.repository.UserRepostory;
import com.lnr.ecom.order.domian.user.vo.UserAddressToUpdate;
import com.lnr.ecom.order.domian.user.vo.UserEmail;
import com.lnr.ecom.order.domian.user.vo.UserPublicId;
import com.lnr.ecom.order.infrastrature.secondary.entity.UserEntity;
import com.lnr.ecom.order.domian.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class springDataUserRepository implements UserRepostory {

    private final JpaUserRepository repository;

    @Override
    public void save(User user) {
        if(user.getDbId()!=null){
            Optional<UserEntity> userToUpadteOp= repository.findById(user.getDbId());

            if(userToUpadteOp.isPresent()){
                UserEntity userToUpdate=userToUpadteOp.get();
                userToUpdate.updateFromUser(user);
                repository.saveAndFlush(userToUpdate);

            }
        }else{
            repository.save(UserMapper.toEntity(user));
        }
    }

    @Override
    public Optional<User> get(UserPublicId publicId) {
        return repository.findOneByPublicId(publicId.userPublicId())
                .map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> getOneByEmail(UserEmail email) {
        return repository.findByEmail(email.email()).map(UserMapper::toDomain);
    }

    @Override
    public void updateAddress(UserPublicId publicId, UserAddressToUpdate address) {
        repository.updateAddress(
                publicId.userPublicId()
                ,address.userAddress().street()
                ,address.userAddress().city()
                ,address.userAddress().cuntry()
                ,address.userAddress().zipCode()
        );
    }

}






