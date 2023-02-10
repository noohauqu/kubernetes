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
            #gcloud auth activate-service-account --key-file="/secure/jenkins-kube-sa.json"
            kubectl create deployment nh-webserver --image=us.gcr.io/burner-noohauqu/highway-site:v2
            kubectl expose deployment nh-webserver --type LoadBalancer --port 80 --target-port 80
            Kubectl get deployment -o wide
          '''
        }
      }
    }
  }
}