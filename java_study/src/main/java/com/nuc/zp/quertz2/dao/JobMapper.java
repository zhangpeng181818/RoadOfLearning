package com.nuc.zp.quertz2.dao;



import com.nuc.zp.quertz2.domain.MyJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobMapper extends JpaRepository<MyJob, Integer> {

	@Query("select j from MyJob j")
	List<MyJob> queryList();
}