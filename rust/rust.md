# Rust Starter Guide

## What is Rust?

Rust is a systems programming language focused on safety, speed, and concurrency. It prevents common bugs like null pointer dereferences and data races at compile time, making it ideal for building reliable and efficient software.

## Installation

### Install Rust via rustup

```bash
# On macOS, Linux, or WSL
curl --proto '=https' --tlsv1.2 -sSf https://sh.rustup.rs | sh

# On Windows, download and run rustup-init.exe from rust-lang.org
```

After installation, restart your terminal and verify:

```bash
rustc --version
cargo --version
```

## Your First Program

Create a new project:

```bash
cargo new hello_world
cd hello_world
```

This creates a project structure:
```
hello_world/
├── Cargo.toml      # Project metadata and dependencies
└── src/
    └── main.rs     # Your code
```

Edit `src/main.rs`:

```rust
fn main() {
    println!("Hello, world!");
}
```

Run your program:

```bash
cargo run
```

## Core Concepts

### Variables and Mutability

Variables are immutable by default:

```rust
let x = 5;           // Immutable
let mut y = 10;      // Mutable
y = 15;              // OK
```

### Data Types

```rust
// Integers
let a: i32 = 42;           // Signed 32-bit
let b: u64 = 100;          // Unsigned 64-bit

// Floating point
let c: f64 = 3.14;

// Boolean
let d: bool = true;

// Character
let e: char = '🦀';

// String types
let s1: &str = "string slice";    // Immutable reference
let s2: String = String::from("owned string");  // Heap-allocated
```

### Functions

```rust
fn add(x: i32, y: i32) -> i32 {
    x + y  // No semicolon = return value
}

fn main() {
    let result = add(5, 3);
    println!("Result: {}", result);
}
```

### Ownership (Key Rust Concept)

Rust's ownership system ensures memory safety without a garbage collector:

```rust
fn main() {
    let s1 = String::from("hello");
    let s2 = s1;  // s1 is moved to s2, s1 is no longer valid
    
    // println!("{}", s1);  // Error! s1 was moved
    println!("{}", s2);     // OK
    
    // To copy instead of move
    let s3 = s2.clone();
    println!("{} {}", s2, s3);  // Both valid
}
```

### Borrowing and References

```rust
fn calculate_length(s: &String) -> usize {
    s.len()  // Borrow s, don't take ownership
}

fn main() {
    let s = String::from("hello");
    let len = calculate_length(&s);  // Borrow with &
    println!("Length of '{}' is {}", s, len);  // s still valid
}
```

### Control Flow

```rust
// If expressions
let number = 7;
if number < 5 {
    println!("less than 5");
} else if number < 10 {
    println!("between 5 and 10");
} else {
    println!("10 or greater");
}

// Loop
let mut counter = 0;
loop {
    counter += 1;
    if counter == 10 {
        break;
    }
}

// While loop
while counter > 0 {
    counter -= 1;
}

// For loop
for i in 0..5 {
    println!("{}", i);
}

// Iterate over collections
let arr = [1, 2, 3, 4, 5];
for element in arr.iter() {
    println!("{}", element);
}
```

### Structs

```rust
struct User {
    username: String,
    email: String,
    age: u32,
}

impl User {
    // Associated function (like a constructor)
    fn new(username: String, email: String, age: u32) -> User {
        User { username, email, age }
    }
    
    // Method
    fn display(&self) {
        println!("{} ({})", self.username, self.email);
    }
}

fn main() {
    let user = User::new(
        String::from("alice"),
        String::from("alice@example.com"),
        30
    );
    user.display();
}
```

### Enums and Pattern Matching

```rust
enum IpAddr {
    V4(u8, u8, u8, u8),
    V6(String),
}

fn main() {
    let home = IpAddr::V4(127, 0, 0, 1);
    
    match home {
        IpAddr::V4(a, b, c, d) => {
            println!("IPv4: {}.{}.{}.{}", a, b, c, d);
        }
        IpAddr::V6(addr) => {
            println!("IPv6: {}", addr);
        }
    }
}
```

### Option and Result

Rust doesn't have null. Instead, it uses `Option` to represent values that might not exist:

```rust
// Option is defined as: enum Option<T> { Some(T), None }

// Example 1: Finding an item
fn find_user(id: u32) -> Option<String> {
    if id == 1 {
        Some(String::from("Alice"))
    } else {
        None
    }
}

fn main() {
    // Method 1: Using match
    match find_user(1) {
        Some(name) => println!("Found user: {}", name),
        None => println!("User not found"),
    }
    
    // Method 2: Using if let (when you only care about Some)
    if let Some(name) = find_user(1) {
        println!("Found: {}", name);
    }
    
    // Method 3: Using unwrap_or (provide default)
    let name = find_user(99).unwrap_or(String::from("Guest"));
    println!("Name: {}", name);
    
    // Method 4: Using unwrap_or_else (compute default lazily)
    let name = find_user(99).unwrap_or_else(|| {
        println!("Computing default...");
        String::from("Guest")
    });
    
    // Method 5: Using map (transform the value if it exists)
    let uppercase = find_user(1).map(|name| name.to_uppercase());
    println!("{:?}", uppercase);  // Some("ALICE")
    
    // Method 6: Using and_then (chain operations that return Option)
    let length = find_user(1).and_then(|name| {
        if name.len() > 3 {
            Some(name.len())
        } else {
            None
        }
    });
    println!("{:?}", length);  // Some(5)
}

// Example 2: Array access returns Option
fn safe_divide(numbers: Vec<i32>, index: usize) -> Option<i32> {
    numbers.get(index).map(|&n| n / 2)
}

// Example 3: Parsing strings
fn parse_number(s: &str) -> Option<i32> {
    s.parse().ok()  // Convert Result to Option
}

fn example_parse() {
    match parse_number("42") {
        Some(n) => println!("Parsed: {}", n),
        None => println!("Not a valid number"),
    }
    
    // Using ? operator (returns early if None)
    fn add_parsed_numbers(a: &str, b: &str) -> Option<i32> {
        let num_a = parse_number(a)?;  // Returns None if parsing fails
        let num_b = parse_number(b)?;
        Some(num_a + num_b)
    }
    
    println!("{:?}", add_parsed_numbers("10", "20"));  // Some(30)
    println!("{:?}", add_parsed_numbers("10", "abc")); // None
}

// Example 4: Working with nested Options
fn get_first_letter(name: Option<String>) -> Option<char> {
    name.and_then(|n| n.chars().next())
}
```

For operations that can fail with an error message, use `Result`:

```rust
use std::fs::File;
use std::io::{self, Read};

// Result is defined as: enum Result<T, E> { Ok(T), Err(E) }

fn main() {
    // Method 1: Using match
    let f = File::open("hello.txt");
    let f = match f {
        Ok(file) => file,
        Err(error) => {
            println!("Failed to open file: {:?}", error);
            return;
        }
    };
    
    // Method 2: Using unwrap (panics if error)
    // let f = File::open("hello.txt").unwrap();
    
    // Method 3: Using expect (panics with custom message)
    // let f = File::open("hello.txt").expect("Failed to open hello.txt");
    
    // Method 4: Using unwrap_or_else (handle error gracefully)
    let f = File::open("hello.txt").unwrap_or_else(|error| {
        println!("Using default file due to: {:?}", error);
        File::create("hello.txt").unwrap()
    });
}

// Example: Custom Result types
fn divide(x: f64, y: f64) -> Result<f64, String> {
    if y == 0.0 {
        Err(String::from("Cannot divide by zero"))
    } else {
        Ok(x / y)
    }
}

fn example_divide() {
    match divide(10.0, 2.0) {
        Ok(result) => println!("Result: {}", result),
        Err(e) => println!("Error: {}", e),
    }
    
    // Using ? operator to propagate errors
    fn calculate() -> Result<f64, String> {
        let a = divide(10.0, 2.0)?;  // Returns Err early if error
        let b = divide(20.0, 4.0)?;
        Ok(a + b)
    }
    
    println!("{:?}", calculate());  // Ok(10.0)
}
```

## Working with Cargo

```bash
# Create new project
cargo new my_project

# Build project
cargo build

# Build optimized release version
cargo build --release

# Run project
cargo run

# Check code without building
cargo check

# Run tests
cargo test

# Generate documentation
cargo doc --open

# Add dependencies (edit Cargo.toml)
```

Example `Cargo.toml`:

```toml
[package]
name = "my_project"
version = "0.1.0"
edition = "2021"

[dependencies]
serde = { version = "1.0", features = ["derive"] }
tokio = { version = "1.0", features = ["full"] }
```

## Common Collections

### Vector

```rust
let mut v: Vec<i32> = Vec::new();
v.push(1);
v.push(2);
v.push(3);

// Or use macro
let v = vec![1, 2, 3];

for i in &v {
    println!("{}", i);
}
```

### HashMap

```rust
use std::collections::HashMap;

let mut scores = HashMap::new();
scores.insert(String::from("Blue"), 10);
scores.insert(String::from("Red"), 50);

if let Some(score) = scores.get("Blue") {
    println!("Blue team score: {}", score);
}
```

## Error Handling Best Practices

```rust
use std::fs::File;
use std::io::{self, Read};

fn read_username() -> Result<String, io::Error> {
    let mut f = File::open("username.txt")?;  // ? operator propagates errors
    let mut s = String::new();
    f.read_to_string(&mut s)?;
    Ok(s)
}
```

## Next Steps

1. **Read the Rust Book**: The official guide at doc.rust-lang.org/book
2. **Try Rustlings**: Interactive exercises at github.com/rust-lang/rustlings
3. **Explore crates.io**: Discover libraries for your projects
4. **Join the community**: Rust forums, Discord, and Reddit
5. **Build projects**: Start with CLI tools, then web servers or system utilities

## Useful Resources

- Official Documentation: doc.rust-lang.org
- Rust by Example: doc.rust-lang.org/rust-by-example
- Rust Playground: play.rust-lang.org (test code in browser)
- Cargo Book: doc.rust-lang.org/cargo

Happy coding! 🦀
