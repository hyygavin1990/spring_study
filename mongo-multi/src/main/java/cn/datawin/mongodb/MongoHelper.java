package cn.datawin.mongodb;

import cn.datawin.bean.User;
import cn.datawin.util.DateFormat;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.Assert;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 假设配置中的数据库为myDB
 * 1、myDB::col                  本库中的表 (JPA中MongoRepository调用)
 * 2、myDB::col_yyyyMM           本库中的月表
 * 3、myDB::col_yyyyMMdd         本库中的日表
 * 4、myDB::col+suffix           本库中的自定义后缀表
 * 5、myDB_yyyyMM::col           月库中的表
 * 6、myDB_yyyyMM::col_yyyyMMdd  月库中日表
 * 7、myDB_yyyyMMdd::col         日库中的表
 * 7、dbName::col                自定义库中的表
 * Created by zhouyi on 2018/6/25.
 */
public class MongoHelper extends MongoTemplate {

    private MyMongoDbFactory myMongoDbFactory;

    public MongoHelper(MongoDbFactory mongoDbFactory) {
        super(mongoDbFactory);
        myMongoDbFactory = new MyMongoDbFactory(mongoDbFactory);
    }

    //=======================================myDB=======================================
    /**
     * 获取myDB库中的entityClass类反射的集合
     * @param entityClass
     * @return myDB::reflect(entityClass)
     */
    public MongoCollection getCollection(Class<?> entityClass){
        return myMongoDbFactory.getDb().getCollection(getCollectionName(entityClass));
    }

    /**
     * 获取myDB_yyyyMM库中的collectionName集合
     * @param date
     * @param collectionName
     * @return myDB_yyyyMM::collectionName
     */
    public MongoCollection getCollectionInMonthDb(Date date, String collectionName){
        String monthStr = DateFormat.dateFormat(date, "yyyyMM");
        return myMongoDbFactory.getDbWithSuffix(monthStr).getCollection(collectionName);
    }

    /**
     * 获取myDB_yyyyMM库中的entityClass类反射的集合
     * @param date
     * @param entityClass
     * @return myDB_yyyyMM::reflect(entityClass)
     */
    public MongoCollection getCollectionInMonthDb(Date date, Class<?> entityClass){
        return getCollectionInMonthDb(date,getCollectionName(entityClass));
    }

    /**
     * 获取myDB_yyyyMMdd库中的collectionName集合
     * @param date
     * @param collectionName
     * @return myDB_yyyyMMdd::collectionName
     */
    public MongoCollection getCollectionInDateDb(Date date, String collectionName){
        String dateStr = DateFormat.dateFormat(date, "yyyyMMdd");
        return myMongoDbFactory.getDbWithSuffix(dateStr).getCollection(collectionName);
    }

    /**
     * 获取myDB_yyyyMMdd库中的entityClass类反射的集合
     * @param date
     * @param entityClass
     * @return myDB_yyyyMMdd::reflect(entityClass)
     */
    public MongoCollection getCollectionInDateDb(Date date, Class<?> entityClass){
        return getCollectionInDateDb(date,getCollectionName(entityClass));
    }

    /**
     * 获取myDB_suffix库中的collectionName集合
     * @param suffix
     * @param collectionName
     * @return myDB_month::collectionName
     */
    public MongoCollection getCollectionInSuffixDb(String suffix, String collectionName){
        Assert.hasText(suffix, "suffix must not be empty.");
        return myMongoDbFactory.getDbWithSuffix(suffix).getCollection(collectionName);
    }

    /**
     * 获取myDB_suffix库中的entityClass类反射的集合
     * @param suffix
     * @param entityClass
     * @return myDB_suffix::reflect(entityClass)
     */
    public MongoCollection getCollectionInSuffixDb(String suffix, Class<?> entityClass){
        return getCollectionInSuffixDb(suffix,getCollectionName(entityClass));
    }

    /**
     * 获取在myDB_yyyyMM库中的collectionName_yyyyMMdd
     * @param date
     * @param collectionName
     * @return myDB_yyyyMM::collectionName_yyyyMMdd
     */
    public MongoCollection getDateCollectionInMonthDb(Date date, String collectionName){
        String dateStr = DateFormat.dateFormat(date,"yyyyMMdd");
        collectionName = collectionName + "_" + dateStr;
        String monthStr = DateFormat.dateFormat(date,"yyyyMM");
        return myMongoDbFactory.getDbWithSuffix(monthStr).getCollection(collectionName);
    }

    /**
     * 获取在myDB_yyyyMM库中的reflect(entityClass)_yyyyMMdd
     * @param date
     * @param entityClass
     * @return myDB_yyyyMM::reflect(entityClass)_yyyyMMdd
     */
    public MongoCollection getDateCollectionInMonthDb(Date date, Class<?> entityClass){
        String collectionName = getCollectionName(entityClass);
        return getDateCollectionInMonthDb(date,collectionName);
    }


    //=======================================dbName=======================================
    /**
     * 获取dbName库中的collectionName集合
     * @param dbName
     * @param collectionName
     * @return dbName::collectionName
     */
    public MongoCollection getCollection(String dbName, String collectionName){
        Assert.hasText(dbName, "dbName must not be empty.");
        Assert.hasText(collectionName, "collectionName must not be empty.");
        return myMongoDbFactory.getDb(dbName).getCollection(collectionName);
    }

    /**
     * 获取dbName库中的entityClass类反射的集合
     * @param dbName
     * @param entityClass
     * @return dbName::reflect(entityClass)
     */
    public MongoCollection getCollection(String dbName, Class<?> entityClass){
        return getCollection(dbName,getCollectionName(entityClass));
    }

    //=========================操作=========================

    public void insert(Object objectToSave) {
        super.insert(objectToSave);
    }

    public void insertAll(Collection<? extends Object> objectsToSave) {
        super.insertAll(objectsToSave);
    }

    public void insert(Object objectToSave,MongoCollection collection) {
        collection.insertOne(obj2Document(objectToSave));
    }

    public void insertAll(Collection<? extends Object> objectsToSave,MongoCollection collection) {
        List<Document> documentList = new ArrayList<>();
        for (Object o : objectsToSave) {
            documentList.add(obj2Document(o));
        }
        collection.insertMany(documentList);
    }

    public UpdateResult updateFirst( Query query, Update update, Class<?> entityClass) {
        return super.updateFirst(query, update, entityClass);
    }

    public UpdateResult updateMulti( Query query, Update update, Class<?> entityClass) {
        return super.updateMulti(query, update, entityClass);
    }

    /**
     * 更新
     * @param query 更新条件
     * @param key 要更新的字段
     * @param value 要更新的字段内容
     */
    public UpdateResult update(MongoCollection collection, Document query, String key, Object value){
        if (query == null) {
            query = new Document();
        }
        Document update = new Document(key, value);
        Document setUpdate = new Document("$set", update);
        return collection.updateMany(query, setUpdate);
    }

    /**
     * 更新
     * @param query 更新条件
     * @param update 更新内容
     */
    public UpdateResult update(MongoCollection collection, Document query, Document update){
        if (query == null) {
            query = new Document();
        }
        Document setUpdate = new Document("$set", update);
        return collection.updateMany(query, setUpdate);
    }

    /**
     * 更新
     * @param query 更新条件
     * @param setUpdate 更新内容
     * @param incUpdate 更新内容
     */
    public UpdateResult update(MongoCollection collection, Document query, Document setUpdate, Document incUpdate) {
        if (query == null) {
            query = new Document();
        }
        Document update = new Document();
        update.put("$set", setUpdate);
        update.put("$inc", incUpdate);
        return collection.updateMany(query, update);
    }

    /**
     * inc更新
     * @param query 更新条件
     * @param key 要更新的字段
     * @param value 要更新的字段内容
     */
    public UpdateResult incUpdate(MongoCollection collection, Document query, String key, int value){
        if (query == null) {
            query = new Document();
        }
        Document update = new Document(key, value);
        Document incUpdate = new Document("$inc", update);
        return collection.updateMany(query, incUpdate);
    }

    /**
     * inc更新
     * @param query 更新条件
     * @param update 更新内容
     */
    public UpdateResult incUpdate(MongoCollection collection, Document query, Document update){
        if (query == null) {
            query = new Document();
        }
        Document incUpdate = new Document("$inc", update);
        return collection.updateMany(query, incUpdate);
    }

    /**
     * 根据id查询一条记录
     * @param id 表的_id主键
     */
    public Document getOne(MongoCollection collection, ObjectId id) {
        if (id == null) {
            return null;
        }
        return (Document) collection.find(new Document("_id", id)).first();
    }

    /**
     * 根据id查询一条记录
     * @param id 表的_id主键
     * @param keys 要查询的字段
     */
    public Document getOne(MongoCollection collection, ObjectId id, Document keys) {
        if (id == null) {
            return null;
        }
        return (Document) collection.find(new Document("_id", id)).projection(keys).first();
    }

    /**
     * 查询列表
     * @param query 查询条件
     */
    public List<Document> findList(MongoCollection collection, Document query) {
        if (query == null) {
            query = new Document();
        }
        List<Document> list = new ArrayList<>();
        return (List<Document>) collection.find(query).into(list);
    }

    /**
     * 查询列表
     * @param query 查询条件
     * @param sort 排序
     */
    public List<Document> findList(MongoCollection collection, Document query, Document sort) {
        if (query == null) {
            query = new Document();
        }
        List<Document> list = new ArrayList<>();
        return (List<Document>) collection.find(query).sort(sort).into(list);
    }

    /**
     * 查询列表
     * @param query 查询条件
     * @param keys 字段
     */
    public List<Document> findListByKeys(MongoCollection collection, Document query, Document keys) {
        if (query == null) {
            query = new Document();
        }
        List<Document> list = new ArrayList<>();
        return (List<Document>) collection.find(query).projection(keys).into(list);
    }

    /**
     * 查询列表
     * @param query 查询条件
     * @param sort 排序
     */
    public List<Document> findListByKeys(MongoCollection collection, Document query, Document keys, Document sort) {
        if (query == null) {
            query = new Document();
        }
        List<Document> list = new ArrayList<>();
        return (List<Document>) collection.find(query).projection(keys).sort(sort).into(list);
    }

    /**
     * 分页查询
     * @param query 查询条件
     */
    public List<Document> findList(MongoCollection collection, Document query, MongoPage page) {
        if (query == null) {
            query = new Document();
        }
        FindIterable findIterable = collection.find(query);
        //设置总数和分页数
        long totalCount = collection.count(query);
        page.setTotalCount(totalCount);
        long totalPage = Math.round(Math.ceil((double)totalCount/(double)page.getPageSize()));
        page.setTotalPage(totalPage);
        //设置排序
        if (page.getOrderBy() != null) {
            findIterable.sort(page.getOrderBy());
        } else {
//            Document doc = new Document();
//            doc.append("inittime",1);
            findIterable.sort(new Document("_id", 1));
//            findIterable.sort(doc);
        }
        //设置分页查询
        findIterable.skip((page.getPageNumber() - 1) * page.getPageSize()).limit(page.getPageSize());
        List<Document> list = new ArrayList<>();
        return (List<Document>) findIterable.into(list);
    }

    /**
     * 分页查询，只查询需要的字段keys
     * @param query 查询条件
     */
    public List<Document> findList(MongoCollection collection, Document query, MongoPage page, Document keys) {
        if (query == null) {
            query = new Document();
        }
        FindIterable findIterable = collection.find(query);
        //设置字段
        if (keys != null && keys.size() > 0) {
            findIterable.projection(keys);
        }
        // 设置总数和分页数
        long totalCount = collection.count(query);
        page.setTotalCount(totalCount);
        long totalPage = Math.round(Math.ceil((double) totalCount / (double) page.getPageSize()));
        page.setTotalPage(totalPage);
        // 设置排序
        if (page.getOrderBy() != null) {
            findIterable.sort(page.getOrderBy());
        } else {
            findIterable.sort(new Document("_id", 1));
        }
        // 设置分页查询
        findIterable.skip((page.getPageNumber() - 1) * page.getPageSize()).limit(page.getPageSize());
        List<Document> list = new ArrayList<>();
        return (List<Document>) findIterable.into(list);
    }

    /**
     * 分页查询
     * @param query 查询条件
     */
    public List<Document> findListBySort(MongoCollection collection, Document query, MongoPage page, Integer sort, String sidx) {
        if (query == null) {
            query = new Document();
        }
        FindIterable findIterable = collection.find(query);
        //设置总数和分页数
        long totalCount = collection.count(query);
        page.setTotalCount(totalCount);
        long totalPage = Math.round(Math.ceil((double)totalCount/(double)page.getPageSize()));
        page.setTotalPage(totalPage);
        //设置排序
        if (page.getOrderBy() != null) {
            findIterable.sort(page.getOrderBy());
        } else {
            if("".equals(sidx)){
                findIterable.sort(new Document("_id", 1));
            }else{
                Document doc = new Document();
                doc.append(sidx,sort);
                findIterable.sort(doc);
            }

        }
        //设置分页查询
        findIterable.skip((page.getPageNumber() - 1) * page.getPageSize()).limit(page.getPageSize());
        List<Document> list = new ArrayList<>();
        return (List<Document>) findIterable.into(list);
    }


    /**
     * 查询下一条记录
     * @param query 查询条件
     */
    public List<Document> findNextDocument(MongoCollection collection, Document query , Integer sort, String sidx) {
        if (query == null) {
            query = new Document();
        }
        FindIterable findIterable = collection.find(query);
        findIterable.sort(new Document("_id", 1));
        findIterable.limit(1);
        List<Document> list = new ArrayList<>();
        return (List<Document>) findIterable.into(list);
    }


    private Document obj2Document(Object object){
        Document document = new Document();
        document.putAll(beanToMap(object));
        return document;
    }

    public static JSONObject beanToMap(Object bean){
        BeanInfo info =null;
        JSONObject map = new JSONObject();
        Class<?> _class = bean.getClass();
        try {
            info = Introspector.getBeanInfo(_class);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        PropertyDescriptor[] pros = info.getPropertyDescriptors();
        for (PropertyDescriptor pro : pros) {
            String name = pro.getName();
            if (!"class".equals(name)) {
                Method method = pro.getReadMethod();
                try {
                    Object result = method.invoke(bean);
                    if (result != null) {
                        map.put(name, result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }
    public static void main(String[] args) throws  Exception {
        User user = new User();
        user.setName("Tom");
        user.setGender("male");
        user.setInittime(new Date());
        beanToMap(user);

//        Document document = new Document();
//        document.putAll(map);
    }

}
