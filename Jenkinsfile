pipeline {
  agent any
  environment { SONARQUBE = 'sonar-server' } // must match Jenkins Configure System name

  stages {
    stage('Checkout') {
      steps { git branch: 'main', url: 'https://github.com/udamale/student-login-maven-project.git' }
    }

    stage('Build') {
      steps { sh 'mvn -B -DskipTests clean package' }
    }

    stage('Sonar Analysis') {
      steps {
        withSonarQubeEnv("${SONARQUBE}") {
          sh 'mvn -B sonar:sonar'
        }
      }
    }

    stage('Quality Gate') {
      steps {
        timeout(time: 5, unit: 'MINUTES') {
          script {
            def qg = waitForQualityGate()
            echo "Quality Gate: ${qg.status}"
            if (qg.status != 'OK') { error "Pipeline stopped: Quality Gate = ${qg.status}" }
          }
        }
      }
    }
     stage('maven') {
            steps {
                    sh 'mvn package'
                }
        }
        stage('tomcat') {
            steps {
                deploy adapters: [tomcat9(alternativeDeploymentContext: '', credentialsId: 'shh', path: '', url: 'http://3.108.55.178:8081')], contextPath: null, war: '**/*.war'
             }
       }
  }

  post {
    success { echo '✅ Build + Sonar OK' }
    failure { echo '❌ Failed (see console output)' }
  }
}
