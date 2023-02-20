pipeline {
  agent any
  environment {
    CLOUDSDK_CORE_PROJECT='burner-noohauqu'
  }
  stages {
    stage('test') {
      steps {
        withCredentials([file(credentialsId: 'kubernetes-account', variable: 'kubernetes-account')]) {
          sh '''
            gcloud auth activate-service-account --key-file="/secure/kubernetes-account.json"
            kubectl apply -f https://github.com/noohauqu/kubernetes/blob/main/create-ns.yaml
            kubectl apply -f https://github.com/noohauqu/kubernetes/blob/main/deployment.yaml
          '''
        }
      }
    }
  }
}