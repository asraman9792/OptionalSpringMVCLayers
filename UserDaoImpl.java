@Repository
public class UserDao implements UserDao{

 @Autowired
 private SessionFactory sessionFactory;
 
 @Override
 public void saveOrUpdateUser(User user) {
  Session session = sessionFactory.getCurrentSession();
  session.saveOrUpdate(user);
 
 }
 
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

 @Override
 public long checkUserExist(String userName) {
  Session session = sessionFactory.getCurrentSession();
  Criteria criteria = session.createCriteria(User.class)
    .add(Restrictions.eq("userName", userName))
    .add(Restrictions.eq("enabled", true));
  criteria.setProjection(Projections.rowCount());
  return (Long) criteria.uniqueResult();
 }
 
 @Override
 public List<UserResponse> getUsers(){
  Session session = sessionFactory.getCurrentSession();
  final List<UserResponse> users = new ArrayList<>();
  
  // get users code here
  return users;
 }
 
 @Override
 public void deleteUser(User user){
  Session session = sessionFactory.getCurrentSession();
  session.delete(user);
 }

}