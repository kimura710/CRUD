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
	
	// EntityManager�Ƃ����N���X�̓G���e�B�e�B�𗘗p
	//�@���邽�߂ɕK�v�ȋ@�\��񋟂���
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
		// Query��SQL�Ńf�[�^��₢���킹�邽�߂̃N�G���[���ɑ�������@�\������
		//�@�I�u�W�F�N�g�ł��B�B�@�h��rom�@�l���c�������h�Ƃ����̂́hselect * from Mydata"
		//�@�Ɠ����Ӗ����������N�G���[���ł�
		Query query = entityManager.createQuery("from MyData");
		@SuppressWarnings("unchecked")
		List<MyData> list = query.getResultList();
		entityManager.close();
		return list;
	}*/
	@Override
	public List<MyData> getAll(){
		// Root�̎擾�ł́A���̌^�Ƃ���MyData���w�肵�Ă��܂��B�ufrom�v���\�b�h���Ăяo�����Ƃ�MyData����擾�����
		// �SMyData�����Ƃ��ĕێ�����Root�C���X�^���X��������
		// Root�擾��͂��ׂĂ�MyData���擾���邽�߂�CriteriaQuery�́uselect�v���Ăяo���A������MyData.class��
		// �w�肷�邱�Ƃ�Root�ɕێ�����Ă���SMyData���擾����悤��CriteriaQuery���ݒ肳���B
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
		// id=�Z�Z�Ƃ�����������ݒ肵��MyData����擾���Ă���
		// getSingleresult���\�b�h�Ƃ́AQuery���瓾����G���e�B�e�B����������o���Ԃ�����
		return (MyData)entityManager.createQuery("from MyData where id ="+ id).getSingleResult();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<MyData> findByName(String name){
		// name=�Z�Z�Ƃ���������ݒ肵��MyData�����o���B
		// name�͓������̂��������݂���\��������܂��̂ŁAgetResultList��List��
		// ���̂܂ܕԂ��B�u�G���e�B�e�B�P�̂��AList���v�́A�����������Ɏg�������悤�B
		return (List<MyData>)entityManager.createQuery("from MyData where name =" + name).getResultList();
	}
	/*@Override
	public List<MyData> find(String fstr){
		List<MyData> list =null;
		// :fstr�ł����A����́u:�Z�Z�v�Ƃ����`����JSQL�̃N�G���[���ɏ����ꂽ���̂́A
		// �p�����[�^�p�̕ϐ��Ƃ��Ĉ�����B�܂肱��fstr�ɒl��ݒ肷�邱�Ƃ�
		//�@�N�G���[��������������
		String qstr = "from MyData where id = :fstr";
		// ��L�̂��Ƃ��s���Ă���̂��AQuery�C���X�^���X�́usetParameter�v�ł��B�����
		// ��1�����̕ϐ��ɑ�2�����̒l��ݒ肷�铭��������B
		
		// ��F�j�hfstr"�Ƃ����ϐ���Long.parseLong(fstr)��ݒ肵�Ă���
		// ��2������Long.parseLong�ł��闝�R��MyData�N���X��long�l�Ƃ��Ē�`����Ă���������
		// �����l�����킳�Ȃ��Ƃ����Ȃ�����B
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
