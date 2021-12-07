# MyProjectReactor

Project Reactor от Pivotal (создатели Spring Framework и Spring Boot) - реактивная библиотека, помогающая лучше организовать код в виде асинхронных неблокирующих потоков.

Reactor - новый взгляд на разработку приложений. С его помощью можно более грамотно и удобно организовать работу со сложным кодом. Почти также, как это сделано с java stream api в jdk 8, но на более высоком уровне и с большими возможностями масштабирования и параллельной обработки.

### IntelliJ IDEA ###
*.idea/
*.iws
*.ipr

### Maven ###
*/mvnw
*/mvnw.cmd
*.mvn
!.mvn/wrapper/maven-wrapper.jar

# Sonar
*credential.properties

# Compiled class file
*.class
target/
!**/src/main/**/target/
!**/src/test/**/target/

# Log file
*.log

# Package Files #
*.jar
*.war
*.nar
*.ear
*.zip
*.tar.gz
*.rar
*.iml

# virtual machine crash logs, see http://www.java.com/en/download/help/error_hotspot.xml
hs_err_pid*

### NetBeans ###
/nbproject/private/
/nbbuild/
/dist/
/nbdist/
/.nb-gradle/
build/
!**/src/main/**/build/
!**/src/test/**/build/

### VS Code ###
.vscode/

### STS ###
.apt_generated
.classpath
.factorypath
.project
.settings
.springBeans
.sts4-cache

# BlueJ files
*.ctxt

# Mobile Tools for Java (J2ME)
.mtj.tmp/
