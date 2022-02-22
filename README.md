# ClickUp 4 Java

ClickUp4j attempts to be a complete, full wrapper for the ClickUp REST API in Java.

**This project is in active development and is not yet complete. If you intend to use this, be cautious things may change!**

## Creating the ClickUp4j Object

Creating the `ClickUp4j` Object is done via the `ClickUp4jBuilder` class.
After setting the token and other options via setters, 
the `ClickUp4j` Object is then created by calling the `build()` method.

**Example**:

```java
ClickUp4j clickup = ClickUp4jBuilder.create("token").build();
```

Now, you can start making requests. See below for a full example.

```java
import pw.chew.clickup4j.api.*;

public class MyClickUpProject {
    public static void main(String[] args) {
        // Build the ClickUp4j Object
        ClickUp4j clickup = ClickUp4jBuilder.create("token").build();
        // Queue up the request
        clickup.retrieveTask("aaaaa").queue(task -> {
            // Print out the title
            System.out.println(task.getTitle());
        });
    }
}
```

## Download

Latest Release: None yet!

Be sure to replace the **VERSION** key below with the one of the versions shown above! For snapshots, please use the instructions provided by [JitPack](https://jitpack.io/#JavaAPIs/ClickUp4j).

**Maven**
```xml
<dependency>
    <groupId>pw.chew.clickup4j</groupId>
    <artifactId>clickup4j</artifactId>
    <version>VERSION</version>
</dependency>
```

**Gradle**
```gradle
repositories {
    // soon
}

dependencies {
    // Change 'implementation' to 'compile' in old Gradle versions
    implementation("pw.chew.clickup4j:clickup4j:VERSION")
}
```

The snapshot builds are only available via JitPack and require adding the JitPack resolver, you need to specify specific commits to access those builds.

## Documentation

Docs can be found for the master branch directly [here](https://javaapis.github.io/ClickUp4j/)

A simple Wiki can also be found in this repository's [Wiki section](https://github.com/JavaAPIs/ClickUp4j/wiki)
