!define SOURCE_DIR "C:\Users\lamp\Downloads\"
!define MYSQL_MSI  "mariadb-10.3.12-winx64.msi"
!define JETTY_ZIP  "jetty-distribution-9.4.15.v20190215.zip"

;--------------------------------
;Include Modern UI

  !include "MUI2.nsh"

;--------------------------------
;General

  ;Name and file
  Name "Educasoft iTrivio installer"
  OutFile "Educasoft.exe"

  ;Request application privileges for Windows Vista
  RequestExecutionLevel user

  ;Allow file overwriting
  SetOverWrite try

;--------------------------------
;Pages

  !insertmacro MUI_PAGE_WELCOME
  !insertmacro MUI_PAGE_COMPONENTS
  !insertmacro MUI_PAGE_DIRECTORY
  !insertmacro MUI_PAGE_INSTFILES
  !insertmacro MUI_PAGE_FINISH

;--------------------------------
;Languages

  !insertmacro MUI_LANGUAGE "English"

;--------------------------------
;Functions

Function .onInit

    StrCpy $INSTDIR $PROFILE

FunctionEnd

;--------------------------------
;Installer Sections

Section "${MYSQL_MSI}" MySQL

    SetOutpath "$TEMP"
    ;File "${SOURCE_DIR}${MYSQL_MSI}"
    ;ExecWait "msiexec /i $TEMP\${MYSQL_MSI}"

    Delete "$TEMP\${MYSQL_MSI}"

SectionEnd

Section "${JETTY_ZIP}" Jetty

    SetOutpath "$TEMP"
    File "${SOURCE_DIR}${JETTY_ZIP}"
    ZipDLL::extractall "$TEMP\${JETTY_ZIP}" "$INSTDIR"

    Delete "$TEMP\${JETTY_ZIP}"

SectionEnd
