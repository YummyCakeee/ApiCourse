package ru.nikita.apicourse.mapper;

import ru.nikita.apicourse.dao.UserDao;
import ru.nikita.apicourse.models.User;

public class UserMapper {

    public static UserDao userToUserDao(User user) {
        return UserDao.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .secondName(user.getSecondName())
                .email(user.getEmail())
                .build();
    }

    public static User userDaoToUser(UserDao userDao) {
        return User.builder()
                .id(userDao.getId())
                .firstName(userDao.getFirstName())
                .secondName(userDao.getSecondName())
                .email(userDao.getEmail())
                .password(userDao.getPassword())
                .build();
    }
}
