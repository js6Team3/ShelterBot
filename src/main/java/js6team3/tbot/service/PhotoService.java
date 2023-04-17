package js6team3.tbot.service;

import js6team3.tbot.model.report.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Service for managing daily photo files
 */
public interface PhotoService {
    void save(MultipartFile file) throws IOException;
    Optional<Photo> getFile(String id);
    List<Photo> getAllFiles();
}