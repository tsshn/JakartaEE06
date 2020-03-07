package com.yatsyshyn.JakartaEE06;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final EntityManager entityManager;

    @Transactional
    public UserEntity createUser(String firstName, String lastName, String email) {
        UserEntity user = new UserEntity();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        return entityManager.merge(user);
    }

    @Transactional
    public List<UserEntity> getAll() {
        return entityManager.createQuery(
                "SELECT u FROM UserEntity u", UserEntity.class).getResultList();
    }

    @Transactional
    public List<UserEntity> getByLastName(String lastName) {
        return entityManager.createQuery(
                "SELECT u FROM UserEntity u WHERE u.lastName = :lastName", UserEntity.class)
                .setParameter("lastName", lastName).getResultList();
    }

    @Transactional
    public List<UserEntity> getBySubstring(String substring) {
        return entityManager.createQuery(
                "SELECT u FROM UserEntity u " +
                        "WHERE u.firstName LIKE CONCAT('%', :substring, '%') " +
                        "OR u.lastName LIKE CONCAT('%', :substring, '%')",
                UserEntity.class).setParameter("substring", substring).getResultList();

    }
}
