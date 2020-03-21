public interface UserDao {
 User saveOrUpdateUser(UserRequest userRequest) throws BadRequestException;
 boolean checkUserExist(String userName);
 Optional<User> loadUserByUsername(String userName);
 void deleteUser(int userId) throws BaseException, ResourceNotFoundException;
 List<UserResponse> getUsers() throws BaseException;
 Optional<User> getUserById(Integer userId) throws BaseException;
}