package com.example.demo;

import java.io.Serializable;
import java.util.List;

public interface MyDataDao<T> extends Serializable {
	// �S�G���e�B�e�B���擾����
	public List<T> getAll();
	//ID�ԍ��������Ɏw�肵�ăG���e�B�e�B���������A�Ԃ��B�G���e�B�e�B�擾�̊�
	public T findById(long id);
	//���O����G���e�B�e�B����������B�������CRUD�̃��\�b�h�ł͗��p���Ȃ���
	//�����̊�{�Ƃ������Ƃŗp�ӂ����B
	public List<T> findByName(String name);
	public List<T> find(String fstr);
}
