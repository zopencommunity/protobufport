node('linux')
{
  stage ('Poll') {
    //Poll from upstream
    checkout([
      $class: 'GitSCM',
      branches: [[name: '*/main']],
      doGenerateSubmoduleConfigurations: false,
      extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/protocolbuffers/protobuf.git']]])
  }
    //Poll for local changes
    checkout([
      $class: 'GitSCM',
      branches: [[name: '*/main']],
      doGenerateSubmoduleConfigurations: false,
      extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/zopencommunity/protobufport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/protobufport.git'), string(name: 'PORT_DESCRIPTION', value: 'Protocol Buffers - Google data interchange format' ), string(name: 'BUILD_LINE', value: 'DEV')]
  }
}
