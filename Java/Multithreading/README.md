# Java Multithreading Concepts

This folder contains small Java examples for learning how threads are created,
started, observed, and interrupted. The examples demonstrate both normal
thread behavior and common concurrency problems.

## 1. Creating a Thread by Extending `Thread`

**Example:** [Demo.java](./Demo.java)

`Demo.java` defines `MyTread`, which extends `Thread` and overrides `run()`.
The `run()` method contains the work that the new thread executes.

```java
class MyTread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread is running");
    }
}
```

### Methods used

- `Thread.start()` creates a separate thread of execution and invokes `run()`.
- `Thread.run()` contains the task, but calling it directly does not create a
  separate thread.

Extending `Thread` is straightforward for simple examples. Implementing
`Runnable` is generally more flexible because it separates the task from the
threading mechanism.

## 2. Creating a Thread with `Runnable`

**Example:** [DemoRunnable.java](./DemoRunnable.java)

`DemoRunnable.java` shows both approaches for implementing `Runnable`:

- Implement the `Runnable` interface in `MyRunnable`.
- Pass a lambda expression to the `Thread` constructor.

```java
MyRunnable myRunnable = new MyRunnable();
Thread thread = new Thread(myRunnable);
thread.start();

Thread lambdaThread =
    new Thread(() -> System.out.println("Thread is running"));
lambdaThread.start();
```

### Methods used

- `Runnable.run()` is the task that the thread executes.
- `Thread.start()` starts the thread asynchronously.

**Note:** This file currently contains two public declarations named
`DemoRunnable`. Java permits only one public class declaration in a file with
that filename, so the two approaches should be kept in separate files or
combined under one public class definition.

## 3. Difference Between `start()` and `run()`

**Example:** [StartRun.java](./StartRun.java)

This example calls `t1.run()` instead of `t1.start()`. The lambda therefore
runs on the main thread rather than on a newly created thread.

### Methods used

- `Thread.start()` asks the JVM to schedule the thread for execution on a new
  thread.
- `Thread.run()` executes the task in the thread that calls `run()`.
- `Thread.currentThread().getName()` identifies the currently executing thread.
- `Thread.currentThread().getId()` returns the thread's numeric ID.

Calling `start()` more than once on the same `Thread` object throws
`IllegalThreadStateException`.

## 4. Thread Names and IDs

**Example:** [ThreadId.java](./ThreadId.java)

This example prints the name and ID of the main thread and two custom threads.
Each custom thread is created with a lambda and then started.

### Methods used

- `Thread.currentThread()` returns the thread currently executing the code.
- `getName()` returns the thread name.
- `getId()` returns the thread ID.
- `Thread.start()` schedules a custom thread.

The execution order of the custom threads is not guaranteed.

## 5. Thread Sleep and Join

**Example:** [ThreadMethods.java](./ThreadMethods.java)

`ThreadMethods.java` starts a worker thread that sleeps for two seconds and
then prints a message. The main thread calls `join()` to wait until the worker
has completed.

### Methods used

- `Thread.sleep(long millis)` pauses the current thread for the requested time.
- `Thread.start()` begins worker-thread execution.
- `Thread.join()` makes the calling thread wait until the target thread ends.
- `InterruptedException` is caught because `sleep()` can be interrupted.

`join()` is useful when a thread must finish before dependent work continues.
It does not give that thread higher priority or change scheduling.

## 6. Thread Priority

**Example:** [Priority.java](./Priority.java)

This example creates two threads and sets the first thread's priority to 3.
The second thread keeps the default priority.

### Methods used

- `setPriority(int priority)` changes a thread's priority.
- `getPriority()` reads the current priority.
- `start()` starts each thread.

Java defines:

| Constant | Value |
|---|---:|
| `Thread.MIN_PRIORITY` | 1 |
| `Thread.NORM_PRIORITY` | 5 |
| `Thread.MAX_PRIORITY` | 10 |

Priority is only a scheduling hint. The operating system and JVM decide which
thread actually receives CPU time, so thread priority does not guarantee
execution order.

## 7. Thread States

**Example:** [Phases.java](./Phases.java)

This example checks a thread's state before startup, shortly after startup,
and after a delay.

### Methods used

- `getState()` returns the current `Thread.State`.
- `start()` changes the thread from `NEW` to a state that can be scheduled.
- `Thread.sleep(long millis)` pauses the main thread for observation.

Important states include:

- `NEW`: the thread object exists but has not started.
- `RUNNABLE`: it can run or is running.
- `BLOCKED`: it is waiting to enter synchronization.
- `WAITING`: it is waiting indefinitely.
- `TIMED_WAITING`: it is waiting for a timed operation such as `sleep()`.
- `TERMINATED`: its `run()` method has completed or ended.

The exact state observed immediately after `start()` or `sleep()` can vary
because of scheduler timing.

## 8. Thread is Alive

**Example:** [Alive.java](./Alive.java)

This example checks whether a thread is alive before and after `start()`.

### Methods used

- `isAlive()` returns `true` when the thread has started and has not terminated.
- `start()` starts the thread.
- `sleep()` keeps the thread active for a limited time.

Before `start()`, `isAlive()` returns `false`. After `start()`, it normally
returns `true`; the worker later becomes inactive after its sleep finishes.

## 9. Daemon Threads

**Example:** [DaemonThread.java](./DaemonThread.java)

A daemon thread provides background work and does not prevent the JVM from
exiting. This example marks a thread as a daemon before starting it.

### Methods used

- `setDaemon(true)` marks a thread as a daemon.
- `start()` starts the daemon.
- `sleep()` keeps the main thread alive briefly.

A daemon thread should generally perform work that can safely stop when the
application ends. Garbage collection is a common type of daemon activity, but
the example itself is only a demonstration of daemon lifetime.

## 10. Nondeterministic Thread Execution

**Example:** [NonDeterminism.java](./NonDeterminism.java)

Two threads print odd and even numbers. One thread prints odd values beginning
with `T1`, while the other prints even values beginning with `T2`.

### Methods used

- `start()` starts both threads.
- Each thread runs its own loop independently.

The JVM scheduler determines how the output is interleaved. The resulting
order can change between runs, so a program should not assume that thread
creation order determines execution order.

## 11. Thread Interruption

**Example:** [Interrupt.java](./Interrupt.java)

This example starts a worker that repeatedly prints `Running` until the main
thread sends it an interruption request.

### Methods used

- `interrupt()` sets the interruption status and requests interruption.
- `isInterrupted()` checks the interruption status without clearing it.
- `Thread.interrupted()` also checks interruption status but clears the flag
  for the calling thread.
- `Thread.currentThread()` obtains the thread whose status is being checked.
- `sleep()` pauses the main thread before it interrupts the worker.

Interruption is cooperative. Java does not forcibly terminate a thread.
Interruptible code should check the flag or catch
`InterruptedException` and stop cleanly.

## 12. Shared-Memory Visibility with `volatile`

**Example:** [Visibility.java](./Visibility.java)

This example uses a shared `volatile` boolean named `flag`. One thread changes
it to `true` after two seconds, while another thread waits for the change.

### Methods used

- `volatile` makes shared variable reads and writes visible across threads.
- `sleep()` delays the thread that changes the flag.
- `start()` starts both threads.

`volatile` is suitable for simple state visibility. It does not make a sequence
of read-modify-write operations atomic.

## 13. Race Conditions

**Example:** [RaceCondition.java](./RaceCondition.java)

Two threads call `Counter.increment()`-style logic 10,001 times each. The
operation `count++` consists of a read, an increment, and a write. When both
threads perform those steps concurrently, lost updates can occur.

### Methods used

- `start()` starts both worker threads.
- `join()` waits for both workers before printing the shared result.

The expected mathematical result is 20,002, but an unsynchronized
`count++` may produce a lower result.

### Ways to protect the shared value

- Synchronize the increment method with `synchronized`.
- Use a lock such as `ReentrantLock`.
- Use `AtomicInteger` for atomic integer operations.

Synchronization also establishes visibility and memory ordering for the
protected critical section.

## 14. Synchronizing an Instance Method

**Example:** [SynchronizationII.java](./SynchronizationII.java)

Both threads call the synchronized `show()` method on the same `Test` object.
The object lock allows only one thread to enter the method at a time. The
second thread waits until the first thread finishes, including its
two-second sleep.

The thread that acquires the lock first is not guaranteed, so the thread names
in the output may vary. However, each thread's `Inside show` and `Show finish`
messages remain together.

## 15. Locking Multiple Synchronized Methods

**Example:** [SynchronizationIII.java](./SynchronizationIII.java)

`m1()` and `m2()` are different synchronized instance methods, but both use
the same `Test` object. They therefore share the object's monitor lock. If
one thread enters `m1()`, another thread cannot enter `m2()` on that same
object until the first method returns.

This demonstrates that synchronization is associated with the object, not
only with an individual method.

## 16. Static Method Synchronization

**Example:** [StaticSync.java](./StaticSync.java)

The `count` field and `increment()` method are static, so they belong to the
`Counter` class rather than to an individual object. A static synchronized
method locks `Counter.class`. This ensures that only one thread can update the
shared counter at a time.

The threads may start in either order, but the protected updates produce the
values `1` and `2` without a simultaneous update.

## 17. Synchronizing Separate Critical Sections

**Example:** [CustomLock.java](./CustomLock.java)

This example uses two explicit lock objects, `lock1` and `lock2`. `deposit()`
uses `lock1`, while `withdraw()` uses `lock2`, so the two operations do not
block each other. Separate locks can improve concurrency when the operations
protect independent resources.

The lock objects should remain private implementation details in production
code so outside code cannot accidentally acquire them and interfere with
the synchronization.

## 18. Inter-Thread Communication with `wait()` and `notify()`

**Example:** [InterThreadCommIII.java](./InterThreadCommIII.java)

This example implements a one-slot producer-consumer buffer. The producer
places one integer in `Box`, and the consumer removes it before the producer
can place the next integer. The `flag` field represents the buffer state:

- `false`: the buffer is empty, so the producer may write.
- `true`: the buffer contains an item, so the consumer may read.

Both methods are `synchronized`, which means they use the same `Box` monitor.
Only one thread can inspect and update the buffer at a time. When a thread
cannot continue, `wait()` releases the monitor and suspends it instead of
using CPU in a busy-wait loop. `notify()` wakes one waiting thread after the
buffer state changes.

The condition is checked inside a `while` loop rather than an `if` statement.
A thread can wake up before its condition is true, or another thread can
acquire the monitor first, so the condition must always be verified again.
`InterThreadCommI.java` shows the uncoordinated version, while
`InterThreadCommII.java` shows busy waiting; this example demonstrates the
monitor-based approach.

### Methods and concepts used

- `synchronized` protects the shared buffer and provides monitor ownership.
- `wait()` releases the monitor and waits for a notification.
- `notify()` signals one thread waiting on the same monitor.
- `volatile` makes the state fields visible across threads, although the
  `synchronized` methods already provide the coordination needed here.
- `InterruptedException` is declared because `wait()` can be interrupted.

## 19. Nondeterminism and Thread Ordering

Threads do not execute in a predictable order merely because they are created
or started in sequence. `ThreadId.java`, `NonDeterminism.java`, and
`ThreadMethods.java` demonstrate these behaviors.

Use these mechanisms for predictable coordination:

- `join()` waits for completion.
- `synchronized` controls access to critical sections.
- Locks protect shared operations.
- `volatile` provides visibility for a simple shared state.
- Interrupt methods request cooperative cancellation.

## Common Thread Lifecycle

```text
NEW -> RUNNABLE -> TERMINATED
          |
          +-> BLOCKED
          +-> WAITING
          +-> TIMED_WAITING
```

The `run()` method completes normally, throws an exception, or is interrupted
where the code supports interruption, and the thread eventually becomes
`TERMINATED`.
