# README #
THIS IS NOT A SAMPLE. IT'S A USABLE MODULE FOR THE LONG RUN
Common library project. To be injected into every UI projects. Should be configured to deploy its jar to repository after each build.
More details: http://maven.apache.org/plugins/maven-deploy-plugin/usage.html
Example:
...
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-deploy-plugin</artifactId>
                <version>2.8.2</version>
            </plugin>
        </plugins>
...
        <distributionManagement>
            <repository>
                <id>hm.internal.repo</id>
                <name>hm internal repo</name>
                <url>http://HM.internal.repo.com</url>
            </repository>
        </distributionManagement>
...
Then define your username/password in settings.xml in your local:
...
        <server>
            <id>hm.internal.repo</id>
            <username>usernamegivenbythedevops</username>
            <password>password</password>
        </server>
...
So every time you run mvn deploy, a newly built jar will be uploaded to said repo.
In UI projects, refer to this artifact as normally do:
...
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>hm-tools-common-security</artifactId>
        </dependency>
...