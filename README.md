#gwt-generators

This tool makes developing with [GWT](http://www.gwtproject.org/) easier.  
Use it to create [CellTable](http://www.gwtproject.org/javadoc/latest/com/google/gwt/user/cellview/client/CellTable.html).

How to use it:

get from [The Central Repository](http://search.maven.org/)
```xml
   <dependency>
      <groupId>com.github.symulakr</groupId>
      <artifactId>gwt-generators</artifactId>
      <version>0.2.2</version>
      <scope>compile</scope>
   </dependency>
```

```java
public class User{

   private String firstName;
   private String lastName;
   private String email;

   public String getFirstName(){
      return firstName;
   }

   public String getLastName(){
      return lastName;
   }

   public String getEmail(){
      return email;
   }
   ......
}
```
Just implement [CellTableModel](https://github.com/symulakr/gwt-generators/blob/master/src/main/java/com/github/symulakr/gwt/generators/client/celltable/CellTableModel.java):

```java 
public class User implements CellTableModel
```
Mark getters which should be represented as columns by [@Column](https://github.com/symulakr/gwt-generators/blob/master/src/main/java/com/github/symulakr/gwt/generators/client/celltable/annotation/Column.java) annotation:
```java
.......
   @Column(header = "First Name")
   public String getFirstName(){
      return firstName;
   }

   @Column(header = "Last Name")
   public String getLastName(){
      return lastName;
   }

   @Column(header = "Email")
   public String getEmail(){
      return email;
   }
.......
```
Now use this model to create a table:
```java
CellTable<User> userCellTable = GWT.create(User.class);
//Just set "List<User> users" to this table
userCellTable.setRowData(users);
```

![](https://raw.githubusercontent.com/symulakr/generators-example/master/src/main/resources/com/github/symulakr/img/user_table.png "User table")
 
