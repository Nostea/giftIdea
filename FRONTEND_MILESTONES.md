# Frontend Meilenstein-Plan

## M0 – Setup & Grundlagen
Ziel: Repo startklar, Tech-Stack steht, erste Seite läuft.
- Next.js (App Router) + TypeScript + Bun
- Tailwind + shadcn/ui (Defaults)
- Projektstruktur feature-first
- .env mit NEXT_PUBLIC_API_URL
- Basis-Layouts & Landing Page Skeleton
- einfache Security-Headers

## M1 – Auth & Routing
Ziel: Login-Flow + Session-Handling im UI.
- Auth.js Magic-Link (JWT-Sessions)
- Login Page + Callback Flow
- Protected Routes (Gruppen-Bereiche)
- zentraler Fetch-Wrapper + Error-Handling

## M2 – Kern-Flows (Gruppen & Geschenke)
Ziel: MVP-Kernfunktionalität.
- Gruppen-Übersicht (eigene Gruppen)
- Gruppen-Detailseite + Invite-Button
- Geschenkideen: anlegen, anzeigen, löschen
- Geschenk-Detailseite
- leichtes Revalidate-Pattern

## M3 – CMS & Inhalte
Ziel: Artikel sichtbar für Gäste und eingeloggte Nutzer.
- CMS-SaaS integrieren (API)
- Artikel-Schema (Titel, Slug, Cover, Kurztext, Markdown, Status)
- Rendering via ISR
- Bilder via CMS-Storage + next/image
- Alt-Text Pflicht im CMS-Workflow

## M4 – Tests & Stabilisierung
Ziel: kritische Pfade abgesichert.
- Unit-Tests mit Vitest (Fetch-Wrapper, Zod-Schemas)
- E2E-Tests mit Cypress:
  - Magic-Link Login
  - Gruppe erstellen + Geschenkidee
- Testdaten per Seeding
- kleine A11y-Baseline (Labels, Fokus, Tastatur)

