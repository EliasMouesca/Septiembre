#!/usr/bin/env bash

set -euo pipefail

readonly root_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
readonly vault="${SEPTIEMBRE_1PASSWORD_VAULT:-Code}"
readonly item="${SEPTIEMBRE_1PASSWORD_ITEM:-Septiembre Android Signing}"
readonly secret_prefix="op://${vault}/${item}"
readonly temp_dir="$(mktemp -d "${TMPDIR:-/tmp}/septiembre-signing.XXXXXX")"
readonly keystore_file="${temp_dir}/keystore.jks"

cleanup() {
    rm -rf "$temp_dir"
}

trap cleanup EXIT

command -v op >/dev/null 2>&1 || {
    printf '%s\n' 'Error: 1Password CLI (op) is required.' >&2
    exit 1
}

op read --out-file "$keystore_file" "${secret_prefix}/keystore" >/dev/null

export SEPTIEMBRE_KEYSTORE_FILE="$keystore_file"
export SEPTIEMBRE_KEYSTORE_PASSWORD="$(op read "${secret_prefix}/keystore_password")"
export SEPTIEMBRE_KEY_ALIAS="$(op read "${secret_prefix}/key_alias")"
export SEPTIEMBRE_KEY_PASSWORD="$(op read "${secret_prefix}/key_password")"

cd "$root_dir"
./gradlew assembleRelease "$@"
