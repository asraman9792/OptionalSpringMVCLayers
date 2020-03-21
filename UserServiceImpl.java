@Service
public class UserService implements IUserService {

 @Inject
 private IUserDao userDao;

 @Inject
 private PasswordEncoder passwordEncoder;

 @Inject
 private RoleService roleService;

 @Override
 @Transactional(readOnly = false)
 public User saveOrUpdateUser(User user) throws BadRequestException {
  validateUser(user); 
  this.userDao.save(user);
  return user;
 }

 private void validateUser(UserRequest userRequest) throws BadRequestException {

  if (userRequest == null) {
   throw new BadRequestException(" User object can't be null.");
  }

  if (userRequest.getEmail() == null) {
   throw new BadRequestException(" User email can't be null.");
  }

  if (userRequest.getFirstName() == null) {
   throw new BadRequestException(" User First name can't be null.");
  }

  if (userRequest.getLastName() == null) {
   throw new BadRequestException(" User last name can't be null.");
  }

  if (userRequest.getPassword() == null) {
   throw new BadRequestException(" Password can't be null.");
  }
 }

 @Override
 @Transactional
 public boolean checkUserExist(String userName) {
  final long count = userDao.checkUserExist(userName);
  return count > 0;
 }

 @Override
 @Transactional
 public User loadUserByUsername(String userName) {
 return userDao.loadUserByUsername(userId).orElseThrow(
    () -> new ResourceNotFoundException(Integer.valueOf(userId)));
 }

 @Override
 @Transactional
 public void deleteUser(Integer userId) throws BaseException, ResourceNotFoundException {

  if (userId == null) {
   throw new BaseException(" Request param can't be null . " + userId);
  }
  final User user = userDao.getById(userId)
    .orElseThrow(() -> new UnauthorizedRequestException("User not exist"));
  user.setEnabled(false);
  userDao.update(user);

 }

 @Override
 @Transactional(readOnly = true)
 public List<UserResponse> getUsers() throws BaseException {

  final List<UserResponse> listOfUsers = userDao.getUsers();
  if (listOfUsers == null) {
   throw new BaseException("Users not exist in database");
  }
  return listOfUsers;
 }

 @Override
 @Transactional(readOnly = true)
 public User getUserById(Integer userId) throws BaseException {

  if (userId == null) {
   throw new BaseException(" Request param can't be null . " + userId);
  }

  return userDao.getById(userId).orElseThrow(
    () -> new ResourceNotFoundException(Integer.valueOf(userId)));
 }

}