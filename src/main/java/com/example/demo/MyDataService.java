package com.example.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

// ���̃N���X���T�[�r�X�Ƃ��ēo�^���邽�߂̃A�m�e�[�V����
@Service
public class MyDataService {
	// @PersistenceContext��EntityManager��Bean�������I�Ɋ��蓖�Ă邽�߂̃A�m�e�[�V����
	
	@PersistenceContext
	private EntityManager entitymanager;
	
	@SuppressWarnings("unchecked")
	public List<MyData> getAll(){
		return(List<MyData>) entitymanager.createQuery("from MyData").getResultList();
		
	}
	public MyData get(int num) {
		return (MyData)entitymanager.createQuery("from MyData where id =" + num).getSingleResult();
		
	}
     public List<MyData> find(String fstr){
    	 CriteriaBuilder builder = entitymanager.getCriteriaBuilder();
    	 CriteriaQuery<MyData> query = builder.createQuery(MyData.class);
    	 Root<MyData> root = query.from(MyData.class);
    	 query.select(root).where(builder.equal(root.get("name"),fstr));
    	 List<MyData> list = null;
    	 list = (List<MyData>) entitymanager.createQuery(query).getResultList();
    	 return list;
     }
}
