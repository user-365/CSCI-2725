# Full Name

Yitao Tian

## University Email Address

ct91548@uga.edu

## Compile

javac -d .\bin\ .\src\**

## Run

java -cp .\bin\ BinarySearchTreeDriver .\lib\input.txt

## Description of Directories

### `bin`

Compiled .class files

### lib

input.txt

### src

Source code in .java files

## Pseudocode and Complexity

**Note:** for the following functions, only the recursive functions wrapped inside (or for `getCousins(NodeType)`, the function itself) are analysed.

### `getSingleParent` function

#### `getSingleParent` Pseudocode

```java
// The recursive function wrapped inside: void get_uniparous_parents(NodeType subroot)
// Base case:
if subroot is null, then return
// Recursive case: Pre-order traversing:
if subroot has both children:
    get_uniparous_parents(subroot.left);
    get_uniparous_parents(subroot.right);
else, if subroot has only the left child:
    System.out.print(subroot.info.getValue() + " ");
    get_uniparous_parents(subroot.left);
else, if subroot has only the right child:
    System.out.print(subroot.info.getValue() + " ");
    get_uniparous_parents(subroot.right);
// otherwise, it has zero children and implicitly return
```

#### `getSingleParent` Complexity

We only care about the recursive calls.
The recursive calls are in one of the three if-elif-elif conditional blocks.
Choose the most expensive of the three (subroot has both children).
There are two recursive calls inside (and no other function calls/operations).
Let the units be *n*, the number of nodes in the subtree (we are assuming the tree is balanced).
Since each of the two recursive calls act on a left/right subtree of the original tree, they only act on *0.5n* nodes.
So the big function has:
    T(n) = T(0.5n) + T(0.5n) = 2T(0.5n)
Using the *Divide and Conquer* version of the *master theorem*, we have:
    a = 2, b = 2, so \log_b(a) = 1
Since the power of f(n) is 0 and \log_b(a) = 1, we have **case 1** of the *master theorem*:
So, T(n) \in \Theta(n^{\log_b(a)}) = \Theta(n^1).

### `getNumLeafNodes` function

#### `getNumLeafNodes` Pseudocode

```java
// The recursive function wrapped inside: int get_num_leaf_nodes(NodeType subroot)
// Base case:
if subroot is null, then return 0
// Recursive case: Post-order traversing:
int sum = get_num_leaf_nodes(subroot.left) + get_num_leaf_nodes(subroot.right);
return sum > 0 ? sum : 1;
```

#### `getNumLeafNodes` Complexity

We only care about the recursive calls.
The recursive calls is in one line in the **Recursive case**.
There are two recursive calls making up the summation (and one constant-complexity function).
Let the units be *n*, the number of nodes in the subtree (we are assuming the tree is balanced).
Since each of the two recursive calls act on a left/right subtree of the original tree, they only act on *0.5n* nodes.
So the big function has:
    T(n) = T(0.5n) + T(0.5n) = 2T(0.5n)
Using the *Divide and Conquer* version of the *master theorem*, we have:
    a = 2, b = 2, so \log_b(a) = 1
Since the power of f(n) is 0 and \log_b(a) = 1, we have **case 1** of the *master theorem*:
So, T(n) \in \Theta(n^{\log_b(a)}) = \Theta(n^1).

### `getCousins` function

#### `getCousins` Pseudocode

```java
// The recursive function wrapped inside: n/a
// Special Case: empty BST
if BST is empty, then return
// General Case:
Traverse the BST to find the target node. // O(\log(n))
Store target node.
if target node isnt null, then:
    if target node has no parent:
        then return  // Note: constant due to Traversal above
    (else,) Store parent
    if target node has no grandparent:
        then return  // Note: constant due to Traversal above
    (else,) Store grandparent node
    if aunt does not exist, then return immediately
    else:
        if the aunt has a left child, then print it
        if the aunt has a right child, then print it
```

#### `getCousins` Complexity

Let the units be *n*, the number of nodes in the subtree (we are assuming the tree is balanced).
As one can see, other than the Traversal function call (in the first line of **General case**), all other function calls/operations are constant time (no recursion).
The Traversal call (i.e., `survey(ItemType, NodeType, ArrayList<NodeType>)`), as noted in the code comment, is O(\log(n)).
Specifically, the Traversal call is the standard (binary) search we have shown in class, which has recurrence relation:
    T(n) = T(0.5n)
Using the *Divide and Conquer* version of the *master theorem*, we have:
    a = 1, b = 2, so \log_b(a) = 0
Since the power of f(n) is 0 and \log_b(a) = 0, we have **case 2** of the *master theorem*:
So, T(n) \in \Theta(n^{\log_b(a)} \log(n)) = \Theta(n^0 \log(n)) = \Theta(\log(n)), justifying my earlier claim.
Again, other than this Traversal call, there is no recursion, so for the rest of the function, we don't need the *master theorem*.
So, in total, T(n) (of `getCousins(NodeType)`) is \Theta(\log(n)).
