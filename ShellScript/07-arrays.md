# Topic 7: Arrays

## Declaring and Initializing Arrays

```bash
#!/bin/bash

fruits=("apple" "banana" "cherry" "mango")
echo "${fruits[@]}"
```

**Explanation:**
- `fruits=(...)` — parentheses create an **indexed array**, with elements
  separated by spaces.
- `"${fruits[@]}"` — expands to all elements of the array (quoted, so each
  element stays intact even if it has spaces).
- Arrays in Bash are **zero-indexed** (first element is index `0`).

## Accessing Elements

```bash
#!/bin/bash

fruits=("apple" "banana" "cherry" "mango")

echo "First: ${fruits[0]}"
echo "Second: ${fruits[1]}"
echo "Last: ${fruits[-1]}"
```

**Explanation:**
- `${fruits[0]}` — accesses the element at index 0.
- `${fruits[-1]}` — negative indexing (Bash 4.3+) gives the **last**
  element without needing to know the array's length.
- Curly braces `${ }` are **required** here — `$fruits[0]` without braces
  just gives `${fruits}` followed by the literal text `[0]`.

## Array Length

```bash
#!/bin/bash

fruits=("apple" "banana" "cherry" "mango")
echo "Number of fruits: ${#fruits[@]}"
```

**Explanation:**
- `${#fruits[@]}` — the `#` before an array expansion gives the **count of
  elements**, not the string length of any single one.

## Adding Elements

```bash
#!/bin/bash

fruits=("apple" "banana")

fruits+=("cherry")               # append one element
fruits+=("mango" "grape")        # append multiple elements
fruits[10]="kiwi"                 # set a specific (sparse) index

echo "${fruits[@]}"
echo "Length: ${#fruits[@]}"
```

**Explanation:**
- `+=` — the append operator adds new elements to the end without
  disturbing existing ones.
- Assigning to an arbitrary index like `fruits[10]` creates a **sparse
  array** (indices 4–9 don't exist) — `${#fruits[@]}` counts only elements
  that actually exist, not the highest index + 1.

## Removing Elements

```bash
#!/bin/bash

fruits=("apple" "banana" "cherry" "mango")

unset fruits[1]     # removes "banana"
echo "${fruits[@]}"
echo "${!fruits[@]}"  # shows remaining indices
```

**Explanation:**
- `unset fruits[1]` — removes the element at index 1, but **leaves a
  gap** — the array becomes sparse (indices 0, 2, 3 remain; 1 is gone).
- `${!fruits[@]}` — the `!` prefix gives you the **list of indices**
  currently in use, useful for detecting gaps.
- To fully remove the array: `unset fruits` (no index) deletes the whole
  thing.

## Slicing Arrays

```bash
#!/bin/bash

numbers=(10 20 30 40 50 60)

echo "${numbers[@]:1:3}"   # start at index 1, take 3 elements
echo "${numbers[@]:2}"     # from index 2 to the end
```

**Explanation:**
- `${array[@]:start:length}` — array slicing syntax, similar to string
  slicing.
- `${numbers[@]:1:3}` → elements at index 1, 2, 3 → `20 30 40`
- `${numbers[@]:2}` → everything from index 2 onward → `30 40 50 60`

## Looping Over Arrays

```bash
#!/bin/bash

fruits=("apple" "banana" "cherry")

# Loop over values
for fruit in "${fruits[@]}"; do
    echo "Fruit: $fruit"
done

# Loop over indices
for i in "${!fruits[@]}"; do
    echo "Index $i: ${fruits[$i]}"
done
```

**Explanation:**
- First loop — iterates directly over the values.
- `"${!fruits[@]}"` — iterates over the **indices**, letting you access
  both the index and corresponding value inside the loop — useful when you
  need the position, not just the value.

## `@` vs `*` in Array/Argument Expansion

```bash
#!/bin/bash

arr=("one two" "three")

echo "Using @:"
for item in "${arr[@]}"; do
    echo "[$item]"
done

echo "Using *:"
for item in "${arr[*]}"; do
    echo "[$item]"
done
```

**Output:**
```
Using @:
[one two]
[three]
Using *:
[one two three]
```

**Explanation:**
- `"${arr[@]}"` — each element remains a **separate** word (correctly
  preserves internal spaces within an element).
- `"${arr[*]}"` — all elements are joined into a **single string**,
  separated by the first character of `IFS` (default: space).
- **Rule of thumb: almost always use `"${arr[@]}"` when quoted**, unless
  you specifically want everything merged into one string.

## Associative Arrays (Key-Value / "Dictionaries")

```bash
#!/bin/bash

declare -A capitals

capitals[India]="New Delhi"
capitals[Japan]="Tokyo"
capitals[France]="Paris"

echo "Capital of Japan: ${capitals[Japan]}"

echo "All countries:"
for country in "${!capitals[@]}"; do
    echo "$country -> ${capitals[$country]}"
done
```

**Explanation:**
- `declare -A name` — **required** to create an associative array (string
  keys instead of numeric indices). Without `-A`, Bash defaults to indexed
  arrays.
- `capitals[India]="New Delhi"` — assigns a value to the key `India`.
- `${!capitals[@]}` — gives all the **keys** (works the same way as indices
  for indexed arrays).
- `${capitals[$country]}` — looks up the value for a given key.
- Associative arrays are a Bash 4+ feature (not available in older Bash
  like macOS's default 3.2, and not in POSIX `sh`).

## Alternate Declaration Syntax

```bash
#!/bin/bash

declare -a indexed_arr=("a" "b" "c")
declare -A assoc_arr=([name]="Jils" [role]="Engineer")

echo "${indexed_arr[@]}"
echo "${assoc_arr[name]} - ${assoc_arr[role]}"
```

**Explanation:**
- `declare -a` — explicitly declares an indexed array (optional, since
  `arr=(...)` does this implicitly).
- `declare -A ...=([key]=value [key2]=value2)` — inline initialization of
  an associative array with multiple key-value pairs at once.

## Checking if a Key/Index Exists

```bash
#!/bin/bash

declare -A user
user[name]="Jils"

if [ -v user[name] ]; then
    echo "Key 'name' exists"
fi

if [ -v user[age] ]; then
    echo "Key 'age' exists"
else
    echo "Key 'age' does NOT exist"
fi
```

**Explanation:**
- `-v` inside `[ ]` — checks whether a variable (or array key) is **set**,
  regardless of whether its value is empty. This distinguishes "key
  doesn't exist" from "key exists but is empty."

## Key Points to Remember

- `arr=(a b c)` — indexed array, zero-based
- `declare -A arr` — required for associative (key-value) arrays
- `${arr[i]}` — access one element; `${arr[@]}` — all elements
- `${#arr[@]}` — number of elements
- `${!arr[@]}` — list of indices/keys
- `arr+=(x)` — append
- `unset arr[i]` — remove one element (leaves gaps); `unset arr` — remove
  entire array
- Always prefer `"${arr[@]}"` (quoted, with `@`) over `*` to preserve
  elements correctly
- `[ -v arr[key] ]` — test existence of a key/index
