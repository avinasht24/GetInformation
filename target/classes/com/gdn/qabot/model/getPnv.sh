source=$2
result=$(redis-cli -h $source keys *$1* | grep "userToken")
final_result=$(redis-cli -h $source get "$result")
echo "One Time Password for PNV : ${final_result%%-*}"
