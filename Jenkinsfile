pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    parameters{
    gitParameter branchFilter: 'origin/(.*)', defaultValue: 'master', name: 'TESTRAIL_BRANCH', type: 'PT_BRANCH'
    string(name: 'TESTRAIL_XMLFILE', defaultValue: 'src/test/resources/regression.xml', description: '')
    string(name: 'TESTRAIL_BASEURL', defaultValue: 'https://hdn.testrail.io/index.php?', description: '')
    string(name: 'TESTRAIL_EMAIL', defaultValue: 'hdn_tms@mail.ru', description: '')
    string(name: 'TESTRAIL_PASSWORD', defaultValue: 'pVui0CaU1AsUDIXrPMws', description: '')
    string(name: 'TESTRAIL_USERNAME', defaultValue: 'Dima Hilko', description: '')
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
                //bat "mvn -Dmaven.test.failure.ignore=true clean test"
                bat "mvn -Dmaven.test.failure.ignore=true clean test -DsuiteXmlFile=${TESTRAIL_XMLFILE}"
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
