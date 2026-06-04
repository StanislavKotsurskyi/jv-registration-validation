package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.exception.InvalidDataException;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        if (user == null) {
            throw new InvalidDataException("User can`t be null");
        }
        if (user.getLogin() == null
                || user.getPassword() == null
                || user.getAge() == null) {
            throw new InvalidDataException("User fields cannot be null");
        }
        if (user.getLogin().length() < 6) {
            throw new InvalidDataException("User login length must be at least 6 characters");
        }
        if (user.getPassword().length() < 6) {
            throw new InvalidDataException("User password length must be at least 6 characters");
        }
        if (user.getAge() < 18) {
            throw new InvalidDataException("User must be at least 18 years old");
        }
        if (storageDao.get(user.getLogin()) != null) {
            throw new InvalidDataException("User already exists");
        }
        return storageDao.add(user);
    }
}
