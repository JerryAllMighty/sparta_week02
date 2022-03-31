ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh
source ${ABSDIR}/switch.sh

IDLE_PORT=$(find_idle_port)
sleep 10

for RETRY_COUNT in {1..10}
do
  RESPONSE=$(curl -s http://localhost:$IDLE_PORT/profile)
  UP_COUNT=$(echo ${}RESPONSE} | grep 'real' | wc -1)

  if [ ${UP_COUNT} -ge 1]
  then # $up_count >= 1 ("real 문자열이 있는지 검증")
    switch_proxy
    break
  else
    echo "> Health Check : ${RESPONSE}"
  fi

  if [${RETRY_COUNT} -eq 10]
  then
    echo "health check 실패"
    exit 1
  fi

  sleep 10
done


