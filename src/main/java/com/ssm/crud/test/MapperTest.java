package com.ssm.crud.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ssm.crud.bean.Comment;
import com.ssm.crud.bean.Article;
import com.ssm.crud.bean.Category;
import com.ssm.crud.bean.Message;
import com.ssm.crud.bean.TimeLine;
import com.ssm.crud.bean.User;
import com.ssm.crud.dao.CommentMapper;
import com.ssm.crud.dao.ArticleMapper;
import com.ssm.crud.dao.CategoryMapper;
import com.ssm.crud.dao.CommentMapper;
import com.ssm.crud.dao.MessageMapper;
import com.ssm.crud.dao.TimeLineMapper;
import com.ssm.crud.dao.UserMapper;


/*@SuppressWarnings("all")*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {
	
	@Autowired
	UserMapper userMapper;
	@Autowired
	TimeLineMapper timeLineMapper;
	@Autowired
	CategoryMapper categoryMapper;
	@Autowired
	MessageMapper messageMapper;
	@Autowired
	CommentMapper commentMapper;
	@Autowired
	ArticleMapper articleMapper;
	
	@Autowired
	SqlSession sqlSession;
	
	/**
	 * 测试DepartmentMapper
	 */
	@Test
	public void testCRUD(){
	/*	//1、创建SpringIOC容器
		ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
		//2、从容器中获取mapper
		DepartmentMapper bean = ioc.getBean(DepartmentMapper.class);*/
		System.out.println(userMapper);
		System.out.println(timeLineMapper);
		System.out.println(messageMapper);
		System.out.println(categoryMapper);
		System.out.println(articleMapper);
		System.out.println(commentMapper);
		
		//1、插入几个部门
//		departmentMapper.insertSelective(new Department(null, "开发部"));
//		departmentMapper.insertSelective(new Department(null, "测试部"));
		
		//2、生成员工数据，测试员工插入
		SimpleDateFormat sft=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date nowDate=new Date();
		String createTime=sft.format(nowDate);
		/*userMapper.insertSelective(new User(null, "123", "M123", 1,createTime,createTime));
		timeLineMapper.insertSelective(new TimeLine(null, "123", "G", "1","2018-08-25",createTime,createTime));
		messageMapper.insertSelective(new Message(null, "123","8361@qq.com","hello",createTime,""));
		categoryMapper.insertSelective(new Category(null, "123"));
		articleMapper.insertSelective(new Article(null,"234","xub",1,createTime,createTime,""));
		commentMapper.insertSelective(new Comment(null, 1, "xub","hello","1","aishan","243"));*/
		messageMapper.insertSelective(new Message(null, "123","8361@qq.com","hello","",createTime));
		
		//3、批量插入多个员工；批量，使用可以执行批量操作的sqlSession。
		
//		for(){
//			employeeMapper.insertSelective(new Employee(null, , "M", "Jerry@atguigu.com", 1));
//		}
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		TimeLineMapper mapper1 = sqlSession.getMapper(TimeLineMapper.class);
		CategoryMapper mapper2 = sqlSession.getMapper(CategoryMapper.class);
		MessageMapper mapper3 = sqlSession.getMapper(MessageMapper.class);
		ArticleMapper mapper4 = sqlSession.getMapper(ArticleMapper.class);
		CommentMapper mapper5 = sqlSession.getMapper(CommentMapper.class);
		for(int i = 0;i<100;i++){
			String uid = UUID.randomUUID().toString().substring(0,5)+i;
			/*mapper.insertSelective(new User(null,uid, "M123", 1,createTime,createTime));
			mapper1.insertSelective(new TimeLine(null, "123", "G", "1","2018-08-25",createTime,createTime));
			mapper2.insertSelective(new Category(null, "123"));
			mapper3.insertSelective(new Message(null, "123","8361@qq.com","hello",createTime,""));
			mapper4.insertSelective(new Article(null,"234","xub",1,createTime,createTime,""));
			mapper5.insertSelective(new Comment(null, 8, "xub","hello","1","aishan","243"));*/
		}
		System.out.println("批量完成");
		
	}

}
