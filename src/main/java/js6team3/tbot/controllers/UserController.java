package js6team3.tbot.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import js6team3.tbot.entity.User;
import js6team3.tbot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Сущность: UserController
 *
 * @author Юрий Калынбаев
 * @author zalex14
 * @version 2.0
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user")
@Tag(name = "API для работы с сущностью User", description = "CRUD-операции для сущности User")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "ОК. Данные успешно обработаны",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        array = @ArraySchema(schema = @Schema(implementation = User.class)))),
        @ApiResponse(responseCode = "400", description = "Ошибка 400. Параметры запроса некорректны"),
        @ApiResponse(responseCode = "404", description = "Ошибка 404. Неправильный id. Результат запроса равен NULL"),
        @ApiResponse(responseCode = "500", description = "Ошибка 500. Внутренняя ошибка программы")
})
public class UserController {
    private final UserService userService;

    /**
     * Post a new user
     */
    @Operation(summary = "Внесение данных о новом пользователе",
            description = "Внесение полных данных пользователя в определённом формате")
    @PostMapping
    public User createUser(@Parameter(description = "Полные данные пользователя",
            example = "{firstName: Name, lastName : LastName, userPhoneNumber : +75558804420, userEmail: mail@mail.ru}")
                           @RequestBody User user) {
        return this.userService.createUserInDb(user);
    }

    /**
     * Delete a user by id
     */
    @Operation(summary = "Удаление данных о пользователе", description = "Удаляет пользователя по id")
    @DeleteMapping("/{id}")
    public User deleteUser(@Parameter(description = "Id удаляемого пользователя") @PathVariable("id") Long id) {
        return this.userService.deleteUserById(id);
    }

    /**
     * Put new data about the user
     */
    @Operation(summary = "Изменение данных о пользователе",
            description = "Ищет пользователя по id, затем заменяет в нем данные")
    @PutMapping("/{id}")
    public User updateUser(@Parameter(description = "Id пользователя") @PathVariable("id") Long id,
                           @Parameter(description = "Полные данные пользователя",
                                   example = "{firstName: Name, lastName : LastName," +
                                           " userPhoneNumber : +75558804420, userEmail: mail@mail.ru}")
                           @RequestBody User user) {
        return this.userService.replaceUserById(id, user);
    }

    /**
     * Get all users
     */
    @Operation(summary = "Получение списка всех пользователей")
    @GetMapping("/getAll")
    public Collection<User> getAllUsers() {
        return this.userService.getAllUsers();
    }
}