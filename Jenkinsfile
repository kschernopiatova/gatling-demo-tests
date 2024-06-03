pipeline {
    agent {
        node('agent-gatling')
    }

    stages {
        stage("Build maven project") {
            steps {
                git(
                    url: "https://github.com/kschernopiatova/gatling-demo-tests.git",
                    branch: "main",
                    changelog: true,
                    poll: true
                )
                sh 'mvn clean install'
            }
        }
        stage("Run gatling simulation") {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    sh './mvnw gatling:test -Dgatling.simulationClass=com.solvd.gatlingeshop.simulations.${SIMULATION}'
                }
                archiveArtifacts artifacts: 'target/gatling/*simulation*/**'
            }
            post {
                success {
                    script {
                        slackSend (color: '#36a64f',
                            message: "Gatling simulation succeded!\n${BUILD_URL}/Gatling_20${SIMULATION}_20Report/")
                        emailext (subject : "Gatling simulation results",
                            body: "Gatling simulation succeded!\n${BUILD_URL}Gatling_20${SIMULATION}_20Report/",
                            to: 'test@mailhog.com')
                    }
                }
                failure {
                    script {
                        slackSend (color: '#a83236',
                            message: "Gatling simulation failed!\n${BUILD_URL}Gatling_20${SIMULATION}_20Report/")
                        emailext (subject : "Gatling simulation results",
                            body: "Gatling simulation succeded!\n${BUILD_URL}Gatling_20${SIMULATION}_20Report/",
                            to: 'test@mailhog.com')
                    }
                }
            }
        }
        stage("Publish report") {
            environment {
                REPORT_NAME="""${sh(
                    returnStdout: true,
                    script: "cat target/gatling/lastRun.txt | tr -d '\n'"
                )}"""
            }
            steps {
                publishHTML (target: [
                  allowMissing: true,
                  alwaysLinkToLastBuild: false,
                  keepAll: true,
                  reportDir: "target/gatling/$REPORT_NAME",
                  reportFiles: 'index.html',
                  reportName: "Gatling ${SIMULATION} Report"
                ])
            }
        }
    }
}
