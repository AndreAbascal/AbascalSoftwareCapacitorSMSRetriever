
  Pod::Spec.new do |s|
    s.name = 'AbascalSoftwareCapacitorSMSRetriever'
    s.version = '0.0.1'
    s.summary = 'Plugin para o recebimento de SMS automático para Android. Atualizado para Ionic V5 + Capacitor. Desenvolvido por Abascal Software: https://abascalsoftware.com.br'
    s.license = 'MIT'
    s.homepage = 'https://AndreAbascal@bitbucket.org/abascalsoftware/capacitorsmsretriever.git'
    s.author = 'Abascal Software'
    s.source = { :git => 'https://AndreAbascal@bitbucket.org/abascalsoftware/capacitorsmsretriever.git', :tag => s.version.to_s }
    s.source_files = 'ios/Plugin/**/*.{swift,h,m,c,cc,mm,cpp}'
    s.ios.deployment_target  = '11.0'
    s.dependency 'Capacitor'
  end