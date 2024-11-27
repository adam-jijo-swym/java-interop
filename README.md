# java-interop

## Usage

Clojure must be aot-compiled (note uberjar profile in `project.clj`

```sh
$ lein uberjar
Compiling jinterop.core
Compiling jinterop.defjava
Compiling jinterop.random
"myrandom clojure returns" "fromclojure54"
"myrandom clojure returns" "fromclojure17"
"mynotrandom clojure returns" "fromclojure50"
Created /Users/adam/Development/work/random/java-interop/target/uberjar/jinterop-0.1.0-SNAPSHOT.jar
Created /Users/adam/Development/work/random/java-interop/target/uberjar/jinterop-0.1.0-SNAPSHOT-standalone.jar
```

Java files should use the generated jar file in their class path.

```sh
$ javac -cp "src/java/jinterop:target/uberjar/jinterop-0.1.0-SNAPSHOT-standalone.jar" src/java/jinterop/FromJava.java
$ java -cp "src/java/jinterop:target/uberjar/jinterop-0.1.0-SNAPSHOT-standalone.jar" FromJava
"myrandom clojure returns" "fromclojure5"
"myrandom clojure returns" "fromclojure29"
"mynotrandom clojure returns" "fromclojure50"
fromjava17
fromjava50
```
