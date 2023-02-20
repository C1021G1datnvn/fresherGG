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


public interface IUserRepository extends JpaRepository<User, Long> {

    @Query(value = "insert into `user` (user.name_user, user.address_user, user.phone_user, user.birthday_user, user.id_type_user, user.del_flag) values(?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    @Modifying
    void createUser(String nameUser, String addressUser, String phoneUser, Date birthdayUser, Long typeUser, Boolean delFlag );

    @Modifying
    @Query(value = "update `user` SET user.name_user= ?1, user.address_user = ?2, user.phone_user = ?3, user.birthday_user = ?4, user.del_flag = ?5, user.id_type_user = ?6 " +
            "WHERE user.id = ?7", nativeQuery = true)
    void updateUser(String nameUser, String addressUser, String phoneUser, Date birthdayUser, Boolean delFlag, Long typeUser , Long id);

    @Modifying
    @Query(value = "UPDATE `user`" +
            " SET del_flag = 0 " +
            "where id = ?", nativeQuery = true)
    void deleteUserById(Long id);

    @Query(value ="select user.id, user.name_user, user.address_user, user.phone_user, user.birthday_user, user.del_flag, user.id_type_user \n" +
            "from `user` \n" +
            "where del_flag = 1",
            nativeQuery = true)
    Page<User> findAllUser(Pageable pageable);

    @Query(value = "select user.id, user.name_user, user.address_user, user.phone_user, user.birthday_user, user.del_flag, user.id_type_user \n" +
            "from `user` WHERE del_flag = '1' AND user.name_user LIKE %?1% AND user.address_user LIKE %?2% AND user.phone_user LIKE %?3% ", nativeQuery = true)
    Page<User> searchUserContaining(String name, String address, String phone, Pageable pageable);
}
