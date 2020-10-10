package com.example.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

@Repository
public class MyDataDaolmpl implements MyDataDao<MyData> {
	private static final long serialVersionUID = 1L;
	
	// EntityManagerというクラスはエンティティを利用
	//　するために必要な機能を提供する
	private EntityManager entityManager;
	public MyDataDaolmpl() {
		super();
	}
	public MyDataDaolmpl(EntityManager manager) {
		this();
		entityManager = manager;
	}

	/*@Override
	public List<MyData> getAll() {
		// QueryはSQLでデータを問い合わせるためのクエリー文に相当する機能を持つ
		//　オブジェクトです。。　”ｆrom　ＭｙＤａｔａ”というのは”select * from Mydata"
		//　と同じ意味合いを持つクエリー文です
		Query query = entityManager.createQuery("from MyData");
		@SuppressWarnings("unchecked")
		List<MyData> list = query.getResultList();
		entityManager.close();
		return list;
	}*/
	@Override
	public List<MyData> getAll(){
		// Rootの取得では、総称型としてMyDataを指定しています。「from」メソッドを呼び出すことでMyDataから取得される
		// 全MyDataを情報として保持したRootインスタンスが得られる
		// Root取得後はすべてのMyDataを取得するためにCriteriaQueryの「select」を呼び出し、引数にMyData.classを
		// 指定することでRootに保持されている全MyDataを取得するようにCriteriaQueryが設定される。
		List<MyData> list = null;
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MyData> query = builder.createQuery(MyData.class);
		Root<MyData> root = query.from(MyData.class);
		query.select(root);
		list = (List<MyData>)entityManager.createQuery(query).getResultList();
		return list;
	}
	@Override
	public MyData findById(long id) {
		// id=〇〇といった条件を設定してMyDataから取得している
		// getSingleresultメソッドとは、Queryから得られるエンティティを一つだけ取り出し返すもの
		return (MyData)entityManager.createQuery("from MyData where id ="+ id).getSingleResult();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<MyData> findByName(String name){
		// name=〇〇という条件を設定してMyDataを取り出す。
		// nameは同じものが複数存在する可能性がありますので、getResultListでListを
		// そのまま返す。「エンティティ単体か、Listか」は、こういう風に使い分けよう。
		return (List<MyData>)entityManager.createQuery("from MyData where name =" + name).getResultList();
	}
	/*@Override
	public List<MyData> find(String fstr){
		List<MyData> list =null;
		// :fstrですが、これは「:〇〇」という形式でJSQLのクエリー文に書かれたものは、
		// パラメータ用の変数として扱われる。つまりこのfstrに値を設定することで
		//　クエリー文を完成させる
		String qstr = "from MyData where id = :fstr";
		// 上記のことを行っているのが、Queryインスタンスの「setParameter」です。これは
		// 第1引数の変数に第2引数の値を設定する働きをする。
		
		// 例：）”fstr"という変数にLong.parseLong(fstr)を設定していた
		// 第2引数がLong.parseLongである理由はMyDataクラスでlong値として定義されていいたため
		// 検索値も合わさないといけないから。
		Query query =entityManager.createQuery(qstr).setParameter("fstr", Long.parseLong(fstr));
		list = query.getResultList();
		return list;
	}*/
	@Override
	public List<MyData> find(String fstr){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MyData> query = builder.createQuery(MyData.class);
		Root<MyData> root = query.from(MyData.class);
		query.select(root).where(builder.equal(root.get("name"), fstr));
		List<MyData> list =null;
		list = (List<MyData>) entityManager.createQuery(query).getResultList();
		return list;
	}

}
