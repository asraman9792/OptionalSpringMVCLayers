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