# ClickUp 4 Java

ClickUp4j aims to be a complete, full wrapper for the ClickUp REST API in Java.

**This project is in active development and is not yet complete. If you intend to use this, be cautious things may change!**

## Features

The list is based on the [ClickUp API documentation](https://clickup.com/docs/api/).

Main Features:
- [x] Reading the API (tasks, workspaces, views)
- [ ] Modifying/Creating [In Progress!]
- [ ] Time Tracking
- [ ] Webhooks

<details>
<summary>
Full Breakdown
</summary>

- Attachments
  - [x] Upload Attachments (`Task#uploadAttachment`)
- Authorization
  - [ ] Get Access Token
  - [x] Get Authorized User (`ClickUp4j#retrieveSelfUser`)
  - [x] Get Authorized Teams (`ClickUp4j#retrieveWorkspaces`)
- Checklists
  - [ ] Create Checklist
  - [ ] Edit Checklist
  - [ ] Delete Checklist
  - [ ] Create Checklist Item
  - [ ] Edit Checklist Item
  - [ ] Delete Checklist Item
- Comment
  - [ ] Create Task Comment
  - [ ] Create Chat View Comment
  - [ ] Create List Comment
  - [ ] Get Task Comments
  - [ ] Get Chat View Comments
  - [ ] Get List Comments
  - [ ] Update Comment
  - [ ] Delete Comment
- Custom Fields
  - Routes:
    - [ ] Get Accessible Custom Fields
    - [ ] Set Custom Field Value
    - [ ] Remove Custom Field Value
  - Fields:
    - [x] URL
    - [x] Dropdown
    - [x] Email
    - [x] Phone
    - [x] Date
    - [x] Text
    - [x] Checkbox
    - [x] Number
    - [x] Currency
    - [x] Tasks
    - [x] Users
    - [x] Rating/Emoji
    - [x] Labels
    - [x] Automatic Progress
    - [x] Manual Progress
    - [x] Short Text
    - [ ] File
    - [ ] Formula
- Dependencies
  - [ ] Add Dependency
  - [ ] Delete Dependency
  - [ ] Add Task Link
  - [ ] Delete Task Link
- Folders
  - [ ] Create Folder
  - [ ] Update Folder
  - [ ] Delete Folder
  - [ ] Get Folders
  - [ ] Get Folder
- Goals
  - [ ] Create Goal
  - [ ] Update Goal
  - [ ] Delete Goal
  - [ ] Get Goals
  - [ ] Get Goal
  - [ ] Create Key Result
  - [ ] Edit Key Result
  - [ ] Delete Key Result
- Lists
  - [ ] Create List
  - [ ] Create Folderless List
  - [ ] Update List
  - [ ] Delete List
  - [ ] Get Lists
  - [ ] Get Folderless Lists
  - [ ] Get List
  - [ ] Add Task To List
  - [ ] Remove Task From List
- Members
  - [ ] Get Task Members
  - [ ] Get List Members
- Shared Hierarchy
  - [ ] Get Shared Hierarchy
- Spaces
  - [ ] Create Space
  - [ ] Update Space
  - [ ] Delete Space
  - [ ] Get Spaces
  - [x] Get Space (`ClickUp4j#retrieveSpace(String)`)
- Tags
  - [ ] Get Space Tags
  - [ ] Create Space Tag
  - [ ] Edit Space Tag
  - [ ] Delete Space Tag
  - [ ] Add Tag To Task
  - [ ] Remove Tag From Task
- Tasks
  - [ ] Create Task
  - [ ] Update Task
  - [ ] Delete Task
  - [x] Get Tasks (`ClickUp4j#retrieveTasks(String)`)
  - [x] Get Task (`ClickUp4j#retrieveTask`)
  - [ ] Get Filtered Team Tasks
  - [ ] Get Task's Time in Status
  - [ ] Get Bulk Tasks' Time in Status
- Task Templates
  - [ ] Get Task Template
  - [ ] Create Task From Template
- Teams
  - [ ] Create Team
  - [ ] Update Team
  - [ ] Delete Team
  - [ ] Get Teams
- Time Tracking
  - [ ] Get time entries within a date range
  - [ ] Get singular time entry
  - [ ] Get time entry history
  - [ ] Get running time entry
  - [ ] Create a time entry
  - [ ] Remove tags from time entries
  - [ ] Get all tags from time entries
  - [ ] Add tags from time entries
  - [ ] Change tag names from time entries
  - [ ] Start a time Entry
  - [ ] Stop a time Entry
  - [ ] Delete a time Entry
  - [ ] Update a time Entry
- Views
  - [ ] Create Team View
  - [ ] Create Space View
  - [ ] Create Folder View
  - [ ] Create List View
  - [ ] Get Team Views
  - [ ] Get Space Views
  - [ ] Get Folder Views
  - [ ] Get List Views
  - [ ] Get View
  - [ ] Get View Tasks
  - [ ] Update View
  - [ ] Delete View
- Workspace
  - [x] Get Workspaces (`ClickUp4j#retrieveWorkspaces`)
- Webhooks
  - Events
    - [ ] taskCreated
    - [ ] taskUpdated
    - [ ] taskDeleted
    - [ ] taskPriorityUpdated
    - [ ] taskStatusUpdated
    - [ ] taskAssigneeUpdated
    - [ ] taskDueDateUpdated
    - [ ] taskTagUpdated
    - [ ] taskMoved
    - [ ] taskCommentPosted
    - [ ] taskCommentUpdated
    - [ ] taskTimeEstimateUpdated
    - [ ] taskTimeTrackedUpdated
    - [ ] listCreated
    - [ ] listUpdated
    - [ ] listDeleted
    - [ ] folderCreated
    - [ ] folderUpdated
    - [ ] folderDeleted
    - [ ] spaceCreated
    - [ ] spaceUpdated
    - [ ] spaceDeleted
    - [ ] goalCreated
    - [ ] goalUpdated
    - [ ] goalDeleted
    - [ ] keyResultCreated
    - [ ] keyResultUpdated
    - [ ] keyResultDeleted
  - Routes
    - [ ] Create Webhook
    - [ ] Update Webhook
    - [ ] Delete Webhook
    - [ ] Get Webhooks

</details>

## Creating the ClickUp4j Object

Creating the `ClickUp4j` Object is done via the `ClickUp4jBuilder` class.
After setting the token and other options via setters, 
the `ClickUp4j` Object is then created by calling the `build()` method.

**Example**:

```java
ClickUp4j clickup = ClickUp4jBuilder.create("token").build();
```

Now, you can start making requests. See below for a full example.

```java
import pw.chew.clickup4j.api.*;

public class MyClickUpProject {
    public static void main(String[] args) {
        // Build the ClickUp4j Object
        ClickUp4j clickup = ClickUp4jBuilder.create("token").build();
        // Queue up the request
        clickup.retrieveTask("aaaaa").queue(task -> {
            // Print out the title
            System.out.println(task.getTitle());
        });
    }
}
```

## Download

Latest Release: None yet!

Be sure to replace the **VERSION** key below with the one of the versions shown above! For snapshots, please use the instructions provided by [JitPack](https://jitpack.io/#JavaAPIs/ClickUp4j).

**Maven**
```xml
<dependency>
    <groupId>pw.chew.clickup4j</groupId>
    <artifactId>clickup4j</artifactId>
    <version>VERSION</version>
</dependency>
```

**Gradle**
```gradle
repositories {
    // soon
}

dependencies {
    // Change 'implementation' to 'compile' in old Gradle versions
    implementation("pw.chew.clickup4j:clickup4j:VERSION")
}
```

The snapshot builds are only available via JitPack and require adding the JitPack resolver, you need to specify specific commits to access those builds.

## Documentation

Docs can be found for the master branch directly [here](https://javaapis.github.io/ClickUp4j/)

A simple Wiki can also be found in this repository's [Wiki section](https://github.com/JavaAPIs/ClickUp4j/wiki)
