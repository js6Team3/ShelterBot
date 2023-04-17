package js6team3.tbot.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import js6team3.tbot.model.report.Photo;
import js6team3.tbot.model.report.PhotoResponse;
import js6team3.tbot.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * a REST controller for handing HTTP requests POST (for uploading photo), GET (for listing and downloading photo)
 */
@RestController
@RequestMapping("/api/photo")
public class PhotoController {
    private final PhotoService photoService;
    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @PostMapping
    @Operation(summary = "Загрузка фотографии животного")
    @ApiResponse(responseCode = "200", description = "Фото успешно загружено")
    @ApiResponse(responseCode = "400", description = "Ошибка 400. Параметры некорректны")
    @ApiResponse(responseCode = "500", description = "Ошибка 500. Внутренняя ошибка")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        try {
            photoService.save(file);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }

    @GetMapping
    @Operation(summary = "Фотографии животных")
    @ApiResponse(responseCode = "200", description = "Фото успешно загружено")
    @ApiResponse(responseCode = "400", description = "Ошибка 400. Параметры некорректны")
    @ApiResponse(responseCode = "500", description = "Ошибка 500. Внутренняя ошибка")
    public List<PhotoResponse> list() {
        return photoService.getAllFiles()
                .stream()
                .map(this::mapToPhotoResponse)
                .collect(Collectors.toList());
    }

    private PhotoResponse mapToPhotoResponse(Photo photo) {
        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/")
                .path(photo.getId())
                .toUriString();
        PhotoResponse fileResponse = new PhotoResponse();
        fileResponse.setId(photo.getId());
        fileResponse.setName(photo.getName());
        fileResponse.setContentType(photo.getContentType());
        fileResponse.setSize(photo.getSize());
        fileResponse.setUrl(downloadURL);

        return fileResponse;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получить фотографию животного по id")
    @ApiResponse(responseCode = "200", description = "Фото успешно загружено")
    @ApiResponse(responseCode = "400", description = "Ошибка 400. Параметры некорректны")
    @ApiResponse(responseCode = "500", description = "Ошибка 500. Внутренняя ошибка")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        Optional<Photo> fileEntityOptional = photoService.getFile(id);

        if (fileEntityOptional.isEmpty()) {
            return ResponseEntity.notFound()
                    .build();
        }

        Photo photo = fileEntityOptional.get();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + photo.getName() + "\"")
                .contentType(MediaType.valueOf(photo.getContentType()))
                .body(photo.getFilePhoto());
    }
}