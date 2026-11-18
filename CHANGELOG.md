# KreasiFlow Studio v5.0.1 — Android startup hotfix

- Harden updater startup and Android 14+ dynamically registered download receiver.
- Keep editor open when background update setup fails.
- Graceful recovery UI for embedded browser initialization exceptions.
- Null-safe WebView state restoration; prevent secondary lifecycle crash.
- Preserve package id and release signing configuration for in-place upgrades.

This is a preventative hotfix based on source inspection; an on-device crash trace is still needed to confirm the original trigger.
