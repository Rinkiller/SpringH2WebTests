package Rin.compani.H2DBUser;



import Rin.compani.H2DBUser.Model.User;
import Rin.compani.H2DBUser.Repositories.UserRpository;
import Rin.compani.H2DBUser.Service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class H2DbUserApplicationTests {

		// Мок репозитория пользователей
		@Mock
		private UserRpository userRepository;

		// Инстанс UserService с внедренным моком репозитория
		@InjectMocks
		private UserService userService;

		/**
		 * Инициализирует моки перед каждым тестом.
		 */
		@Before
		public void setUp() {
			MockitoAnnotations.initMocks(this);
		}

	/**
	 * Тестирование метода saveUser.
	 * Проверяет, что метод вызывает соответствующий метод репозитория
	 * и возвращает сохраненного пользователя.
	 */
	@Test
	public void testSaveUser() {
		// Создаем тестового пользователя
		User user = new User();
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setAge(30);
		user.setEmail("john@example.com");

		// Настройка поведения мока: при вызове save() он должен вернуть тот же объект
		when(userRepository.save(any(User.class))).thenReturn(user);

		// Вызываем метод saveUser
		User savedUser = userService.saveUser(user);

		// Проверяем, что метод save() репозитория был вызван один раз
		verify(userRepository, times(1)).save(any(User.class));

		// Проверяем, что возвращенный объект идентичен исходному
		assertEquals(user, savedUser);
	}

	/**
	 * Тестирование метода findAllUsers.
	 * Проверяет, что метод вызывает соответствующий метод репозитория
	 * и возвращает список всех пользователей.
	 */
	@Test
	public void testFindAllUsers() {
		// Создаем список тестовых пользователей
		List<User> users = new ArrayList<>();
		User user1 = new User();
		user1.setFirstName("John");
		user1.setLastName("Doe");
		user1.setAge(30);
		user1.setEmail("john@example.com");
		User user2 = new User();
		user2.setFirstName("John");
		user2.setLastName("Doe");
		user2.setAge(30);
		user2.setEmail("john@example.com");
		users.add(user1);
		users.add(user2);

		// Настройка поведения мока: при вызове findAll() он должен вернуть подготовленный список
		when(userRepository.findAll()).thenReturn(users);

		// Вызываем метод findAllUsers
		List<User> result = userService.findAllUsers();

		// Проверяем, что метод findAll() репозитория был вызван один раз
		verify(userRepository, times(1)).findAll();

		// Проверяем, что возвращенный список идентичен исходному
		assertEquals(users, result);
	}
	/**
	 * Тестирование метода getUserById.
	 * Проверяет, что метод вызывает соответствующий метод репозитория
	 * и возвращает пользователя по заданному ID.
	 */
	@Test
	public void testGetUserById() {
		// Создаем тестового пользователя
		User user = new User();
		int userId = 1;
		user.setId(userId);
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setAge(30);
		user.setEmail("john@example.com");
		// Настройка поведения мока: при вызове getUserById() он должен вернуть Optional с пользователем
		when(userRepository.getUserById(userId)).thenReturn(user);

		// Вызываем метод getUserById
		User result = userService.getUserById(userId);

		// Проверяем, что метод getUserById() репозитория был вызван один раз
		verify(userRepository, times(1)).getUserById(userId);

		// Проверяем, что возвращенный пользователь идентичен исходному
		assertEquals(user, result);
	}
	/**
	 * Тестирование метода deleteUser.
	 * Проверяет, что метод вызывает соответствующий метод репозитория.
	 */
	@Test
	public void testDeleteUser() {
		int userId = 1;

		// Настройка поведения мока: при вызове deleteUser() ничего не происходит
		doNothing().when(userRepository).deleteUser(userId);

		// Вызываем метод deleteUser
		userService.deleteUser(userId);

		// Проверяем, что метод deleteUser() репозитория был вызван один раз
		verify(userRepository, times(1)).deleteUser(userId);
	}
	/**
	 * Тестирование метода editUser.
	 * Проверяет, что метод вызывает соответствующие методы репозитория.
	 */
	@Test
	public void testEditUser() {
		// Создаем исходного пользователя
		User originalUser = new User();
		originalUser.setId(1);
		originalUser.setFirstName("John");
		originalUser.setLastName("Doe");
		originalUser.setAge(30);
		originalUser.setEmail("john@example.com");


		// Создаем обновленного пользователя
		User updatedUser = new User();
		updatedUser.setFirstName("David");
		updatedUser.setLastName("Lee");
		updatedUser.setAge(46);
		updatedUser.setEmail("david@example.com");
		int userId = 1;

		// Настройка поведения мока: при вызове getUserById() он должен вернуть Optional с исходным пользователем
		when(userRepository.getUserById(userId)).thenReturn(originalUser);
		// Настройка поведения мока: при вызове editUser() ничего не происходит
		doNothing().when(userRepository).editUser(userId, any(User.class));

		// Вызываем метод editUser
		userService.editUser(userId, updatedUser);

		// Проверяем, что метод getUserById() репозитория был вызван один раз
		verify(userRepository, times(1)).getUserById(userId);
		// Проверяем, что метод editUser() репозитория был вызван один раз
		verify(userRepository, times(1)).editUser(userId, any(User.class));
	}
}
