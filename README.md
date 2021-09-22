# spring-security-template

This template contains Spring Security implementations using Spring Boot for Basic Auth, JWT and JWT using 2FA. The respective implentations can be found in seperate braches.

## How to use it

Check out the deired branch containing your security method. The projects are built up as a starting point contianing all neccessary security components. The application can be run without dependencies like databases etc.
Moreover, components are created to be extensible with your logic, like changing the source of your users using any database.

## Basic logic

The projec implements some basic logic like roles, permissions, security filters etc.

### Roles, Permissions

Roles and their permissions are defined as enums. There is an Admin and User role and a write and read permission. This can be extended to your needs.

### Preauthorize

Some hello world controllers are defined with `@Preauthorize` annotations. These annotations check for the right role or permission.

### CustomUserDetailDao

There is a `CustomUserDetailDao` interface which already contains a test implementation `CustomUserDetailsDaoImpl`. To use your own logic, implement this interface to get a user with his username from your datasource.

### PasswordConfig

Change the `PasswordConfig` class for your desired password encoder. By default, BCrypt is used.

### JWT 

Using JWT token, make a post request to `/login` with a JSON body as follows
`
{ 
  "username": "bob",
  "password": "password"
}
`.
\
JWT configurations can be changed in the `application.yml`. Add a secure and long secret-key wich will be used to sign the tokens. Set the expiration date for the token and the token-prefix.

### JWT 2FA

Using JWT with 2FA, make a post request to `/login` with a JSON body as follows
`
{ 
  "username": "bob",
  "password": "password",
  "code": "1234"
}
`.
\
You can easily implement your own 2FA logic by implementing the `TwofaAuthentication` interface. For an example see the `TwofaAuthenticationImpl`.

