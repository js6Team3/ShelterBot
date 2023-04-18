package js6team3.tbot.service.impl;

import js6team3.tbot.entity.report.Photo;
import js6team3.tbot.repository.PhotoRepository;
import js6team3.tbot.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Service for managing daily photo files from Report Photo Repository
 * (Ежедневно усыновитель направляет в приют фото состояния животного)
 */
@Service
public class PhotoServiceImpl implements PhotoService {
    private final PhotoRepository photoRepository;

    @Autowired
    public PhotoServiceImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public void save(MultipartFile file) throws IOException {
        Photo photo = new Photo();
        photo.setName(StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
        photo.setContentType(file.getContentType());
        photo.setFilePhoto(file.getBytes());
        photo.setSize(file.getSize());
        photoRepository.save(photo);
    }

    @Override
    public Optional<Photo> getFile(String id) {
        return photoRepository.findById(id);
    }

    @Override
    public List<Photo> getAllFiles() {
        return photoRepository.findAll();
    }
}