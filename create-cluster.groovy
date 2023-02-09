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
            gcloud container clusters create prod-cluster --machine-type n1-standard-2 --num-nodes 1 --zone us-central1-a --scopes "https://www.googleapis.com/auth/source.read_write,cloud-platform" --cluster-version latest
          '''
        }
      }
    }
  }
}