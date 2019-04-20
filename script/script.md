### Maven
- run-dev

```
mvn clean package -Dmaven.test.skip=true -Dbuild.path=S:\Code\Github\vertx-kotlin-scaffold\build -f pom.xml

java -jar api-1.0.0-SNAPSHOT-fat.jar -conf conf/dev/conf.json
```

- run-production

```
kill -9 pid
rm -rf /home/build/api-1.0.0-SNAPSHOT-fat.jar
rm -rf /home/build/conf/api 

mvn clean package -Dmaven.test.skip=true -Dbuild.path=/home/build -f pom.xml
cd /home/build
java -jar api-1.0.0-SNAPSHOT-fat.jar -conf conf/production/conf.json
```