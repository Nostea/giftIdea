# AGENTS Guide for `giftIdea`

## Scope and source of truth
- This project has **no existing AI rule files** from the requested glob (`.github/copilot-instructions.md`, `AGENT.md`, `AGENTS.md`, `CLAUDE.md`, `README.md`, etc.) at generation time.
- Treat **code as source of truth**; PlantUML files describe target ideas and can diverge from runtime behavior.
- Start from: `src/main/java/org/nostea/gift/GiftIdeaApplication.java` (Spring Boot entrypoint).

## Architecture at a glance
- Active stack: Spring Boot WebMVC (`pom.xml`, Java 21), controller -> service -> model.
- REST layer lives in `src/main/java/org/nostea/gift/controller`:
  - `UserController` (`/users`), `GroupController` (`/groups`), `GroupMembershipController` (`/groups/groupmemberships`).
- Service layer (`src/main/java/org/nostea/gift/service`) is currently **in-memory stub logic** (hardcoded `List.of(...)`), not persistence-backed.
- Domain models are POJOs in `src/main/java/org/nostea/gift/model` (`User`, `Group`, `GroupMembership`, `Gift`, enums).

## Important data-flow reality (easy to miss)
- There are **two parallel worlds**:
  - Runtime REST responses use in-memory objects from services.
  - CSV persistence code exists separately (`CsvReaderWriter`, `UsersCsvRepository`, `GroupsCsvRepository`) and is **not wired into controllers/services**.
- CSV files live in `src/main/java/org/nostea/gift/*.CSV` and use `;` separators with headers.
- `CsvFilePaths.java` currently points to `src/main/java/org/nostea/springbootstarter/` (likely stale path), so check before relying on CSV writes.

## Conventions specific to this codebase
- Controllers validate basic ID input (`id <= 0 -> 400`) and return `204` for empty collections.
- Not-found handling is usually `null` from service -> `404` in controller (instead of exceptions).
- Side effects are logged with `System.out.println(...)` in services/repositories.
- Membership updates are manual bidirectional list updates (example: `GroupService.addMemberToGroup` updates both `group.members` and `user.memberships`).

## Developer workflows
- Build and test (Windows PowerShell):
  - `./mvnw.cmd clean test`
- Run app locally:
  - `./mvnw.cmd spring-boot:run`
- Package jar:
  - `./mvnw.cmd clean package`
- Current automated test coverage is minimal (`GiftIdeaApplicationTests.contextLoads`).

## Integration points and docs
- API contract examples/aspirational scope: `REST-Routes.plantuml`, `REST-Router-Groupchange.plantuml`.
- Broad domain vision: `UMLProjectDiagram.plantuml` (includes entities not implemented in runtime APIs).
- CSV behavior notes: `src/main/java/org/nostea/gift/CSV_READER_EXPLANATION.md`.

## Agent working rules for changes
- If changing REST behavior, update both controller + corresponding service method.
- If introducing persistence, decide explicitly whether to replace in-memory service data with CSV repositories or keep both paths.
- When unsure due to UML/code mismatch, follow implemented Java classes first and document deltas in PR notes.

