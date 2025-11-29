package org.aston.unit.service;

import org.aston.dao.UserDao;
import org.aston.model.User;
import org.aston.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService userService;

    @Test
    void getUserById_shouldReturnUser() {
        User user = new User("John", "john@mail.com", 25);

        Mockito.when(userDao.get(1L))
                .thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals("John", result.getName());
        assertEquals("john@mail.com", result.getEmail());
        assertEquals(25, result.getAge());
    }

    @Test
    void getUserById_shouldThrowException_whenNotFound() {
        Mockito.when(userDao.get(999L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> userService.getUserById(999L));
    }
}
