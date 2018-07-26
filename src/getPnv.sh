source=$2
echo "Source is : $source" > /Users/avinash.t/Documents/workspace/qabot/src/debug1.txt
echo "keys $1" >> /Users/avinash.t/Documents/workspace/qabot/src/debug1.txt
echo "command run is: redis-cli -h $source keys *$1* | grep "userToken" " >> /Users/avinash.t/Documents/workspace/qabot/src/debug1.txt
result=$(redis-cli -h $source keys *$1* | grep "userToken")
echo $result >> /Users/avinash.t/Documents/workspace/qabot/src/debug1.txt
echo "redis-cli -h $source get "$result"" >> /Users/avinash.t/Documents/workspace/qabot/src/debug1.txt
final_result=$(redis-cli -h $source get "$result")
echo $final_result >> /Users/avinash.t/Documents/workspace/qabot/src/debug1.txt
echo "One Time Password for PNV : ${final_result%%-*}" > /Users/avinash.t/Documents/workspace/qabot/src/pnv.txt

