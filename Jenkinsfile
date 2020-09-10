pipeline {
    agent { label "android" } 

    stages {
        stage("Fetch Android Timer App") {
            steps {
               sh "git clone https://github.com/manguilar22/Android-Timer.git"
               sh "pwd"  
            }
        }
        stage("Build Android Timer App") {
            steps {
                sh "pwd"
                dir("Android-Timer") {
                    sh "gradle build"
                }
                sh "ls -ltr"
            }
        }
        stage("Clean Directory") {
            steps {
                sh "rm -rf Android-Timer"
            }
        }
    }
}
