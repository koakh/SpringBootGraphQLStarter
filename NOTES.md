https://plugins.jetbrains.com/plugin/8097-js-graphql
https://github.com/jimkyndemeyer/js-graphql-intellij-plugin/issues/81
require to goto settings and enable Node.js and NPM 



# Spring Boot GraphQL Starter 

- [Getting Started with GraphQL and Spring Boot](http://www.baeldung.com/spring-graphql)
- [Download Eugenp Spring Tutorials : Files are in spring-boot project](https://github.com/eugenp/tutorials)
- [https://github.com/eugenp/tutorials/tree/master/spring-boot](https://github.com/eugenp/tutorials/tree/master/spring-boot)

# Related Links

- [Awesome graphql-java](https://github.com/graphql-java/awesome-graphql-java)

- [Writing a GraphQL service using Kotlin and Spring Boot](https://blog.pusher.com/writing-graphql-service-using-kotlin-spring-boot/)

- [Source](https://github.com/sazzer/pusher-familytree)

> Great Kotlin Post 

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

# Start Project

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

### Copy files from eugenp project to namespace, and refator namespace

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
    author: Author!
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
}

# The Root Mutation for the application
type Mutation {
    writePost(title: String!, text: String!, category: String) : Post!
}
```



No method found with any of the following signatures (with or without graphql.schema.DataFetchingEnvironment as the last argument), in priority order:

  com.koakh.springbootgraphqlstarter.resource.graphql.Mutation.writePost(~title, ~text, ~category)
  com.koakh.springbootgraphqlstarter.resource.graphql.Mutation.getWritePost(~title, ~text, ~category)



  public Post writePost(String title, String text, String category, String author) {


Unable to match type definition (NonNullType{type=TypeName{name='Author'}}) with java type (java.util.Optional<com.koakh.springbootgraphqlstarter.domain.Author>): graphql type is marked as nonnull but java.util.Optional was used

Unable to match type definition (NonNullType{type=TypeName{name='Author'}}) with java type (java.util.Optional<com.koakh.springbootgraphqlstarter.domain.Author>): graphql type is marked as nonnull but java.util.Optional was used






graphql.schema.GraphQLObjectType cannot be cast to graphql.schema.GraphQLInputType
	at graphql.schema.GraphQLArgument.replaceTypeReferences(





```json
query{
  getAuthor(id: "Author4") {
    id
    name
    thumbnail
    posts {
      id
      title
    }
  }
}
```

### Run and test root query and mutation query


### Configure H2

- [How to configure spring-boot to use file based H2 database](https://stackoverflow.com/questions/37903105/how-to-configure-spring-boot-to-use-file-based-h2-database)

Adding the following to my `application.properties` definitely creates the database file in the right place:

```
spring.datasource.url=jdbc:h2:file:~/test;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.username=test
spring.datasource.password=test
spring.datasource.driverClassName=org.h2.Driver
```

I can even connect to it with the **H2 console when devtools is enabled** 

[http://localhost:8080/h2-console/](http://localhost:8080/h2-console/)

The next logical step is to visit the [http://localhost:8080/autoconfig](http://localhost:8080/autoconfig) endpoint and check the auto-configuration status.

#### Run and Test

Endpoints: (Search Mapped "{[/)
    Actuator:
        http://localhost:8080/application
        http://localhost:8080/application/status
        http://localhost:8080/application/info
    GraphiQL
        http://localhost:8080/graphiql
            