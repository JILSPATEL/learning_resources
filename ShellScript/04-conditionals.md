# Topic 4: Conditionals (`if`, `elif`, `else`, `test`)

## Basic `if` Statement

```bash
#!/bin/bash

number=10

if [ $number -gt 5 ]; then
    echo "Number is greater than 5"
fi
```

**Explanation:**
- `if [ condition ]; then ... fi` — the basic structure.
- `[ ]` is actually the **`test` command** in disguise — `[ $number -gt 5 ]`
  is equivalent to `test $number -gt 5`. The closing `]` is a required
  argument to `[`, not just punctuation.
- `-gt` — "greater than" (numeric comparison operator).
- `fi` — closes the `if` block (it's "if" spelled backwards — a Bash
  convention also seen in `esac`/`case` and `done`/`do`).
- Spaces around `[` and `]` are **mandatory** — `[$number -gt 5]` will error.

## `if / else`

```bash
#!/bin/bash

age=16

if [ $age -ge 18 ]; then
    echo "You are an adult"
else
    echo "You are a minor"
fi
```

**Explanation:**
- `-ge` — greater than or equal to.
- `else` provides the alternative branch when the condition is false.

## `if / elif / else`

```bash
#!/bin/bash

marks=75

if [ $marks -ge 90 ]; then
    echo "Grade: A"
elif [ $marks -ge 75 ]; then
    echo "Grade: B"
elif [ $marks -ge 50 ]; then
    echo "Grade: C"
else
    echo "Grade: F"
fi
```

**Explanation:**
- `elif` = "else if" — checked only if the previous condition(s) were false.
- Conditions are checked top to bottom; the first true one runs, and the
  rest are skipped.

## Numeric Comparison Operators

| Operator | Meaning |
|----------|---------|
| `-eq` | equal to |
| `-ne` | not equal to |
| `-gt` | greater than |
| `-lt` | less than |
| `-ge` | greater than or equal to |
| `-le` | less than or equal to |

```bash
#!/bin/bash

a=5
b=10

if [ $a -ne $b ]; then
    echo "$a and $b are not equal"
fi
```

## String Comparison Operators

```bash
#!/bin/bash

str1="hello"
str2="world"

if [ "$str1" = "$str2" ]; then
    echo "Strings are equal"
else
    echo "Strings are different"
fi

if [ -z "$str1" ]; then
    echo "str1 is empty"
fi

if [ -n "$str1" ]; then
    echo "str1 is NOT empty"
fi
```

**Explanation:**
- `=` — string equality (note: single `=`, not `==`, though `==` also works
  in Bash's `[ ]`)
- `!=` — string inequality
- `-z` — true if string is **empty** (zero length)
- `-n` — true if string is **not empty**
- Always quote variables like `"$str1"` in string comparisons — if the
  variable is empty or unset, an unquoted `[ $str1 = "world" ]` becomes
  `[ = "world" ]`, which throws a syntax error.

## File Test Operators

```bash
#!/bin/bash

file="/etc/passwd"

if [ -e "$file" ]; then
    echo "File exists"
fi

if [ -f "$file" ]; then
    echo "It's a regular file"
fi

if [ -d "/etc" ]; then
    echo "It's a directory"
fi

if [ -r "$file" ]; then
    echo "File is readable"
fi

if [ -w "$file" ]; then
    echo "File is writable"
fi

if [ -x "/bin/bash" ]; then
    echo "File is executable"
fi
```

**Explanation:**
- `-e` — exists (any type)
- `-f` — is a regular file
- `-d` — is a directory
- `-r` / `-w` / `-x` — readable / writable / executable permission checks

## Logical Operators (AND / OR / NOT)

```bash
#!/bin/bash

age=25
has_id=true

if [ $age -ge 18 ] && [ "$has_id" = true ]; then
    echo "Entry allowed"
fi

if [ $age -lt 18 ] || [ "$has_id" = false ]; then
    echo "Entry denied"
fi

if [ ! -f "nonexistent.txt" ]; then
    echo "File does not exist"
fi
```

**Explanation:**
- `&&` between `[ ]` blocks — logical AND (both must be true)
- `||` — logical OR (either can be true)
- `!` — logical NOT (negates the condition)
- Inside a single `[ ]`, you can also use `-a` (AND) and `-o` (OR), but
  `&&`/`||` between separate `[ ]` blocks is preferred and more modern.

## `[[ ]]` — The Bash Extended Test

```bash
#!/bin/bash

name="Jils Patel"

if [[ $name == "Jils"* ]]; then
    echo "Name starts with Jils"
fi

if [[ $name =~ ^[A-Z][a-z]+\ [A-Z][a-z]+$ ]]; then
    echo "Matches First Last name pattern"
fi
```

**Explanation:**
- `[[ ]]` is a **Bash-specific** (not POSIX `sh`) enhanced test construct.
- Doesn't require quoting variables as strictly (though still good
  practice).
- Supports **pattern matching** with `==` (glob-style, e.g. `"Jils"*`).
- Supports **regex matching** with `=~`.
- Allows `&&` and `||` directly *inside* the brackets:
  `[[ $a -gt 5 && $b -lt 10 ]]`.

## `case` Statement (Multi-way Branching)

```bash
#!/bin/bash

read -p "Enter a fruit: " fruit

case $fruit in
    apple)
        echo "It's a red or green fruit"
        ;;
    banana)
        echo "It's a yellow fruit"
        ;;
    orange|mango)
        echo "It's an orange-colored fruit"
        ;;
    *)
        echo "Unknown fruit"
        ;;
esac
```

**Explanation:**
- `case $var in ... esac` — cleaner alternative to long `elif` chains when
  comparing one variable against many possible values.
- Each pattern ends with `)`.
- `;;` marks the end of a branch (like `break` in other languages).
- `|` — allows matching multiple patterns for one branch (`orange|mango`).
- `*)` — the catch-all default case, similar to `else`.
- `esac` — closes the case block ("case" spelled backwards).

## Key Points to Remember

- `[ ]` = `test` command; requires spaces inside brackets and quoted
  variables
- `[[ ]]` = Bash extended test; supports pattern/regex matching, safer with
  unquoted vars
- Numeric comparisons use `-eq`, `-gt`, etc. — **not** `==` or `>` (those
  compare strings or redirect, in `[ ]`)
- String comparisons use `=`, `!=`, `-z`, `-n`
- File tests: `-e`, `-f`, `-d`, `-r`, `-w`, `-x`
- Logical: `&&`, `||`, `!`
- `case` is ideal for matching one variable against multiple discrete values
