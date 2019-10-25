package com.laohu.springboot.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * @program: springboot
 * @description: redis基本操作类型实践
 * @author: Holland
 * @create: 2019-10-18 14:55
 **/
@Controller
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 操作redis的字符串和散列类型
     * @return
     */
    @RequestMapping("/stringAndHash")
    @ResponseBody
    public Map<String,Object> testStringAndHash(){
        redisTemplate.opsForValue().set("key1","value1");
        //注意这里使用了默认的jdk序列化器,所以redis保存的不是整数 ,不能运算
        redisTemplate.opsForValue().set("int_key","1");
        //stringRedisTemplate 保存的数据就是字符串,可以进行运算
        stringRedisTemplate.opsForValue().set("int","1");
        //使用自增运算,每次加一
        stringRedisTemplate.opsForValue().increment("int",1);
        //获取底层的jedis连接
        Jedis jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        //进行减一操作,因为这个操作redistemplate不能直接支持,所以我们通过redistemplate获取他的底层jedis连接来直接操作
        jedis.decr("int");
        //新建一个map,保存两组数据
        Map<String,String> hash = new HashMap<>();
        hash.put("field1","value1");
        hash.put("field2","value2");
        //存入一个散列数据类型
        stringRedisTemplate.opsForHash().putAll("hash",hash);
        //新增一对数据
        stringRedisTemplate.opsForHash().put("hash","field3","value3");
        //绑定散列操作的key,这样可以连续对同一个散列数据类型进行操作
        BoundHashOperations hashOps = stringRedisTemplate.boundHashOps("hash");
        //删除两个数据
        hashOps.delete("field1","field2");
        //新增一对数据
        hashOps.put("field5","value5");
        //返回响应结果
        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }

    /**
     * 操作redis的列表(链表类型),redis中的列表是一种链表结构,查慢,增删快
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> testList(){
        //插入两个列表,注意它们在链表的顺序
        //下面为左插入,链表从左到右的顺序为v10,v8,v4,v2
        stringRedisTemplate.opsForList().leftPushAll("list1","v2","v4","v6","v8","v10");
        //链表从左到右顺序为v1,v2,v3,v4,v5,v6
        stringRedisTemplate.opsForList().rightPushAll("list2", "v1","v2","v3","v4","v5","v6");
        //绑定list2链表进行操作
        BoundListOperations listOps = stringRedisTemplate.boundListOps("list2");
        //从右边弹出一个成员
        Object result1 = listOps.rightPop();
        //获取定位元素,redis从0下标开始计算,这里下标为1的值为v2
        Object result2 = listOps.index(1);
        //从左边向链表插入数据
        listOps.leftPush("v0");
        //求链表长度
        Long size = listOps.size();
        //求链表下标区间成员,整个链表下标范围为0-(size-1),这里不取最后一个元素
        List elements = listOps.range(0, size - 2);
        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        map.put("result1",result1);
        map.put("result2",result2);
        map.put("size",size);
        map.put("elements",elements);
        return map;
    }

    /**
     * 操作redis集合
     */
    @RequestMapping("/set")
    @ResponseBody
    public Map<String,Object> testSet(){
        //请注意,这里的v1重复了两次,目的是说明集合不允许重复,所以总共只是插入5个成员到集合中
        stringRedisTemplate.opsForSet().add("set1","v1","v1","v2","v3","v4","v5");
        stringRedisTemplate.opsForSet().add("set2","v2","v4","v6","v8");
        //绑定set1集合进行操作
        BoundSetOperations setOps = stringRedisTemplate.boundSetOps("set1");
        //增加两个元素
        setOps.add("v6","v7");
        //删除两个元素
        setOps.remove("v1","v7");
        //返回所有元素
        Set set1 = setOps.members();
        //求成员数
        Long size = setOps.size();
        //求交集
        Set inter = setOps.intersect("set2");
        //求交集,并且用新集合inter保存
        setOps.intersectAndStore("set2","inter");
        //求差集
        Set diff = setOps.diff("set2");
        //求差集,并且用新集合diff保存
        setOps.diffAndStore("set2","diff");
        //求并集
        Set union = setOps.union("set2");
        //求并集,并且用新集合union保存
        setOps.unionAndStore("set2","union");
        //返回结果
        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        map.put("set1",set1);
        map.put("size",size);
        map.put("inter",inter);
        map.put("diff",diff);
        map.put("union",union);
        return map;
    }

    /**
     * 测试redis有序集合
     * @return
     */
    @RequestMapping("/zset")
    @ResponseBody
    public Map<String,Object> testZest(){
        Set<ZSetOperations.TypedTuple<String>> typedTupleSet = new HashSet<>();
        for(int i = 1;i <= 9;i++){
            //分数
            double score = i*0.1;
            //创建一个TypedTuple对象,存入值和分数
            ZSetOperations.TypedTuple<String> typedTuple = new DefaultTypedTuple<>("value"+i,score);
            typedTupleSet.add(typedTuple);
        }
        //往有序集合插入元素
        stringRedisTemplate.opsForZSet().add("zset1",typedTupleSet);
        //绑定zset1有序集合
        BoundZSetOperations zSetOps = stringRedisTemplate.boundZSetOps("zset1");
        //增加一个元素
        zSetOps.add("value10",0.26);
        Set<String> setRange = zSetOps.range(1, 6);
        //按分数排序获取有序集合
        Set setScore = zSetOps.rangeByScore(0.2, 0.6);
        //定义值范围
        RedisZSetCommands.Range range = new RedisZSetCommands.Range();
        //大于value3
        range.gt("value3");
        //大于等于value3
        //range.gte("value3");
        //小于value8
        //range.lt("value8");
        //小于等于value8
        range.lte("value8");
        //按值排序,请注意这个排序是按字符串排序
        Set<String> setLex = zSetOps.rangeByLex(range);
        //删除元素
        zSetOps.remove("value9","value2");
        //求分数
        Double score = zSetOps.score("value8");
        //在下标区间下,按分数排序,同时返回value和scor|e
        Set<ZSetOperations.TypedTuple<String>> rangeSet = zSetOps.rangeWithScores(1, 6);
        //在分数区间下,按分数排序,同时返回value和score
        Set<ZSetOperations.TypedTuple<String>> scoreSet = zSetOps.rangeByScoreWithScores(1,6);
        //按从大到小排序
        Set<String> reverseSet = zSetOps.reverseRange(2, 8);
        //返回结果
        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        map.put("setRange",setRange);
        map.put("setScore",setScore);
        map.put("setLex",setLex);
        map.put("reverseSet",reverseSet);
        map.put("rangeSet",rangeSet);
        map.put("scoreSet",scoreSet);
        return map;
    }

    /**
     * 测试redis的事务机制
     * @return
     */
    @RequestMapping("/multi")
    @ResponseBody
    public Map<String,Object> testMulti() {
        redisTemplate.opsForValue().set("key1", "value1");
        List list = (List) redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                //设置要监控key1
                operations.watch("key1");
                //开启事务，在exec命令执行前，全部只是进入队列
                operations.multi();
                operations.opsForValue().set("key2", "value2");
                //①
                operations.opsForValue().increment("key1",1);
                //获取值将为null，因为redis只是把命令放入队列
                Object value2 = operations.opsForValue().get("key2");
                System.out.println("命令在队列中，所以value为null[" + value2 + "]");
                operations.opsForValue().set("key3", "value3");
                Object value3 = operations.opsForValue().get("key3");
                System.out.println("命令在队列中，所以value为null[" + value3 + "]");
                //执行exec命令，将先判别key1是否在监控后被修改过，如果是，则不执行任务，否则就执行任务 ②
                return operations.exec();
            }
        });
        System.out.println(list);
        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }

    /**
     * 测试redis流水线
     * @return
     */
    @RequestMapping("/pipeline")
    @ResponseBody
    public Map<String,Object> testPipeline(){
        long start = System.currentTimeMillis();
        redisTemplate.executePipelined(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                Long start = System.currentTimeMillis();
                for(int i = 1;i <= 100000;i++){
                    operations.opsForValue().set("pipeline_"+i,"value_"+i);
                    String value = (String) operations.opsForValue().get("pipeline_"+i);
                    if(i == 100000){
                        System.out.println("命令只是进入队列，所以值为空【"+value+"】");
                    }
                }
                return null;
            }
        });
        long end = System.currentTimeMillis();
        System.out.println("耗时："+(end-start)+"毫秒。");
        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }

    /**
     * 测试redis的发布和订阅
     * @param msg
     */
    @RequestMapping("/publish")
    @ResponseBody
    public void testPublish(@RequestParam String msg){
        redisTemplate.convertAndSend("topic1",msg);
    }
}
