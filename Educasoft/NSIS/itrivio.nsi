!define SOURCE_DIR   ".\iTrivio\"
!define ITRIVIO_DIR  "\itrivio\"
!define IROOT_DIR    "\webapps\ROOT"
!define ITRIVIO_DATA "\itrivio_data"
!define JDK8_EXE     "jdk-8u201-windows-x64.exe"
!define MYSQL_MSI    "mariadb-5.5.63-winx64.msi"
!define LOFFICE_MSI  "LibreOffice_6.1.4_Win_x64.msi"
!define PSPAD_EXE    "pspad501inst_cz.exe"
!define FREECOMM_MSI "FreeCommanderXE-32-public_790.msi"
!define JETTY_DIR    "jetty-distribution-9.4.14"
!define JBASEIN_BAT  "jettyBaseInstall.bat"  ; replacements: JETTYDIR
!define JSTART_BAT   "start.bat"             ; replacements: JETTYDIR
!define INSTS_BAT    "install_service.bat"   ; replacements: INSTDIR, JETTYDIR, JDKDIR
!define UNINSTS_BAT  "uninstall_service.bat" ; replacements: INSTDIR, JETTYDIR
!define COMMONS_DIR  "commons-daemon"
!define ITRIVIO_WAR  "ROOT_itrivio.war"
!define ISETPROP_DIR "\WEB-INF\classes\"
!define ISPROP_FILE  "settings.properties"  ; replacements: INSTDIR

;--------------------------------
;For replacing in files

  !include StrRep.nsh
  !include ReplaceInFile.nsh

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

    StrCpy $INSTDIR $PROFILE

FunctionEnd

;--------------------------------
;Installer Sections

Section "${JDK8_EXE}" JDK8

    SetOutPath "$TEMP"
    ;File "${SOURCE_DIR}${JDK8_EXE}"
    ExecWait "$TEMP\${JDK8_EXE}"

    Delete "$TEMP\${JDK8_EXE}"

SectionEnd

Section "${MYSQL_MSI}" MySQL

    SetOutPath "$TEMP"
    ;File "${SOURCE_DIR}${MYSQL_MSI}"
    ExecWait "msiexec /i $TEMP\${MYSQL_MSI}"

    Delete "$TEMP\${MYSQL_MSI}"

SectionEnd

Section "${LOFFICE_MSI}" LibreOffice

    SetOutPath "$TEMP"
    ;File "${SOURCE_DIR}${LOFFICE_MSI}"
    ExecWait "msiexec /i $TEMP\${LOFFICE_MSI}"

    Delete "$TEMP\${LOFFICE_MSI}"

SectionEnd

Section "${PSPAD_EXE}" PSPad

    SetOutPath "$TEMP"
    ;File "${SOURCE_DIR}${PSPAD_EXE}"
    ExecWait "$TEMP\${PSPAD_EXE}"

    Delete "$TEMP\${PSPAD_EXE}"

SectionEnd

Section "${FREECOMM_MSI}" FreeCommander

    SetOutPath "$TEMP"
    ;File "${SOURCE_DIR}${FREECOMM_MSI}"
    ExecWait "msiexec /i $TEMP\${FREECOMM_MSI}"

    Delete "$TEMP\${FREECOMM_MSI}"

SectionEnd

Section "${JETTY_DIR}" Jetty

    SetOutPath "$TEMP"
    File "${SOURCE_DIR}${JETTY_DIR}.zip"

    SetOutPath "$INSTDIR\${COMMONS_DIR}"
    File "${SOURCE_DIR}${COMMONS_DIR}\*"

    ZipDLL::extractall "$TEMP\${JETTY_DIR}.zip" $INSTDIR

    Delete "$TEMP\${JETTY_DIR}.zip"

    SetOutPath "$INSTDIR${ITRIVIO_DIR}"
    File "${SOURCE_DIR}${JBASEIN_BAT}"

    !insertmacro ReplaceInFile "$INSTDIR${ITRIVIO_DIR}${JBASEIN_BAT}" "{JETTYDIR}" ${JETTY_DIR}

    ExpandEnvStrings $0 %COMSPEC%
    ExecWait '"$0" /C "$INSTDIR${ITRIVIO_DIR}${JBASEIN_BAT}"'

    CreateDirectory $INSTDIR${ITRIVIO_DATA}

    SetOutPath "$INSTDIR${ITRIVIO_DIR}"
    File "${SOURCE_DIR}${JSTART_BAT}"

    !insertmacro ReplaceInFile "$INSTDIR${ITRIVIO_DIR}${JSTART_BAT}" "{JETTYDIR}" ${JETTY_DIR}

    # getting path to JDK
    nsExec::ExecToStack 'cmd /c "where java"'
    Pop $0
    Pop $1
    StrCpy $0 $1 -15 ; cutting "\bin\java.exe"

    SetOutPath "$INSTDIR"
    File "${SOURCE_DIR}${INSTS_BAT}"
    File "${SOURCE_DIR}${UNINSTS_BAT}"

    # replacements in install_service.bat
    !insertmacro ReplaceInFile "$INSTDIR\${INSTS_BAT}" "{INSTDIR}" $INSTDIR
    !insertmacro ReplaceInFile "$INSTDIR\${INSTS_BAT}" "{JETTYDIR}" ${JETTY_DIR}
    !insertmacro ReplaceInFile "$INSTDIR\${INSTS_BAT}" "{JDKHOME}" $0

    # replacements in uninstall_service.bat
    !insertmacro ReplaceInFile "$INSTDIR\${UNINSTS_BAT}" "{INSTDIR}" $INSTDIR
    !insertmacro ReplaceInFile "$INSTDIR\${UNINSTS_BAT}" "{JETTYDIR}" ${JETTY_DIR}

SectionEnd

Section "${ITRIVIO_WAR}" iTrivio.war

    SetOutPath "$TEMP"
    ;File "${SOURCE_DIR}${ITRIVIO_WAR}"

    ZipDLL::extractall "$TEMP\${ITRIVIO_WAR}" "$INSTDIR\${ITRIVIO_DIR}\${IROOT_DIR}"

    Delete "$TEMP\${ITRIVIO_WAR}"

    !insertmacro StrRep $1 $INSTDIR "\" "/"
    !insertmacro ReplaceInFile "$INSTDIR${ITRIVIO_DIR}${IROOT_DIR}${ISETPROP_DIR}${ISPROP_FILE}" "{INSTDIR}" $1

SectionEnd
