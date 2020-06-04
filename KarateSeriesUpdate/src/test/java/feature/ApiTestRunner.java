package feature;

import com.intuit.karate.junit5.Karate;


public class ApiTestRunner {
	
	@Karate.Test
	Karate testSample() {
        //return Karate.run("sample").relativeTo(getClass());
		return new Karate().feature("demo").relativeTo(getClass());
		
	}
	
}
