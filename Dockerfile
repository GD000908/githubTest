# Tomcat 9 + Java 11 기반 이미지 사용
FROM tomcat:9.0.85-jdk11

# 기존에 있는 예제들 삭제
RUN rm -rf /usr/local/tomcat/webapps/*

# Maven 빌드 결과물(war 파일)을 Tomcat에 복사
COPY target/userdemo.war /usr/local/tomcat/webapps/ROOT.war

# 포트 설정 (기본 8080)
EXPOSE 8080

# Tomcat 실행
CMD ["catalina.sh", "run"]
