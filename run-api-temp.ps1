$ErrorActionPreference = "Stop"

# Temporary run script for this session only:
# - Ensures Java 21 is available (installs Temurin 21 if missing)
# - Uses portable Maven from %TEMP%
# - Starts Spring Boot app

$projectRoot = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $projectRoot

function Get-JdkHome {
    $jdkBase = "C:\Program Files\Eclipse Adoptium"
    if (-not (Test-Path $jdkBase)) {
        return $null
    }

    $jdk = Get-ChildItem $jdkBase -Directory |
        Where-Object { $_.Name -like "jdk-21*" } |
        Sort-Object LastWriteTime -Descending |
        Select-Object -First 1

    if ($null -eq $jdk) {
        return $null
    }

    return $jdk.FullName
}

$jdkHome = Get-JdkHome
if ($null -eq $jdkHome) {
    Write-Host "Java 21 not found. Installing Temurin 21 temporarily for use..."
    winget install --id EclipseAdoptium.Temurin.21.JDK -e --accept-package-agreements --accept-source-agreements
    $jdkHome = Get-JdkHome
}

if ($null -eq $jdkHome) {
    throw "Unable to find Java 21 installation."
}

$env:JAVA_HOME = $jdkHome
$env:Path = "$env:JAVA_HOME\bin;$env:Path"

Write-Host "JAVA_HOME set to: $env:JAVA_HOME"
java -version

$mavenVersion = "3.9.6"
$mavenZip = Join-Path $env:TEMP "apache-maven-$mavenVersion-bin.zip"
$mavenHome = Join-Path $env:TEMP "apache-maven-$mavenVersion"

if (-not (Test-Path $mavenHome)) {
    Write-Host "Downloading portable Maven $mavenVersion ..."
    Invoke-WebRequest -Uri "https://archive.apache.org/dist/maven/maven-3/$mavenVersion/binaries/apache-maven-$mavenVersion-bin.zip" -OutFile $mavenZip
    Expand-Archive -Path $mavenZip -DestinationPath $env:TEMP -Force
}

$env:MAVEN_HOME = $mavenHome
$env:Path = "$env:MAVEN_HOME\bin;$env:Path"

Write-Host "MAVEN_HOME set to: $env:MAVEN_HOME"
mvn -version

Write-Host "Starting Spring Boot API on port 8080 ..."
mvn spring-boot:run
