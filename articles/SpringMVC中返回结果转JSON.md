## SpringMVC中返回结果转JSON

1、在配置文件中配置

```xml
<!--json转化器，它可以将结果转化-->
<bean class="org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter">
	<property name="messageConverters">
		<list>
			<ref bean="mappingJacksonHttpMessageConverter" />
		</list>
	</property>
</bean>
<bean id="mappingJacksonHttpMessageConverter" class="org.springframework.http.converter.json.MappingJacksonHttpMessageConverter">
	<!--	
	</property > 
	 	<property name="supportedMediaTypes">
		<list>
			<value>application/json;charset=UTF-8</value>
		</list>
	</property> -->
</bean>
```
2、引入jackson包

```xml
<dependency>
	    <groupId>com.fasterxml.jackson.core</groupId>
	    <artifactId>jackson-databind</artifactId>
	    <version>2.2.1</version>
	</dependency>
	
	<dependency>
	<groupId>org.codehaus.jackson</groupId>
	  <artifactId>jackson-mapper-asl</artifactId>
	  <version>1.9.13</version>
	</dependency>
```








