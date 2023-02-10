package com.example.springmaven.repository;

import com.example.springmaven.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
@Transactional
public interface IUserRepository extends JpaRepository<User, Long> {
    Page<User> findUserByNameUserContainingAndAddressUserContainingAndPhoneUserContaining(
            String name, String address, String phone, Pageable pageable);

    @Query(value = "insert into `user` (user.name_user, user.address_user, user.phone_user, user.birthday_user) values(?1, ?2, ?3, ?4)", nativeQuery = true)
    @Modifying
    void createUser(String nameUser, String addressUser, String phoneUser, Date birthdayUser );

    @Modifying
    @Query(value = "update `user` SET user.name_user= ?1, user.address_user = ?2, user.phone_user = ?3, user.birthday_user = ?4 " +
            "WHERE user.id = ?5", nativeQuery = true)
    void updateUser(String nameUser, String addressUser, String phoneUser, Date birthdayUser, Long id);
}
