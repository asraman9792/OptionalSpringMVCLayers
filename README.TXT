1.In DAO layer,the code to retrieve a record by id or some fields from the database, basically, it is not sure whether the record exists or not, in that case, the method returns the null reference.code to avoid such null pointer exception.
Example:
@Override
public Optional<User> getUserById(Integer userId) {
 return Optional.ofNullable((User) sessionFactory.getCurrentSession()
   .createCriteria(User.class).add(Restrictions.eq("userId", userId))
   .uniqueResult());
}

@Override
public Optional<User> loadUserByUsername(String userName) {
 Session session = sessionFactory.getCurrentSession();
 Criteria criteria = session.createCriteria(User.class)
   .add(Restrictions.eq("userName", userName))
   .add(Restrictions.eq("enabled", true));
 return Optional.ofNullable((User) criteria.uniqueResult());

}
Note that the usage Optional.ofNullable() static factory method which returns value if present otherwise returns an empty Optional instance.

2. Usage of Optional Class API's in Service Layer
 In Service Layer, we need to handle whether the record exists or not. If it exists then return the object otherwise throw an exception or report an error to the client.
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
public User getUserById(Integer userId) throws BaseException {

 if (userId == null) {
  throw new BaseException(" Request param can't be null . " + userId);
 }

 return userDao.getById(userId).orElseThrow(
   () -> new ResourceNotFoundException(Integer.valueOf(userId)));
}
Note that the usage orElseThrow() method which returns value if present otherwise throws an exception. In the above,  the Optional.orElseThrow() method is used with a lambda expression.


3. Usage of Java 8 Optional API in Controller Layer
Let's assume, UserService interface in injected in UserController class then the UserService interface methods can be used in UserController directly.

In this example, note that the usage isPresent() method of Optional Class used.
@RequestMapping(path = "/users/{userId}", 
 method = RequestMethod.GET, 
 produces = MediaType.APPLICATION_JSON_VALUE)
public @ResponseBody ResponseEntity<User> getUser(
  @PathVariable("userId") String userId) {
 Optional<User> user = userService.getUser(userId);
 if (user.isPresent()) {
  return ResponseEntity.ok().body(user.get());
 }
 return ResponseEntity.badRequest().build();
}
One more use case is, if you have used the Generic Dao layer then the Optional Class API can be used like :
@Override
public Optional<T> getById(final K id) {
 try {
  return Optional.ofNullable((T) getSession().get(getType(), id));
 } catch (final RuntimeException re) {
  LOGGER.error("get failed", re);
  throw re;
 }
} 
