package js6team3.tbot.controllers.pet;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import js6team3.tbot.entity.pet.Cat;
import js6team3.tbot.service.pet.CatService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for handle HTTP requests for the cat's shelter
 *
 * @author Юрий Калынбаев
 * @author zalex14
 * @version 2.0
 */
@RestController
@RequestMapping("/cats")
@Tag(name = "API для работы с сущностью Cat", description = "CRUD-операции для сущности Cat")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "ОК. Данные успешно загружены/получены/обновлены"),
        @ApiResponse(responseCode = "400", description = "Ошибка 400. Параметры запроса некорректны"),
        @ApiResponse(responseCode = "404", description = "Ошибка 404. Неправильный id. Результат запроса равен NULL"),
        @ApiResponse(responseCode = "500", description = "Ошибка 500. Внутренняя ошибка программы")
})
@AllArgsConstructor
public class CatController {
    private final CatService catService;

    /**
     * Post information about new cat
     */
    @Operation(summary = "Внесение данных о новом питомце",
            responses = {@ApiResponse(responseCode = "200", description = "Информация о питомце занесена",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = Cat.class))
                    ))})
    @PostMapping("/create")
    public Cat createCat(@RequestBody Cat cat) {
        return this.catService.createCatInDB(cat);
    }

    /**
     * Put information about the cat
     */
    @Operation(summary = "Изменение данных о питомце",
            responses = {@ApiResponse(responseCode = "200", description = "Информация о питомце изменена",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = Cat.class))
                    ))})
    @PutMapping("/{id}")
    public Cat updateCat(@Parameter(description = "Id питомца") @PathVariable("id") Long id, @RequestBody Cat cat) {
        return this.catService.replaceCatById(id, cat);
    }

    /**
     * Delete the cat
     */
    @Operation(summary = "Удаление информации о питомце",
            responses = {@ApiResponse(responseCode = "200", description = "Информация о питомце удалена",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))})
    @DeleteMapping("/{id}")
    public Cat deleteCat(@Parameter(description = "Id питомца", example = "1") @PathVariable("id") Long id) {
        return this.catService.deleteCatById(id);
    }

    /**
     * Get all cats
     *
     * @return List of cats
     */
    @Operation(summary = "Список всех кошек приюта")
    @GetMapping
    public ResponseEntity<List<Cat>> getAllCats() {
        return ResponseEntity.ok(catService.getAllCats());
    }
}