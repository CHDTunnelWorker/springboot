--- 获取key
local key1 = KEYS[1]
local key2 = KEYS[2]
local arg1 = ARGV[1]
local arg2 = ARGV[2]
--- lua逻辑脚本
redis.call("set",key1,arg1)
redis.call("set",key2,arg2)
local str1 = redis.call("get",key1)
local str2 = redis.call("get",key2)
if str1 == str2 then
return 1
end
return 0