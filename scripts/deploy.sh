#!/bin/bash


REPOSITORY=/home/ubuntu
PROJECT_NAME=Juteukki_week02

echo "> Build 파일 복사"
cp $REPOSITORY/zip/*.jar $REPOSITORY/

CURRENT_PID=$(pgrep -fl $PROJECT_NAME | grep jar | awk '{print $1}')


if [ -z $CURRENT_PID ];
then
  echo "> 종료할 것 없음"
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)
chmod +x $JAR_NAME



echo "> JAR 파일 배포"
nohup java -jar \
-Dspring.config.location=classpath:/application.
properties,classpath:/application-real.properties,/home/ubuntu/application-oauth.properties,
/home/ubuntu/application-real-db.properties \
-Dspring.profiles.active=real \
$JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
