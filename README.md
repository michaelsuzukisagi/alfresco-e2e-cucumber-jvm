## Alfresco Functional test

This is a poc project to see how functional test can be used to test Alfresco's new features using Cucumber and Java.

### Get the code

Git:

    git clone https://github.com/michaelsuzukisagi/alfresco-e2e-cucumber-jvm.git
    cd alfresco-e2e-cuecumber-jvm

Subversion:

    svn checkout https://github.com/michaelsuzukisagi/alfresco-e2e-cucumber-jvm.git
    cd alfresco-e2e-cuecumber-jvm



### Use Maven

Open a command window and run:

    mvn test

This runs Cucumber features using Cucumber's JUnit runner. The `@RunWith(Cucumber.class)` annotation on the `RunCukesTest`
class tells JUnit to kick off Cucumber.

### Use Ant

Open a command window and run:

    ant download
    ant runcukes

This runs Cucumber features using Cucumber's Command Line Interface (CLI) runner. Note that the `RunCukesTest` junit class is not used at all.
If you remove it (and the `cucumber-junit` jar dependency), it will run just the same.

#### Overriding options

The Cucumber runtime parses command line options to know what features to run, where the glue code lives, what formatters to use etc.
When you use the JUnit runner, these options are generated from the `@CucumberOptions` annotation on your test.

Sometimes it can be useful to override these options without changing or recompiling the JUnit class. This can be done with the
`cucumber.options` system property. The general form is:

Using Maven:

    mvn -Dcucumber.options="..." test

Using Ant:

    JAVA_OPTIONS='-Dcucumber.options="..."' ant runcukes

Let's look at some things you can do with `cucumber.options`. Try this:

    -Dcucumber.options="--help"

That should list all the available options.

#### Run a subset of Features or Scenarios

Specify a particular scenario by *line* (and use the pretty format)

    -Dcucumber.options="classpath:alfresco-e2e/login.feature:4 --format pretty"

This works because Maven puts `./src/test/resources` on your `classpath`.
You can also specify files to run by filesystem path:

    -Dcucumber.options="src/test/resources/alfresco-e2e/login.feature:4 --format pretty"

You can also specify what to run by *tag*:

    -Dcucumber.options="--tags @bar --format pretty"


#### Specify a different formatter:

For example a JUnit formatter:

    -Dcucumber.options="--format junit:target/cucumber-junit-report.xml"
