package PO_BD.gestion_de_formation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import PO_BD.gestion_de_formation.model.Training;
import java.util.Optional;

@Repository
public interface TrainingRepository extends MongoRepository<Training, String> {
	/**
	 * Find a training by its title
	 * 
	 * @param title the title to search
	 * @return an Optional containing the training if found
	 */
	Optional<Training> findByTitle(String title);
	
	/**
	 * Check if a training exists with the specified title
	 * 
	 * @param title the title to check
	 * @return true if a training exists with this title, false otherwise
	 */
	boolean existsByTitle(String title);
}
