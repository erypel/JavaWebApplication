/*package test.java.unit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.javawebapp.util.JavaWebAppUtils;

public class TestJavaWebAppUtils
{
	@Test
	public void testGenerateUniqueIdNotNull()
	{
		assertNotNull(JavaWebAppUtils.generateUniqueId());
	}
	
	@Test
	public void testGenerateUniqueIdIsLong()
	{
		assertTrue(JavaWebAppUtils.generateUniqueId() instanceof Long);
	}
	
	@Test
	public void testGenerateUniqueIdAreUnique()
	{
		Set<Long> set = new HashSet<Long>();
		
		for(int i = 0; i < 100; i++)
		{
			long id = JavaWebAppUtils.generateUniqueId();
			if(set.contains(id))
				fail("Duplicate id created. Try running the tests one more time. The odds of this happening should be miniscule.");
			set.add(id);
		}
		
		assertTrue(true);
	}
}
*/