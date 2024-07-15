pipeline {
    agent any
    
    tools {
        jdk 'jdk17'
        maven 'maven3'
    }
    
    environment {
        SCANNER_HOME = tool 'sonar-scanner'
    }

    stages {
        stage('Git Checkout') {
            steps {
              git changelog: false, credentialsId: 'c7e467bd-32e5-478d-90b4-7268eb1b60e8', poll: false, url: 'https://github.com/tamitkumar/emp-demo.git'
            }
        }
        
        stage('Compile') {
            steps {
              bat 'mvn clean package'
            }
        }
        
        stage('Test') {
			steps {
				sh 'mvn test'
			}
			post {
				always {
					junit 'target/reports/*.xml'
				}
			}
		}
        
        stage('Sonar Quebe Analysis') {
            steps {
              bat "\"%SCANNER_HOME%\\bin\\sonar-scanner\"" +
                    " -Dsonar.url=http://localhost:9000/" +
                    " -Dsonar.login=squ_77e698f98330d57150201cb7f0ba40125adb03e0" +
                    " -Dsonar.projectName=emp-demo" +
                    " -Dsonar.java.binaries=." +
                    " -Dsonar.projectKey=emp-demo"
            }
        }
        
        stage('OWASP SCAN') {
            steps {
              dependencyCheck additionalArguments: ' --scan ./', odcInstallation: 'DP'
              dependencyCheckPublisher pattern: '**/dependency-check-report.xml'
            }
        }
        
        stage('BUILD APPLICATION') {
            steps {
              bat 'mvn clean install'
            }
        }
        
        stage('BUILD & PUSH TO DOCKER IMAGE') {
            steps {
                bat 'docker --version'
                echo "Building Docker image..."
                bat 'docker build -t employee-jpa-tutorial-v1 .'
                echo 'Tagging Docker image...'
                bat 'docker tag employee-jpa-tutorial-v1 tamitkumar16/emp-demo:latest'
                echo 'Pushing Docker image...'
                bat 'docker push tamitkumar16/emp-demo:latest'
//              bat 'docker run -p 8080:8080 employee-jpa-tutorial-v1'
            }
        }
        
    }
        
}

