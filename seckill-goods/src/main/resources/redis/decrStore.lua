local storeNums = redis.call('GET',KEYS[1]);
if  storeNums > '0'  then
    redis.call('DECR',KEYS[1]);
    return true
else
    return false
end