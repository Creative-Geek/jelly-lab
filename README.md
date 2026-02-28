# Jelly Lab — ITI Assignment

This project uses **Apache Commons Jelly** — an XML-based scripting engine that lets you call Java code directly from an XML template file (`.jelly`). The Maven build system compiles the Java code and runs the Jelly scripts, producing an HTML output file.

---

## Project Structure

```
jelly-lab/
├── pom.xml                                          # Maven build configuration
├── runWatch.sh                                      # Script to auto-run the project on file save
├── src/
│   └── main/
│       ├── java/com/example/
│       │   ├── Main.java                            # Entry point — runs all .jelly files
│       │   └── SimpleFunctions.java                 # Java helper functions called from Jelly
│       └── resources/jelly/
│           └── hello.jelly                          # Main Jelly script with all tasks
└── target/
    └── hello.html                                   # Generated HTML output
```

---

## File Descriptions

### `pom.xml`

The Maven project configuration file. It defines the project dependencies (Apache Commons Jelly) and the build plugin that allows running the project with `mvn exec:java`.

---

### `src/main/java/com/example/Main.java`

The Java entry point of the project. It:

- Scans the `src/main/resources/jelly/` directory for all `.jelly` files.
- Creates a **Jelly context** and injects shared variables (e.g., app name, build date, Java version).
- Parses and executes each `.jelly` script.
- Writes the output to an HTML file inside the `target/` folder.

It also contains an inner `JellyHelper` utility class with some built-in helper methods.

---

### `src/main/java/com/example/SimpleFunctions.java`

A Java class containing the custom functions written for this assignment. It is instantiated inside the Jelly script using `<j:new>` and its methods are called directly from the template.

| Method                              | Description                                                                                                   |
| ----------------------------------- | ------------------------------------------------------------------------------------------------------------- |
| `sayHello(String name)`             | Returns a welcome greeting string                                                                             |
| `isEven(int number)`                | Returns `true` if the number is even, `false` otherwise                                                       |
| `capitalize(String str)`            | Capitalizes the first letter of a string; returns a message if the string is null or empty                    |
| `calculateAge(String dobString)`    | Parses a date string (`YYYY-MM-DD`) and returns the person's age in years; returns `-1` for invalid input     |
| `validatePassword(String password)` | Throws a `RuntimeException` if the password is shorter than 8 characters; returns a success message otherwise |

---

### `src/main/resources/jelly/hello.jelly`

The main Jelly template file. It uses XML tags from the Jelly core library (`j:` prefix) to run Java logic and produce HTML output. This file contains all the assignment tasks in order:

**Part 2 Tasks:**

1. **Custom Date** — Creates a `Calendar` object and sets a specific date (Jan 1, 2025) instead of using today's date.
2. **Random Integer** — Calls `Math.random()`, multiplies by 100, and converts to an integer using `.intValue()`.
3. **Minimum Value** — Creates an `ArrayList`, adds numbers, sorts it with `Collections.sort()`, then reads the first element (the minimum).

**Part 3 Tasks:**

1. **Purple Style** — Creates a `java.awt.Color` object for purple (RGB: 128, 0, 128) and uses its RGB values to apply an inline CSS style.
2. **Even Check** — Calls `functions.isEven(10)` to demonstrate the even-number checker.
3. **Capitalize** — Calls `functions.capitalize(...)` to demonstrate first-letter capitalization with an empty-string guard.
4. **Calculate Age** — Sets a date-of-birth string (`2000-01-01`) and calls `functions.calculateAge(...)` to display the calculated age.
5. **Password Validation** — Sets a short password (`1234567`), calls `functions.validatePassword(...)` inside a `<j:catch>` block to safely catch the error, and displays a red error message or a green success message.

---

### `target/hello.html`

The **generated output** file produced when the project runs. Open this file in a browser to see the results of all tasks rendered as an HTML page.

---

### `runWatch.sh`

A shell script that watches for file changes and automatically re-runs `mvn exec:java` every time a `.jelly` or `.java` file is saved. Useful during development to see live output without manually running Maven each time.

---

## How to Run

```bash
mvn exec:java
```

The output will be written to `target/hello.html`.

To run automatically on every file save:

```bash
./runWatch.sh
```

## Output

<html><head><title>Hello Scripts</title></head><body style="font-family: Arial, sans-serif; padding: 20px;"><h2>Custom Date Task</h2><p style="color: gray;">Setting the date to 2025/01/01</p><p>Wed Jan 01 16:16:59 EET 2025</p><hr></hr><h2>Random Number Task</h2><p style="color: gray;">Generating a random number between 0 and 100</p><p>40</p><hr></hr><h2>Min Max Task</h2><p style="color: gray;">Input: 10, 20, 30</p><p>Minimum value is 10</p><p>Maximum value is 30</p><hr></hr><h2>Set a style on purple color using java color</h2><p style="color: gray;">Color object with arguments: 128, 0, 128</p><p style="color: rgb(128, 0, 128);">This
                text is purple</p><hr></hr><h2>is Even Task</h2><p style="color: gray;">Input: 10</p><p>true</p><hr></hr><h2>capitalize Task</h2><p style="color: gray;">Inputs: 'hello there!' and an empty string</p><p>Hello there!</p><p>str is null or empty</p><hr></hr><h2>Function that takes a date and calculates age in years</h2><div><p style="color: gray;">Input: 2000-01-01</p><p>Calculated Age: 26 years old</p></div><hr></hr><h2>Function that throws an error if password is less than 8 characters</h2><div><p style="color: gray;">Input: 1234567</p><p style="color: gray;">Password Length: 7</p><p style="color: red;">Error: Password must be at least 8 characters long</p></div><hr></hr><img src="https://media.tenor.com/pagVxAkHfWAAAAAM/my-job-here-is-done-bye.gif"></img></body></html>
