pipeline
{
    agent any
    tools
    {
        jdk 'JAVA_HOME'
        maven 'MAVEN_HOME'
    } 
    environment
    {
        scanner_home= tool 'sonarqube-scanner'
    }
    stages
    {
        stage('Clean Workspace')
        {
            steps
            {
                cleanWs()
            }
        }
        stage('Git Checkout')
        {
            steps
            {
                git branch:'master', url:'https://github.com/sureshkumar-devops/coupon.git'                
            }
        }
        stage('Code Compile')
        {
            steps
            {
                sh 'mvn clean compile'
            }
        }
        stage('SonarQube Analysis')
        {
            steps
            {
                withSonarQubeEnv('sonarqube-server')
                {
                    sh ''' $scanner_home/bin/sonar-scanner \
                    -Dsonar.projectName=Coupon \
                    -Dsonar.projectKey=Coupon \
                    -Dsonar.java.binaries=. \
                    -Dsonar.projectVersion=${BUILD_NUMBER}'''
                }                
            }
        }
        stage('Quality Gates')
        {
            steps
            {
                timeout(time: 5, unit: 'MINUTES')
                {
                    waitForQualityGate abortPipeline: false
                }
            }
        }
        stage('OWASP DP-CHECK')
        {
            steps
            {
                dependencyCheck additionalArguments: '--scan ./ --disableYarnAudit --disableNodeAudit',odcInstallation: 'DPCHECK_HOME'
                dependencyCheckPublisher pattern: '**/dependency-check-report.xml'
            }
        }
        stage('TRIVY FS SCAN')
        {
            steps
            {
                sh 'trivy fs . > trivy-fs-report.txt'
            }
        }
        stage('CODE BUILD')
        {
            steps
            {
                sh 'mvn clean install'
            }
        }
        stage('DOCKER IMAGE BUILD')
        {
            steps
            {
                script
                {
                    withDockerRegistry(credentialsId: 'docker-credentials', toolName: 'DOCKER_HOME')
                    {
                        sh 'docker build -t coupon:v${BUILD_NUMBER} .'    
                        sh 'docker tag coupon:v${BUILD_NUMBER} lehardocker/coupon:latest'
                        sh 'docker push lehardocker/coupon:latest'
                    }
                    
                }
            }
        }
        stage('TRIVY IMAGE SCAN')
        {
            steps
            {
                sh 'trivy image lehardocker/coupon:latest >trivy-image-report.txt'
            }
        }
        stage('DEPLOY TO CONTAINER')
        {
            steps
            {
                sh 'docker run -d --name coupon-v${BUILD_NUMBER} -p 8088:8080 lehardocker/coupon:latest'
            }        
        }
        stage("Deploy into Tomcat Server")
        {
            steps
            {
              deploy adapters: [tomcat9(url: 'http://localhost:8090/',credentialsId: 'tomcat-user')],war: 'target/*.war',contextPath: '/coupon'
            }
        }
    }
    post
    {
        always
        { 
            echo "This Section always run." 
            junit '**/target/surefire-reports/*.xml'
        }
        success
        { echo "Jenkins Pipeline Status: SUCCESSFUL" }
        failure
        { echo "Jenkins Pipeline Status: FAILED" }
    }

}
