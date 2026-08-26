# Topic 5: Loops (`for`, `while`, `until`, `break`, `continue`)

## Basic `for` Loop — List of Values

```bash
#!/bin/bash

for fruit in apple banana mango; do
    echo "Fruit: $fruit"
done
```

**Explanation:**
- `for var in list; do ... done` — iterates `var` over each item in `list`,
  running the block once per item.
- `apple banana mango` — a space-separated list of literal values.
- `done` closes the loop block.

## `for` Loop Over a Range of Numbers

```bash
#!/bin/bash

for i in {1..5}; do
    echo "Count: $i"
done

# With a step
for i in {0..20..5}; do
    echo "Step: $i"
done
```

**Explanation:**
- `{1..5}` — brace expansion generates the sequence `1 2 3 4 5`.
- `{0..20..5}` — start..end..step → generates `0 5 10 15 20`.
- This is a Bash-specific feature (not in plain POSIX `sh`).

## C-style `for` Loop

```bash
#!/bin/bash

for (( i=0; i<5; i++ )); do
    echo "i is $i"
done
```

**Explanation:**
- `(( i=0; i<5; i++ ))` — familiar C/Java-style loop: initializer, condition,
  increment.
- Useful when you need custom step logic or to loop based on a variable
  (not a hardcoded literal like `{1..5}`).
- `i++` — increment operator inside arithmetic context `(( ))`.

## `for` Loop Over Files (Globbing)

```bash
#!/bin/bash

for file in *.txt; do
    echo "Found file: $file"
done
```

**Explanation:**
- `*.txt` — a glob pattern expanded by the shell **before** the loop runs,
  into a list of all matching filenames in the current directory.
- If no `.txt` files exist, `file` will literally be `*.txt` (the pattern
  itself) unless `nullglob` shell option is set — worth knowing to avoid
  bugs.

## `for` Loop Over Command Output

```bash
#!/bin/bash

for user in $(cut -d: -f1 /etc/passwd); do
    echo "User: $user"
done
```

**Explanation:**
- `$(cut -d: -f1 /etc/passwd)` — command substitution produces a list of
  words (split by whitespace/newlines), which the `for` loop then iterates
  over.
- Caveat: this splits on whitespace, so it breaks if values contain
  spaces — arrays (a later topic) are safer for that case.

## `for` Loop Over Array Elements

```bash
#!/bin/bash

colors=("red" "green" "blue")

for color in "${colors[@]}"; do
    echo "Color: $color"
done
```

**Explanation:**
- `"${colors[@]}"` — expands to each array element as a **separate, quoted
  word** — this correctly handles elements containing spaces (unlike
  unquoted expansion).

## `while` Loop

```bash
#!/bin/bash

count=1

while [ $count -le 5 ]; do
    echo "Count is $count"
    count=$((count + 1))
done
```

**Explanation:**
- `while [ condition ]; do ... done` — repeats the block **as long as** the
  condition remains true.
- `count=$((count + 1))` — arithmetic expansion `$(( ))` evaluates the
  expression and returns the result. This is how you increment a variable.
- If you forget to update `count` inside the loop, you get an **infinite
  loop**.

## `while` Loop Reading a File Line by Line

```bash
#!/bin/bash

while read -r line; do
    echo "Line: $line"
done < "data.txt"
```

**Explanation:**
- Combines `read` (Topic 3) with `while` — the loop continues as long as
  `read` successfully reads another line (i.e., hasn't hit end-of-file).
- `< "data.txt"` — feeds the file's contents into the loop's standard input.

## Infinite `while` Loop with `break`

```bash
#!/bin/bash

while true; do
    read -p "Enter 'quit' to stop: " input
    if [ "$input" = "quit" ]; then
        break
    fi
    echo "You entered: $input"
done

echo "Loop ended."
```

**Explanation:**
- `while true; do ... done` — `true` is a command that always exits
  successfully (status 0), so the loop never naturally ends on its own.
- `break` — immediately exits the nearest enclosing loop, regardless of the
  loop's condition.

## `until` Loop

```bash
#!/bin/bash

count=1

until [ $count -gt 5 ]; do
    echo "Count is $count"
    count=$((count + 1))
done
```

**Explanation:**
- `until [ condition ]; do ... done` — the **opposite** of `while`: repeats
  the block as long as the condition is **false**, stopping once it becomes
  true.
- Here it runs until `count` becomes greater than 5 — functionally
  identical to the `while` example above, just phrased inversely. Choose
  whichever reads more naturally for the situation.

## `continue` — Skip to Next Iteration

```bash
#!/bin/bash

for i in {1..10}; do
    if [ $((i % 2)) -eq 0 ]; then
        continue
    fi
    echo "Odd number: $i"
done
```

**Explanation:**
- `continue` — skips the rest of the current iteration's code and jumps
  straight to the next iteration (doesn't exit the loop like `break` does).
- `$((i % 2))` — `%` is the modulo operator; `i % 2 == 0` means `i` is even,
  so we skip printing it.

## Nested Loops and Labeled `break`/`continue`

```bash
#!/bin/bash

for i in 1 2 3; do
    for j in 1 2 3; do
        if [ $j -eq 2 ]; then
            continue 2   # skips to the next iteration of the OUTER loop
        fi
        echo "i=$i, j=$j"
    done
done
```

**Explanation:**
- `break N` / `continue N` — an optional numeric argument specifies **how
  many nested loop levels** to break/continue out of. `continue 2` here
  skips the rest of both the inner and outer loop's current pass, jumping
  to the outer loop's next iteration.
- Without the number, `break`/`continue` only affect the innermost loop.

## Loop Control with `select` (Menu-Style)

```bash
#!/bin/bash

echo "Choose your favorite language:"
select lang in "Python" "Bash" "C++" "Quit"; do
    case $lang in
        "Quit")
            echo "Goodbye!"
            break
            ;;
        *)
            echo "You chose: $lang"
            ;;
    esac
done
```

**Explanation:**
- `select var in list; do ... done` — a Bash-specific construct that
  automatically prints a numbered menu and repeatedly prompts the user to
  pick a number, storing the corresponding text in `var`.
- Commonly paired with `case` to handle each choice, and `break` to exit
  once the user is done.

## Key Points to Remember

- `for var in list; do ... done` — iterate over explicit values, ranges,
  globs, or command output
- `for (( ; ; )); do ... done` — C-style counting loop
- `while [ cond ]; do ... done` — loop while condition is true
- `until [ cond ]; do ... done` — loop while condition is false
- `break` — exit the loop entirely; `continue` — skip to next iteration
- `break N` / `continue N` — control nested loops
- `select` — quick interactive numbered menus
- Always quote array expansions (`"${arr[@]}"`) to handle spaces correctly
