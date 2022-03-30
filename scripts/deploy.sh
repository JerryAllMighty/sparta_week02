#!/bin/bash

echo "> deploy 실행이 되는가"

REPOSITORY=/home/ubuntu/Juteukki_week02
cd $REPOSITORY
APP_NAME=cicdapp

echo "> 새 어플리케이션 배포 중"
JAR_NAME=$(ls $REPOSITORY/build/libs/ | grep 'SNAPSHOT.jar' | tail -n 1)
JAR_PATH=$REPOSITORY/build/libs/$JAR_NAME

CURRENT_PID=$(pgrep -f $APP_NAME)

if [ -z $CURRENT_PID ]
then
  echo "> 종료할 것 없음"
else
  echo "> kill -9 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> JAR 파일 배포"
nohup java -jar $JAR_PATH > /dev/null 2> /dev/null < /dev/null &