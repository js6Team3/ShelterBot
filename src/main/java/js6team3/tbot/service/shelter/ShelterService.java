package js6team3.tbot.service.shelter;

import js6team3.tbot.entity.shelter.Shelter;
import js6team3.tbot.exception.NullValueException;
import js6team3.tbot.listener.TBotListener;
import js6team3.tbot.repository.shelter.ShelterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Service for managing the shelter information
 *
 * @author zalex14
 */
@Service
public class ShelterService {
    private final Logger logger = LoggerFactory.getLogger(TBotListener.class);

    private final ShelterRepository shelterRepository;

    public ShelterService(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    /**
     * Add new shelter
     *
     * @param shelter obj
     */
    public Shelter addShelter(Shelter shelter) {
        String methodName = new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName();
        logger.info("Current Method is - " + methodName);

        if (shelter.getName().isBlank() || shelter.getName() == null) {
            throw new NullValueException("Имя приюта не задано");
        }
        return this.shelterRepository.save(shelter);
    }

    /**
     * Remove the shelter
     *
     * @param id The shelter id
     */
    public Shelter deleteShelter(Long id) {
        String methodName = new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName();
        logger.info("Current Method is - " + methodName);
        Shelter deleteShelter = shelterRepository.findById(id).orElse(null);
        shelterRepository.deleteById(id);
        return deleteShelter;
    }

    /**
     * Update the shelter data
     *
     * @param id      the shelter id
     * @param shelter obj
     */
    public Shelter updateShelter(Long id, Shelter shelter) {
        String methodName = new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName();
        logger.info("Current Method is - " + methodName);

        Shelter updateShelter = shelterRepository.findById(id).orElse(null);
        if (updateShelter != null) {
            updateShelter.setName(shelter.getName());
            updateShelter.setInformation(shelter.getInformation());

        } else {
            throw new NullValueException("Недостаточно данных для обновления информации о приюте");
        }
        return shelterRepository.save(updateShelter);

    }

    /**
     * Outpoint of shelters' list
     */
    public Collection<Shelter> getAllShelters() {
        return this.shelterRepository.findAll();
    }

    /**
     * The shelter's info
     */
    public Shelter getShelter(Long id) {
        return shelterRepository.findById(id).orElse(null);
    }
}