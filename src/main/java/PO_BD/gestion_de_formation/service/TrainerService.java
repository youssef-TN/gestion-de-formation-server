package PO_BD.gestion_de_formation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import PO_BD.gestion_de_formation.model.Trainer;
import PO_BD.gestion_de_formation.repository.TrainerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {
	
	private final TrainerRepository trainerRepository;

	@Autowired
	public TrainerService(TrainerRepository trainerRepository) {
		this.trainerRepository = trainerRepository;
	}

	/**
	 * Get all trainers
	 * 
	 * @return a list of all trainers
	 */
	public List<Trainer> getAllTrainers() {
		return trainerRepository.findAll();
	}

	/**
	 * Get a trainer by ID
	 * 
	 * @param id the ID of the trainer
	 * @return an Optional containing the trainer if found
	 */
	public Optional<Trainer> getTrainerById(String id) {
		return trainerRepository.findById(id);
	}

	/**
	 * Create a new trainer
	 * 
	 * @param trainer the trainer to create
	 * @return the created trainer
	 * @throws IllegalArgumentException if a trainer with the same email already exists
	 */
	public Trainer createTrainer(Trainer trainer) {
		if (trainerRepository.existsByEmail(trainer.getEmail())) {
			throw new IllegalArgumentException("A trainer with email " + trainer.getEmail() + " already exists");
		}
		trainer.setCreatedAt(java.time.Instant.now().toString());
        trainer.setUpdatedAt(java.time.Instant.now().toString());
        
		return trainerRepository.save(trainer);
	}

	/**
	 * Update a trainer
	 * 
	 * @param id the ID of the trainer to update
	 * @param trainerDetails the updated trainer details
	 * @return the updated trainer
	 * @throws IllegalArgumentException if the trainer is not found
	 */
	public Trainer updateTrainer(String id, Trainer trainerDetails) {
		Trainer trainer = trainerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Trainer not found with id " + id));
		trainer.setFirstName(trainerDetails.getFirstName());
		trainer.setLastName(trainerDetails.getLastName());
		trainer.setEmail(trainerDetails.getEmail());
		trainer.setTel(trainerDetails.getTel());
		trainer.setType(trainerDetails.getType());
		trainer.setCreatedAt(trainerDetails.getCreatedAt());
		trainer.setUpdatedAt(java.time.Instant.now().toString());

		return trainerRepository.save(trainer);
	}

	/**
	 * Delete a trainer by ID
	 * 
	 * @param id the ID of the trainer to delete
	 */
	public void deleteTrainer(String id) {
		if (!trainerRepository.existsById(id)) {
			throw new IllegalArgumentException("Trainer not found with id: " + id);
		}
		trainerRepository.deleteById(id);
	}

	/**
     * Get recent activities
     * 
     * @return a list of with recent activities
     */
    public List<Trainer> getRecentActivities() {
        Pageable limitThree = PageRequest.of(0, 3);
        return trainerRepository.findFreshTrainers(limitThree);
    }
}
