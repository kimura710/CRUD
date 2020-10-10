package com.example.demo;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.repository.MyDataRepository;

@Controller
public class HeloController {
	
	@Autowired
	MyDataRepository repository;
	//　@PersistenceContextというのはEntityManagerのBeanを取得
	//　してフィールドに設定します。
	//　EntityManagerの取得には、このアノテーションを利用するのが
	//　Spring Bootの基本
	// @PersistenceContext
	EntityManager entityManager;
	@Autowired
	private MyDataService service;
	
	MyDataDaolmpl dao;
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("msg","MyDataのサンプルです");
		List<MyData> list = service.getAll();
		mav.addObject("datalist",list);
		return mav;
	}
	@RequestMapping(value = "/",method = RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView form(@ModelAttribute("formModel")MyData mydata,ModelAndView mav) {
		repository.saveAndFlush(mydata);
		return new ModelAndView("redirect:/");
	}
	@PostConstruct
	public void init() {
		dao = new MyDataDaolmpl(entityManager);
		MyData d1 = new MyData();
		d1.setName("tuyano");
		d1.setAge(123);
		d1.setMail("syouda@tuyano.com");
		d1.setMemo("this is MyData");
		repository.saveAndFlush(d1);
		
		MyData d2 = new MyData();
		d2.setName("takumi");
		d2.setAge(123);
		d2.setMail("syouda@tuyano.com");
		d2.setMemo("this is MyData");
		repository.saveAndFlush(d2);
		
		MyData d3 = new MyData();
		d3.setName("tuyano");
		d3.setAge(123);
		d3.setMail("syouda@tuyano.com");
		d3.setMemo("this is MyData");
		repository.saveAndFlush(d3);

	}
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute MyData mydata,@PathVariable int id,ModelAndView mav) {
		mav.setViewName("edit");
		mav.addObject("title","edit mydata.");
		Optional<MyData> data = repository.findById((long)id);
		mav.addObject("formModel",data.get());
		return mav;
	}
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView update(@ModelAttribute MyData mydata,ModelAndView mav) {
		repository.saveAndFlush(mydata);
		return new ModelAndView("redirect:/");
	}
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public ModelAndView delete(@PathVariable int id,ModelAndView mav) {
		mav.setViewName("delete");
		mav.addObject("title","delete mydata.");
		Optional<MyData> data =repository.findById((long)id);
		mav.addObject("formModel",data.get());
		return mav;
	}
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView remove(@RequestParam long id,ModelAndView mav) {
		repository.deleteById(id);
		return new ModelAndView("redirect:/");
	}
	@RequestMapping(value="/find",method=RequestMethod.GET)
	public ModelAndView find(ModelAndView mav) {
		mav.setViewName("find");
		mav.addObject("title","find page");
		mav.addObject("msg","MyDataのサンプル");
		mav.addObject("value","");
		List<MyData> list = service.getAll();
		mav.addObject("datalist",list);
		return mav;
	}
	// HttpServletRequestとうのはJSP/サーブレットで必ずお世話になるもの
	// サーブレットでdoGet/doPostする際に使用
	@RequestMapping(value ="/find",method=RequestMethod.POST)
	public ModelAndView search(HttpServletRequest request,ModelAndView mav) {
		mav.setViewName("find");
		String param = request.getParameter("fstr");
		if(param == "") {
			mav = new ModelAndView("redirect:/find");
		}else {
			mav.addObject("title","find result");
			mav.addObject("msg","「" + param + "」の検索結果");
			mav.addObject("value",param);
			List<MyData> list = service.find(param);
			mav.addObject("datalist",list);
			
		}
		return mav;
	}
	

}
