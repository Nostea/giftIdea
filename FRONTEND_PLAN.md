# Frontend Plan (Planning Session Results)

## Summary
This document captures the decisions from the frontend planning session for the gift idea app.

## Decisions
### Auth
- Magic-link first, password login later for learning bcrypt.
- JWT sessions.
- External email provider.
- Invite tokens: one-time, stored in DB, 7 days expiry.
- Invite flow: /invite/[token] page with confirmation button.
- Invite validation and accept are separate endpoints.

### CMS
- Minimal CMS via a lightweight SaaS (selection TBD).
- Articles schema:
  - title, slug, cover image, short text, content (Markdown), publish date, status (draft/published)
  - images allowed inline in Markdown
  - alt text required
- Images stored in CMS asset storage.
- Render with ISR.
- SEO postponed for now.

### Frontend Architecture
- Next.js App Router.
- Feature-first folder structure (default).
- React state + fetch wrapper.
- Centralized error handling.
- Light revalidate pattern.
- Tailwind CSS + shadcn/ui, defaults first.

### Testing
- E2E: Cypress.
- Unit tests: Vitest.
- Unit targets: fetch-wrapper error handling, Zod schemas.
- E2E flows:
  - Magic-link login.
  - Group create + gift flow.
- E2E runs against local backend.
- Test data: seeding.

### GDPR and Privacy
- Only necessary cookies (no analytics).

### Docker and Repos
- Frontend repo separate from backend repo from the start.
- Dockerization later (after backend is stable).

### Environment
- Use .env (NEXT_PUBLIC_API_URL).
- API prefix: /api/v1.

### Security and A11y
- Basic security headers in MVP.
- A11y baseline in MVP (labels, focus, keyboard).

### MVP Scope
- Screens:
  - Landing page
  - Login (magic link)
  - Group overview
  - Group detail
  - Gift detail
- Features:
  - Create and list groups
  - Invite members (button on group detail)
  - Create, list, delete gifts

## Open Items
- Choose specific CMS SaaS solution.
- Decide whether CMS is SaaS-hosted long-term or self-hosted in EU.
- Performance target (set later).
- SEO scope (set later).


