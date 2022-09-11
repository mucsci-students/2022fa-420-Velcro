package velcro;

public class UnitTest1 {

	public static void main(String[] args) {
		// Tests Attribute-related functions work correctly.
		int correct = 0;
		int totalTests = 0;
		
		System.out.println("Testing Attributes...");
		System.out.println();
		
		// Tests getName()
		Attributes testAttribute = new Attributes("testtext");
		correct += output("testtext", testAttribute.getName(), "getName()");
		totalTests++;
		
		// Tests equals()
		Attributes testAttribute2 = new Attributes("testtext");
		correct += output(true, testAttribute.equals(testAttribute2), "equals()");
		totalTests++;
	
		// Tests rename()
		testAttribute2.rename("newtesttext");
		correct += output(false, testAttribute2.getName().equals("testtext"), "rename()");
		totalTests++;
		
		// Tests Instance.addAttribute()
		Instance newInstance = new Instance();
		newInstance.addAttribute("testtext");
		correct += output("testtext", newInstance.attributeList[0].name, "Instance.addAttribute()");
		totalTests++;
		
		// Tests Instance.checkAttribute()
		correct += output(false, newInstance.checkAttribute("testtextwrong"), "Instance.checkAttribute()");
		totalTests++;
		
		// Tests Instance.countAttribute()
		correct += output(1, newInstance.countAttribute("testtext"), "Instance.countAttribute()");
		totalTests++;
		
		// Tests Instance.removeAttribute() using String
		newInstance.removeAttribute("testtext");
		correct += output(0, newInstance.attributeList.length, "Instance.removeAttribute(String)");
		totalTests++;
		
		// Tests Instance.removeAttribute() using Attributes
		newInstance.addAttribute("anothertesttext");
		Attributes testAttribute3 = new Attributes("anothertesttext");
		newInstance.removeAttribute(testAttribute3);
		correct += output(0, newInstance.attributeList.length, "Instance.removeAttribute(Attributes)");
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
	
	private static int output (Attributes expected, Attributes result, String testName) {
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
