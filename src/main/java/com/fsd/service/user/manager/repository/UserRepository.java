package com.fsd.service.user.manager.repository;

import com.fsd.service.user.manager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value="SELECT count(P.USER_ID) from USERS U, PROJECT P WHERE  P.USER_ID=U.USER_ID AND U.USER_ID= :userId")
    long checkIfUserAssignedToProject(@Param("userId") Long id);

    @Query(nativeQuery = true, value="SELECT count(T.USER_ID) from TASKS T, USERS U where T.USER_ID=U.USER_ID AND U.USER_ID= :userId ")
    long checkIfUserAssignedToTask(@Param("userId") Long id);
}
