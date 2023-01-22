// seed - use of DSL - create new jobs
String projectName = "Python-WebApp"
pipelineJob(projectName) {
  def repo = 'https://github.com/RoySCV/Jenkins.git'
  definition {
    cpsScm {
      scm {
        git {
          remote {
            url(repo)
            credentials('github-user-and-access-token')
          }
          branch('main')
        }
      }
      scriptPath('pipeline/python-webapp-pipeline.groovy')
    }
  }
}