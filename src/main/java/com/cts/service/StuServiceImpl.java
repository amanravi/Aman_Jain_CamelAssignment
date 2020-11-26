package com.cts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.controller.model.Students;
@Service
public class StuServiceImpl extends RouteBuilder {

	@Autowired
	DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		}
	

	@Override
	public void configure() throws Exception {

		
		from("direct:insert").process(new Processor() {
			
			public void process(Exchange exchange) throws Exception{
				Students e = exchange.getIn().getBody(Students.class);
				String query = "INSERT INTO students(stuId,stuName) "
						+ "VALUES('" +e.getStuId()+ "','" +e.getStuName()+ "')";
				
				exchange.getIn().setBody(query);
			}
		})
		.to("jdbc:dataSource");
		
		
		
		
		from("direct:select").setBody(constant("select * from Students")).to("jdbc:dataSource")
		.process(new Processor() {
			public void process(Exchange exchange) throws Exception {
				ArrayList<Map<String, String>> dataList = (ArrayList<Map<String, String>>) exchange.getIn()
						.getBody();
				List<Students> studen = new ArrayList<Students>();
				System.out.println(dataList);
				for (Map<String, String> data : dataList) {
					Students stu = new Students();
					stu.setStuId(data.get("stuId"));
					stu.setStuName(data.get("stuName"));
					studen.add(stu);
				}
				exchange.getIn().setBody(studen);
			}
		});

	}

}