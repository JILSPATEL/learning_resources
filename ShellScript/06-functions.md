# Topic 6: Functions

## Basic Function Definition and Call

```bash
#!/bin/bash

greet() {
    echo "Hello from a function!"
}

greet
```

**Explanation:**
- `greet() { ... }` — defines a function named `greet`. The parentheses `()`
  are empty (Bash doesn't put parameter names there, unlike other
  languages).
- The code between `{` and `}` is the function body.
- `greet` (on its own line) — calling the function is just like calling any
  other command, by name.
- Alternative syntax: `function greet { ... }` — the `function` keyword is
  optional in Bash (but not in POSIX `sh`).

## Passing Arguments to Functions

```bash
#!/bin/bash

greet() {
    echo "Hello, $1! You are $2 years old."
}

greet "Jils" 23
```

**Explanation:**
- Functions don't declare named parameters. Instead, arguments passed at
  call time become `$1`, `$2`, etc. **inside the function** — exactly like
  script arguments (Topic 2), but scoped to this function call.
- `$0` inside a function is still the script's name, **not** the function's
  name.
- `$#` inside the function gives the count of arguments passed to the
  function call.
- `$@` gives all arguments passed to the function.

## Returning Values

Bash functions don't return arbitrary values like other languages — they
use **exit status** (0–255) or print output to be captured.

### Method 1: `return` (exit status only, 0-255)

```bash
#!/bin/bash

is_even() {
    if [ $(($1 % 2)) -eq 0 ]; then
        return 0   # success/true
    else
        return 1   # failure/false
    fi
}

is_even 4
if [ $? -eq 0 ]; then
    echo "4 is even"
fi

is_even 7
if [ $? -eq 0 ]; then
    echo "7 is even"
else
    echo "7 is odd"
fi
```

**Explanation:**
- `return N` — sets the function's exit status to `N` (must be 0–255;
  anything outside that range wraps around).
- `$?` right after calling the function captures that exit status.
- By convention, `0` means success/true, non-zero means failure/false —
  this matches how every Linux command reports success.
- `return` **cannot** send back arbitrary data like a string or a large
  number — for that, use output (below).

### Method 2: Echo + Command Substitution (returning data)

```bash
#!/bin/bash

add() {
    local sum=$(( $1 + $2 ))
    echo "$sum"
}

result=$(add 5 7)
echo "The sum is: $result"
```

**Explanation:**
- Since `return` can't carry data, the common pattern is: the function
  `echo`s its "result", and the caller captures it using `$(...)` (command
  substitution) — same mechanism as capturing any command's output.
- `local sum=...` — declares `sum` as a **local variable**, confined to
  this function (see next section). This avoids accidentally overwriting a
  variable of the same name elsewhere in the script.

## Local vs Global Variables

```bash
#!/bin/bash

message="Global message"

change_message() {
    local message="Local message"
    echo "Inside function: $message"
}

change_message
echo "Outside function: $message"
```

**Output:**
```
Inside function: Local message
Outside function: Global message
```

**Explanation:**
- Without `local`, any variable assigned inside a function is **global by
  default** — it will overwrite a variable of the same name outside the
  function, and persist after the function returns.
- `local` restricts the variable's scope to just that function call — it's
  created fresh each call and destroyed when the function exits.
- **Best practice:** always use `local` for variables inside functions
  unless you specifically intend to modify something global.

## Functions Must Be Defined Before They're Called

```bash
#!/bin/bash

# This works:
say_hi() {
    echo "Hi!"
}
say_hi

# This does NOT work (function not yet defined):
# say_bye
# say_bye() {
#     echo "Bye!"
# }
```

**Explanation:**
- Bash reads and executes scripts top to bottom. A function must be
  **defined (its `name() {...}` block executed)** before you can call it —
  just like you can't use a variable before assigning it.

## Recursive Functions

```bash
#!/bin/bash

factorial() {
    local n=$1
    if [ $n -le 1 ]; then
        echo 1
    else
        local prev=$(factorial $((n - 1)))
        echo $((n * prev))
    fi
}

result=$(factorial 5)
echo "5! = $result"
```

**Explanation:**
- A function can call itself — here `factorial` calls
  `factorial $((n - 1))` to solve a smaller sub-problem.
- Each call captures the recursive result via `$(...)`, just like calling
  any other function that returns data through echo.
- Bash recursion works but is slower than iterative loops for this kind of
  task — used here mainly to illustrate the concept.

## Passing Arrays to Functions

```bash
#!/bin/bash

print_all() {
    local arr=("$@")
    for item in "${arr[@]}"; do
        echo "Item: $item"
    done
}

fruits=("apple" "banana" "cherry")
print_all "${fruits[@]}"
```

**Explanation:**
- `"${fruits[@]}"` — expands each array element as a separate quoted
  argument when calling the function.
- Inside the function, `"$@"` captures all those passed arguments;
  `local arr=("$@")` rebuilds them into a new array local to the function.
- This is the standard workaround since Bash functions can't accept an
  array as a single named parameter directly.

## Default Values for Function Arguments

```bash
#!/bin/bash

greet() {
    local name="${1:-Guest}"
    echo "Hello, $name!"
}

greet "Jils"
greet
```

**Output:**
```
Hello, Jils!
Hello, Guest!
```

**Explanation:**
- `${1:-Guest}` — if `$1` was not passed (or is empty), use `"Guest"` as
  the default instead. (Same parameter-expansion trick seen in Topic 3.)

## Checking Number of Arguments Passed

```bash
#!/bin/bash

divide() {
    if [ $# -ne 2 ]; then
        echo "Error: divide requires exactly 2 arguments"
        return 1
    fi
    echo $(( $1 / $2 ))
}

divide 10 2
divide 10
```

**Explanation:**
- `$#` inside the function — number of arguments passed to *this function
  call*.
- Validating argument count is good practice before using `$1`, `$2`, etc.,
  to avoid silent bugs from missing arguments.

## Key Points to Remember

- `name() { ... }` — define a function; call it by name like a command
- `$1`, `$2`, `$#`, `$@` — refer to the function's own arguments, not the
  script's
- `return N` — sets exit status (0–255) only; use `echo` + `$(func)` to
  return actual data
- Always use `local` for variables inside functions to avoid polluting
  global scope
- Functions must be defined before they are called
- Functions can be recursive
- Pass arrays via `"${arr[@]}"` and rebuild with `local arr=("$@")` inside
