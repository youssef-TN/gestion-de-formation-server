package PO_BD.gestion_de_formation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import PO_BD.gestion_de_formation.model.Training;
import PO_BD.gestion_de_formation.repository.TrainingRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingService {
    
    private final TrainingRepository trainingRepository;

    @Autowired
    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }
    
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    /**
     * Get a training by ID
     * 
     * @param id the ID of the training
     * @return an Optional containing the training if found
     */
    
    public Optional<Training> getTrainingById(String id) {
        return trainingRepository.findById(id);
    }

    /**
     * Create a new training
     * 
     * @param training the training to create
     * @return the created training
     * @throws IllegalArgumentException if a training with the same login already exists
     */

    public Training createTraining(Training training) {
        if (trainingRepository.existsByTitle(training.getTitle())) {
            throw new IllegalArgumentException("A training with title " + training.getTitle() + " already exists");
        }
        training.setCreatedAt(java.time.Instant.now().toString());
        training.setUpdatedAt(java.time.Instant.now().toString());
        
        return trainingRepository.save(training);
    }
    
    public Training updateTraining(String id, Training trainingDetails) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Training not found with id " + id));
        training.setTitle(trainingDetails.getTitle());
        training.setYear(trainingDetails.getYear());
        training.setDuration(trainingDetails.getDuration());
        training.setField(trainingDetails.getField());
        training.setBudget(trainingDetails.getBudget());
        training.setCreatedAt(trainingDetails.getCreatedAt());
        training.setUpdatedAt(java.time.Instant.now().toString());

        return trainingRepository.save(training);
    }

    /**
     * Delete a training by ID
     * 
     * @param id the ID of the training to delete
     * @throws IllegalArgumentException if the training doesn't exist
     */
    public void deleteTraining(String id) {
         if (!trainingRepository.existsById(id)) {
            throw new IllegalArgumentException("Training not found with id: " + id);
        }
        trainingRepository.deleteById(id);
    }

    /**
     * Get recent activities
     * 
     * @return a list of with recent activities
     */
    public List<Training> getRecentActivities() {
        Pageable limitThree = PageRequest.of(0, 3);
        return trainingRepository.findFreshTrainings(limitThree);
    }
}
