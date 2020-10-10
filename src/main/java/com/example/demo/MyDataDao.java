package com.example.demo;

import java.io.Serializable;
import java.util.List;

public interface MyDataDao<T> extends Serializable {
	// 全エンティティを取得する
	public List<T> getAll();
	//ID番号を引数に指定してエンティティを検索し、返す。エンティティ取得の基
	public T findById(long id);
	//名前からエンティティを検索する。これ実はCRUDのメソッドでは利用しないが
	//検索の基本ということで用意した。
	public List<T> findByName(String name);
	public List<T> find(String fstr);
}
