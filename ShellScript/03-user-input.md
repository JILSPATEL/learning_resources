# Topic 3: User Input (`read` command)

## Basic Input Reading

```bash
#!/bin/bash

echo "What is your name?"
read name
echo "Hello, $name! Nice to meet you."
```

**Explanation:**
- `read name` — pauses execution, waits for the user to type something and
  press Enter, then stores that input into the variable `name`.
- You then use `$name` like any other variable.

## Prompting on the Same Line with `-p`

```bash
#!/bin/bash

read -p "Enter your age: " age
echo "You are $age years old"
```

**Explanation:**
- `-p "text"` — displays the prompt text and waits for input on the same
  line, instead of needing a separate `echo` before it.

## Reading Multiple Values at Once

```bash
#!/bin/bash

read -p "Enter your first and last name: " first last
echo "First: $first"
echo "Last: $last"
```

**Explanation:**
- If you give `read` multiple variable names, it splits the input by
  whitespace and assigns each word to a variable in order.
- If there are more words than variables, all extras go into the **last**
  variable.

Example: input `Jils Kumar Patel` → `first=Jils`, `last="Kumar Patel"`.

## Silent Input (for Passwords)

```bash
#!/bin/bash

read -sp "Enter your password: " password
echo
echo "Password received (length: ${#password} characters)"
```

**Explanation:**
- `-s` — silent mode; characters typed are **not displayed** on screen (used
  for sensitive input like passwords).
- `echo` (empty) — since `-s` suppresses the newline the Enter key would
  normally show, we print a blank line manually so the next output doesn't
  jam onto the same line.
- `${#password}` — the `#` inside `${}` gives the **length** of the string
  stored in the variable.

## Setting a Timeout

```bash
#!/bin/bash

if read -t 5 -p "Answer within 5 seconds: " answer; then
    echo "You said: $answer"
else
    echo "Time's up, no input received."
fi
```

**Explanation:**
- `-t 5` — wait a maximum of 5 seconds for input.
- If the user doesn't respond in time, `read` fails (returns non-zero exit
  status), so the `if` block's `else` branch runs. (`if` is covered in
  detail in the Conditionals topic.)

## Reading a Fixed Number of Characters

```bash
#!/bin/bash

read -n 1 -p "Press any key to continue..." key
echo
echo "You pressed: $key"
```

**Explanation:**
- `-n 1` — reads exactly 1 character and returns immediately (doesn't wait
  for Enter).
- Useful for "press any key" style prompts or single-character menu choices.

## Reading Input into an Array

```bash
#!/bin/bash

echo "Enter several fruits separated by spaces:"
read -a fruits

echo "First fruit: ${fruits[0]}"
echo "All fruits: ${fruits[@]}"
echo "Count: ${#fruits[@]}"
```

**Explanation:**
- `-a fruits` — splits input by whitespace and stores each word as an
  element in the array `fruits`.
- `${fruits[0]}` — first element (arrays are zero-indexed).
- `${fruits[@]}` — all elements.
- `${#fruits[@]}` — number of elements in the array. (Arrays get their own
  dedicated topic later.)

## Reading from a File Instead of Keyboard

```bash
#!/bin/bash

while read -r line; do
    echo "Line: $line"
done < names.txt
```

**Explanation:**
- `< names.txt` — redirects the file's contents as input to the `while`
  loop, so `read` pulls one line at a time from the file instead of the
  keyboard.
- `-r` — "raw" mode; prevents backslashes in the file from being
  interpreted as escape characters (almost always good practice to
  include this).
- `while` loops are covered properly in the Loops topic, but this is a very
  common `read` use case worth knowing now.

## Default Value if No Input Given

```bash
#!/bin/bash

read -p "Enter a city (default: Bangalore): " city
city="${city:-Bangalore}"
echo "City selected: $city"
```

**Explanation:**
- `${city:-Bangalore}` — if `city` is empty or unset, this expression
  evaluates to `"Bangalore"` instead, without changing the actual variable.
  Then we reassign it. (Parameter expansion defaults are covered in more
  depth in the String Manipulation topic.)

## Key Points to Remember

- `read var` — basic input into a variable
- `-p` — inline prompt
- `-s` — silent (hidden) input
- `-t N` — timeout after N seconds
- `-n N` — read exactly N characters
- `-a arr` — read into an array
- `-r` — treat backslashes literally (use this by default, especially when
  reading files)
- Input from a file can be redirected in via `<`
