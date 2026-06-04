package core.basesyntax.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.exception.InvalidDataException;
import core.basesyntax.model.User;
import org.junit.jupiter.api.Test;

class RegistrationServiceImplTest {

    @Test
    void wrongAge_notOk() {
        User user = new User();
        user.setAge(15);
        user.setLogin("dasersix11");
        user.setPassword("1234565");
        RegistrationServiceImpl reg = new RegistrationServiceImpl();
        assertThrows(InvalidDataException.class, () -> reg.register(user));
    }

    @Test
    void minusAge_notOk() {
        User user = new User();
        user.setAge(-5);
        user.setLogin("dasersix111");
        user.setPassword("1234565");
        RegistrationServiceImpl reg = new RegistrationServiceImpl();
        assertThrows(InvalidDataException.class, () -> reg.register(user));
    }

    @Test
    void edgeAge_notOk() {
        User user = new User();
        user.setAge(17);
        user.setLogin("dasersix1111");
        user.setPassword("1234565");
        RegistrationServiceImpl reg = new RegistrationServiceImpl();
        assertThrows(InvalidDataException.class, () -> reg.register(user));
    }

    @Test
    void exactAge_Ok() {
        User user = new User();
        user.setAge(18);
        user.setLogin("dasersix45");
        user.setPassword("12345665");
        RegistrationServiceImpl reg = new RegistrationServiceImpl();
        User result = reg.register(user);
        assertNotNull(result);
        assertEquals(18, result.getAge());
    }

    @Test
    void wrongPassword_notOk() {
        User user = new User();
        user.setAge(19);
        user.setLogin("sazadi4");
        user.setPassword("1234");
        RegistrationServiceImpl reg = new RegistrationServiceImpl();
        assertThrows(InvalidDataException.class, () -> reg.register(user));
    }

    @Test
    void edgePassword_notOk() {
        User user = new User();
        user.setAge(19);
        user.setLogin("sazadi41");
        user.setPassword("12345");
        RegistrationServiceImpl reg = new RegistrationServiceImpl();
        assertThrows(InvalidDataException.class, () -> reg.register(user));
    }

    @Test
    void exactPassword_Ok() {
        User user = new User();
        user.setAge(19);
        user.setLogin("sazadi75");
        user.setPassword("123456");
        RegistrationServiceImpl reg = new RegistrationServiceImpl();
        User result = reg.register(user);
        assertNotNull(result);
        assertEquals("123456", result.getPassword());
    }

    @Test
    void wrongLogin_notOk() {
        User user = new User();
        user.setAge(19);
        user.setLogin("dase");
        user.setPassword("12345678");
        RegistrationServiceImpl reg = new RegistrationServiceImpl();
        assertThrows(InvalidDataException.class, () -> reg.register(user));
    }

    @Test
    void edgeLogin_notOk() {
        User user = new User();
        user.setAge(19);
        user.setLogin("daser");
        user.setPassword("12345678");
        RegistrationServiceImpl reg = new RegistrationServiceImpl();
        assertThrows(InvalidDataException.class, () -> reg.register(user));
    }

    @Test
    void exactLogin_Ok() {
        User user = new User();
        user.setAge(19);
        user.setLogin("dasers");
        user.setPassword("12345678");
        RegistrationServiceImpl reg = new RegistrationServiceImpl();
        User result = reg.register(user);
        assertNotNull(result);
        assertEquals("dasers", result.getLogin());
    }

    @Test
    void emptyUser_notOk() {
        User user = new User();
        RegistrationServiceImpl reg = new RegistrationServiceImpl();
        assertThrows(InvalidDataException.class, () -> reg.register(user));
    }

    @Test
    void userExists_notOk() {
        assertThrows(InvalidDataException.class, () -> {
            User user = new User();
            user.setAge(19);
            user.setLogin("dasersix");
            user.setPassword("12345678");
            RegistrationServiceImpl reg = new RegistrationServiceImpl();
            reg.register(user);
            reg.register(user);
        });
    }

    @Test
    void nullUser_notOk() {
        User user = null;
        RegistrationServiceImpl reg = new RegistrationServiceImpl();
        assertThrows(InvalidDataException.class, () -> reg.register(user));
    }

    @Test
    void nullPassword_notOk() {
        User user = new User();
        user.setAge(19);
        user.setLogin("12345678");
        user.setPassword(null);
        RegistrationServiceImpl reg = new RegistrationServiceImpl();
        assertThrows(InvalidDataException.class, () -> reg.register(user));
    }

    @Test
    void nullLogin_notOk() {
        User user = new User();
        user.setAge(19);
        user.setLogin(null);
        user.setPassword("dasersazadi");
        RegistrationServiceImpl reg = new RegistrationServiceImpl();
        assertThrows(InvalidDataException.class, () -> reg.register(user));
    }

    @Test
    void nullAge_notOk() {
        User user = new User();
        user.setAge(null);
        user.setLogin("doublebyte");
        user.setPassword("12345678");
        RegistrationServiceImpl reg = new RegistrationServiceImpl();
        assertThrows(InvalidDataException.class, () -> reg.register(user));
    }
}
