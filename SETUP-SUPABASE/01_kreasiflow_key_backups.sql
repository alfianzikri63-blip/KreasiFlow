-- Jalankan di Supabase SQL Editor pada proyek KHUSUS KreasiFlow.
-- Backups contain AES-256-GCM ciphertext, not the underlying API keys.
-- One encrypted backup per authenticated user; no anonymous table permissions.
create table if not exists public.kreasiflow_key_backups (
  user_id uuid primary key references auth.users (id) on delete cascade,
  vault_version smallint not null default 1 check (vault_version = 1),
  kdf_iterations integer not null check (kdf_iterations between 200000 and 1000000),
  salt_b64 text not null check (length(salt_b64) between 20 and 60),
  nonce_b64 text not null check (length(nonce_b64) between 12 and 40),
  ciphertext_b64 text not null check (length(ciphertext_b64) between 24 and 100000),
  updated_at timestamptz not null default now()
);

alter table public.kreasiflow_key_backups enable row level security;
revoke all on public.kreasiflow_key_backups from anon, authenticated;
grant usage on schema public to authenticated;
grant select, insert, update, delete on public.kreasiflow_key_backups to authenticated;

drop policy if exists "kf_backup_select_self" on public.kreasiflow_key_backups;
create policy "kf_backup_select_self"
  on public.kreasiflow_key_backups for select to authenticated
  using ((select auth.uid()) = user_id);

drop policy if exists "kf_backup_insert_self" on public.kreasiflow_key_backups;
create policy "kf_backup_insert_self"
  on public.kreasiflow_key_backups for insert to authenticated
  with check ((select auth.uid()) = user_id);

drop policy if exists "kf_backup_update_self" on public.kreasiflow_key_backups;
create policy "kf_backup_update_self"
  on public.kreasiflow_key_backups for update to authenticated
  using ((select auth.uid()) = user_id)
  with check ((select auth.uid()) = user_id);

drop policy if exists "kf_backup_delete_self" on public.kreasiflow_key_backups;
create policy "kf_backup_delete_self"
  on public.kreasiflow_key_backups for delete to authenticated
  using ((select auth.uid()) = user_id);
