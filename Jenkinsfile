pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

//        environment {
//             //TESTRAIL_XMLFILE = '${params.TESTRAIL_XMLFILE}'
//         }

    parameters{
    gitParameter branchFilter: 'origin/(.*)', defaultValue: 'master', name: 'TESTRAIL_BRANCH', type: 'PT_BRANCH'
    string(name: 'TESTRAIL_XMLFILE', defaultValue: 'src/test/resources/regression.xml', description: 'Выбрать XML-файл с набором тестов')
    string(name: 'TESTRAIL_BASEURL', defaultValue: 'https://hdn.testrail.io/index.php?', description: 'Базовый URL приложения TestRail')
    string(name: 'TESTRAIL_EMAIL', defaultValue: 'hdn_tms@mail.ru', description: 'Логин для входа в приложения TestRail')
    string(name: 'TESTRAIL_PASSWORD', defaultValue: 'pVui0CaU1AsUDIXrPMws', description: 'Пароль для входа в приложения TestRail')
    string(name: 'TESTRAIL_USERNAME', defaultValue: 'Dima Hilko', description: 'После входа в приложение TestRail в правом верхнем углу приложения отображается имя пользователя, под которым вошли в приложение')
    string(name: 'TESTRAIL_AUTHORIZATION', defaultValue: 'Basic aGRuX3Rtc0BtYWlsLnJ1OnBWdWkwQ2FVMUFzVURJWHJQTXdz', description: 'Строка авторизации при выполнении API-тестов для приложения TestRail')
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
                bat "mvn -Dmaven.test.failure.ignore=true clean test -DBROWSER='${params.BROWSER}' -DsuiteXmlFile='${params.TESTRAIL_XMLFILE}'"
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