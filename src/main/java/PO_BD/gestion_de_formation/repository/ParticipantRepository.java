package PO_BD.gestion_de_formation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import PO_BD.gestion_de_formation.model.Participant;
import PO_BD.gestion_de_formation.model.Trainer;
import PO_BD.gestion_de_formation.model.Training;

import java.util.Optional;

@Repository
public interface ParticipantRepository extends MongoRepository<Participant, String> {
	/**
     * Find participants by structure
     * 
     * @param structure the structure to search
     * @return a list of participants with the specified structure
     */
    java.util.List<Participant> findByStructure(String structure);
	
	/**
	 * Find a participant by its email
	 * 
	 * @param email the email to search
	 * @return an Optional containing the participant if found
	 */
	Optional<Training> findByEmail(String email);
	
	/**
	 * Check if a training exists with the specified email
	 * 
	 * @param email the email to check
	 * @return true if a training exists with this email, false otherwise
	 */
	boolean existsByEmail(String email);
}
