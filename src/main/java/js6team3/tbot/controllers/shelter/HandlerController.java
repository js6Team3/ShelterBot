package js6team3.tbot.controllers.shelter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import js6team3.tbot.entity.shelter.Handler;
import js6team3.tbot.service.shelter.HandlerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * a REST controller for handle HTTP requests for the handler's information
 *
 * @author zalex14
 * @version 1.0
 */
@RestController
@RequestMapping("/api/handler")
@Tag(name = "Информация о рекомендованных кинологах", description = "CRUD-операции с кинолагами собак")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "ОК. Данные успешно обработаны",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        array = @ArraySchema(schema = @Schema(implementation = Handler.class)))),
        @ApiResponse(responseCode = "400", description = "Ошибка 400. Параметры запроса некорректны"),
        @ApiResponse(responseCode = "404", description = "Ошибка 404. Неправильный id. Результат запроса равен NULL"),
        @ApiResponse(responseCode = "500", description = "Ошибка 500. Внутренняя ошибка программы")
})
public class HandlerController {
    private final HandlerService handlerService;

    public HandlerController(HandlerService handlerService) {
        this.handlerService = handlerService;
    }

    /**
     * Add new handler
     *
     * @param handler obj
     * @return create of the handler
     */
    @Operation(summary = "Добавить нового кинолога", description = "ОК. Кинолог добавлен")
    @PostMapping
    public Handler addHandler(@RequestBody Handler handler) {
        return this.handlerService.add(handler);
    }

    /**
     * The handler's remove
     *
     * @param id The handler's id
     */
    @Operation(summary = "Удаление кинолога по id", description = "ОК.Кинолог удален")
    @DeleteMapping("/{id}")
    public void delete(@Parameter(description = "id кинолога") @PathVariable("id") Long id) {
        handlerService.delete(id);
    }

    /**
     * The handler's update
     *
     * @param id      The handler's id
     * @param handler The handler obj
     * @return The handler obj
     */
    @Operation(summary = "Редактировать информацию о кинологе", description = "ОК. Информация обновлена")
    @PutMapping("/{id}")
    public Handler updateHandler(@Parameter(description = "id кинолога", example = "1") @PathVariable("id") Long id,
                                 @RequestBody Handler handler) {
        return this.handlerService.update(id, handler);
    }

    /**
     * Get a list of all handlers
     *
     * @return a list of handlers
     */
    @Operation(
            summary = "Вывести списк всех кинологов", description = "ОК. Получен список кинологов")
    @GetMapping("/all")
    public Collection<Handler> getAllHandlers() {
        return this.handlerService.getAllHandlers();
    }

    /**
     * Get the handler's information
     *
     * @param id The handler's id
     * @return The handler obj
     */
    @Operation(summary = "Получить информацию о кинологе по id", description = "Ок. Информация о кинологе получена")
    @GetMapping("/get/{id}")
    public Handler getHandler(@Parameter(description = "id кинолога", example = "1") @PathVariable("id") Long id) {
        return this.handlerService.getHandler(id);
    }
}