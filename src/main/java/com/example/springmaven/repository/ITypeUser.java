package com.example.springmaven.repository;

import com.example.springmaven.model.TypeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
@Repository
public interface ITypeUser extends JpaRepository<TypeUser, Long> {
    @Query(value = "select id, name_type from type_user where id = ?", nativeQuery = true)
    TypeUser findTypeUserById(Long id);

    @Query(value = "select id, name_type from type_user", nativeQuery = true)
    List<TypeUser> findAllTypeUser();
}
