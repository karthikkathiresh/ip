# Nimbus User Guide

Nimbus is an **app for managing tasks and is optimized for use via a Command Line Interface (CLI)**. If you can type fast, Nimbus can get your task management duties done quickly!

* [Quick Start](#quick-start)
* [Features](#features)
    * [Adding a ToDo task: `todo`](#adding-a-todo-task-todo)
    * [Adding a Deadline task: `deadline`](#adding-a-deadline-task-deadline)
    * [Adding an Event task: `event`](#adding-an-event-task-event)
    * [Listing all tasks: `list`](#listing-all-tasks-list)
    * [Marking a task as done: `mark`](#marking-a-task-as-done-mark)
    * [Unmarking a task: `unmark`](#unmarking-a-task-unmark)
    * [Deleting a task: `delete`](#deleting-a-task-delete)
    * [Locating tasks by keyword: `find`](#locating-tasks-by-keyword-find)
    * [Exiting the program: `bye`](#exiting-the-program-bye)
* [FAQ](#faq)
* [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick Start

1. Ensure you have Java `17` or above installed in your Computer.
2. Download the latest `nimbus.jar` from the releases.
3. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar nimbus.jar` command to run the application.
4. Type the command in the command box and press Enter to execute it.

--------------------------------------------------------------------------------------------------------------------

## Features

> **Notes about the command format:**
> * Words in `<UPPER_CASE>` are the parameters to be supplied by the user.
    >   For example, in `todo <DESCRIPTION>`, `read book` is a parameter which can be used as `todo read book`.
> * Command words are **case-insensitive**. `TODO`, `ToDo`, and `todo` are all treated as the same command.

### Adding a ToDo task: `todo`

Adds a basic task without any date or time constraints to the list.

**Format:** `todo <DESCRIPTION>`

**Examples:**
* `todo read book`
* `todo finish CS2113 tutorial`

**Output:**
```
Enter Command: todo finish CS2113 tutorial
_____________________________________________________
Got it. I've added this task:
[T][ ] finish CS2113 tutorial
Now you have 2 tasks in the list
_____________________________________________________
```

### Adding a Deadline task: `deadline`

Adds a task that needs to be done before a specific date/time.

**Format:** `deadline <DESCRIPTION> /by <DATE_OR_TIME>`

**Examples:**
* `deadline submit assignment /by Sunday 1159pm`
* `deadline return library book /by 2026-08-09`

**Output:**
```
Enter Command: deadline return library book /by 2026-08-09
_____________________________________________________
Got it. I've added this task:
[D][ ] return library book (by: Aug 9 2026)
Now you have 3 tasks in the list
_____________________________________________________
```

### Adding an Event task: `event`

Adds a task that starts and ends at a specific time.

**Format:** `event <DESCRIPTION> /from <START_TIME> /to <END_TIME>`

**Examples:**
* `event team meeting /from Monday 2pm /to 4pm`
* `event career fair /from 2026-03-01 /to 2026-03-03`

**Output:**
```
Enter Command: event career fair /from 2026-03-01 /to 2026-03-03
_____________________________________________________
Got it. I've added this task:
[E][ ] career fair (from: Mar 1 2026 to: Mar 3 2026)
Now you have 4 tasks in the list
_____________________________________________________
```
### Listing all tasks: `list`

Shows a list of all tasks currently tracked by the application.

**Format:** `list`

**Output:**

```
Enter Command: list
_____________________________________________________
Here are the tasks in your list:
1. [D][ ] CG2023 Lab (by: Mar 7 2026)
2. [T][ ] finish CS2113 tutorial
3. [D][ ] return library book (by: Aug 9 2026)
4. [E][ ] career fair (from: Mar 1 2026 to: Mar 3 2026)
_____________________________________________________
```

### Marking a task as done: `mark`

Marks a specified task in the list as completed. Note that this will not automatically delete the task.

**Format:** `mark <INDEX>`
* Marks the task at the specified `INDEX` as done.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, ...

**Examples:**
* `list` followed by `mark 2` marks the 2nd task in the list as completed.

**Output:**
```
Enter Command: mark 2
_____________________________________________________
Nice! I've marked this task as done:
[T][X] finish CS2113 tutorial
_____________________________________________________
Enter Command: list
_____________________________________________________
Here are the tasks in your list:
1. [D][ ] CG2023 Lab (by: Mar 7 2026)
2. [T][X] finish CS2113 tutorial
3. [D][ ] return library book (by: Aug 9 2026)
4. [E][X] career fair (from: Mar 1 2026 to: Mar 3 2026)
_____________________________________________________
```

### Unmarking a task: `unmark`

Marks a completed task as not done yet.

**Format:** `unmark <INDEX>`
* Unmarks the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.

**Examples:**
* `list` followed by `unmark 1` marks the 1st task in the list as incomplete.

**Output:**
```
Enter Command: unmark 4
_____________________________________________________
OK, I've marked this task as not done yet:
[E][ ] career fair (from: Mar 1 2026 to: Mar 3 2026)
_____________________________________________________
Enter Command: list
_____________________________________________________
Here are the tasks in your list:
1. [D][ ] CG2023 Lab (by: Mar 7 2026)
2. [T][X] finish CS2113 tutorial
3. [D][ ] return library book (by: Aug 9 2026)
4. [E][ ] career fair (from: Mar 1 2026 to: Mar 3 2026)
_____________________________________________________
```

### Deleting a task: `delete`

Removes a task from the list permanently.

**Format:** `delete <INDEX>`
* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.

**Examples:**
* `list` followed by `delete 3` deletes the 3rd task in the list.

**Output:**
```
Enter Command: delete 2
_____________________________________________________
Noted. I've removed this task:
[T][X] finish CS2113 tutorial
Now you have 3 tasks in the list
_____________________________________________________
Enter Command: list
_____________________________________________________
Here are the tasks in your list:
1. [D][ ] CG2023 Lab (by: Mar 7 2026)
2. [D][ ] return library book (by: Aug 9 2026)
3. [E][ ] career fair (from: Mar 1 2026 to: Mar 3 2026)
_____________________________________________________
```

### Locating tasks by keyword: `find`

Finds tasks whose descriptions contain the specified keyword.

**Format:** `find <KEYWORD>`
* The search is **case-insensitive**. e.g `book` will match `Book`.
* Only the description of the task is searched.

**Examples:**
* `find book` returns `read book` and `return library book`.

**Output:**

```
Enter Command: find book
_____________________________________________________
Here are the matching tasks in your list:
1. [D][ ] return library book (by: Aug 9 2026)
_____________________________________________________
```

### Exiting the program: `bye`

Exits the program and automatically saves your task list to the hard drive.

**Format:** `bye`

**Output:**
```
Enter Command: bye
_____________________________________________________
Bye. Hope to see you again soon!
_____________________________________________________
```

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: Where is my data saved?
**A**: Nimbus data is saved in a folder named `data`, automatically created in the same directory as your `nimbus.jar` file. The file itself is called `nimbus.txt`.

**Q**: How do I transfer my data to another Computer?
**A**: Simply copy the `data/nimbus.txt` file into the same directory as the `nimbus.jar` file on your new computer.

--------------------------------------------------------------------------------------------------------------------

## Command Summary

| Action | Format, Examples |
|--------|------------------|
| **ToDo** | `todo <DESCRIPTION>` <br> e.g., `todo read book` |
| **Deadline** | `deadline <DESCRIPTION> /by <DATE_OR_TIME>` <br> e.g., `deadline submit assignment /by Sunday 1159pm` |
| **Event** | `event <DESCRIPTION> /from <START_TIME> /to <END_TIME>` <br> e.g., `event project meeting /from 2pm /to 4pm` |
| **List** | `list` |
| **Mark** | `mark <INDEX>` <br> e.g., `mark 1` |
| **Unmark** | `unmark <INDEX>` <br> e.g., `unmark 2` |
| **Delete** | `delete <INDEX>` <br> e.g., `delete 3` |
| **Find** | `find <KEYWORD>` <br> e.g., `find book` |
| **Exit** | `bye` |