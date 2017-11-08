# Spring Boot GraphQL Starter 

- [Getting Started with GraphQL and Spring Boot](http://www.baeldung.com/spring-graphql)

- [Download Eugenp Spring Tutorials : Files are in spring-boot project](https://github.com/eugenp/tutorials)

- [https://github.com/eugenp/tutorials/tree/master/spring-boot](https://github.com/eugenp/tutorials/tree/master/spring-boot)

# Related Links

- [Awesome graphql-java](https://github.com/graphql-java/awesome-graphql-java)

- [Writing a GraphQL service using Kotlin and Spring Boot](https://blog.pusher.com/writing-graphql-service-using-kotlin-spring-boot/)

- [Source](https://github.com/sazzer/pusher-familytree)

> Great Kotlin Post 

# Other Links

- [Spring Boot @Qualifier annotation](http://zetcode.com/articles/springbootqualifier/)

# Warning dont use milestones spring boot starter parent ex 2.0.0.M5, use RELEASE like 1.5.8.RELEASE

> After try to reach /graphql endpoint, and always get this error

```json
{
  "timestamp": 1508165237543,
  "status": 404,
  "error": "Not Found",
  "message": "No message available",
  "path": "/graphql"
}
```
find this post [graphql-java-tools fail with spring boot 2.0.0M3](https://stackoverflow.com/questions/46773274/graphql-java-tools-fail-with-spring-boot-2-0-0m3)
that explains the problem, Im using 

```xml
<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-parent</artifactId>
  <version>2.0.0.M5</version>
  <relativePath/> <!-- lookup parent from repository -->
</parent>
```

! Kotlin demo uses `1.5.6.RELEASE` and it works, like my old project with `1.5.7.RELEASE`

use this release parent

```xml
<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-parent</artifactId>
  <version>1.5.8.RELEASE</version>
  <relativePath/> <!-- lookup parent from repository -->
</parent>
```

# Start Project Implementation

Create a spring boot initializr maven project with 1.5.8.RELEASE and

Selected Dependencies

- Actuator
- Web
- DevTools
- H2
- JPA

add to `pom.xml`

```xml
<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-parent</artifactId>
  <version>1.5.8.RELEASE</version>
  <relativePath/> <!-- lookup parent from repository -->
</parent>

<dependencies>
  ...
  <dependency>
    <groupId>com.graphql-java</groupId>
    <artifactId>graphiql-spring-boot-starter</artifactId>
    <version>3.6.0</version>
  </dependency>
  <dependency>
    <groupId>com.graphql-java</groupId>
    <artifactId>graphql-java-tools</artifactId>
    <version>4.0.0</version>
  </dependency>
  <dependency>
    <groupId>com.graphql-java</groupId>
    <artifactId>graphql-spring-boot-starter</artifactId>
    <version>3.6.0</version>
  </dependency>
</dependencies>
```

### Open Project in IDEA

add simple `src/main/resources/graphql/schema.graphqls` with just a simple root type to output version

```json
type Query {
  # The API Version
  version: String!
}
```

add `GraphqlConfiguration` file `src/main/java/com/koakh/springbootgraphqlstarter/GraphqlConfiguration.java`

```java
package com.koakh.springbootgraphqlstarter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphqlConfiguration {
  @Bean
  public Query query() {
    return new Query();
  }
}
```

add root query `src/main/java/com/koakh/springbootgraphqlstarter/Query.java`

```java
/**
 * Root Query Resolver
 * The root query needs to have special beans defined in the Spring context to handle the various fields in this root query. Unlike the schema definition, there is no restriction that there only be a single Spring bean for the root query fields.
 * The only requirements are that the beans implement GraphQLQueryResolver and that every field in the root query from the schema has a method in one of these classes with the same name.
 */
@Component
public class Query implements GraphQLQueryResolver {
  public String version() {
    return "1.0.0";
  }
}
```

test it

```shell
mvn install
mvn spring-boot:run
```

### Copy files from eugenp project to namespace, and refactor namespace

```shell
cp .src/tutorials-master/spring-boot/src/main/java/com/baeldung/graphql/* src/main/java/com/example/demo/
```

- AuthorDao.java
- Author.java
- AuthorResolver.java
- GraphqlConfiguration.java
- Mutation.java
- PostDao.java
- Post.java
- PostResolver.java
- Query.java

Now change default namespace `com.example.demo` to `com.koakh.springbootgraphqlstarter` in project this way we change all classes to new package name

replace old `schema.graphqls` with

```json
type Post {
  id: ID!
  title: String!
  text: String!
  category: String
  author: Author
}

type Author {
  id: ID!
  name: String!
  thumbnail: String
  posts: [Post]!
}

# The Root Query for the application
type Query {
  # The API Version
  version: String!
  # Blog
  recentPosts(count: Int, offset: Int): [Post]!
  getAuthor(id: String!): Author
}

# The Root Mutation for the application
type Mutation {
  writePost(title: String!, text: String!, category: String, author: String!): Post!
}
```

add extra Query endpoint to Query.java

```java
public Optional<Author> getAuthor(String id) {

  return authorDao.getAuthor(id);
}
```

# Reorganize Files Structure

- configuration/DaoConfiguration.java
- configuration/GraphqlConfiguration.java
- dao
- dao/AuthorDao.java
- dao/PostDao.java
- domain
- domain/Author.java
- domain/Post.java
- repository
- repository/AuthorRepository.java
- repository/PostRepository.java
- resource/graphql
- resource/graphql/AuthorResolver.java
- resource/graphql/Mutation.java
- resource/graphql/PostResolver.java
- resource/graphql/Query.java
- Application.java

# Run and test root query and mutation query

```shell
mvn install
mvn spring-boot:run
```

Create some new mutations and query like

```json
# The Root Query for the application
type Query {
  getAuthors(count: Int, offset: Int): [Author]!
  getAuthor(id: String!): Author
}

# The Root Mutation for the application
type Mutation {
  createAuthor(name: String!, thumbnail: String): Author!
}
```

```json
query {
  version
}
```

```json
query {
  recentPosts(count: 10, offset: 0) {
    id
    title
    text
    category
    author {
      id
      name
      thumbnail
      posts {
        id
        title
      }
    }
  }
}
```

```json
mutation {
  createAuthor(name: "Diana", thumbnail: "http://example.com/authors/diana") {
    id
    name
  }
}
```

```json
mutation {
  writePost(title: "post title diana", text: "post text diana", 
    category: "development", 
    author: "6be4d543-f8db-4386-9727-63fefc137370") {
    id
  }
}
```

```json
query{
  getAuthor(id: "6be4d543-f8db-4386-9727-63fefc137370") {
    id
    name
    thumbnail
    posts {
      id
      title
      text
      category
    }
  }
}
```

# Configure H2 Database

- [How to configure spring-boot to use file based H2 database](https://stackoverflow.com/questions/37903105/how-to-configure-spring-boot-to-use-file-based-h2-database)

Adding the following to my `application.properties` definitely creates the database file in the right place:

```yml
spring.datasource.url=jdbc:h2:file:~/test;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.username=test
spring.datasource.password=test
spring.datasource.driverClassName=org.h2.Driver
```

### Use H2 console when devtools is enabled

- [http://localhost:8080/h2-console/](http://localhost:8080/h2-console/)

### Turn Pojos domain into JPA Entities

Add `@Entity` and `@Id` to `Author` and `Posts` Entities, to turn pojos into spring managed jpa beans, else 

> `Error creating bean with name 'postService': Invocation of init method failed; nested exception is java.lang.IllegalArgumentException: Not a managed type: class com.koakh.springbootgraphqlstarter.domain.Post`

ex

```java
@Entity
public class Author {
  @Id
  private String id;
  private String name;
  private String thumbnail;
  ...
```

# Moke SQL DAta 

`src/main/resources/application.yml`

```yml
spring:
  datasource:
    url: jdbc:h2:file:~/test;DB_CLOSE_ON_EXIT=FALSE
    username: test
    password: test
    driverClassName: org.h2.Driver
  jpa:
    show-sql: true
    hibernate:
      ddl-auto: create-drop
      naming:
        # Required to use db fields with field names and use @Column annotation, else authorId Truens into AUTHOR_ID
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
```

`src/main/resources/data.sql`

```sql
INSERT INTO Author (id, name, thumbnail ) VALUES ('4f2a2492-c279-11e7-abc4-cec278b6b50a', 'mario', 'http://example.com/authors/mario');
INSERT INTO Author (id, name, thumbnail ) VALUES ('4f2a2faa-c279-11e7-abc4-cec278b6b50a', 'alex', 'http://example.com/authors/alex');
INSERT INTO Author (id, name, thumbnail ) VALUES ('4f2a3284-c279-11e7-abc4-cec278b6b50a', 'jorge', 'http://example.com/authors/jono');
INSERT INTO Author (id, name, thumbnail ) VALUES ('4f2a3450-c279-11e7-abc4-cec278b6b50a', 'bruno', 'http://example.com/authors/bruno');
INSERT INTO Author (id, name, thumbnail ) VALUES ('4f2a35cc-c279-11e7-abc4-cec278b6b50a', 'andreia', 'http://example.com/authors/andreia');
INSERT INTO Author (id, name, thumbnail ) VALUES ('4f2a35ca-c279-11e7-abc4-cec278b6b50a', 'diana', 'http://example.com/authors/diana');

INSERT INTO Post (id, title, text, category, authorId) VALUES ('b4415228-c27a-11e7-abc4-cec278b6b50a', 'title post 02', 'text post 02', 'category b', '4f2a35cc-c279-11e7-abc4-cec278b6b50a');
INSERT INTO Post (id, title, text, category, authorId) VALUES ('b4415229-c27a-11e7-abc4-cec278b6b50a', 'title post 03', 'text post 03', 'category c', '4f2a2492-c279-11e7-abc4-cec278b6b50a');
INSERT INTO Post (id, title, text, category, authorId) VALUES ('b4415230-c27a-11e7-abc4-cec278b6b50a', 'title post 04', 'text post 04', 'category d', '4f2a3284-c279-11e7-abc4-cec278b6b50a');
INSERT INTO Post (id, title, text, category, authorId) VALUES ('b4415231-c27a-11e7-abc4-cec278b6b50a', 'title post 05', 'text post 05', 'category e', '4f2a3450-c279-11e7-abc4-cec278b6b50a');
INSERT INTO Post (id, title, text, category, authorId) VALUES ('b4415232-c27a-11e7-abc4-cec278b6b50a', 'title post 06', 'text post 06', 'category a', '4f2a35ca-c279-11e7-abc4-cec278b6b50a');
INSERT INTO Post (id, title, text, category, authorId) VALUES ('b4415233-c27a-11e7-abc4-cec278b6b50a', 'title post 07', 'text post 07', 'category b', '4f2a3284-c279-11e7-abc4-cec278b6b50a');
INSERT INTO Post (id, title, text, category, authorId) VALUES ('b4415234-c27a-11e7-abc4-cec278b6b50a', 'title post 08', 'text post 08', 'category c', '4f2a2faa-c279-11e7-abc4-cec278b6b50a');
INSERT INTO Post (id, title, text, category, authorId) VALUES ('b4415235-c27a-11e7-abc4-cec278b6b50a', 'title post 09', 'text post 09', 'category d', '4f2a35ca-c279-11e7-abc4-cec278b6b50a');
INSERT INTO Post (id, title, text, category, authorId) VALUES ('b4415236-c27a-11e7-abc4-cec278b6b50a', 'title post 10', 'text post 10', 'category e', '4f2a3450-c279-11e7-abc4-cec278b6b50a');
INSERT INTO Post (id, title, text, category, authorId) VALUES ('b4415237-c27a-11e7-abc4-cec278b6b50a', 'title post 01', 'text post 01', 'category a', '4f2a2492-c279-11e7-abc4-cec278b6b50a');
INSERT INTO Post (id, title, text, category, authorId) VALUES ('b4415238-c27a-11e7-abc4-cec278b6b50a', 'title post 11', 'text post 11', 'category a', '4f2a35cc-c279-11e7-abc4-cec278b6b50a');
INSERT INTO Post (id, title, text, category, authorId) VALUES ('b4415239-c27a-11e7-abc4-cec278b6b50a', 'title post 12', 'text post 12', 'category b', '4f2a2faa-c279-11e7-abc4-cec278b6b50a');
```

# Run and Test

> WIP

# Configure Interfaces for Dao and H2 Repositories

View Implemented files in project

### Problems : The dependencies of some of the beans in the application context form a cycle

> Never use Impl PostFix ex PostRepositoryH2Impl, else it gives circular problems

the problem is that we have a JPA PostRepositoryH2 class and PostRepositoryH2Impl, and it turns into a conflit because of [Custom implementations for Spring Data repositories](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.custom-implementations) that uses this postfix convention

```java
class CustomizedUserRepositoryImpl implements CustomizedUserRepository
```

> Tip: Use `PostRepositoryH2Implementation` or other class name for Implementation

error 

```
***************************
APPLICATION FAILED TO START
***************************

Description:

The dependencies of some of the beans in the application context form a cycle:

┌─────┐
|  postRepositoryH2Impl defined in file [/home/mario/Documents/Development/IntelliJIdeaProjects/SpringBootGraphQLStarter/target/classes/com/koakh/springbootgraphqlstarter/repository/h2/PostRepositoryH2Impl.class]
└─────┘
```

# Actuator

`src/main/resources/application.yml`

disable security to use actuator

```yml
# Actuator
# Full authentication is required to access actuator endpoints. Consider adding Spring Security or set 'management.security.enabled' to false.
management:
  security:
    enabled: false
```
else 

```
2017-11-07 22:53:35.677  INFO 8216 --- [nio-8080-exec-1] s.b.a.e.m.MvcEndpointSecurityInterceptor : Full authentication is required to access actuator endpoints. Consider adding Spring Security or set 'management.security.enabled' to false.
```

The next logical step is to visit the [http://localhost:8080/autoconfig](http://localhost:8080/autoconfig) endpoint and check the auto-configuration status.

**other mappings**

- [http://localhost:8080/mappings](http://localhost:8080/mappings)
- [http://localhost:8080/autoconfig](http://localhost:8080/autoconfig)
- [http://localhost:8080/beans](http://localhost:8080/beans)
- [http://localhost:8080/metrics](http://localhost:8080/metrics)
