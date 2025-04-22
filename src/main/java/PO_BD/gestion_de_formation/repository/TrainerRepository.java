package PO_BD.gestion_de_formation.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import PO_BD.gestion_de_formation.model.Trainer;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainerRepository extends MongoRepository<Trainer, String> {
	/**
     * Find a trainer by email
     * 
     * @param email the first name to search
     * @return an Optional containing the trainer if found
     */
    Optional<Trainer> findByEmail(String email);
    
	/**
     * Check if a trainer exists with the specified login
     * 
     * @param email the email to check
     * @return true if a trainer exists with this email, false otherwise
     */
    boolean existsByEmail(String email);

	/**
     * Find trainers by type
     * 
     * @param type the type to search
     * @return a list of trainers with the specified type
     */
    java.util.List<Trainer> findByType(String type);
	
    /**
     * Find the three most recent trainers
     * 
     * @return a list of the three most recent trainers
     */
    @Query(value = "{ }", sort = "{ 'updatedAt': -1 }")
    List<Trainer> findFreshTrainers(Pageable pageable);
}	
