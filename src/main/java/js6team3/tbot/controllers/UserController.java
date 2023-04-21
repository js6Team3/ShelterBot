package js6team3.tbot.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import js6team3.tbot.entity.Cat;
import js6team3.tbot.entity.User;
import js6team3.tbot.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Сущность: UserController
 *
 * @author Юрий Калынбаев
 */
@RestController
@RequestMapping("/user")
@Tag(name = "API для работы с сущностью User", description = "CRUD-операции для сущности User")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Получение списка всех пользователей сущности "User"
     */
    @Operation(
            summary = "Получение списка всех пользователей",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список пользователей",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = User.class))
                            )
                    ),
                    @ApiResponse(responseCode = "400",
                            description = "параметры запроса отсутствуют или имеют некорректный формат"),
                    @ApiResponse(responseCode = "500",
                            description = "произошла ошибка, не зависящая от вызывающей стороны")
            })
    @GetMapping("/getAll")
    public ResponseEntity<Collection<User>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

//    public Collection<User> getAllUsers() {
//        return this.userService.getAllUsers();
//    }

    /**
     * чтение записи о пользователе
     */
    @Operation(
            summary = "Чтение информации о пользователе по идентификатору",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Чтение информации о пользователе завершено успешно",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = User.class))
                    )),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Не найден запрашиваемый ресурс"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "параметры запроса отсутствуют или имеют некорректный формат"),
                    @ApiResponse(
                            responseCode = "500",
                            description = "произошла ошибка, не зависящая от вызывающей стороны")
            })
    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUserById(@Parameter(description = "Id пользователя", example = "1")
                                            @PathVariable("id") Long id) {
        return ResponseEntity.ok(this.userService.getUserById(id));
    }

//    public User getUserById(@Parameter(description = "Id пользователя", example = "1") @PathVariable("id") Long id) {
//        return this.userService.getUserById(id);
//    }

    /**
     * создание записи о новом пользователе
     */
    @Operation(
            summary = "Внесение данных о новом пользователе",
            description = "Внесение полных данных пользователя в определённом формате",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Информация о пользователе занесена",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = User.class))
                            )),
                    @ApiResponse(responseCode = "400",
                            description = "параметры запроса отсутствуют или имеют некорректный формат"),
                    @ApiResponse(responseCode = "500",
                            description = "произошла ошибка, не зависящая от вызывающей стороны")
            })
    @PostMapping("/create")
    public ResponseEntity<User> createUserInDb(@Parameter(
            description = "Полные данные пользователя",
            example = "{firstName: Name, lastName : LastName, userPhoneNumber : +75558804420, userEmail: mail@mail.ru}")
                                               @RequestBody User user) {
        return ResponseEntity.ok(this.userService.createUserInDb(user));
    }

//    public User createUserInDb(@Parameter(
//            description = "Полные данные пользователя",
//            example = "{firstName: Name, lastName : LastName, userPhoneNumber : +75558804420, userEmail: mail@mail.ru}")
//                               @RequestBody User user) {
//        return this.userService.createUserInDb(user);
//    }

    /**
     * удаление данных о пользователе
     */
    @Operation(
            summary = "Удаление данных о пользователе",
            description = "Удаляет пользователя по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Информация о пользователе удалена",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = User.class))
                            )),
                    @ApiResponse(
                            responseCode = "400",
                            description = "параметры запроса отсутствуют или имеют некорректный формат"),
                    @ApiResponse(
                            responseCode = "500",
                            description = "произошла ошибка, не зависящая от вызывающей стороны")
            })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@Parameter(
            description = "Id пользователя, которого необходимо удалить",
            example = "1") @PathVariable("id") Long id) {
        return ResponseEntity.ok(this.userService.deleteUserById(id));
    }

//    public User deleteUser(@Parameter(
//            description = "Id пользователя, которого необходимо удалить",
//            example = "1") @PathVariable("id") Long id) {
//        return this.userService.deleteUserById(id);
//    }

    /**
     * изменение данных о пользователе
     */
    @Operation(
            summary = "Изменение данных о пользователе",
            description = "Ищет пользователя по id, затем заменяет в нем данные на те," +
                    " что записаны в передаваемом user.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Информация о пользователе изменена",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = User.class))
                            )),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Не найден запрашиваемый ресурс"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "параметры запроса отсутствуют или имеют некорректный формат"),
                    @ApiResponse(
                            responseCode = "500",
                            description = "произошла ошибка, не зависящая от вызывающей стороны")
            })
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@Parameter(
            description = "Id пользователя",
            example = "1") @PathVariable("id") Long id,
                                           @Parameter(
                                                   description = "Полные данные пользователя",
                                                   example = "{firstName: Name, lastName : LastName," +
                                                           " userPhoneNumber : +75558804420, userEmail: mail@mail.ru}")
                                           @RequestBody User user) {
        return ResponseEntity.ok(this.userService.replaceUserById(id, user));
    }

//    public User updateUser(@Parameter(
//            description = "Id пользователя",
//            example = "1") @PathVariable("id") Long id,
//                           @Parameter(
//                                   description = "Полные данные пользователя",
//                                   example = "{firstName: Name, lastName : LastName," +
//                                           " userPhoneNumber : +75558804420, userEmail: mail@mail.ru}")
//                           @RequestBody User user) {
//        return this.userService.replaceUserById(id, user);
//    }

}
