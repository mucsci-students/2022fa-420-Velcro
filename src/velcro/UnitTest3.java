package velcro;

public class UnitTest3 {

	public static void main(String[] args) {
		// Tests Relationship-related functions work correctly.
		int correct = 0;
		int totalTests = 0;
		
		System.out.println("Testing Relationships...");
		System.out.println();
		
		// Tests getSource()
		Relationships testRelationship = new Relationships("testsource", "testdestination");
		correct += output("testsource", testRelationship.getSource(), "getSource()");
		totalTests++;

		// Tests getDestination()
		correct += output("testdestination", testRelationship.getDestination(), "getDestination()");
		totalTests++;
		
		// Tests equals()
		Relationships testRelationship2 = new Relationships("testsource", "testdestination");
		correct += output(true, testRelationship.equals(testRelationship2), "equals()");
		totalTests++;
	
		// Tests Instance.addRelationship()
		Instance newInstance = new Instance();
		newInstance.addRelationship("testsource", "testdestination");
		correct += output("testsource", newInstance.relationshipList[0].source, "Instance.addRelationship() source");
		correct += output("testdestination", newInstance.relationshipList[0].destination, "Instance.addRelationship() destination");
		totalTests++;
		totalTests++;
		
		// Tests Instance.checkRelationship()
		correct += output(false, newInstance.checkRelationship("testtextwrong", "testdestination"), "Instance.checkRelationship()");
		totalTests++;
		
		// Tests Instance.countRelationship()
		correct += output(1, newInstance.countRelationship("testsource", "testdestination"), "Instance.countRelationship()");
		totalTests++;
		
		// Tests Instance.removeRelationship() using String
		newInstance.removeRelationship("testsource", "testdestination");
		correct += output(0, newInstance.relationshipList.length, "Instance.removeRelationship(String)");
		totalTests++;
		
		// Tests Instance.removeRelationship() using Relationships
		newInstance.addRelationship("anothertestsource", "anothertestdestination");
		Relationships testRelationship3 = new Relationships("anothertestsource", "anothertestdestination");
		newInstance.removeRelationship(testRelationship3);
		correct += output(0, newInstance.relationshipList.length, "Instance.removeRelationship(Relationships)");
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
	
	private static int output (Relationships expected, Relationships result, String testName) {
		System.out.println(testName + ": Expected: " + expected + ". Result: " + result + ".");
		if (expected.destination.equals(result.destination) && expected.source.equals(result.source)) {
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
