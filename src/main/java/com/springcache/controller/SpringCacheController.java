package com.springcache.controller;

import com.springcache.Entity.Item;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SpringCacheController {
    /**
     * @Cacheable 应用到读取数据的方法上，先从缓存中读取，如果没有再从DB获取数据，然后把数据添加到缓存中
     * unless 表示条件表达式成立的话不放入缓存
     *
     * @Cacheable注解，缓存到redis
     *
     *      cacheNames：指定缓存的名称
     *
     *      key：定义组成的key值，如果不定义，则使用全部的参数计算一个key值。可以使用spring El表达式
     *
     *      condition：在执行方法之前条件判断，满足条件缓存，负责不缓存
     *
     *      unless：在执行方法之后条件判断，不满足条件返回true，满足条件返回false
     *
     * key 的值可以指定没固定值，也可以取方法参数，例如：key = "#studentId"
     *
     * sync：redis中有值时，多个线程可以同时访问，如果没有值，只允许一个线程查询
     *
     * 3、@CacheEvict注解，删除指定的key
     *
     * cacheNames：指定缓存的名称
     *
     * key：定义组成的key值，如果不定义，则使用全部的参数计算一个key值。可以使用spring El表达式
     *
     * 4、@cachePut注解，更新数据之后，更新redis对应key的值，和@Cacheable配套使用，但是添加这两个注解的方法，返回必须一样
     *
     * 5、@CacheConfig注解，作用域是当前类，这样不需要每个类设置cacheName的值
     *
     *
     *
     */
    @RequestMapping("/item")
    @Cacheable(value = "item")
    public Item getItemById(Integer id) {
        Item item = new Item();
        item.setId(id);
        item.setName("德玛西亚" + id);
        return item;
    }

    /**
     * @Cacheable 应用到读取数据的方法上，先从缓存中读取，如果没有再从DB获取数据，然后把数据添加到缓存中
     * unless 表示条件表达式成立的话不放入缓存
     */
    @GetMapping("/item/all")
    @Cacheable(value = "item")
    public List<Item> getAllItem() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Item item1 = new Item();
        item1.setId(666);
        item1.setName("德玛西亚" + 666);
        Item item2 = new Item();
        item2.setId(999);
        item2.setName("德玛西亚" + 999);
        List<Item> items = new ArrayList<Item>();
        items.add(item1);
        items.add(item2);
        return items;
    }

    /**
     * 这个是空方法，由于缓存的原因，上面/item/all设置的值会被这个方法作为缓存拿出来
     */
    @GetMapping("/item/all2")
    @Cacheable(value = "item")
    public List<Item> getAllItem2() {
        List<Item> items = new ArrayList<Item>();
        return items;
    }

    /**
     * @param
     * @return
     * @CachePut 应用到写数据的方法上，如新增/修改方法，调用方法时会自动把相应的数据放入缓存
     */
    @CachePut(value = "item")
    @GetMapping("/item/{id}/update")
    public Item updateItem(@PathVariable int id) {
        Item item = new Item();
        item.setId(id);
        item.setName("德玛西亚XXX" + id);
        return item;
    }

    /**
     * #item.itemId或者#p0.itemId是EL表达式来指定我们的key
     */
    @Cacheable(value = "item", key = "#item.itemId")
    @GetMapping("/item/object")
    public Item updateItem(Item item) {
        return item;
    }
}

