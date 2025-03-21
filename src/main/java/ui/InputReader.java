package ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringJoiner;
import java.util.stream.IntStream;

/**
 * Utility class for reading and parsing command line arguments.
 * Provides methods to extract values and create argument maps from command input.
 */
public class InputReader {

    /**
     * Combines an array of strings with spaces between them.
     *
     * @param array The array of strings to combine.
     * @param startIndex The starting index in the array (inclusive).
     * @param endExclusivestartIndex The ending index in the array (exclusive).
     * @return The combined string with spaces between elements.
     * @throws IllegalArgumentException If the resulting string is empty.
     */
    private static String combineSpacedStrings(String[] array, int startIndex,
        int endExclusivestartIndex) {
        StringJoiner sj = new StringJoiner(" ");
        IntStream.range(startIndex, endExclusivestartIndex)
                .forEach(i -> sj.add(array[i]));
        String str = sj.toString().trim();

        return str;
    }

    /**
     * Finds the index of a specified argument in an array within a given range.
     *
     * @param arg The argument to search for.
     * @param array The array to search in.
     * @param startIndex The starting index to begin the search.
     * @param endExclusiveIndex The ending index to end the search (exclusive).
     * @return The index of the argument if found.
     * @throws IllegalArgumentException If there is an illegal argument.
     */
    private static int indexOfArg(String arg, String[] array, int startIndex,
        int endExclusiveIndex) throws IllegalArgumentException  {
        return IntStream.range(startIndex, endExclusiveIndex)
                .filter(i -> array[i].toLowerCase().equals(arg))
                .findFirst()
                .orElseThrow(() ->
                    new IllegalArgumentException("'" + arg + "' argument missing."));
    }

    /**
     * Retrieves the argument value for a command with a single implicit argument.
     * The value is assumed to start from index 1 of the array.
     *
     * @param givenArgs The array of command arguments.
     * @return The combined string value of all arguments after index 0.
     * @throws IllegalArgumentException If there is an illegal argument.
     */
    public static String retriveStringArg(String[] givenArgs) throws IllegalArgumentException {
        String argValue = combineSpacedStrings(givenArgs, 1, givenArgs.length);

        if (argValue.isEmpty()) {
            throw new IllegalArgumentException("No parameter provided.");
        }

        return argValue;
    }

    /**
     * Retrieves an integer argument from the command line arguments.
     *
     * @param givenArgs The array of command arguments.
     * @return The parsed integer value.
     * @throws IllegalArgumentException If there is an illegal argument.
     */
    public static int retriveIntArg(String[] givenArgs) throws IllegalArgumentException {
        String argValue = retriveStringArg(givenArgs);

        try {
            return Integer.parseInt(argValue);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("'" + argValue + "' is not a number.");
        }
    }

    /**
     * Creates a map of argument names to their values from the command line arguments.
     *
     * @param givenArgs The array of command arguments.
     * @param requiredArgs The array of required argument names.
     * @param hasImplicitInitialArg Whether the command has an implicit initial argument.
     * @return A HashMap mapping argument names to their values.
     * @throws IllegalArgumentException If there is an illegal argument.
     */
    public static HashMap<String, String> retriveArgMap(String[] givenArgs,
        String[] requiredArgs, boolean hasImplicitInitialArg) throws IllegalArgumentException {
        // Number of arguments required for command
        int requiredArgCount = requiredArgs.length + (hasImplicitInitialArg ? 1 : 0);

        if (requiredArgs.length == 0) {
            throw new IllegalArgumentException("No arguments provided for retrieval.");
        }

        // Number of split strings from input
        int givenArgCount = givenArgs.length;

        // Argument map to store argument names and values
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

            if (argValue.isEmpty()) {
                throw new IllegalArgumentException("No parameter provided for '" + arg + "'.");
            }

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

    /**
     * Creates a map of argument names to their values, assuming an implicit initial argument.
     * This is a convenience method that calls retriveArgMap with hasImplicitInitialArg set to true.
     *
     * @param givenArgs The array of command arguments.
     * @param requiredArgs The array of required argument names.
     * @return A HashMap mapping argument names to their values.
     * @throws IllegalArgumentException If there is an illegal argument.
     */
    public static HashMap<String, String> retriveArgMap(String[] givenArgs,
        String[] requiredArgs) throws IllegalArgumentException {
        return retriveArgMap(givenArgs, requiredArgs, true);
    }

}