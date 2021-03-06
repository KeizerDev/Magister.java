[<img src="http://i.imgur.com/TSBwaOQ.png" alt="Magister.java" align="left"/>](https://github.com/iLexiconn/magister6-api)
<p align="right">
    <a href="https://travis-ci.org/iLexiconn/Magister.java">
        <img src="https://img.shields.io/travis/iLexiconn/Magister.java.png?style=flat-square" alt="Travis CI Badge"/>
    </a>
</p>

==========

A Java implementation of the [Magister 6](http://magister6.nl/) API.

Quickstart
==========
NOTE: The maven artifacts are not yet available, as we haven't released 0.1.0 yet.

To use this API is your project, you just have to add this snippet if you're using Grade:
```gradle
repositories {
    maven {
        name = "ilexiconn"
        url = "http://maven.ilexiconn.net/"
    }
}

dependencies {
    compile "net.ilexiconn:magister:0.1.0"
}
```

or Maven:

```xml
<repositories>
    <repository>
      <id>ilexiconn</id>
      <name>ilexiconn</name>
      <url>http://maven.ilexiconn.net/</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
      <groupId>net.ilexiconn</groupId>
      <artifactId>magister</artifactId>
      <version>0.1.0</version>
    </dependency>
</dependencies>
```

How to compile from source
==========
1. Make sure you have a JDK (minimal version 6) installed.
2. If you have Gradle installed, simply run `gradle build` from the project root. Otherwise, run `gradlew.bat build` from the project directory. If you are on Mac or Linux, run `./gradew build`.

Before creating issues
==========
1. Make sure you're running the latest version.
2. Make sure you're using the API correctly.
3. Make sure you're using an unmodified version of the API.

When creating an issue
==========
1. Post output of https://school.magister.net/api/versie where `school` is the used school.
2. Post as much of the error logs you get.
3. Keep it English. :)

Credits
==========
[MrWolvetech](https://github.com/MrWolvetech)  
[KeizerDev](https://github.com/KeizerDev)

License
==========
[MIT](LICENSE.md)
