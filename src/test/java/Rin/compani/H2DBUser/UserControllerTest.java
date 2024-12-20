package Rin.compani.H2DBUser;

import Rin.compani.H2DBUser.Controllers.UserController;
import Rin.compani.H2DBUser.Model.User;
import Rin.compani.H2DBUser.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/**
 * Тестовый класс для UserController.
 * Этот класс содержит интеграционные тесты для контроллера,
 * проверяющие обработку HTTP-запросов и ответы в контексте всего приложения.
 */
@SpringBootTest
@WebMvcTest(UserController.class)
public class UserControllerTest {

    // MockMvc - инструмент для тестирования MVC-контроллеров
    @Autowired
    private MockMvc mockMvc;

    // Мок сервиса UserService для изоляции от реальной бизнес-логики
    @Mock
    private UserService userService;

    // Экземпляр контроллера с внедренным моком сервиса
    @InjectMocks
    private UserController userController;

    // ObjectMapper для сериализации объектов в JSON
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Настраивает MockMvc перед выполнением каждого теста.
     */
    @Before
    public void setup() {
        // Инициализация моков
        MockitoAnnotations.initMocks(this);
        // Создание MockMvc с standaloneSetup
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    // Остальные методы тестов остаются без изменений
    // ...

    /**
     * Тестирует метод editUser контроллера.
     * Проверяет, что POST-запрос на "/users/edit/{id}" обновляет данные пользователя.
     *
     * @throws Exception в случае ошибки при выполнении запроса
     */
    @Test
    public void testEditUser() throws Exception {
        // Подготовка обновленных данных пользователя
        User user = new User();
        user.setFirstName("Updated");
        user.setLastName("User");
        user.setAge(31);
        user.setEmail("updated@example.com");
        user.setId(1);

        // Настройка поведения мока: при вызове editUser() возвращается обновленный пользователь
        doNothing().when(userService).editUser(1, user);

        mockMvc.perform(post("/users/edit/1")
                        // Устанавливаем тип контента JSON
                        .contentType(MediaType.APPLICATION_JSON)
                        // Отправляем обновленные данные пользователя в формате JSON
                        .content(objectMapper.writeValueAsString(user)))
                // Ожидаем редирект (HTTP 302 Found)
                .andExpect(status().isFound())
                // Проверяем, что происходит редирект на "/users/"
                .andExpect(redirectedUrl("/users/"));

        // Дополнительная проверка: убедимся, что метод editUser() был вызван с правильными аргументами
        verify(userService).editUser(1, user);
    }
    /**
     * Тестирует метод createUser контроллера.
     * Проверяет, что POST-запрос на "/users/create" создает нового пользователя.
     *
     * @throws Exception в случае ошибки при выполнении запроса
     */

    @Test
    public void testCreateUser() throws Exception {
        // Подготовка нового пользователя
        User user = new User();
        user.setFirstName("New");
        user.setLastName("User");
        user.setAge(20);
        user.setEmail("new@example.com");
        user.setId(1);
        // Настройка поведения мока: при вызове saveUser() возвращается сохраненный пользователь
        when(userService.saveUser(user)).thenReturn(user);

        // Выполнение POST-запроса на "/users/create"
        mockMvc.perform(post("/users/create")
                        // Устанавливаем тип контента JSON
                        .contentType(MediaType.APPLICATION_JSON)
                        // Отправляем данные пользователя в формате JSON
                        .content(objectMapper.writeValueAsString(user)))
                // Ожидаем редирект (HTTP 302 Found)
                .andExpect(status().isFound())
                // Проверяем, что происходит редирект на "/users/"
                .andExpect(redirectedUrl("/users/"));
    }

    /**
     * Тестирует метод deleteUser контроллера.
     * Проверяет, что GET-запрос на "/users/delete/{id}" удаляет пользователя.
     *
     * @throws Exception в случае ошибки при выполнении запроса
     */
    @Test
    public void testDeleteUser() throws Exception {
        // Выполнение GET-запроса на "/users/delete/1"
        mockMvc.perform(get("/users/delete/1"))
                // Ожидаем редирект (HTTP 302 Found)
                .andExpect(status().isFound())
                // Проверяем, что происходит редирект на "/users/"
                .andExpect(redirectedUrl("/users/"));
    }
}
