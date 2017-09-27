node {

    //All code must be in try block to be caught by the notification sender.
    try{

    // Mark the code checkout 'stage'....
    stage 'Checkout'

        // Checkout code from repository
        deleteDir()
        checkout scm

    // Get the maven tool.
    // ** NOTE: This 'M3' maven tool must be configured
    // **       in the global configuration.
    def mvnHome = tool 'M3'

    // Mark the code build 'stage'....
    stage 'Build'

        // Run the maven build to check compilation, currently not running of tests.
        // Remove -DskipTests and pass an environment identifier to run the tests against an environment
        // E.g. mvn clean test -Denv.HOME=dev
        sh "${mvnHome}/bin/mvn clean install -DskipTests"

    // Mark the code build 'stage'....
    stage 'Run Tests'

        if(SUITE != null){
            if (SUITE != 'all'){
                sh "${mvnHome}/bin/mvn clean install -Dmaven.test.failure.ignore=false -Denv.HOME=${ENVIRONMENT} -Dhub=hubtest -Dcucumber.options='-t @${SUITE}'"
            }else{
                sh "${mvnHome}/bin/mvn clean install -Dmaven.test.failure.ignore=false -Denv.HOME=${ENVIRONMENT} -Dhub=hubtest"
            }
        }



    //stage 'Code-Analysis'
        //def scannerHome = tool 'SonarQubeScanner';
        //withSonarQubeEnv('sonarqube') {
        //    sh "${scannerHome}/bin/sonar-scanner"
        //}

    stage 'Cucumber Reporting'
        step([$class: 'CucumberReportPublisher',
          fileExcludePattern: '',
          fileIncludePattern: '',
          ignoreFailedTests: false,
          jenkinsBasePath: '',
          jsonReportDirectory: '',
          missingFails: false,
          parallelTesting: false,
          pendingFails: false,
          skippedFails: false,
          undefinedFails: false])

     } catch (e) {
              // If there was an exception thrown, the build failed
              currentBuild.result = "FAILED"
              throw e
            } finally {
              // Success or failure, always send notifications
              notifyBuild(currentBuild.result)
            }
    }

    def notifyBuild(String buildStatus = 'STARTED') {
      // build status of null means successful
      buildStatus =  buildStatus ?: 'SUCCESSFUL'

      GIT_NAME = sh (
                script: 'git --no-pager show -s --format=\'%an\'',
                returnStdout: true
                ).trim()

      GIT_EMAIL = sh (
                  script: 'git --no-pager show -s --format=\'%ae\'',
                  returnStdout: true
                  ).trim()

      def SLACK_USER_NAME_AND_EMAIL = GIT_EMAIL.split( '@' )
      SLACK_USER_NAME = SLACK_USER_NAME_AND_EMAIL[0]

      // Default values
      def colorName = 'RED'
      def colorCode = '#FF0000'
      def subject = "${buildStatus}: '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
      def summary = "${subject} (${env.BUILD_URL})"

      // Override default values based on build status
      if (buildStatus == 'STARTED') {
        color = 'YELLOW'
        colorCode = '#FFFF00'
      } else if (buildStatus == 'SUCCESSFUL') {
        color = 'GREEN'
        colorCode = '#008000'
      } else {
        color = 'RED'
        colorCode = '#FF0000'
        summary = "${subject} (${env.BUILD_URL}) : *@here Acceptance Tests Failure*"
      }

      // Send notifications
      slackSend (color: colorCode, message: summary)
}