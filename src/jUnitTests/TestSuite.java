package jUnitTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	TestComment.class,
	TestDatabase.class
})

public class TestSuite {   
}  	