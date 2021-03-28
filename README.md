# The largest heading
This project shows the errors encountered during a Java upgrade and the necessary fixes. 

Per Java version there is a Maven module to show what went wrong starting in that version and how it can be fixed.

This project uses Maven, however the issues will be the same with other buildtools.

To run the examples several options can be used:

## Set JAVA_HOME before running an example
The value of `JAVA_HOME` is used by Maven to select the JDK.

Display the current `JAVA_HOME`
```shell script
echo $JAVA_HOME
C:\Program Files\AdoptOpenJDK\jdk-16.0.0.36-hotspot
```

Set `JAVA_HOME` to another JDK:
```shell script
JAVA_HOME="[location of JDK]"
```

Then use the following Maven command inside one of the Java directories (java11, java16...) to build all submodules and continue when something goes wrong:
```shell script
mvn compile --fail-at-end -Dmaven.compiler.release=[Specify JDK version. Don't use this on Java 8 as it's not supported]
```
Or the shorter version:
```shell script
mvn compile -fae -Dmaven.compiler.release=[Specify JDK version. Don't use this on Java 8 as it's not supported]
```
Or change the Maven releases in the `pom.xml`:
```xml
<properties>
    <maven.compiler.release>7</maven.compiler.release>
</properties>
```

## Using Docker containers
Then use the following Docker command inside one of the Java directories (java11, java16...):

```shell script
docker build -t javaupgrades -f ..\Dockerfile --build-arg DISABLE_CACHE="%date%-%time%" --build-arg JDK_VERSION=16 .
```

Or to build on Java 8, which requires a different configuration:
```shell script
docker build -t javaupgrades -f ..\DockerfileJava8 --build-arg DISABLE_CACHE="%date%-%time%" --build-arg JDK_VERSION=10 --build-arg DOCKER_IMAGE=maven:3.6-jdk .
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

So I don't recommend the usage of Maven Toolchains for upgrading Java at this point in time.