# JWT 
 - to use scopes or roles...they should be converted to Granted authority ( part of authentication )
 - default jwt converter converts only scopes to authority...but not roles
 - so, we are using custom converter to convert roles also as authorities
 - roles are pre-fixed with ROLE_ and scopes are pre-fixed with SCOPE_ in the granted authorities


 - Role hierarchy see security config

