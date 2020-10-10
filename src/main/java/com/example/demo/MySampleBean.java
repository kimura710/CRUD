/*package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

/* @Componentというアノテーションはこのクラスがコンポーネントとしアプリケーションに
 *　に認識されるようになる。コンポーネントクラスでは必ず必要 
 　@ComponentがないとクラスのインスタンスがBeanとして登録されない
 
@Component
public class MySampleBean {
	private int counter = 0;
	private int max = 10;
	
	/*　@Autowiredの付いたコンストラクタが用意されていないとアプリケーション実行時に
	 * エラーが発生し起動に失敗するのでこのアノテーションも必ず用意する
	 *
	@Autowired
	public MySampleBean(ApplicationArguments args) {
		List<String> files = args.getNonOptionArgs();
		try {
			max= Integer.parseInt(files.get(0));
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
	}
	public int count() {
		counter++;
		counter = counter > max ? 0 : counter;
		return counter;
	}

}*/
