package velcro;

public class UnitTest2 {

	public static void main(String[] args) {
		// Tests Class-related functions work correctly.
		int correct = 0;
		int totalTests = 0;
		
		System.out.println("Testing Classes...");
		System.out.println();
		
		// Tests getName()
		Classes testClass = new Classes("testtext");
		correct += output("testtext", testClass.getName(), "getName()");
		totalTests++;
		
		// Tests equals()
		Classes testClass2 = new Classes("testtext");
		correct += output(true, testClass.equals(testClass2), "equals()");
		totalTests++;
	
		// Tests rename()
		testClass2.rename("newtesttext");
		correct += output(false, testClass2.getName().equals("testtext"), "rename()");
		totalTests++;
		
		// Tests Instance.addClass()
		Instance newInstance = new Instance();
		newInstance.addClass("testtext");
		correct += output("testtext", newInstance.classList[0].name, "Instance.addClass()");
		totalTests++;
		
		// Tests Instance.checkClass()
		correct += output(false, newInstance.checkClass("testtextwrong"), "Instance.checkClass()");
		totalTests++;
		
		// Tests Instance.countClass()
		correct += output(1, newInstance.countClass("testtext"), "Instance.countClass()");
		totalTests++;
		
		// Tests Instance.removeClass() using String
		newInstance.removeClass("testtext");
		correct += output(0, newInstance.classList.length, "Instance.removeClass(String)");
		totalTests++;
		
		// Tests Instance.removeClass() using Classes
		newInstance.addClass("anothertesttext");
		Classes testClass3 = new Classes("anothertesttext");
		newInstance.removeClass(testClass3);
		correct += output(0, newInstance.classList.length, "Instance.removeClass(Classes)");
		totalTests++;
		
		// Results
		finale(totalTests, correct);
	}

	
	
	// Output text and test results. Overloaded to account for multiple input data types.
	private static int output (String expected, String result, String testName) {
		System.out.println(testName + ": Expected: " + expected + ". Result: " + result + ".");
		if (expected.equals(result)) {
			System.out.println("         PASS");
			return 1;
		} else {
			System.out.println("         FAIL");
			return 0;
		}
	}
	
	private static int output (int expected, int result, String testName) {
		System.out.println(testName + ": Expected: " + expected + ". Result: " + result + ".");
		if (expected == result) {
			System.out.println("         PASS");
			return 1;
		} else {
			System.out.println("         FAIL");
			return 0;
		}
	}
	
	private static int output (boolean expected, boolean result, String testName) {
		System.out.println(testName + ": Expected: " + expected + ". Result: " + result + ".");
		if (expected == result) {
			System.out.println("         PASS");
			return 1;
		} else {
			System.out.println("         FAIL");
			return 0;
		}
	}
	
	private static int output (Classes expected, Classes result, String testName) {
		System.out.println(testName + ": Expected: " + expected + ". Result: " + result + ".");
		if (expected.name.equals(result.name)) {
			System.out.println("         PASS");
			return 1;
		} else {
			System.out.println("         FAIL");
			return 0;
		}
	}
	
	// Test conclusion
	private static void finale (int expected, int result) {
		System.out.println();
		System.out.println("" + expected + "/" + result + " correct");
		if (expected/result == 1) {
			System.out.println("All tests cleared without issue");
		}
		else {
			System.out.println("See above for failed tests");
		}
	}
}
