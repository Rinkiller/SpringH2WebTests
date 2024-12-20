package Rin.compani.H2DBUser.Repositories;

import Rin.compani.H2DBUser.Aspects.TrackUserAction;
import Rin.compani.H2DBUser.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRpository {
    private final JdbcTemplate jdbc;

    @Autowired
    public UserRpository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    public List<User> findAll(){
        String sgl = "SELECT * FROM USERTABLE";
        RowMapper<User> userRowMapper = (r ,i) ->{
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            rowObject.setAge(r.getInt("age"));
            rowObject.setEmail(r.getString("email"));
            return rowObject;
        };
        return jdbc.query(sgl,userRowMapper);

    }
    @TrackUserAction
    public User save(User user){
        String sql = "INSERT INTO USERTABLE (firstName, lastName, age, email) VALUES ( ?, ?, ?, ?)";
        jdbc.update(sql,user.getFirstName(),user.getLastName(),user.getAge(),user.getEmail());
        return user;
    }

    public void deleteUser(int id) {
        String sql ="DELETE FROM USERTABLE WHERE Id=?";
        jdbc.update(sql,id);
    }
    /*поиск пользователя по id (int) в базе*/
    public User getUserById(int id) {
        String sql = "SELECT * FROM USERTABLE WHERE id=" + String.valueOf(id);
        RowMapper<User> userRowMapper = (r ,i) ->{
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            rowObject.setAge(r.getInt("age"));
            rowObject.setEmail(r.getString("email"));
            return rowObject;
        };
        return jdbc.query(sql,userRowMapper).get(0);
    }
 /*   присвоение новых данных пользователю с данным id*/
    public void editUser(int id, User user) {
        String sql = "UPDATE USERTABLE SET firstName = ?, lastName = ?, age = ? , email = ? WHERE id =" + String.valueOf(id);
        jdbc.update(sql, user.getFirstName(),user.getLastName(),user.getAge(),user.getEmail());
    }
}
