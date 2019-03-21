!define SOURCE_DIR   "F:\iTrivio\source\"
!define JDK8_EXE     "jdk-8u201-windows-x64.exe"
!define MYSQL_MSI    "mariadb-5.5.63-winx64.msi"
!define LOFFICE_MSI  "LibreOffice_6.1.4_Win_x64.msi"
!define PSPAD_EXE    "pspad501inst_cz.exe"
!define FREECOMM_MSI "FreeCommanderXE-32-public_790.msi"
!define JETTY_ZIP    "jetty-distribution-9.4.14.zip"
!define JINST_BAT    "jettyBaseInstall.bat"
!define INSTS_BAT    "install_service.bat"
!define UNINSTS_BAT  "uninstall_service.bat"
!define COMMONS_DIR  "commons-daemon"
!define ITRIVIO_WAR  "ROOT_itrivio.war"

;--------------------------------
;Include Modern UI

  !include "MUI2.nsh"

;--------------------------------
;General

  ;Name and file
  Name "Educasoft iTrivio installer"
  OutFile "iTrivio.exe"

  ;Request application privileges for Windows
  RequestExecutionLevel admin

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

    StrCpy $INSTDIR "$PROFILE"

FunctionEnd

;--------------------------------
;Installer Sections

Section "${JDK8_EXE}" JDK8

    SetOutPath "$TEMP"
    File "${SOURCE_DIR}${JDK8_EXE}"
    ExecWait "$TEMP\${JDK8_EXE}"

    Delete "$TEMP\${JDK8_EXE}"

SectionEnd

Section "${MYSQL_MSI}" MySQL

    SetOutPath "$TEMP"
    File "${SOURCE_DIR}${MYSQL_MSI}"
    ExecWait "msiexec /i $TEMP\${MYSQL_MSI}"

    Delete "$TEMP\${MYSQL_MSI}"

SectionEnd

Section "${LOFFICE_MSI}" LibreOffice

    SetOutPath "$TEMP"
    File "${SOURCE_DIR}${LOFFICE_MSI}"
    ExecWait "msiexec /i $TEMP\${LOFFICE_MSI}"

    Delete "$TEMP\${LOFFICE_MSI}"

SectionEnd

Section "${PSPAD_EXE}" PSPad

    SetOutPath "$TEMP"
    File "${SOURCE_DIR}${PSPAD_EXE}"
    ExecWait "$TEMP\${PSPAD_EXE}"

    Delete "$TEMP\${PSPAD_EXE}"

SectionEnd

Section "${FREECOMM_MSI}" FreeCommander

    SetOutPath "$TEMP"
    File "${SOURCE_DIR}${FREECOMM_MSI}"
    ExecWait "msiexec /i $TEMP\${FREECOMM_MSI}"

    Delete "$TEMP\${FREECOMM_MSI}"

SectionEnd

Section "${JETTY_ZIP}" Jetty

    SetOutPath "$TEMP"
    File "${SOURCE_DIR}${JETTY_ZIP}"

    SetOutPath "$INSTDIR"
    File "${SOURCE_DIR}${JINST_BAT}"
    File "${SOURCE_DIR}${INSTS_BAT}"
    File "${SOURCE_DIR}${UNINSTS_BAT}"

    SetOutPath "$INSTDIR\${COMMONS_DIR}"
    File "${SOURCE_DIR}${COMMONS_DIR}\*"

    ZipDLL::extractall "$TEMP\${JETTY_ZIP}" "$INSTDIR"

    Delete "$TEMP\${JETTY_ZIP}"

SectionEnd

Section "${ITRIVIO_WAR}" iTrivio.war

    SetOutPath "$INSTDIR"
    File "${SOURCE_DIR}${ITRIVIO_WAR}"

SectionEnd
