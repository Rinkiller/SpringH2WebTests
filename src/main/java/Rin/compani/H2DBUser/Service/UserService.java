package Rin.compani.H2DBUser.Service;

import Rin.compani.H2DBUser.Aspects.TrackUserAction;
import Rin.compani.H2DBUser.Model.User;
import Rin.compani.H2DBUser.Repositories.UserRpository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRpository userRepository;
    @Autowired
    public UserService(UserRpository userRepository) {
        this.userRepository = userRepository;
    }
     /*Ищем всех User в репозитории*/
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }
    /*Сохраняем user в репозиторий*/
    public User saveUser(User user){
        return userRepository.save(user);
    }
    @TrackUserAction
    public void deleteUser(int id){
        userRepository.deleteUser(id);
    }
    /*Поиск пользователя по id (int)*/
    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

    /*редактирование пользователя с id*/
    public void editUser(int id, User user) {
        userRepository.editUser(id,user);
    }

    public void deleteAllUsers() {
    }
}
