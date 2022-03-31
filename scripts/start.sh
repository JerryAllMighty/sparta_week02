ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

REPOSITORY=/home/ubuntu/nonstop

PROJECT_NAME=Juteukki_week02

echo "> Build 파일 복사"
echo "> cp $REPOSITORY/zip/*.jar" $REPOSITORY/"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

chmod +x $JAR_NAME

IDLE_PROFILE=$(find_idle_profile)

nohup java -jar \
  -Dspring.config.location=classpath:/application.properties,classpath:/application-$IDLE_PROFILE.properties,/
  home/ubuntu/application-oauth.properties,/home/ubuntu/application-real-db.properties \
  -Dspring.profiles.active=$IDLE_PROFILE \
  $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &

