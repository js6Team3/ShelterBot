package js6team3.tbot.service;

import js6team3.tbot.entity.Cat;
import js6team3.tbot.entity.CatPhoto;
import js6team3.tbot.entity.Dog;
import js6team3.tbot.entity.DogPhoto;
import js6team3.tbot.listener.TBotUpdatesListener;
import js6team3.tbot.repository.CatPhotoRepository;
import js6team3.tbot.repository.CatRepository;
import js6team3.tbot.repository.DogPhotoRepository;
import js6team3.tbot.repository.DogRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

/**
 * Сервис для работы с сущностью "DogPhoto"
 *
 * @author Юрий Калынбаев
 */
@Service
@RequiredArgsConstructor
public class DogPhotoService {

    private static final Logger logger = LoggerFactory.getLogger(TBotUpdatesListener.class);

    private final DogRepository dogRepository;
    private final DogPhotoRepository dogPhotoRepository;

    @Value("${dir.path.PhotoDog}")
    /**
     * "PhotoDogDir" - директория фото сущности Dog
     */
    private String PhotoDogDir;

    /**
     * <b>Метод загружает фото питомца в базу данных и на диск</b>
     * <br> Используется метод репозитория {@link JpaRepository#findById(Object)}
     * <br> Используется метод репозитория {@link JpaRepository#save(Object)}
     * <br> Используется метод репозитория {@link DogPhotoService#getExtensions(String)}
     *
     * @param id   идентификатор питомца
     * @param file считываемый файл
     * @throws IOException - может возникнуть исключение ввода/вывода
     */
    public DogPhoto uploadPhotoDog(Long id, MultipartFile file) throws IOException {
        String methodName = new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName();
        logger.info("Текущий метод - " + methodName);
        Dog dog = dogRepository.findById(id).orElseThrow();
        Path filePath = Path.of(PhotoDogDir, id + "." + getExtensions(file.getOriginalFilename()));
        /** создаем директорию, если её нет */
        Files.createDirectories(filePath.getParent());
        /**
         * если такой файл существует, то мы его удаляем
         */
        Files.deleteIfExists(filePath);
        try (
                InputStream is = file.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 2048);
                BufferedOutputStream bos = new BufferedOutputStream(os, 2048);
        ) {
            bis.transferTo(bos);
        }
        DogPhoto dogPhoto = dogPhotoRepository.findById(id).orElseGet(DogPhoto::new);
        dogPhoto.setDog(dog);
        dogPhoto.setFilePath(filePath.toString());
        dogPhoto.setFileSize(file.getSize());
        dogPhoto.setMediaType(file.getContentType());
        dogPhoto.setPhotoDog(file.getBytes());
        return dogPhotoRepository.save(dogPhoto);
    }

    /**
     * <b> Метод формирует имя файла </b>
     *
     * @return Возвращает имя файла
     */
    public String getExtensions(String fileName) {
        String methodName = new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName();
        logger.info("Текущий метод - " + methodName);
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * <b>Метод ищет питомца по его id идентификатору</b>
     * <br> Используется метод репозитория {@link JpaRepository#findById(Object)}
     *
     * @param dogId идентификатор питомца
     * @return Возвращает найденного питомца
     */
    public Dog findDogById(Long dogId) {
        String methodName = new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName();
        logger.info("Текущий метод - " + methodName);
        return dogRepository.findById(dogId).orElseThrow();
    }

    /**
     * <b>Метод ищет фото питомца по id идентификатору питомца</b>
     * <br> Используется метод репозитория {@link JpaRepository#findById(Object)}
     *
     * @param dogId идентификатор питомца
     * @return Возвращает объект фото
     * @throws IOException - может возникнуть исключение ввода/вывода
     */
    public DogPhoto findPhotoDog(Long dogId) {
        String methodName = new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName();
        logger.info("Текущий метод - " + methodName);
        return dogPhotoRepository.findById(dogId).orElseThrow();
    }
}
