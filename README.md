# Java upgrade example errors and solutions
This project shows the errors encountered during a Java upgrade and the necessary fixes. 

Per Java version there is a Maven module to show what went wrong starting in that version and how it can be fixed.

This project uses Maven, however the issues will be the same with other buildtools.

This readme first describes the issues and solutions for Java in general and then for each specific Java version. After that the various ways to run multiple JDK's on one machine are described. 

The last part also describes how to run the examples. For instance the Java 15 examples work on Java 14. When run on Java 15 the broken examples will fail, while the fixed ones will succeed.


# Java general challenges
This document describes the bigger changes of Java. There are many more (smaller) items of Java removed. This chapter lists the various categories which were removed. For detailed information, please look at the release notes:

Java release notes
- [Java 10](https://www.oracle.com/java/technologies/javase/10-relnote-issues.html)
- [Java 11](https://www.oracle.com/java/technologies/javase/11-relnote-issues.html)
- [Java 12](https://www.oracle.com/java/technologies/javase/12-relnote-issues.html)
- [Java 13](https://www.oracle.com/java/technologies/javase/13-relnote-issues.html)
- [Java 14](https://www.oracle.com/java/technologies/javase/14-relnote-issues.html)
- [Java 15](https://www.oracle.com/java/technologies/javase/15-relnote-issues.html)
- [Java 16](https://www.oracle.com/java/technologies/javase/16-relnote-issues.html)
- [Java 17](https://www.oracle.com/java/technologies/javase/17-relnote-issues.html)
  
Security roadmap
- [Oracle JRE and JDK Cryptographic Roadmap](https://java.com/en/jre-jdk-cryptoroadmap.html)

OpenJDK features
- [Java 9](https://openjdk.java.net/projects/jdk9/)
- [Java 10](https://openjdk.java.net/projects/jdk/10/)
- [Java 11](https://openjdk.java.net/projects/jdk/11/)
- [Java 12](https://openjdk.java.net/projects/jdk/12/)
- [Java 13](https://openjdk.java.net/projects/jdk/13/)
- [Java 14](https://openjdk.java.net/projects/jdk/14/)
- [Java 15](https://openjdk.java.net/projects/jdk/15/)
- [Java 16](https://openjdk.java.net/projects/jdk/16/)
- [Java 17](https://openjdk.java.net/projects/jdk/17/)


## Removal of VM flags/options
For instance
```bash
-XX:+AggressiveOpts
```
and
```bash
-Xoptimize
```

## Removed (root) certificates
"The T-Systems Deutsche Telekom Root CA 2 certificate has expired and was removed from the cacerts keystore"

## Removed encryption algorithms
Algorithms deemed unsafe are removed.

## Removed garbage collectors
For instance the Concurrent Mark and Sweep (CMS) garbage collector was removed in 14.

## Removed from API
Parts of the API such as methods can be deprecated and later removed.

It's possible to see the deprecated and removed parts of the API per Java version. For instance via the [The Java Version Almanac](https://javaalmanac.io/) or via [foojay](https://foojay.io/almanac/jdk-16/)

## Removed tools
Some are no longer available, others such as JDK Mission Control and JavaFX now are available as separate builds from various vendors.

Some JDK's such as ojdkbuild and some builds of Liberica JDK still offer a JDK which includes some of the tools.


# Issues and solutions per Java version
## Java 11
### JEP 320: Remove the Java EE and CORBA Modules
The EE packages were removed in Java 11. If you still need them, you can add them as Maven/Gradle dependencies.

**Please be aware that you should use the new jakarta packages as the old packages are no longer updated.**

For instance JAXB can be added with the dependency listed below. However, it's no longer updated, the latest version is 2.3.0.
```xml
<dependency>
    <groupId>javax.xml.bind</groupId>
    <artifactId>jaxb-api</artifactId>
    <version>2.3.0</version>
</dependency>
```
You should instead use the Jakarta dependency which has the newer 3.0.0 version.
```xml
<dependency>
    <groupId>jakarta.xml.bind</groupId>
    <artifactId>jakarta.xml.bind-api</artifactId>
    <version>3.0.0</version>
</dependency>
```
#### Removal of javax.activation

##### Example error
*Example code can be found under java11/javaee_removed_broken*

```bash
package javax.activation does not exist
```
```bash
cannot find symbol
[ERROR]   symbol:   class URLDataSource
```
##### Solution
*Example code can be found under java11/javaee_removed_fixed_new_package*

Add the necessary dependencies:
```xml
<dependency>
    <groupId>com.sun.activation</groupId>
    <artifactId>jakarta.activation</artifactId>
    <version>2.0.1</version>
</dependency>
```
#### Removal of javax.annotation

##### Example error
*Example code can be found under java11/javaee_removed_broken*

```bash
package javax.annotation does not exist
```
```bash
cannot find symbol
[ERROR]   symbol:   class PostConstruct
```
##### Solution
*Example code can be found under java11/javaee_removed_fixed_new_package*

Add the necessary dependencies:
```xml
<dependency>
    <groupId>jakarta.annotation</groupId>
    <artifactId>jakarta.annotation-api</artifactId>
    <version>2.0.0</version>
</dependency>
```
#### Removal of javax.transaction

##### Example error
*Example code can be found under java11/javaee_removed_broken*

```bash
package javax.transaction does not exist
```
```bash
cannot find symbol
[ERROR]   symbol:   class TransactionRequiredException
```
##### Solution
*Example code can be found under java11/javaee_removed_fixed_new_package*

Add the necessary dependencies:
```xml
<dependency>
    <groupId>jakarta.transaction</groupId>
    <artifactId>jakarta.transaction-api</artifactId>
    <version>2.0.0</version>
</dependency>
```

#### Removal of javax.xml.bind
##### Example error
*Example code can be found under java11/javaee_removed_broken*

```bash
package javax.xml.bind.annotation does not exist
```
```bash
cannot find symbol
[ERROR]   symbol: class XmlRootElement
```
```bash
cannot find symbol
[ERROR]   symbol:   class JAXBException
```
##### Solution
*Example code can be found under java11/javaee_removed_fixed_new_package*

Add the necessary dependencies:

For the API there's:
```xml
<dependency>
    <groupId>jakarta.xml.bind</groupId>
    <artifactId>jakarta.xml.bind-api</artifactId>
    <version>3.0.0</version>
</dependency>
```

For the implementation there are a few options listed below. If you already use one of them as a transitive dependency then it's probably best to use that one to avoid conflicts.

The following command can be used to check if you already use one of the implementations via a transitive dependency:
```bash
mvn dependency:tree -Dincludes=org.glassfish.jaxb:jaxb-runtime
mvn dependency:tree -Dincludes=com.sun.xml.bind:jaxb-impl
```

If you don't have a JAXB implementation as a transitive dependency then it's probably best to use the following Glassfish implementation.
```xml
<dependency>
    <groupId>org.glassfish.jaxb</groupId>
    <artifactId>jaxb-runtime</artifactId>
    <version>3.0.0</version>
    <scope>runtime</scope>
</dependency>
```
Or the jaxb-impl which is now called the [Old JAXB Runtime](https://mvnrepository.com/artifact/com.sun.xml.bind/jaxb-impl)
```xml
<dependency>
    <groupId>com.sun.xml.bind</groupId>
    <artifactId>jaxb-impl</artifactId>
    <version>3.0.0</version>
</dependency>
```


#### Removal of javax.jws javax.xml.soap javax.xml.ws

##### Example error
*Example code can be found under java11/javaee_removed_broken*

```bash
package javax.xml.ws does not exist
```
```bash
cannot find symbol
[ERROR]   symbol:   class Service
```
##### Solution
*Example code can be found under java11/javaee_removed_fixed_new_package*

Add the necessary dependencies:
```xml
<dependency>
    <groupId>jakarta.xml.ws</groupId>
    <artifactId>jakarta.xml.ws-api</artifactId>
    <version>3.0.0</version>
</dependency>
<dependency>
    <groupId>com.sun.xml.ws</groupId>
    <artifactId>jaxws-rt</artifactId>
    <version>3.0.0</version>
</dependency>
```
#### Removal of Corba javax.activity javax.rmi.*
There's no official replacement/dependency released for Corba
#### Solution
Migrate away from Corba or use something like glassfish-corba

### Font removal
The JDK contained some fonts, but they were removed in Java 11. If the application used these fonts and the operating system doesn't provide them, then an error occurs.

More info from [Azul](https://www.azul.com/the-font-of-all-knowledge-about-java-and-fonts/) and [AdoptOpenJDK](https://blog.adoptopenjdk.net/2021/01/prerequisites-for-font-support-in-adoptopenjdk/)

#### Example errors
*Example code can be found under java11/removed_fonts*

```bash
java.lang.UnsatisfiedLinkError: /usr/local/openjdk-11/lib/libfontmanager.so: 
  libfreetype.so.6: cannot open shared object file: No such file or directory
```

```bash
java.lang.NoClassDefFoundError: Could not initialize class sun.awt.X11FontManager
```

#### Solution
*Example code can be found in DockerfileWithFonts*

Install the necessary fonts, for instance with:
```
apt install fontconfig
```

Depending on your [scenario](https://blog.adoptopenjdk.net/2021/01/prerequisites-for-font-support-in-adoptopenjdk/) you might need to add some extra packages such as: libfreetype6 fontconfig fonts-dejavu.

Some JDK's automatically install the needed packages.

### JavaFX removal
JavaFX is removed from the JDK and continued as OpenJFX.

Some vendors offer builds of OpenJFX such as [Gluon](https://gluonhq.com/products/javafx/)

Some vendors offer JDK builds which include OpenJFX such as [Liberica's full version](https://bell-sw.com/pages/products/) and [ojdkbuild](https://github.com/ojdkbuild/ojdkbuild/wiki/Motivation)

Use the (Maven) dependencies of [OpenJFX](https://openjfx.io/)

### Java Mission Control (JMC) removed
Use one of the builds of JDK Mission control:
 - [Oracle](https://www.oracle.com/java/technologies/jdk-mission-control.html)
 - [AdoptOpenJDK](https://adoptopenjdk.net/jmc.html)

## Java 15
### JEP 372: Remove the Nashorn JavaScript Engine
Nashorn is no longer included in the standard JDK.

#### Example errors
*Example code can be found under java15/nashorn_broken*

```bash
java.lang.NullPointerException: Cannot invoke "javax.script.ScriptEngine.eval(String)" because "engine" is null
```

#### Solution
*Example code can be found under java15/nashorn_fixed*

Add the necessary dependencies:
```xml
<dependency>
    <groupId>org.openjdk.nashorn</groupId>
    <artifactId>nashorn-core</artifactId>
    <version>15.2</version>
</dependency>
```

## Java 16
### JEP 396: Strongly Encapsulate JDK Internals by Default
Internals of the JDK can no longer be used by default. This mainly impacts tooling which uses low level features of the JDK.

#### Example errors
*Example code can be found under java16/lombok_broken*

```bash
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:compile (default-compile) on project broken: Fatal error compiling: java.lang.IllegalAccessError: class lombok.javac.apt.LombokProcessor (in unnamed module @0x21bd20ee) cannot access class com.sun.tools.javac.processing.JavacProcessingEnvironment (in module jdk.compiler) because module jdk.compiler does not export com.sun.tools.javac.processing to unnamed module @0x21bd20ee -> [Help 1]
```

#### Solution
*Example code can be found under java16/lombok_fixed*

Preferably use a new version of the dependency which causes the issue. For instance Lombok 1.18.20 includes support for Java 16:
```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.20</version>
    <scope>provided</scope>
</dependency>
```

If there's no new dependency available it's possible to open up the JDK internals, so they can be used. However, this is not the prettiest solution:
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.8.1</version>
    <configuration>
        <fork>true</fork>
        <compilerArgs>
            <arg>-J--add-opens=jdk.compiler/com.sun.tools.javac.comp=ALL-UNNAMED</arg>
            <arg>-J--add-opens=jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED</arg>
            <arg>-J--add-opens=jdk.compiler/com.sun.tools.javac.main=ALL-UNNAMED</arg>
            <arg>-J--add-opens=jdk.compiler/com.sun.tools.javac.model=ALL-UNNAMED</arg>
            <arg>-J--add-opens=jdk.compiler/com.sun.tools.javac.parser=ALL-UNNAMED</arg>
            <arg>-J--add-opens=jdk.compiler/com.sun.tools.javac.processing=ALL-UNNAMED</arg>
            <arg>-J--add-opens=jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED</arg>
            <arg>-J--add-opens=jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED</arg>
            <arg>-J--add-opens=jdk.compiler/com.sun.tools.javac.jvm=ALL-UNNAMED</arg>
        </compilerArgs>
    </configuration>
</plugin>
```

## Java 17

### Gradle
- Update to Gradle 7.3 to get Java 17 [support](https://github.com/gradle/gradle/issues/16857)
- Update to Kotlin 1.6.0 to be able to set jvmTarget to 17

### JEP 403: Strongly Encapsulate JDK Internals
Launcher option --illegal-access no longer works to access internal JDK API's. 

#### Example errors
```bash
java.lang.reflect.InaccessibleObjectException: Unable to make … accessible: module java.base does not "opens …" to unnamed module …
```
#### Solution
- If triggered by a dependency, upgrade the dependency
- Refactor code to no longer use internal JDK API's
- As a last resort use ```--add-opens``` for instance:
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <configuration>
        <argLine>
            --add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.util=ALL-UNNAMED
        </argLine>
    </configuration>
</plugin>
```

### JEP ?

Mockito raises an exception when Mocking an enum which uses methods in values, such as for example:
```java
public enum ExampleEnum {
    TEST {
        public String retrieve() {
            return "test";
        }
    };
}
```
```java
@ExtendWith(MockitoExtension.class)
public class ExampleEnumTest {

    @Mock
    private ExampleEnum exampleEnum;

    @Test
    public void testEnumWithMethods() {
        assertNotNull(exampleEnum);
    }
}
```

#### Example errors
*Example code can be found under java17/mockito_fixed*

```bash
org.mockito.exceptions.base.MockitoException:

Mockito cannot mock this class: class com.example.ExampleEnum.

If you're not sure why you're getting this error, please report to the mailing list.


Java               : 17
JVM vendor name    : Oracle Corporation
JVM vendor version : 17-ea+24-2164
JVM name           : OpenJDK 64-Bit Server VM
JVM version        : 17-ea+24-2164
JVM info           : mixed mode, sharing
OS name            : Linux
OS version         : 4.19.76-linuxkit


You are seeing this disclaimer because Mockito is configured to create inlined mocks.
You can learn about inline mocks and their limitations under item #39 of the Mockito class javadoc.

Underlying exception : org.mockito.exceptions.base.MockitoException: Could not modify all classes [class com.example.ExampleEnum, interface java.lang.constant.Constable, class java.lang.Object, interface java.lang.Comparable, interface java.io.Serializable, class java.lang.Enum]
Caused by: org.mockito.exceptions.base.MockitoException: Could not modify all classes [class com.example.ExampleEnum, interface java.lang.constant.Constable, class java.lang.Object, interface java.lang.Comparable, interface java.io.Serializable, class java.lang.Enum]
Caused by: java.lang.UnsupportedOperationException: class redefinition failed: attempted to change the class NestHost, NestMembers, Record, or PermittedSubclasses attribute
```

#### Solution
Not yet available, see issue in [Mockito GitHub](https://github.com/mockito/mockito/issues/2315) repo.

## All Java versions
#### Example errors
```bash
An error has occurred in JaCoCo Aggregate report generation. Error while creating report: Error while analyzing … Unsupported class file major version X
```
```bash
Execution default of goal org.pitest:pitest-maven:1.4.10:mutationCoverage failed: Unsupported class file major version 61 
```
```bash
Execution repackage of goal org.springframework.boot:spring-boot-maven-plugin:2.2.10.RELEASE:repackage failed: Unsupported class file major version 61
```
#### Solution
The class file major version 61 is used for Java 17. Make sure your plugins/dependencies are up to date and support Java 17. 

- Update your plugins/dependencies


# Running multiple JDK's on one machine

## Set JAVA_HOME before running an example
The value of `JAVA_HOME` is used by Maven to select the JDK.

Display the current `JAVA_HOME`
```bash
echo $JAVA_HOME
C:\Program Files\AdoptOpenJDK\jdk-16.0.0.36-hotspot
```

Set `JAVA_HOME` to another JDK:
```bash
JAVA_HOME="[location of JDK]"
```

Then use the following Maven command inside one of the Java directories (java11, java16...) to build all submodules and continue when something goes wrong:
```bash
mvn compile --fail-at-end -Dmaven.compiler.release=[Specify JDK version. Don't use this on Java 8 as it's not supported]
```
Or the shorter version:
```bash
mvn compile -fae -Dmaven.compiler.release=[Specify JDK version. Don't use this on Java 8 as it's not supported]
```
The version of the script for Java 8
```bash
mvn compile -fae -Dmaven.compiler.target=8 -Dmaven.compiler.source=8
```
Or change the Maven releases in the `pom.xml`:
```xml
<properties>
    <maven.compiler.release>7</maven.compiler.release>
</properties>
```

## Using Docker containers
Then use the following Docker command inside one of the Java directories (java11, java16...):

Change the JDK_VERSION to whatever version (11 or greater) you want:
```shell script
docker build -t javaupgrades -f ..\Dockerfile --build-arg DISABLE_CACHE="%date%-%time%" --build-arg JDK_VERSION=17 .
```

Or to build on Java 8, which requires a different configuration:
```shell script
docker build -t javaupgrades -f ..\DockerfileJava8 --build-arg DISABLE_CACHE="%date%-%time%" .
```


## Maven Toolchains
**Please read until the end, for now I don't recommend the Maven toolchains as there appears to be a bug.**

Maven Toolchains can be used to configure the JDK's present on your machine and then select one to use in the `pom.xml` of the project.

First create a `toolchains.xml` located in *${user.home}/.m2/*

```xml
<?xml version="1.0" encoding="UTF8"?>
<toolchains>
    <toolchain>
        <type>jdk</type>
        <provides>
            <version>8</version>
        </provides>
        <configuration>
            <jdkHome>/path/to/jdk/8</jdkHome>
        </configuration>
    </toolchain>
    <toolchain>
        <type>jdk</type>
        <provides>
            <version>11</version>
        </provides>
        <configuration>
            <jdkHome>/path/to/jdk/11</jdkHome>
        </configuration>
    </toolchain>
    <toolchain>
        <type>jdk</type>
        <provides>
            <version>16</version>
        </provides>
        <configuration>
            <jdkHome>/path/to/jdk/16</jdkHome>
        </configuration>
    </toolchain>
    <toolchain>
        <type>jdk</type>
        <provides>
            <version>17</version>
        </provides>
        <configuration>
            <jdkHome>/path/to/jdk/16</jdkHome>
        </configuration>
    </toolchain>
</toolchains>
```

Then in the `pom.xml` configure which JDK from the toolchains.xml you want to use:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-toolchains-plugin</artifactId>
            <version>3.0.0</version>
            <configuration>
                <toolchains>
                    <jdk>
                        <version>${maven.compiler.release}</version>
                    </jdk>
                </toolchains>
            </configuration>
            <executions>
                <execution>
                    <goals>
                        <goal>toolchain</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

Unfortunately currently there seems to be a bug as building the project with the toolchain gives:
```shell script
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:compile (default-compile) on project broken: Compilation failure -> [Help 1]
```

While compiling without the toolchain gives the more descriptive error message:
```shell script
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:compile (default-compile) on project broken: Fatal error compiling: java.lang.IllegalAccessError: class lombok.javac.apt.LombokProcessor (in unnamed module @0x21bd20ee) cannot access class com.sun.tools.javac.processing.JavacProcessingEnvironment (in module jdk.compiler) because module jdk.compiler does not export com.sun.tools.javac.processing to unnamed module @0x21bd20ee -> [Help 1]
```

So I don't recommend the usage of Maven Toolchains for upgrading to Java 16 or 17 at this point in time.

# Interesting other things

## Multi release JAR
Multi release JAR's make it possible to create one JAR file which supports multiple Java versions for backward compatibility.

This example uses records and ```String.isBlank()``` which was introduced in Java 11. The example has two main directories:
- src/main/java used on Java versions below Java 17
- src/main/java17 used by Java version 17 and above

The JAR file for this example should be build on Java 17 and can then be used on various Java versions.

The following command can be used to build the examples on Java 17 and then run them on 8, 11 and 17:
```shell script
docker build -t multi-release-jar --build-arg DISABLE_CACHE="%date%-%time%" .
```

Make sure your code for all Java versions contains the same public API's, else you might run into runtime issues. IntelliJ checks this and Java 17 now [contains](https://github.com/openjdk/jdk/pull/3971) the ```jar --validate``` option to verify a JAR file. Build tools like Maven don't verify it automatically.
