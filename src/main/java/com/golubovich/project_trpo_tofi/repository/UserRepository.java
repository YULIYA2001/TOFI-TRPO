package com.golubovich.project_trpo_tofi.repository;

import com.golubovich.project_trpo_tofi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmailOrPhone(String email, String phone);

    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(String phone);

    @Query(value = "SELECT * FROM users JOIN user_details ON users.user_details_id = user_details.id" +
            " WHERE users.role = ?1 OR users.role = ?2 ORDER BY users.id",
            nativeQuery = true)
    List<User> findAllWithDetails(String userRole, String adminRole);

    @Query(value = "SELECT * FROM users JOIN user_details ON users.user_details_id = user_details.id WHERE users.id = ?1",
            nativeQuery = true)
    User findByIdWithDetails(Long id);

}
