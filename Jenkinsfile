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
              git changelog: false, credentialsId: 'e74c9eb4-71e4-44de-ba68-baefbeb44d7e', poll: false, url: 'https://github.com/tamitkumar/emp-demo.git'
            }
        }
        
        stage('Compile') {
            steps {
              bat 'mvn clean package'
            }
        }
        
        stage('Sonar Quebe Analysis') {
            steps {
                withSonarQubeEnv('sonar') {
                    bat "\"%SCANNER_HOME%\\bin\\sonar-scanner\"" +
                        " -Dsonar.projectName=emp-demo" +  
                        " -Dsonar.java.binaries=." +
                        " -Dsonar.projectKey=emp-demo"
                }
              // bat "\"%SCANNER_HOME%\\bin\\sonar-scanner\"" +
              //       " -Dsonar.url=https://sonar.techbrainthinkinsight.com/" +
              //       " -Dsonar.login=squ_ba7f0697a9838f95500c641deef422ef8aa7fec4" +
              //       " -Dsonar.projectName=emp-demo" +
              //       " -Dsonar.java.binaries=." +
              //       " -Dsonar.projectKey=emp-demo"
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

