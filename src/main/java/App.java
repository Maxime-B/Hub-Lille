

import ipint.glp.donnees.EmailManager;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
    	 
    	EmailManager mm = (EmailManager) context.getBean("mailMail");
        mm.sendMail("latifou.sano@gmail.com","latifou.sano@gmail.com","Yong Mook Kim", "This is text content");
        
    }
}
