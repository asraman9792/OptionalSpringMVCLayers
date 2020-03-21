import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonManagedReference;

@Entity
@Table(name = "user")
public class User {

 private int userId;
 private String userName;
 private String password;
 private boolean enabled;
 private String firstName;
 private String lastName;
 private Long dateCreated;
 private Long dateUpdated;
 private long lastAccess;
 private long lastLogin;
 private String email;

 public User() {

 }

 public User(int userId, String userName, String password, boolean enabled
   ) {
  super();
  this.userId = userId;
  this.userName = userName;
  this.password = password;
  this.enabled = enabled;
 }

 
 
 public User(String userName, String password, boolean enabled,
   String firstName, String lastName, Long dateCreated, Long dateUpdated,
   long lastAccess, long lastLogin, String email) {
  super();
  this.userName = userName;
  this.password = password;
  this.enabled = enabled;
  this.firstName = firstName;
  this.lastName = lastName;
  this.dateCreated = System.currentTimeMillis();
  this.dateUpdated = System.currentTimeMillis();
  this.lastAccess = lastAccess;
  this.lastLogin = lastLogin;
  this.email = email;
 }

 @Id
 @Column(name = "userId")
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 public int getUserId() {
  return userId;
 }

 public void setUserId(int userId) {
  this.userId = userId;
 }

 public String getUserName() {
  return userName;
 }

 public void setUserName(String userName) {
  this.userName = userName;
 }

 public String getPassword() {
  return password;
 }

 public void setPassword(String password) {
  this.password = password;
 }

 public boolean isEnabled() {
  return enabled;
 }

 public void setEnabled(boolean enabled) {
  this.enabled = enabled;
 }

 public String getFirstName() {
  return firstName;
 }

 public void setFirstName(String firstName) {
  this.firstName = firstName;
 }

 public String getLastName() {
  return lastName;
 }

 public void setLastName(String lastName) {
  this.lastName = lastName;
 }

 public long getLastAccess() {
  return lastAccess;
 }

 public void setLastAccess(long lastAccess) {
  this.lastAccess = lastAccess;
 }

 public long getLastLogin() {
  return lastLogin;
 }

 
 public void setLastLogin(long lastLogin) {
  this.lastLogin = lastLogin;
 }
 
 public Long getDateCreated() {
  return dateCreated;
 }

 public void setDateCreated(Long dateCreated) {
  this.dateCreated = dateCreated;
 }

 public Long getDateUpdated() {
  return dateUpdated;
 }

 public void setDateUpdated(Long dateUpdated) {
  this.dateUpdated = dateUpdated;
 }

 public String getEmail() {
  return email;
 }

 public void setEmail(String email) {
  this.email = email;
 }

}