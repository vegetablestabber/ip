import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringJoiner;
import java.util.stream.IntStream;

public class ConsoleTools {
    private static String combineSpacedStrings(String[] array, int startIndex, int endExclusiveIndex) {
        StringJoiner sj = new StringJoiner(" ");
        IntStream.range(startIndex, endExclusiveIndex)
                .forEach(i -> sj.add(array[i]));

        return sj.toString();
    }

    private static int indexOfArg(String arg, String[] array, int startIndex, int endExclusiveIndex) throws Exception {
        return IntStream.range(startIndex, endExclusiveIndex)
                .filter(i -> array[i].toLowerCase().equals(arg))
                .findFirst()
                .orElseThrow(() -> new Exception("'" + arg + "' argument missing."));
    }

    public static String retriveArgValue(String[] givenArgs) {
        return combineSpacedStrings(givenArgs, 1, givenArgs.length);
    }

    // TODO: Check if it's better with regex instead
    public static HashMap<String, String> retriveArgMap(String[] givenArgs, String[] requiredArgs,
            boolean hasImplicitInitialArg) throws Exception {
        int requiredArgCount = requiredArgs.length + (hasImplicitInitialArg ? 1 : 0);

        if (requiredArgs.length == 0) {
            throw new Exception("Cannot return argument map for empty required argument array.");
        }

        int givenArgCount = givenArgs.length;
        ArrayList<Integer> startIndices = new ArrayList<>(requiredArgCount);
        HashMap<String, String> argMap = new HashMap<>(requiredArgCount);

        startIndices.add(1);

        for (int i = 0; i < requiredArgCount; i++) {
            int argIndex = hasImplicitInitialArg ? (i - 1) : i;
            String arg = (i == 0 && hasImplicitInitialArg) ? "" : requiredArgs[argIndex];

            int endIndex = (i != requiredArgCount - 1)
                    ? indexOfArg(requiredArgs[argIndex + 1], givenArgs, startIndices.get(i), givenArgCount)
                    : givenArgCount;

            String argValue = combineSpacedStrings(givenArgs, startIndices.get(i), endIndex);
            argMap.put(arg, argValue);

            if (i != requiredArgCount - 1) {
                startIndices.add(endIndex + 1);
            }
        }

        return argMap;
    }

    public static HashMap<String, String> retriveArgMap(String[] givenArgs, String[] requiredArgs) throws Exception {
        return retriveArgMap(givenArgs, requiredArgs, true);
    }
}
