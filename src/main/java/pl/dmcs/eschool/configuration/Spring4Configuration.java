package pl.dmcs.eschool.configuration;

import java.util.Locale;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaImpl;
import pl.dmcs.eschool.dao.ClazzDAO;
import pl.dmcs.eschool.dao.ClazzDAOImpl;
import pl.dmcs.eschool.dao.StudentDAO;
import pl.dmcs.eschool.dao.StudentDAOImpl;
import pl.dmcs.eschool.dao.SubjectDAO;
import pl.dmcs.eschool.dao.SubjectDAOImpl;
import pl.dmcs.eschool.dao.TeacherDAO;
import pl.dmcs.eschool.dao.TeacherDAOImpl;
import pl.dmcs.eschool.dao.UserDAO;
import pl.dmcs.eschool.dao.UserDAOImpl;
import pl.dmcs.eschool.domain.Clazz;
import pl.dmcs.eschool.domain.Mark;
import pl.dmcs.eschool.domain.Student;
import pl.dmcs.eschool.domain.Subject;
import pl.dmcs.eschool.domain.Teacher;
import pl.dmcs.eschool.domain.User;
import pl.dmcs.eschool.domain.UserRole;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "pl.dmcs.eschool")
@EnableTransactionManagement
@Import({ SecurityConfiguration.class })
public class Spring4Configuration extends WebMvcConfigurerAdapter {

	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(new String[] { "/WEB-INF/views/tilesConfiguration/tiles.xml" });
		tilesConfigurer.setCheckRefresh(true);
		return tilesConfigurer;
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		TilesViewResolver viewResolver = new TilesViewResolver();
		registry.viewResolver(viewResolver);
	}

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/eschool");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.put("hibernate.default_schema", "public");
		properties.put("hibernate.hbm2ddl.auto", "update");
		return properties;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.scanPackages("pl.dmcs.eschool.domain");
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClasses(User.class);
		sessionBuilder.addAnnotatedClass(Student.class);
		sessionBuilder.addAnnotatedClass(UserRole.class);
		sessionBuilder.addAnnotatedClass(Clazz.class);
		sessionBuilder.addAnnotatedClass(Mark.class);
		sessionBuilder.addAnnotatedClass(Subject.class);
		sessionBuilder.addAnnotatedClass(Teacher.class);
		return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}

	@Autowired
	@Bean(name = "userDAO")
	public UserDAO getUserDAO(SessionFactory sessionFactory) {
		return new UserDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "studentDAO")
	public StudentDAO getStudentDAO(SessionFactory sessionFactory) {
		return new StudentDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "clazzDAO")
	public ClazzDAO getClazzDAO(SessionFactory sessionFactory) {
		return new ClazzDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "subjectDAO")
	public SubjectDAO getSubjectDAO(SessionFactory sessionFactory) {
		return new SubjectDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "teacherDAO")
	public TeacherDAO getTeacherDAO(SessionFactory sessionFactory) {
		return new TeacherDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "reCaptcha")
	public ReCaptchaImpl getReCaptcha() {
		ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
		reCaptcha.setPrivateKey("6LdZe8QSAAAAAA8DQu_WXpuxQpTVaQM0EYPvO1M5");
		reCaptcha.setPublicKey("6LdZe8QSAAAAANw5tJUftmtx1m45kYk3fw8aNd1N");
		return reCaptcha;
	}

	@Bean
	public JavaMailSender javaMailService() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost("smtp.gmail.com");
		javaMailSender.setPort(587);
		javaMailSender.setUsername("eschool.pl@gmail.com");
		javaMailSender.setPassword("politechnika69");
		javaMailSender.setJavaMailProperties(getMailProperties());
		return javaMailSender;
	}

	private Properties getMailProperties() {
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.from.email", "eschool.pl@gmail.com");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		return properties;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setDefaultLocale(new Locale("en"));
		resolver.setCookieName("myLocaleCookie");
		resolver.setCookieMaxAge(4800);
		return resolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang");
		registry.addInterceptor(interceptor);
	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean
	public ViewResolver pdfViewResolver() {
		ResourceBundleViewResolver pdfViewResolver = new ResourceBundleViewResolver();
		pdfViewResolver.setBasename("views");
		pdfViewResolver.setOrder(3);
		return pdfViewResolver;
	}
}
