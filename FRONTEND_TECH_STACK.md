# Frontend Tech Stack

## Core Dependencies (Runtime)
- next (App Router)
- react, react-dom
- typescript
- tailwindcss, postcss, autoprefixer
- shadcn/ui (CLI; includes Radix UI components as needed)
- @radix-ui/react-* (only used components)
- clsx, tailwind-merge
- react-hook-form
- zod
- @hookform/resolvers
- next-auth (Auth.js)
- next-themes (optional, if dark mode is used)

## Dev Dependencies
- eslint, eslint-config-next
- prettier, prettier-plugin-tailwindcss
- vitest, @vitest/ui
- @testing-library/react, @testing-library/jest-dom
- cypress
- dotenv-cli (optional)

## Project Structure (Feature-First)
```
src/
  app/
    (public)/
    (auth)/
    (dashboard)/
    invite/[token]/
    layout.tsx
    globals.css

  features/
    auth/
      components/
      api/
      hooks/
      types.ts
      schemas.ts
    groups/
      components/
      api/
      hooks/
      types.ts
      schemas.ts
    gifts/
      components/
      api/
      hooks/
      types.ts
      schemas.ts
    articles/
      components/
      api/
      hooks/
      types.ts
      schemas.ts

  components/
  lib/
    fetcher.ts
    env.ts
    constants.ts
  styles/
    tailwind.css
  types/
    index.ts
```

## Scripts
```
"dev": "next dev"
"build": "next build"
"start": "next start"
"lint": "next lint"
"test": "vitest"
"test:ui": "vitest --ui"
"e2e": "cypress open"
"e2e:run": "cypress run"
```

## Env Conventions (.env.local)
```
NEXT_PUBLIC_API_URL=http://localhost:8080/api/v1
NEXTAUTH_URL=http://localhost:3000
NEXTAUTH_SECRET=...
EMAIL_PROVIDER_API_KEY=...
```

## API Layer
- Central fetch wrapper in `src/lib/fetcher.ts`.
- Typed errors for consistent UI handling.
- Light revalidate pattern after mutations.

## Styling
- Tailwind defaults + shadcn/ui.
- Theme tokens later if needed.

