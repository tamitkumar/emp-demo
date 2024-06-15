pipeline {
	agent {
		docker {
			image 'maven:3.9.7'
			args '-v C:\Users\tamit\.m2'
		}
	}
	stages {
		stage('Build') {
			step {
				sh 'mvn -B -DskipTests clean package'
			}
		}
		stage('Test') {
			step {
				sh 'mvn test'
			}
			post {
				always {
					junit 'target/reports/*.xml'
				}
			}
		}
		stage('Deliver') {
			step {
				sh 'mvn -B -DskipTests clean package'
			}
		}
	}
}