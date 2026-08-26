# Topic 1: Introduction & Your First Script

## What is a Shell Script?

A shell script is a text file containing a sequence of commands that the shell
(command-line interpreter) executes in order. Instead of typing commands one
by one into the terminal, you write them in a file and run them all at once.

## The Shebang Line

Every shell script should start with a **shebang** line. This tells the
operating system which interpreter to use to run the script.

```bash
#!/bin/bash
```

- `#!` — special marker recognized by the OS
- `/bin/bash` — path to the Bash interpreter

You could also see `#!/bin/sh` (POSIX shell) or `#!/usr/bin/env bash` (finds
bash via `PATH`, more portable).

## Your First Script

Create a file called `hello.sh`:

```bash
#!/bin/bash
# This is a comment - it's ignored by the shell

echo "Hello, World!"
echo "Today is $(date)"
```

### Explanation line by line

1. `#!/bin/bash` — shebang, tells the system to run this with bash
2. `# This is a comment...` — anything after `#` is a comment (not executed)
3. `echo "Hello, World!"` — `echo` prints text to the terminal
4. `echo "Today is $(date)"` — `$(date)` runs the `date` command and
   substitutes its output into the string (this is called **command
   substitution**)

## Running the Script

```bash
chmod +x hello.sh   # make it executable
./hello.sh           # run it
```

### Explanation

- `chmod +x hello.sh` — changes file permissions to add the "execute" (`x`)
  bit. Without this, you can't run it directly as `./hello.sh`.
- `./hello.sh` — the `./` tells the shell "look in the current directory" for
  this file (since current directory usually isn't in `PATH` for security
  reasons).

Alternative ways to run it without `chmod`:

```bash
bash hello.sh
sh hello.sh
```

## Output

```
Hello, World!
Today is Wed Aug 26 10:30:15 IST 2026
```

## Key Points to Remember

- Scripts are plain text files, typically saved with `.sh` extension (though
  not mandatory on Linux)
- The shebang **must** be the very first line — no blank lines or spaces
  before it
- `echo` is the most basic way to produce output
- `chmod +x` is required before you can execute a script directly
