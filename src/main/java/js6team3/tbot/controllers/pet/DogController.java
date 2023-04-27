package js6team3.tbot.controllers.pet;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import js6team3.tbot.entity.pet.Dog;
import js6team3.tbot.service.pet.DogService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * a REST controller for handle HTTP requests for the dog's shelter
 *
 * @author Юрий Калынбаев
 * @author zalex14
 * @version 2.0
 */
@RestController
@RequestMapping("/dogs")
@Tag(name = "API для работы с сущностью Dog", description = "CRUD-операции для сущности Dog")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "ОК. Данные успешно загружены/получены/обновлены"),
        @ApiResponse(responseCode = "400", description = "Ошибка 400. Параметры запроса некорректны"),
        @ApiResponse(responseCode = "404", description = "Ошибка 404. Неправильный id. Результат запроса равен NULL"),
        @ApiResponse(responseCode = "500", description = "Ошибка 500. Внутренняя ошибка программы")
})
@AllArgsConstructor
public class DogController {
    private final DogService dogService;

    /**
     * Post new dog's information
     */
    @Operation(summary = "Внесение данных о новом питомце",
            responses = {@ApiResponse(responseCode = "200", description = "Информация о питомце занесена",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = Dog.class))
                    ))})
    @PostMapping
    public Dog createDog(@RequestBody Dog dog) {
        return this.dogService.createDog(dog);
    }

    /**
     * Put information about the dog by id
     */
    @Operation(summary = "Изменение данных о питомце",
            responses = {@ApiResponse(responseCode = "200", description = "Информация о питомце изменена",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = Dog.class))
                    ))})
    @PutMapping("/{id}")
    public Dog updateDog(@Parameter(description = "Id питомца") @PathVariable("id") Long id, @RequestBody Dog dog) {
        return this.dogService.replaceDogById(id, dog);
    }

    /**
     * Delete of the dog by id
     */
    @Operation(summary = "Удаление информации о питомце",
            responses = {@ApiResponse(responseCode = "200", description = "Информация о питомце удалена",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))})
    @DeleteMapping("/{id}")
    public Dog deleteDog(@Parameter(description = "Id питомца") @PathVariable("id") Long id) {
        return this.dogService.deleteDogById(id);
    }

    /**
     * Get all dogs
     *
     * @return List of dogs
     */
    @Operation(summary = "Список всех собак и щенков приюта")
    @GetMapping
    public ResponseEntity<List<Dog>> getAllCats() {
        return ResponseEntity.ok(dogService.getAllDogs());
    }
}