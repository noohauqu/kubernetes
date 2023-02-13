pipeline {
  agent any
  environment {
    CLOUDSDK_CORE_PROJECT='burner-noohauqu'
  }
  stages {
    stage('test') {
      steps {
        withCredentials([file(credentialsId: 'jenkins-kube-sa', variable: 'jenkins-kube-sa')]) {
          sh '''
            docker system prune -a --volumes -f
            docker login  --username noohauqu --password Docker@123
            docker run -d -p 8050:80 nginx:latest
            for i in `docker ps -a | awk '{print $1}' `;do docker exec -itd $i /bin/bash && git clone https://github.com/noohauqu/covid19-site.git && rm -rf /usr/share/nginx/html/* && cd "covid site" && cp -r . /usr/share/nginx/html/; done
            
          '''
        }
      }
    }
  }
}