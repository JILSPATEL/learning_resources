# Topic 2: Variables

## What is a Variable?

A variable is a name that holds a value — text, a number, or the output of a
command. Shell variables are **untyped**: everything is stored as a string
internally, even numbers.

## Declaring and Using Variables

```bash
#!/bin/bash

name="Jils"
age=23
echo "My name is $name and I am $age years old"
```

**Explanation:**
- `name="Jils"` — assignment. **No spaces** around `=` — this is critical.
  `name = "Jils"` will fail because the shell tries to run `name` as a
  command with arguments `=` and `"Jils"`.
- `age=23` — same rule; even though it's a number, it's stored as a string.
- `$name` and `$age` — to **read** a variable's value, prefix it with `$`.
- Double quotes `"..."` allow variable expansion inside the string.

## Curly Brace Syntax `${var}`

```bash
#!/bin/bash

fruit="apple"
echo "I have a $fruitpie"     # WRONG - looks for variable "fruitpie"
echo "I have a ${fruit}pie"   # CORRECT - explicitly delimits variable name
```

**Explanation:**
- Without braces, the shell tries to interpret `$fruitpie` as one variable
  name, which doesn't exist (prints nothing).
- `${fruit}` clearly tells the shell where the variable name ends, so `pie`
  is treated as literal text appended after.

## Command Substitution

```bash
#!/bin/bash

current_date=$(date +%Y-%m-%d)
files_count=$(ls | wc -l)

echo "Today's date: $current_date"
echo "Number of files here: $files_count"
```

**Explanation:**
- `$(command)` runs `command` and substitutes its **output** as the value.
- `date +%Y-%m-%d` — the `date` command formatted to show year-month-day.
- `ls | wc -l` — lists files, pipes to `wc -l` (word count, lines mode) to
  count them.
- Older syntax uses backticks: `` current_date=`date` `` — but `$(...)` is
  preferred (nestable, more readable).

## Read-only Variables

```bash
#!/bin/bash

readonly PI=3.14159
echo "Pi is $PI"
PI=3.14   # This will cause an error
```

**Explanation:**
- `readonly` prevents the variable from being reassigned later. Attempting
  to do so prints an error like `bash: PI: readonly variable`.

## Unsetting Variables

```bash
#!/bin/bash

greeting="Hello"
echo $greeting   # Hello
unset greeting
echo $greeting   # (prints nothing - empty)
```

**Explanation:**
- `unset` removes a variable entirely from the shell's memory.

## Environment Variables vs Shell Variables

```bash
#!/bin/bash

local_var="only visible in this shell"
export GLOBAL_VAR="visible in child processes too"

bash -c 'echo "Child sees: $GLOBAL_VAR"'
bash -c 'echo "Child sees: $local_var"'
```

**Explanation:**
- Regular variables (`local_var`) exist only in the current shell session.
- `export` promotes a variable to an **environment variable**, making it
  inherited by any child process (like a subshell or script launched from
  this one).
- Output:
  ```
  Child sees: visible in child processes too
  Child sees:
  ```

## Special/Built-in Variables

```bash
#!/bin/bash

echo "Script name: $0"
echo "First argument: $1"
echo "Second argument: $2"
echo "All arguments: $@"
echo "Number of arguments: $#"
echo "Exit status of last command: $?"
echo "Process ID of script: $$"
```

Run as: `./script.sh foo bar`

**Explanation of each:**
- `$0` — the name of the script itself
- `$1`, `$2`, ... — positional arguments passed to the script
- `$@` — all arguments as a list
- `$#` — count of arguments passed
- `$?` — exit status of the last executed command (0 = success, non-zero =
  failure)
- `$$` — the process ID (PID) of the currently running script

## Naming Rules for Variables

- Must start with a letter or underscore (`_`), not a digit
- Can contain letters, digits, underscores
- Case-sensitive: `Name` and `name` are different variables
- Convention: use lowercase for regular variables, UPPERCASE for
  exported/environment variables and constants

## Key Points to Remember

- No spaces around `=` in assignment
- `$var` to read, `var=value` to assign (no `$` when assigning)
- `${var}` for explicit boundary or when concatenating with text
- `export` to make available to child processes
- `readonly` to make a constant
- Special variables like `$1`, `$@`, `$?`, `$#` handle script arguments and
  status
