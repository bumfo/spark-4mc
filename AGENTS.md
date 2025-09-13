# Repository Guidelines

## Project Structure & Module Organization
- `native/` — C sources for 4mc CLI and JNI library (`libhadoop-4mc`), Makefile and CMake hints.
- `java/hadoop-4mc/` — Hadoop/Spark Java library (Maven module).
- `java/examples/` — MapReduce/Spark usage examples.
- `tool/` — Prebuilt or helper scripts for the CLI.
- Format specs: `4mc-format-spec`, `4mz-format-spec`.

## Build, Test, and Development Commands
- Native (CLI + JNI)
  - `cd native && make` — build `4mc` CLI.
  - `cd native && make libhadoop-4mc` — build shared JNI library.
  - `cd native && make install PREFIX=/usr/local` — install CLI + man page.
  - Requires `JAVA_HOME` for JNI includes; set `JAVA_HOME` before building.
- Java (Hadoop library)
  - `mvn -f java/pom.xml -pl hadoop-4mc -am package` — build jar.
  - `mvn -f java/pom.xml -pl hadoop-4mc test` — run JUnit tests.
  - Examples: `mvn -f java/pom.xml -pl examples -am package` (optional).

## Coding Style & Naming Conventions
- C: C99 (`-std=c99`), keep existing brace/indent style, avoid new globals, prefer `snake_case` for functions and constants.
- Java: target 1.8, package `com.fing.compression.fourmc`; classes `UpperCamelCase`, methods/fields `camelCase`.
- Keep lines readable (~100 chars), group imports logically, avoid wildcard imports in Java.

## Testing Guidelines
- Java tests live under `java/hadoop-4mc/src/test/java` (JUnit). Naming: `Test*.java` extending JUnit/TestCase.
- Run tests: `mvn -f java/pom.xml -pl hadoop-4mc test`.
- Smoke test native CLI:
  - `echo hello > /tmp/in.txt && native/4mc -z /tmp/in.txt -o /tmp/in.txt.4mc`
  - `native/4mc -d /tmp/in.txt.4mc -o /tmp/out.txt && diff /tmp/in.txt /tmp/out.txt`

## Commit & Pull Request Guidelines
- Commits: imperative, concise subject (<72 chars) + brief body of “why”.
- PRs: describe change, risk, and validation; link issues; call out platform impacts (Linux/macOS/Windows) and Hadoop/Spark compatibility.
- Verify both native and Java builds locally before review; include steps to reproduce.

## Security & Configuration Tips
- Hadoop/Spark: include `hadoop-4mc` jar and enable codecs via `io.compression.codecs` (see Readme).
- JNI: when using a custom `libhadoop-4mc`, set `LD_LIBRARY_PATH`/`DYLD_LIBRARY_PATH` appropriately.
- Keep bundled native libs in sync with upstream LZ4/ZSTD versions used here.

