pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    parameters{
    gitParameter branchFilter: 'origin/(.*)', defaultValue: 'master', name: 'TESTRAIL_BRANCH', type: 'PT_BRANCH'
    string(name: 'TESTRAIL_BASEURL', defaultValue: 'https://hdnd.testrail.io/index.php?', description: '')
    string(name: 'TESTRAIL_EMAIL', defaultValue: 'hdn_tmsd@mail.ru', description: '')
    string(name: 'TESTRAIL_PASSWORD', defaultValue: 'yPn9GrueGH.KzMIiFUfX', description: '')
    string(name: 'TESTRAIL_USERNAME', defaultValue: 'Khilko Dima', description: '')
    string(name: 'TESTRAIL_AUTHORIZATION', defaultValue: 'Basic aGRuX3Rtc2RAbWFpbC5ydTp5UG45R3J1ZUdILkt6TUlpRlVmWA==', description: '')
    choice(name: 'BROWSER', choices: ['chrome', 'firefox', 'edge'], description: '')

    }

    stages {
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                //git 'https://github.com/DmitryKhilko/TestRail.git'
                git branch: "${params.TESTRAIL_BRANCH}", url: 'https://github.com/DmitryKhilko/TestRail.git'


                // Run Maven on a Unix agent.
                // sh "mvn -Dmaven.test.failure.ignore=true clean package"

                // To run Maven on a Windows agent, use
                //bat "mvn -Dmaven.test.failure.ignore=true clean test -DsuiteXmlFile=%TESTRAIL_XMLFILE%"
                //bat "mvn -Dmaven.test.failure.ignore=true clean test -DsuiteXmlFile=${params.TESTRAIL_XMLFILE}"
                bat "mvn -Dmaven.test.failure.ignore=true clean test -DBROWSER='${params.BROWSER}'"
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }
        stage('reports') {
            steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'target/allure-results']]
                    ])
                }
            }
        }
    }
}