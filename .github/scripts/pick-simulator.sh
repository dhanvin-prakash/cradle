#!/usr/bin/env bash
# Prints "udid=<id>" for the newest available iPhone simulator.
set -euo pipefail

UDID=$(xcrun simctl list devices available --json \
  | jq -r '[.devices | to_entries[] | select(.key | contains("iOS")) | .value[] | select(.name | startswith("iPhone"))] | last | .udid')

echo "Using simulator $UDID" >&2
echo "udid=$UDID"
