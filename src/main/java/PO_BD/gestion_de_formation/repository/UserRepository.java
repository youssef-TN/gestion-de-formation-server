package PO_BD.gestion_de_formation.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import PO_BD.gestion_de_formation.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * Find a user by login
     * 
     * @param login the login to search
     * @return an Optional containing the user if found
     */
    Optional<User> findByLogin(String login);

    /**
     * Check if a user exists with the specified login
     * 
     * @param login the login to check
     * @return true if a user exists with this login, false otherwise
     */
    boolean existsByLogin(String login);

    /**
     * Find users by role
     * 
     * @param role the role to search
     * @return a list of users with the specified role
     */
    java.util.List<User> findByRole(String role);

    /**
     * Delete a user by login
     * 
     * @param login the login of the user to delete
     */
    void deleteByLogin(String login);

    /**
     * Find the three most recent users
     * 
     * @return a list of the three most recent users
     */
    @Query(value = "{ }", sort = "{ 'updatedAt': -1 }")
    List<User> findFreshUsers(Pageable pageable);
}