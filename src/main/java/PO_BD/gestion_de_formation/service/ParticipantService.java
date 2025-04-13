package PO_BD.gestion_de_formation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PO_BD.gestion_de_formation.model.Participant;
import PO_BD.gestion_de_formation.repository.ParticipantRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {

	private final ParticipantRepository participantRepository;

	@Autowired
    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

	/**
     * Get all participants
     * 
     * @return a list of all participants
     */
	public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

	/**
     * Get a participant by ID
     * 
     * @param id the ID of the participant
     * @return an Optional containing the participant if found
     */
    public Optional<Participant> getParticipantById(String id) {
        return participantRepository.findById(id);
    }

	/**
	 * Get participants by structure
	 * 
	 * @param structure the structure of the participants
	 * @return a list of participants with the specified structure
	 */
	public List<Participant> getParticipantsByStructure(String structure) {
		return participantRepository.findByStructure(structure);
	}

    /**
     * Create a new participant
     * 
     * @param participant the participant to create
     * @return the created participant
     * @throws IllegalArgumentException if a participant with the same email already exists
     */
    public Participant createParticipant(Participant participant) {
        if (participantRepository.existsByEmail(participant.getEmail())) {
            throw new IllegalArgumentException("A participant with email " + participant.getEmail() + " already exists");
        }
        return participantRepository.save(participant);
    }
    
    /**
     * Update an existing participant
     * 
     * @param id the ID of the participant to update
     * @param participantDetails the updated participant details
     * @return the updated participant
     * @throws IllegalArgumentException if the participant doesn't exist
     */
    public Participant updateParticipant(String id, Participant participantDetails) {
        if (!participantRepository.existsById(id)) {
            throw new IllegalArgumentException("Participant with ID " + id + " does not exist");
        }
        Participant participant = participantRepository.findById(id)
                              .orElseThrow(() -> new IllegalArgumentException("Participant not found"));
        participant.setFirstName(participant.getFirstName());
        participant.setLastName(participant.getLastName());
        participant.setEmail(participant.getEmail());
        participant.setStructure(participant.getStructure());
        participant.setTel(participant.getTel());
        participant.setProfile(participant.getProfile());

        return participantRepository.save(participant);
    }

    /**
     * Delete a user by ID
     * 
     * @param id the ID of the user to delete
     * @throws IllegalArgumentException if the user doesn't exist
     */
    public void deleteParticipant(String id) {
        if (!participantRepository.existsById(id)) {
            throw new IllegalArgumentException("Participant not found with ID " + id);
        }
        participantRepository.deleteById(id);
}
}
