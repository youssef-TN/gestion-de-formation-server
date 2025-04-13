package PO_BD.gestion_de_formation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PO_BD.gestion_de_formation.model.User;
import PO_BD.gestion_de_formation.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Get all users
     * 
     * @return a list of all users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Get a user by ID
     * 
     * @param id the ID of the user
     * @return an Optional containing the user if found
     */
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    /**
     * Get a user by login
     * 
     * @param login the login of the user
     * @return an Optional containing the user if found
     */
    public Optional<User> getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    /**
     * Create a new user
     * 
     * @param user the user to create
     * @return the created user
     * @throws IllegalArgumentException if a user with the same login already exists
     */
    public User createUser(User user) {
        if (userRepository.existsByLogin(user.getLogin())) {
            throw new IllegalArgumentException("A user with login " + user.getLogin() + " already exists");
        }
        return userRepository.save(user);
    }

    /**
     * Update an existing user
     * 
     * @param id the ID of the user to update
     * @param userDetails the updated user details
     * @return the updated user
     * @throws IllegalArgumentException if the user doesn't exist
     */
    public User updateUser(String id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        
        user.setLogin(userDetails.getLogin());
        user.setPassword(userDetails.getPassword());
        user.setRole(userDetails.getRole());
        
        return userRepository.save(user);
    }

    /**
     * Delete a user by ID
     * 
     * @param id the ID of the user to delete
     * @throws IllegalArgumentException if the user doesn't exist
     */
    public void deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    /**
     * Find users by role
     * 
     * @param role the role to search
     * @return a list of users with the specified role
     */
    public List<User> getUsersByRole(String role) {
        return userRepository.findByRole(role);
    }
    
    /**
     * Authenticate a user
     * 
     * @param login the user's login
     * @param password the user's password
     * @return an Optional containing the user if authentication is successful
     */
    public Optional<User> authenticate(String login, String password) {
        Optional<User> userOpt = userRepository.findByLogin(login);
        
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            return userOpt;
        }
        
        return Optional.empty();
    }
}