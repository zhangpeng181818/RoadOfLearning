package com.nuc.zp.mongdb.dao;

import com.nuc.zp.mongdb.domain.Student;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Student save(Student student) {
        return mongoTemplate.save(student);
    }

    public Student update(Student student) {
        return mongoTemplate.save(student);
    }

    public Student findById(String id) {
        return mongoTemplate.findById(id, Student.class);
    }

    public long deleteById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.remove(query, Student.class).getDeletedCount();
    }

    public List<Student> queryAll() {
        return mongoTemplate.findAll(Student.class);
    }

    public PageResult<Student> queryAll(int pageNum, int pageSize) {
        return queryAll(pageNum, pageSize, null);
    }


    public PageResult<Student> queryAll(int pageNum, int pageSize, String lastId) {
        final Criteria criteriaDefinition = new Criteria();

        final Query query = new Query(criteriaDefinition).with(new Sort(Arrays.asList(new Sort.Order(Sort.Direction.ASC, "_id"))));

        //分页逻辑
        long total = mongoTemplate.count(query, Student.class);
        final Integer pages = (int) Math.ceil(total / (double) pageSize);
        if (pageNum <= 0 || pageNum > pages) {
            pageNum = 1;
        }

        if (lastId != null && lastId.length() > 0 && lastId.trim().length() > 0) {
            if (pageNum != 1) {
                criteriaDefinition.and("_id").gt(new ObjectId(lastId));
            }
            query.limit(pageSize);
        } else {
            int skip = pageSize * (pageNum - 1);
            query.skip(skip).limit(pageSize);
        }
        List<Student> statByClientList = mongoTemplate.find(query, Student.class);

        PageResult<Student> pageResult = new PageResult<>();
        pageResult.setTotal(total);
        pageResult.setPages(pages);
        pageResult.setPageSize(pageSize);
        pageResult.setPageNum(pageNum);
        pageResult.setList(statByClientList);
        return pageResult;
    }


}
