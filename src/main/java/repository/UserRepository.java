package repository;

import domain.User;
import domain.UserType;
import exception.InfrastructureException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository extends BaseRepository<User>{

    private static final String LOG_ERROR_MSG = "Error during the user %s";


    public List<User> findAll() {

        List<User> users = new ArrayList<User>();

        Connection conn = openConnection();

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            while(resultSet.next()) {
                String id = resultSet.getString("id");
                String email = resultSet.getString("email");
                UserType userType = UserType.valueOf(resultSet.getString("userType"));

                users.add(new User(id, email, userType));
            }
        } catch (SQLException e) {
            logger.error(String.format(LOG_ERROR_MSG, "findAll"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "findAll"),e);
        } finally {
            closeConnection(conn);
        }


        return users;
    }

    public Optional<User> findById(String id) {

        Connection connection = openConnection();

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            statement.setString(1, id);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                UserType type = UserType.valueOf(resultSet.getString("userType"));

                return Optional.of(new User(id, email, password, type));
            }

        } catch(SQLException e){
            logger.error(String.format(LOG_ERROR_MSG, "findById"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "findById"),e);
        } finally {
            closeConnection(connection);
        }

        return Optional.empty();
    }

    public void remove(User user) {
        Connection connection = openConnection();

        try {
            String query = "DELETE FROM users WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getId());

            if(preparedStatement.executeUpdate() == 0 ) {
                throw new InfrastructureException("The delete was not executed!");
            }
        } catch (SQLException e){
            logger.error(String.format(LOG_ERROR_MSG, "delete"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "delete"),e);
        }
    }




    @Override
    public Optional<User> findByCriteria(String field, String id) {
        return Optional.empty();
    }


    @Override
    public void add(User user) {

    }

    @Override
    public void modify(User user) {

    }

}
