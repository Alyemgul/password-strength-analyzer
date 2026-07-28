$ErrorActionPreference = "Stop"

$app = ".\app\build\install\app\bin\app.bat"

function Test-PasswordStrength {
    param(
        [Parameter(Mandatory = $true)]
        [string]$Password,

        [Parameter(Mandatory = $true)]
        [string]$Expected
    )

    Write-Host "Testing password expecting: $Expected"

    $output = & cmd.exe /d /s /c "`"$app`" --password `"$Password`" --loglevel WARN 2>&1"
$exitCode = $LASTEXITCODE
$output = $output | Out-String

if ($exitCode -ne 0) {
    throw "Application exited with code $exitCode"
}

    $output | Write-Host

    if ($output -notmatch "Strength: $Expected") {
        throw "Integration test failed. Expected: Strength: $Expected"
    }
}

Test-PasswordStrength -Password "12345678" -Expected "WEAK"
Test-PasswordStrength -Password "Password123" -Expected "MEDIUM"
Test-PasswordStrength -Password "VeryStrong#Password2026" -Expected "STRONG"

Write-Host "All integration tests passed."