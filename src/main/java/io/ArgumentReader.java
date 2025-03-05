package io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringJoiner;
import java.util.stream.IntStream;

import error.AppException;
import error.ArgMapForNoArgsException;
import error.MissingArgumentException;

public class ArgumentReader {

    // Combine strings with a space in between
    private static String combineSpacedStrings(String[] array, int startIndex,
        int endExclusiveIndex) throws IllegalArgumentException {
        StringJoiner sj = new StringJoiner(" ");
        IntStream.range(startIndex, endExclusiveIndex)
                .forEach(i -> sj.add(array[i]));
        String str = sj.toString().trim();

        if (str.isEmpty()) {
            throw new IllegalArgumentException("Empty value provided.");
        }

        return str;
    }

    // Find the index of an argument in a String array
    private static int indexOfArg(String arg, String[] array, int startIndex,
        int endExclusiveIndex) throws MissingArgumentException {
        return IntStream.range(startIndex, endExclusiveIndex)
                .filter(i -> array[i].toLowerCase().equals(arg))
                .findFirst()
                .orElseThrow(() -> new MissingArgumentException(arg));
    }

    // Obtain the argument value for only one implicit argument
    public static String retriveArgValue(String[] givenArgs) throws MissingArgumentException {
        String argValue = combineSpacedStrings(givenArgs, 1, givenArgs.length);

        if (argValue.isEmpty()) {
            throw new MissingArgumentException();
        }

        return argValue;
    }

    // Obtain an integer argument
    public static int retriveIntArg(String[] givenArgs)
        throws MissingArgumentException, NumberFormatException {
        String argValue = retriveArgValue(givenArgs);
        return Integer.parseInt(argValue);
    }

    // Obtain the argument values as a map for an array of required arguments
    public static HashMap<String, String> retriveArgMap(String[] givenArgs, String[] requiredArgs,
            boolean hasImplicitInitialArg) throws AppException {
        // Number of arguments required for command
        int requiredArgCount = requiredArgs.length + (hasImplicitInitialArg ? 1 : 0);

        if (requiredArgs.length == 0) {
            throw new ArgMapForNoArgsException();
        }

        // Number of split strings from input
        int givenArgCount = givenArgs.length;
        HashMap<String, String> argMap = new HashMap<>(requiredArgCount);

        // Indices of starting argument values within string array from split input
        ArrayList<Integer> startIndices = new ArrayList<>(requiredArgCount);

        // First argument value starts from the index 1 (after the command)
        startIndices.add(1);

        for (int i = 0; i < requiredArgCount; i++) {
            // Obtain the current argument index
            int argIndex = hasImplicitInitialArg ? (i - 1) : i;

            // Obtain the current argument (empty for implicit initial argument)
            String arg = (i == 0 && hasImplicitInitialArg) ? "" : requiredArgs[argIndex];

            // Obtain the index of next required argument within the split string array
            int endIndex = (i != requiredArgCount - 1)
                    ? indexOfArg(requiredArgs[argIndex + 1], givenArgs, startIndices.get(i), givenArgCount)
                    : givenArgCount;

            // Combine the strings from the start index to the end index (exclusive)
            String argValue = combineSpacedStrings(givenArgs, startIndices.get(i), endIndex);

            // Update the argument map
            argMap.put(arg, argValue);

            // Append the new start index
            // (only if 'i' is not the last required argument index)
            if (i != requiredArgCount - 1) {
                startIndices.add(endIndex + 1);
            }
        }

        return argMap;
    }

    public static HashMap<String, String> retriveArgMap(String[] givenArgs,
        String[] requiredArgs) throws AppException {
        return retriveArgMap(givenArgs, requiredArgs, true);
    }

}