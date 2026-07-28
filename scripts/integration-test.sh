#!/usr/bin/env bash
set -euo pipefail

APP="./app/build/install/app/bin/app"

run_test() {
  local password="$1"
  local expected="$2"

  echo "Testing password expecting: $expected"

  output="$("$APP" --password "$password" --loglevel WARN 2>&1)"

  echo "$output"

  if ! grep -q "Strength: $expected" <<< "$output"; then
    echo "Integration test failed."
    echo "Expected: Strength: $expected"
    exit 1
  fi
}

run_test "12345678" "WEAK"
run_test "Password123" "MEDIUM"
run_test "VeryStrong#Password2026" "STRONG"

echo "All integration tests passed."