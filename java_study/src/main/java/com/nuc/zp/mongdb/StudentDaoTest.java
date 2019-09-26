package com.nuc.zp.mongdb;

import com.nuc.zp.mongdb.dao.StudentService;
import com.nuc.zp.mongdb.domain.Student;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentDaoTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void testSaveStudent() throws Exception {
        Student student = new Student("李朗",13);
        System.out.println(studentService.save(student));
    }

    @Test
    public void testUpdateStudent() throws Exception {
        System.out.println(studentService.update(new Student(new ObjectId("5d81f61d98dc96eab84d7a6c"),"李四",11)));
    }

    @Test
    public void testFindById() throws Exception {
        System.out.println(studentService.findById("5d81f61d98dc96eab84d7a6c"));
    }

    @Test
    public void testDeleteById() throws Exception {
        System.out.println(studentService.deleteById("5d81f61d98dc96eab84d7a6c"));
    }

    @Test
    public void testQueryAll() throws Exception {
        System.out.println(studentService.queryAll());
    }

    @Test
    public void testQueryAllList() throws Exception {
        System.out.println(studentService.queryAll(2,2));
    }
    @Test
    public void testQueryAllList2() throws Exception {
        System.out.println(studentService.queryAll(2,2,"5d832f8298dc96427bd647ab"));
    }
}
