# ia-appsub-policy-to-ats103-service (integration tests)

Tests the service that reads a policy from the queue and then moves that policy to ATS.   The service
should log the event in the audit log.    The service may query another system to validate the policy
number is valid.

Tests the reads a file and inserts it as a policy in the queue and then logs that event to the audit log.

Two services are involved in the workflow being tested in this test suite:

    ia-appsub-file-to-policy-adaptor
    ia-appsub-policy-to-ats103-service
    ia-audit-event-service
    
Tertiary services not having an integration test suite.
    
    ia-email-service
    ia-error-handler-service

## Setup

You will need these environment variables on your local system to execute the run configurations.

    export TDM_USER_SYS="test_tdm_dev"
    export TDM_PASS_SYS="__s3cret__"
    export TDM_USER_ACC="test_tdm_acc"
    export TDM_PASS_ACC="__s3cret__"

## Execution Of Tests

To execute Karate tests against your dev environment, follow these steps:

From this sub-module folder:

    mvn clean test -Dtest.env=int -DDB.password=$TDM_PASS_SYS -Dtest.type=bvt // for mac
    mvn clean test -Dtest.env=int -DDB.password=%TDM_PASS_SYS% -Dtest.type=bvt  // for windows


#### launch.json

This project comes with some `launch.json` examples for use in VSCode.

## Debugging

To debug, use VSCode with the 'Karate Runner' plugin and the 'Java Extension Pack'.  
You will be able to put breakpoints on '.feature' files but not on '.js' files.

For Maven, VSCode will need to run the following command via `launch.json`:

    mvn test-compile -f \"${command:karateRunner.getDebugBuildFile}\" exec:java -Dexec.mainClass=com.intuit.karate.cli.Main 
      -D\"exec.args=-d\" -D\"exec.classpathScope=test\" ${config:karateRunner.karateRunner.commandLineArgs} 
      -Dtest.env=sys\" -D\"DB.password=$TDM_PASS_SYS\" -D\"tags=reg\"


## Notes

Karate Documentation:
https://intuit.github.io/karate/

Karate JSON Transforms:
https://github.com/intuit/karate#json-transforms



## Troubleshooting

Common errors and their probable root cause.

    Javascript function call failed: Could not load JDBC driver class [com.microsoft.sqlserver.jdbc.SQLServerDriver]
    ROOT CAUSE?  username/password incorrect?

    JDBC Driver will not load?
    ROOT CAUSE?  If JDBC Driver does not load, it may be related to this: https://github.com/kirksl/karate-runner/issues/53

    